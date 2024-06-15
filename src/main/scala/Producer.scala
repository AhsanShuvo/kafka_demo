package io.kafka.producer

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties

object Producer extends App {
  val config = ConfigFactory.load()
  val properties = buildProperties()

  val producer = new KafkaProducer[String, String](properties)
  val topic = config.getString("kafka.topic")
  val record = new ProducerRecord[String, String](topic, "Non-IT-Dept", "Value from IT dept. Should not be in first 2 partition.")
  producer.send(record)
  producer.close()

  def buildProperties(): Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getString("kafka.bootstrap.servers"))
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getString("kafka.key.serializer"))
    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "PowerUserPartitioner.PowerUserPartitioner")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getString("kafka.value.serializer"))
    props.put("security.protocol", config.getString("kafka.security.protocol"))
    props.put("sasl.mechanism", config.getString("kafka.sasl.mechanism"))
    props.put("sasl.jaas.config", config.getString("kafka.sasl.jaas.config"))
    props.put(ProducerConfig.ACKS_CONFIG, config.getString("kafka.acks"))
    props
  }
}