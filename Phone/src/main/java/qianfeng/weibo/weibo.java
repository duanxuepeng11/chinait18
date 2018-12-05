package qianfeng.weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 发布微博
 * 互粉
 * 取关
 * 查看微博
 */
public class weibo {
    //获取hbase链接
    private static Configuration conf = HbaseDriver.getHbaseConf();
    //创建3个列
    private static byte[] ns_weibo = Bytes.toBytes("ns_weibo");//命名空间 下面有3个表
    private static byte[] TABLE_RELEASE = Bytes.toBytes("TABLE_RELEASE");//关系表
    private static byte[] TABLE_CONTEXT = Bytes.toBytes("TABLE_CONTEXT"); //微博发布内容表
    private static byte[] TABLE__SHOUJIAN = Bytes.toBytes("TABLE_SHOUJIAN");//收件箱表

    //开始创建命名空间
    public static void createNameSpace() throws IOException {
        HBaseAdmin admin = null;
        Connection conn = ConnectionFactory.createConnection(conf);

        admin = (HBaseAdmin) conn.getAdmin();
        //命名空间类似于关系型数据库中的schema。可以想象成文件夹
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

    //初始化3张表 ，第一张TABLE_RELEASE
    public static void createRelease() throws IOException {
        Connection conn = ConnectionFactory.createConnection(conf);
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();

        //创建表描述器
        HTableDescriptor tabledescriptor = new HTableDescriptor(TableName.valueOf(TABLE_RELEASE));
        //创建列簇描述器
        HColumnDescriptor attendsDescriptor = new HColumnDescriptor("attends");
        //设置块缓存
        attendsDescriptor.setBlockCacheEnabled(true);
        //设置块缓存大小 2M
        attendsDescriptor.setBlocksize(2 * 1024 *1024);
        //设置版本
        attendsDescriptor.setMinVersions(1);
        attendsDescriptor.setMaxVersions(1);
        //创建第二个列簇描述器
        HColumnDescriptor fansDescriptor = new HColumnDescriptor("fans");
        fansDescriptor.setBlockCacheEnabled(true); //设置块缓存
        fansDescriptor.setBlocksize(2 *1024 *1024); //设置块缓存大小
        fansDescriptor.setMinVersions(1);
        fansDescriptor.setMaxVersions(1); //设置版本
        //将列簇加入到表描述器中
        tabledescriptor.addFamily(attendsDescriptor);
        tabledescriptor.addFamily(fansDescriptor);
        //使用admin创建表
        admin.createTable(tabledescriptor);
        admin.close();
        conn.close();
        System.out.println("创建TABLE_RELEASE表完成 。。。。");
    }
    //创建第二张表 CONTEXT表
    public static void createContext() throws IOException {
        Connection conn = ConnectionFactory.createConnection(conf);
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(TABLE_CONTEXT));
        //创建列簇秒速器
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(Bytes.toBytes("info"));
        //设置块缓存
        hColumnDescriptor.setBlockCacheEnabled(true);
        //设置块缓存大小 2M
        hColumnDescriptor.setBlocksize(2 * 1024 *1024);
        //设置版本
        hColumnDescriptor.setMinVersions(1);
        hColumnDescriptor.setMaxVersions(1);
        //将列簇加入到表描述器中
        hTableDescriptor.addFamily(hColumnDescriptor);

        //使用admin创建表
        admin.createTable(hTableDescriptor);
        admin.close();
        conn.close();
        System.out.println("创建CONTEXT完成.....");
    }
    //创建第三张表 TABLE_SHOUJIAN
    public static void createShoujian() throws Exception{
        Connection conn = ConnectionFactory.createConnection(conf);
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(TABLE__SHOUJIAN));
        //创建列簇秒速器
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
        //添加约束
        //设置块缓存
        hColumnDescriptor.setBlockCacheEnabled(true);
        //设置块缓存大小 2M
        hColumnDescriptor.setBlocksize(2 * 1024 *1024);
        //设置版本
        hColumnDescriptor.setMinVersions(100);
        hColumnDescriptor.setMaxVersions(100);
        //将列簇加入到表描述器中
        hTableDescriptor.addFamily(hColumnDescriptor);
        //使用admin 创建表
        admin.createTable(hTableDescriptor);
        //关闭链接
        admin.close();
        conn.close();
        System.out.println("创建收件箱表成功....");

    }
    //到此3个表创建完成了，，
    //开始发微博
    public static void publicContext(String uid,String context) throws IOException {
        //获得操作对象
        Connection conn = ConnectionFactory.createConnection(conf);
        //HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        //第一步，---------------------把自己发布的内容存储到 内容表中
        //获得微博内容表对象
        Table Contexttable = conn.getTable(TableName.valueOf(TABLE_CONTEXT));
        //拼接rowkey 对象
        long nowTime = System.currentTimeMillis();
        String rowKey = uid+"_"+nowTime;

        Put put = new Put(Bytes.toBytes(rowKey));
        //添加内容
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("context"),Bytes.toBytes(context));
        //将put对象放入到table表对象中
        Contexttable.put(put);


        //第二步，---------------------通知粉丝，将此次的微博内容推荐给他们
        //获取粉丝
        Table releaseTable = conn.getTable(TableName.valueOf(TABLE_RELEASE));
        Get get = new Get(Bytes.toBytes(uid));
        //指定到粉丝列
        get.addFamily(Bytes.toBytes("fans"));

        Result result = releaseTable.get(get);//得到fans的粉丝id
        //加入粉丝的uid_f 集合，下面遍历这个集合开始往收件箱里面添加内容
        List<byte[]> array = new ArrayList<byte[]>();

        Cell[] cells = result.rawCells();
        for(Cell cell : cells){
            //获取列簇下的key
            array.add(CellUtil.cloneQualifier(cell));
            //获取列簇下的value
            // array.add(CellUtil.cloneValue(cell));
        }
        //第三步 ------添加到收集箱---------------------
        //开始添加收件箱
        Table sjTable = conn.getTable(TableName.valueOf(TABLE__SHOUJIAN));

        //封装用户操作收集箱的put集合
        List<Put> listputs = new ArrayList<Put>();
        for (byte[] bys:array
             ) {
            Put puts = new Put(bys);
            puts.addColumn(Bytes.toBytes("info"),Bytes.toBytes(uid),Bytes.toBytes(rowKey));
            listputs.add(puts);
        }

        sjTable.put(listputs);
        //关闭链接
        sjTable.close();
        releaseTable.close();
        Contexttable.close();
        conn.close();
        System.out.println("publicContext  ......Ok");
    }
    //添加关注
    //关注多个人 用..attend表示
    //1000   1001 1002
    public static void addGuanzhu(String uid,String ...attend) throws IOException {
        //如果被关注的人为空,则退出
        if( attend==null ||attend.length<=0  || uid ==null) return;
        Connection conn = ConnectionFactory.createConnection(conf);
        //获取release关系表
        Table releaseTable = conn.getTable(TableName.valueOf(TABLE_RELEASE));
        List<Put> puts = new ArrayList<Put>();
        //在微博用户关系表中，添加新关注的好友
        Put attendPut = new Put(Bytes.toBytes(uid));
        for(String a: attend){
            //为当前用户添加关注人
            attendPut.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(a), Bytes.toBytes(a));
            //b
            //被关注的人，添加粉丝（uid）
            Put fansPut = new Put(Bytes.toBytes(a));
            fansPut.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes.toBytes(uid));
            puts.add(fansPut);
        }
        puts.add(attendPut);
        releaseTable.put(puts);

        //向关注的人推送被关注的人的消息,向收件箱表中
        Table contentTable = conn.getTable(TableName.valueOf(TABLE_CONTEXT));
        Scan scan = new Scan();
        //用于存放扫面出来的我所关注的人的rowkey
        List<byte[]> rowkeys = new ArrayList<byte[]>();
        for(String s:attend){
            //1002_332323232
            //扫描微博rowkey 使用rowfilter过滤器
            RowFilter filter =new RowFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator(s+"_"));
            scan.setFilter(filter);
            ResultScanner scanner = contentTable.getScanner(scan);
            Iterator<Result> iterator = scanner.iterator();
            while(iterator.hasNext()){
                Result next = iterator.next();
                rowkeys.add(next.getRow());
            }
        }
        //将取出的微博rowkey放置于当前操作的这个用户的收件箱表中
        //如果所关注的人，没有一条微博，则直接返回
        if(rowkeys.size() <= 0) return;
        //将所有关注的rowkey放入到收集箱中
        Table sjTable = conn.getTable(TableName.valueOf(TABLE__SHOUJIAN));
        //创建put对象
        Put putUid = new Put(Bytes.toBytes(uid));
        for(byte[] bs : rowkeys){
            String rowkeyString = Bytes.toString(bs);
            //获取uid
            String uids = rowkeyString.split("_")[0];
            //获取时间戳
            String TimeTamstric = rowkeyString.split("_")[1];
            putUid.addColumn(Bytes.toBytes("info"), Bytes.toBytes(uids), Long.valueOf(TimeTamstric), bs);

            //putUid.addColumn(Bytes.toBytes(uid),Bytes.toBytes("info"),Bytes.toBytes(uids));
        }
        sjTable.put(putUid);
        //关闭链接
        sjTable.close();
        contentTable.close();
        releaseTable.close();
        conn.close();
        System.out.println("addGuanzhu  ......Ok");
    }
    //取消关注
    public static void delGuanzhu(String uid,String ...delattend) throws IOException {
        if(delattend ==null || delattend.length<=0 || uid ==null)return;
        Connection conn = ConnectionFactory.createConnection(conf);
        //获取关系表
        Table releaseTable = conn.getTable(TableName.valueOf(TABLE_RELEASE));
        //第一步,先删除attend关注的对象---------------------------
        Delete attendDelete = new Delete(Bytes.toBytes(uid));
        List<Delete> dellist = new ArrayList<Delete>();
        for(String attend: delattend){
            //b 在对面用户关系表中移除粉丝
            attendDelete.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(attend));
            Delete delete = new Delete(Bytes.toBytes(attend));
            delete.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid));
            dellist.add(delete);
        }
        dellist.add(attendDelete);
        releaseTable.delete(dellist);

        //删除被关注用户在当前用户收集箱中的数据
        //
        Table inboxTable = conn.getTable(TableName.valueOf(TABLE__SHOUJIAN));

        Delete delete = new Delete(Bytes.toBytes(uid));
        for(String attend: delattend){
            delete.addColumns(Bytes.toBytes("info"), Bytes.toBytes(attend));
        }
        inboxTable.delete(delete);


        //释放资源
        inboxTable.close();
        releaseTable.close();
        conn.close();
        System.out.println("delGuanzhu  ......Ok");
    }

    //查看微博内容, 把用户关注的所有人的微博rowkey都展示出来
    public static List<Message> getAllContext(String uid) throws IOException {
        if(uid == null) return null;
        Connection conn = ConnectionFactory.createConnection(conf);
        //第一步 获取uid在收件箱中的数据全部拿出来
        Table sjTable = conn.getTable(TableName.valueOf(TABLE__SHOUJIAN));
        Get get = new Get(Bytes.toBytes(uid));
        get.addFamily(Bytes.toBytes("info"));
        // 准备一个存放  所有微博的rowkey集合
        List<byte[]> rowkeys = new ArrayList<byte[]>();
        //每个Cell中存储了100个版本，我们只取出最新的5个版本
        get.setMaxVersions(5);
        Result result = sjTable.get(get);

        Cell[] cells = result.rawCells();
        for(Cell cell :cells) {
            //  通过此方法可以获取列名    Bytes.toString(CellUtil.cloneQualifier(cell)
            // 通过以下方法可以获取列值
            rowkeys.add(CellUtil.cloneValue(cell));
        }
        //根据rowkey 去内容表中找对应的内容
        Table contextTable = conn.getTable(TableName.valueOf(TABLE_CONTEXT));
        //声明一个存放内容的数组
        List<Get> contextList = new ArrayList<Get>();

        for(byte[] bs :rowkeys){
            Get gets = new Get(bs);
            contextList.add(gets);
        }
        //获取内容
        Result[] results = contextTable.get(contextList);

        List<Message> messages = new ArrayList<Message>();
        for(Result res:results){
            Cell[] cells1 = res.rawCells();
            for(Cell c : cells1){
                //取得contentTable中的rowkey
                String rk = Bytes.toString(res.getRow());
                //发布微博人的UID
                String publishUID = rk.split("_")[0];
                long publishTS = Long.valueOf(rk.split("_")[1]);

                Message msg = new Message();
                msg.setUid(publishUID);
                msg.setTimestamp(publishTS);
                msg.setContent(Bytes.toString(CellUtil.cloneValue(c)));

                messages.add(msg);
            }

        }
        contextTable.close();
        sjTable.close();
        conn.close();
        if(messages.size()==0){
            return null;
        }else{
            return messages;
        }
    }
    public static void main(String[] args) throws Exception {
       weibo.createNameSpace();
        //创建列族
//        createRelease();
//        createShoujian();
//        createContext();

        //开始测试 发布微博
//        publicContext("1001","我也是一直小小鸟");
//        publicContext("1001","我也是一直小小鸟1");
//        publicContext("1001","我也是一直小小鸟2");
//        publicContext("1002","我也是一直小小鸟");
//        publicContext("1002","我也是一直小小鸟1");
//        publicContext("1002","我也是一直小小鸟2");
//        publicContext("1003","我也是一直小小鸟");
//        publicContext("1003","我也是一直小小鸟1");
//        publicContext("1003","我也是一直小小鸟2");
    //addGuanzhu("1001","1003");
        /* delGuanzhu("1001","1003");



        System.out.println("======================");
        List<Message> allContext = getAllContext("1001");
        if(allContext ==null){
            System.out.println("没有查询出来结果");
        }else{
            for(Message ms : allContext){
                System.out.println(ms);
            }
        }*/
    }

}
