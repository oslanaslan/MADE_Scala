package lectures.lecture_9

import cats.effect.{ExitCode, IO, IOApp, Resource}
import fs2.Stream
import fs2.kafka.{AutoOffsetReset, ConsumerSettings, KafkaConsumer, KafkaProducer, ProducerRecord, ProducerRecords, ProducerSettings}
import io.github.embeddedkafka.{EmbeddedKafka, EmbeddedKafkaConfig}
import lectures.lecture_9.kafka_consumer.publishToKafka
import org.apache.kafka.common.serialization.{Serializer, StringSerializer}

object kafka_producer extends IOApp with EmbeddedKafka {

  def broker(port: Long) = s"localhost:$port"

  override def run(args: List[String]): IO[ExitCode] = {
    val topic = "topic"
    val partition = 0
    val clientId = "client"
    val userDefinedConfig = EmbeddedKafkaConfig(kafkaPort = 0, zooKeeperPort = 0)

    val consumerSettings = ConsumerSettings[IO, String, String]
      .withAutoOffsetReset(AutoOffsetReset.Earliest)
      .withEnableAutoCommit(true)
      .withGroupId("group")
      .withClientId(clientId)

    val producerSettings = ProducerSettings[IO, String, String]

    val embeddedKafka = Resource.make(IO(EmbeddedKafka.start()))(kafka => IO(kafka.stop(true)))

    Stream.resource(embeddedKafka).flatMap { kafka =>
      implicit val actualConfig: EmbeddedKafkaConfig = kafka.config
      implicit val des: Serializer[String] = new StringSerializer

      createCustomTopic(topic)

      publishToKafka(topic, "key", "example-message1")
      publishToKafka(topic, "key", "example-message2")


      Stream.apply(ProducerRecords.one(ProducerRecord(topic, "key1", "value")))
        .through(KafkaProducer.pipe(producerSettings.withBootstrapServers(broker(actualConfig.kafkaPort))))

      /*
            KafkaConsumer.stream(consumerSettings.withBootstrapServers(broker(actualConfig.kafkaPort)))
              .subscribeTo(topic)
              .evalTap(_.seekToBeginning)
              .flatMap(_.stream)
              .map { committable =>
                println(committable)
                val key = committable.record.key
                val value = committable.record.value
                ProducerRecords.one(ProducerRecord(topic, key, value), committable.offset)
              }
              .take(1)
              .through(Ka fkaProducer.pipe(producerSettings.withBootstrapServers(broker(actualConfig.kafkaPort)))*/


    }.compile.toList.map(_ => ExitCode.Success)
  }
}
