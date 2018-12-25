package com.qianfeng.Service.Impl;

import com.qianfeng.Dao.User_SummaryDao;
import com.qianfeng.Service.Inter.User_SummaryServiceInter;
import com.qianfeng.domain.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class User_SummaryServiceImpl implements User_SummaryServiceInter {

    @Autowired
    private User_SummaryDao user_summaryDao;

    // 根据用户名来查找学生id
    @Override
    public List<ExamineeInfo> findIdByName() {
        List<ExamineeInfo> info = user_summaryDao.findIdByName();
        return info;

    }

    @Override
    public List<User_Summary> findAll(String examinee_num) {
        List<User_Summary> list = user_summaryDao.findAll(examinee_num);
        return list;
    }

    @Override
    public Ability findAbilityByNum(String examinee_name) {
        return user_summaryDao.findAbilityByNum(examinee_name);
    }

    @Override
    public List<Error_Rate> findErrorByCategory(String category_name) {
        return user_summaryDao.findErrorByCategory(category_name);
    }

    /**
     * 根据学号，考试查找考试信息
     *
     * @param
     * @return
     */
    @Override
    public List<Xiangqing> findAllByExaminee_Num2Hbase(String startRow,String endRow) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "es01:2181,es02:2181,es03:2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("ns1:edu"));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(endRow));
        ResultScanner res = table.getScanner(scan);
        Iterator<Result> iterator = res.iterator();
        byte[] f = Bytes.toBytes("f1");
        byte[] class_names = Bytes.toBytes("class_name");
        byte[] exam_ids = Bytes.toBytes("exam_id");
        byte[] examinee_names = Bytes.toBytes("examinee_name");
        byte[] examinee_nums = Bytes.toBytes("examinee_num");
        byte[] part_question_marks = Bytes.toBytes("part_question_mark");
        byte[] question_difficulties = Bytes.toBytes("question_difficulty");
        byte[] question_ids = Bytes.toBytes("question_id");
        byte[] question_types = Bytes.toBytes("question_type");
        byte[] scores = Bytes.toBytes("score");
        byte[] start_times = Bytes.toBytes("start_time");
        byte[] category_names = Bytes.toBytes("category_name");
        List<Xiangqing> list = new ArrayList<Xiangqing>();
        while(iterator.hasNext()){
            Result r = iterator.next();
            byte[] exam_id = r.getValue(f, exam_ids);
            byte[] start_time = r.getValue(f, start_times);
            byte[] class_name = r.getValue(f, class_names);
            byte[] category_name = r.getValue(f, category_names);
            byte[] question_difficulty = r.getValue(f, question_difficulties);
            byte[] examinee_num = r.getValue(f, examinee_nums);
            byte[] examinee_name = r.getValue(f, examinee_names);
            byte[] part_question_mark = r.getValue(f, part_question_marks);
            byte[] score = r.getValue(f, scores);
            Xiangqing xiangqing = new Xiangqing(Bytes.toString(exam_id),
                    Bytes.toString(start_time),Bytes.toString(class_name),
                    Bytes.toString(category_name),Bytes.toString(question_difficulty),
                    Bytes.toString(examinee_num),Bytes.toString(examinee_name),
                    Bytes.toString(part_question_mark),Bytes.toString(score));
            list.add(xiangqing);

        }
        return list;
    }

    // 从hbase中拉去指定班级指定考试id的学生信息
    @Override
    public List<Huizong> findAllByClass_name2Hbase(String startRow, String endRow) {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "es01:2181,es02:2181,es03:2181");
        Connection conn = null;
        List<Huizong> list = new ArrayList<Huizong>();
        try {
            conn = ConnectionFactory.createConnection(conf);
            Table table = conn.getTable(TableName.valueOf("ns1:huizong"));
            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes(startRow));
            scan.setStopRow(Bytes.toBytes(endRow));
            ResultScanner res = table.getScanner(scan);
            Iterator<Result> iterator = res.iterator();
            byte[] f = Bytes.toBytes("f1");
            byte[] exam_ids = Bytes.toBytes("exam_id");
            byte[] start_times = Bytes.toBytes("start_time");
            byte[] class_names = Bytes.toBytes("class_name");
            byte[] examinee_nums = Bytes.toBytes("examinee_num");
            byte[] examinee_names = Bytes.toBytes("examinee_name");
            byte[] submit_times = Bytes.toBytes("submit_time");
            byte[] exam_times = Bytes.toBytes("exam_time");
            byte[] objective_marks = Bytes.toBytes("objective_mark");
            byte[] subjective_marks = Bytes.toBytes("subjective_mark");
            byte[] total_marks = Bytes.toBytes("total_mark");
            while(iterator.hasNext()){
                Result r = iterator.next();
                byte[] exam_id = r.getValue(f, exam_ids);
                byte[] start_time = r.getValue(f, start_times);
                byte[] class_name = r.getValue(f, class_names);
                byte[] examinee_num = r.getValue(f, examinee_nums);
                byte[] examinee_name = r.getValue(f, examinee_names);
                byte[] submit_time = r.getValue(f, submit_times);
                byte[] exam_time = r.getValue(f, exam_times);
                byte[] objective_mark = r.getValue(f, objective_marks);
                byte[] subjective_mark = r.getValue(f, subjective_marks);
                byte[] total_mark = r.getValue(f, total_marks);
                Huizong huizong = new Huizong(Bytes.toString(exam_id), Bytes.toString(start_time), Bytes.toString(class_name), Bytes.toString(examinee_num), Bytes.toString(examinee_name), Bytes.toString(submit_time), Bytes.toString(exam_time), Bytes.toString(objective_mark), Bytes.toString(subjective_mark), Bytes.toString(total_mark));
                list.add(huizong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
