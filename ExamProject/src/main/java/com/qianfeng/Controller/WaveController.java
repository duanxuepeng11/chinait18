package com.qianfeng.Controller;

import com.qianfeng.Service.Inter.WaveService;
import com.qianfeng.domain.WaveStudent;
import com.qianfeng.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 成绩波动
 */
@Controller
public class WaveController {

    @Autowired
    WaveService waveService;

    @RequestMapping("/wave")
    @ResponseBody
    public List<WaveStudent> findByNameAll(String examinee_name) {

        examinee_name = MD5Utils.md5(examinee_name);
        System.out.println(examinee_name);
        List<WaveStudent> listWaveStudent = waveService.findByNameAll(examinee_name);
        for (WaveStudent w : listWaveStudent){
            System.out.println(w);
        }
        return listWaveStudent;

    }

    @RequestMapping("/tobodong")
    public String toBodong(){
        return "bodong";
    }
}
