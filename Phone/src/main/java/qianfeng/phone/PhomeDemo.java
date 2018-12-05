package qianfeng.phone;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhomeDemo {


    public static FileReader reader = null;
    public static BufferedReader bufr = null;
    //定义写文件的流
    public static FileWriter writer = null;
    public static BufferedWriter bufw = null;
    public static Map<String,String> maps = new HashMap<String, String>();
    public static List<String> list = new ArrayList<String>();
    public static String line = null;
    //定义2个时间
    private static String startTime = "2018-01-01";
    private static String endTime = "2018-12-31";
    //时间格式
    public static String dataStr ="yyyy-MM-dd HH:mm:ss";

    //定义读文件方法
    public static void readFile(String files) throws Exception {
        reader = new FileReader(new File(files));
       bufr = new BufferedReader(reader);

        while((line = bufr.readLine())!=null) {
            String[] s = line.split("\t");
            //将结果放入到maps集合中
          //  System.out.println(s[0],s[1]);
            maps.put(s[0],s[1]);
           list.add(s[0]);
        }
    }
    //开始打电话
    public static String call_Phone() throws Exception {
        Random random = new Random();
        //生成第一个随机数，用于取集合中打电话的人姓名
        int i = random.nextInt(27);
        String name_1 = list.get(i);
        int j = random.nextInt(27);
        String name_2 = list.get(j);
        //System.out.println(name_1+" "+name_2);
        //如果2个姓名姓名一样的话，自己不能给自己打电话，舍弃这次的通话
        if(name_1.equals(name_2)){
            return null;
        }else{
            //到map集合取相应的手机号
            String phone_1 = maps.get(name_1);
            String phone_2 = maps.get(name_2);
            //模拟通话时间 拼接一个4位数
            int dataTime = random.nextInt(10);
            int dataTime2 = random.nextInt(10);
            int dataTime3 = random.nextInt(10);
            int dataTime4 = random.nextInt(10);
           /* System.out.println("1"+dataTime);
            System.out.println("1"+dataTime2);
            System.out.println("1"+dataTime3);
            System.out.println("1"+dataTime4);*/
            String zong_Time = dataTime+""+dataTime2+""+dataTime3+""+dataTime4;
            //System.out.println("aa"+zong_Time);
         /*   Date date = new Date();*/
            SimpleDateFormat sdf = new SimpleDateFormat(dataStr);
            String buildTime = randomBuildTime(startTime, endTime);
           // String start_time = sdf.format(buildTime);
            //开始拼接字符串
            String res = name_1+"\t"+phone_1+"\t"+name_2+"\t"+phone_2+"\t"+buildTime+"\t"+zong_Time+"\n";
            return res;
        }
    }
    /**
     * 根据传入的时间区间，在此范围内随机通话建立的时间
     * startTimeTS + (endTimeTs - startTimeTs) * Math.random();
     *
     * @param startTime
     * @param endTime
     */
    public static String randomBuildTime(String startTime, String endTime) throws Exception {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf1.parse(startTime);
            Date endDate = sdf1.parse(endTime);

            if (endDate.getTime() <= startDate.getTime()) return null;

            long randomTS = startDate.getTime() + (long) ((endDate.getTime() - startDate.getTime()) * Math.random());
            Date resultDate = new Date(randomTS);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String resultTimeString = sdf2.format(resultDate);
            return resultTimeString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //将打电话数据写入到文件中
    private static void writeFile(String res,FileWriter writer) throws Exception {

        writer.write(res);
        writer.flush();
//        writer.close();
    }
    public static void main(String[] args) throws Exception {

        //读文件 并将结果放入到map集合中
         readFile(args[0]);
        writer = new FileWriter(new File(args[1]));
        //开始打电话
        while (true){
            Thread.sleep(3000);
            String res = call_Phone();
            //res 接收到的数据，开始写入文件中
            System.out.println(res);
            //判断返回的结果 如果是自己给自己打，那么不记录
            if(res==null || "null".equals(res)){

            }else
            {
                //写入文件
                writeFile(res,writer);
            }

        }
    }


}
