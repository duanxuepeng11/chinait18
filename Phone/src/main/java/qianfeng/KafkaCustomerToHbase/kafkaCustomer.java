package qianfeng.KafkaCustomerToHbase;



import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import qianfeng.weibo.HbaseDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 读取flume的数据，将数据传入到hbase
 */
public class kafkaCustomer {
    private static final String topic ="test01";
    private static final Integer threads = 2;
    static Configuration conf = HbaseDriver.getHbaseConf();

    public static void main(String[] args) {
        Properties properties = new Properties();

        //指定zk
        properties.put("zookeeper.connect","192.168.244.160:2181,192.168.244.161:2181,192.168.244.162:2181");
        //配置消费者组
        properties.put("group.id","NewHbase");
        //从头消费数据
        //properties.put("auto.offset.rest","smallest");
        //创建消费者配置对象
        ConsumerConfig config = new ConsumerConfig(properties);
        //创建消费者
        ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);
        //创建map 主要用来存储多个Topic信息
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put(topic,threads);
        //然后创建获取信息流
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams =
                connector.createMessageStreams(hashMap);

        List<KafkaStream<byte[], byte[]>> kafkaStreams = messageStreams.get(topic);

        //循环接收map内的topic数据
        for(final KafkaStream<byte[], byte[]> kafka : kafkaStreams){
            new Thread(new Runnable() {

                public void run() {
                    for (MessageAndMetadata<byte[], byte[]> m:kafka){
                        String msg = new String(m.message());
                        String[] str = msg.split("\t");

                        String name = str[0];
                        String phone = str[1];
                        String nameTo = str[2];
                        String phoneTo = str[3];
                        String times = str[4];
                        String sum = str[5];
                        System.out.println(name+phone+nameTo+phoneTo+times+sum);
                        //存放hbase
                        try {
                            toHbase(name,phone,nameTo,phoneTo,times,sum);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public static void toHbase(String name,String phone,String nameTo,String phoneTo,String time,String sum) throws IOException {
        Connection conn = ConnectionFactory.createConnection(conf);
        //获得微博内容表对象
        Table datatable = conn.getTable(TableName.valueOf("kafkaDatas"));
        //拼接rowkey 对象
        long nowTime = System.currentTimeMillis();
        String rowKey = name.hashCode()+"-"+nowTime;

        Put put = new Put(Bytes.toBytes(rowKey));
        //添加内容
        //列信息
        put.addColumn(Bytes.toBytes("infos"),Bytes.toBytes("name"),Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("infos"),Bytes.toBytes("phone"),Bytes.toBytes(phone));
        put.addColumn(Bytes.toBytes("infos"),Bytes.toBytes("nameTo"),Bytes.toBytes(nameTo));
        put.addColumn(Bytes.toBytes("infos"),Bytes.toBytes("phoneTo"),Bytes.toBytes(phoneTo));
        put.addColumn(Bytes.toBytes("infos"),Bytes.toBytes("time"),Bytes.toBytes(time));
        put.addColumn(Bytes.toBytes("infos"),Bytes.toBytes("sum"),Bytes.toBytes(sum));
        //将put对象放入到table表对象中
        datatable.put(put);
        //关闭链接
        datatable.close();
        conn.close();
    }
}
