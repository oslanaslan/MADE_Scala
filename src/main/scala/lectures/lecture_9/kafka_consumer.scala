package lectures.lecture_9

import cats.effect.{ExitCode, IO, IOApp, Resource}
import fs2.{Pure, Stream}
import fs2.kafka.{AutoOffsetReset, ConsumerSettings, KafkaConsumer, KafkaProducer, ProducerRecord, ProducerRecords, ProducerSettings, commitBatchWithin}
import io.github.embeddedkafka.{EmbeddedK, EmbeddedKafka, EmbeddedKafkaConfig}
import org.apache.kafka.common.serialization.{Serializer, StringDeserializer, StringSerializer}

import scala.concurrent.duration.DurationInt

object kafka_consumer extends IOApp with EmbeddedKafka {

  override def run(args: List[String]): IO[ExitCode] = {
    val topic = "topic"

    val partition = 0

    val clientId = "client"

    val userDefinedConfig = EmbeddedKafkaConfig(kafkaPort = 0, zooKeeperPort = 0)

    def broker(port: Long) = s"localhost:$port"

    val consumerSettings = ConsumerSettings[IO, String, String]
      .withAutoOffsetReset(AutoOffsetReset.Earliest)
      .withEnableAutoCommit(true)
      .withGroupId("groupId")
      .withClientId(clientId)

    val embeddedKafka: Resource[IO, EmbeddedK] = Resource.make(IO.delay(EmbeddedKafka.start()))(ek => IO.delay(ek.stop(true)))

    Stream.resource(embeddedKafka).flatMap { ek => //starting kafka
      implicit val serializer = new StringSerializer

      createCustomTopic(topic, partitions = 1)

      val producerSettings = ProducerSettings[IO, String, String]

      //10 partition -> 30 -> 5

      Stream.repeatEval(IO.delay(Math.random()))
        .map(rand => ProducerRecords.one(ProducerRecord(topic, "key" + rand, "value" + rand)))
        .through(KafkaProducer.pipe(producerSettings.withBootstrapServers(broker(ek.config.kafkaPort)))).flatMap { _ =>

        KafkaConsumer.stream(consumerSettings.withBootstrapServers(broker(ek.config.kafkaPort)))
          .subscribeTo(topic)
          .evalTap(_.seekToBeginning)
          .flatMap { consumer =>
            ////////
            consumer //       partition
              .stream // Stream [Stream]
              .parEvalMap(10)(record => IO.sleep(1000.millis) *> IO.delay(println(record)).as(record.offset))
              .through(commitBatchWithin(500, 15.seconds))
          }
      }
    }
      .compile
      .drain
      .map(_ => ExitCode.Success)
  }
}
