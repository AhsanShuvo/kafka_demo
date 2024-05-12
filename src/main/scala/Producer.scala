package io.kafka.producer

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.io.FileReader
import java.util.Properties

object Producer extends App {
  val conf = ConfigFactory.load()

  val props = buildProperties()

  val producer = new KafkaProducer[String, String](props)
  val topic = conf.getString("kafka.topic")
  val record = new ProducerRecord[String, String](topic, "key", "Value from client")
  producer.send(record)
  producer.close()

  def buildProperties(): Properties = {
    val props = new Properties()
    props.load(new FileReader("/Users/ahsanshuvo/project/kafka-producer/client.properties"))
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props
  }
}