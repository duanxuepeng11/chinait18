package qianfeng.KafkaCustomerToHbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import qianfeng.weibo.HbaseDriver;

import java.io.IOException;

/**
 * kafka读取数据存入到hbase
 * 测试创建hbase表跟命名空间是否可用
 */
public class HBaseCURD {
    static Configuration conf = HbaseDriver.getHbaseConf();
    static Connection conn;

    static {
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creadeNamespace()throws Exception{

        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        //创建命名空间描述器
        NamespaceDescriptor weibo = NamespaceDescriptor
                .create("from_kafka")
                .addConfiguration("creator","xuepeng")
                .addConfiguration("creator_time",System.currentTimeMillis()+"")
                .build();
        //使用admin指定创建命名空间
        admin.createNamespace(weibo);
        System.out.println("创建成功--------");
    }

    public static void creadeLie()throws Exception{
        Connection conn = ConnectionFactory.createConnection(conf);
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
// String res = name_1+"\t"+phone_1+"\t"+name_2+"\t"+phone_2+"\t"+buildTime+"\t"+buildTime+"\n";
        //创建表描述器
        HTableDescriptor tabledescriptor = new HTableDescriptor(TableName.valueOf("from_kafka-test1"));
        //创建列簇描述器
        HColumnDescriptor attendsDescriptor = new HColumnDescriptor("infos");
        //设置块缓存
        attendsDescriptor.setBlockCacheEnabled(true);
        //设置块缓存大小 2M
        attendsDescriptor.setBlocksize(2 * 1024 *1024);
        //设置版本
        attendsDescriptor.setMinVersions(3);
        attendsDescriptor.setMaxVersions(3);

        //将列簇加入到表描述器中
        tabledescriptor.addFamily(attendsDescriptor);
        //使用admin创建表
        admin.createTable(tabledescriptor);
        admin.close();
        conn.close();
        System.out.println("创建kafkaDatas表完成 。。。。");
    }
    public static void main(String[] args) throws Exception {
        //creadeNamespace();
        creadeLie();
    }
}
