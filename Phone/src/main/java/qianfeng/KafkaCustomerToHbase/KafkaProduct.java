package qianfeng.KafkaCustomerToHbase;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;


import java.util.Properties;

public class KafkaProduct {

    public static void main(String[] args) {
        //通过propertest类进行配置文件的配置
        Properties properties = new Properties();
        //接下来进行指定端口和IP
        properties.put("metadata.broker.list","192.168.244.160:9092," +
                "192.168.244.161:9092,192.168.244.162:9092");
        //然后进行序列化方式
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        //定义一个producer配置文件API
        ProducerConfig conf = new ProducerConfig(properties);
        //创建producer
        Producer<String, String> producer = new Producer<String, String>(conf);
        int i =0;
        while (true){
            //发送消息
            System.out.println("aaa"+i);
            producer.send(
                    new KeyedMessage<String, String>("tt","hello new Idea"+i)
            );
            i++;
        }
    }

}
