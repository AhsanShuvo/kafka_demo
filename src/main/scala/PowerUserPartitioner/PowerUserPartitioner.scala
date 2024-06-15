package PowerUserPartitioner

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.utils.Utils
import org.apache.kafka.common.{Cluster, InvalidRecordException}

import java.util

class PowerUserPartitioner extends Partitioner {

  override def configure(configs: util.Map[String, _]): Unit = {}

  override def partition(topic: String, key: Any, keyBytes: Array[Byte], value: Any, valueBytes: Array[Byte], cluster: Cluster): Int = {
    val departmentName = "IT"
    val partitions =  cluster.partitionsForTopic(topic);
    val numPartitions = partitions.size()
    val it = Math.abs(numPartitions * 0.4).asInstanceOf[Int]

    if((keyBytes == null) || (!key.isInstanceOf[String])){
      throw  new InvalidRecordException("All message must have a key")
    }

    if(key.asInstanceOf[String].startsWith(departmentName)){
      val p = Utils.toPositive(Utils.murmur2(keyBytes)) % it
      p
    } else {
      val p = Utils.toPositive(Utils.murmur2(keyBytes)) % (numPartitions - it) + it
      p
    }
  }

  override def close(): Unit = {}
}
