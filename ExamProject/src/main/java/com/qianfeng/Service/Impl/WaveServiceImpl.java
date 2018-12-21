package com.qianfeng.Service.Impl;

import com.qianfeng.Dao.WaveMapping;
import com.qianfeng.Service.Inter.WaveService;
import com.qianfeng.domain.WaveStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaveServiceImpl implements WaveService {

    @Autowired
    WaveMapping waveMapping;

    /**
     * 通过姓名查找所有信息
     * @param examinee_name
     * @return
     */
    @Override
    public List<WaveStudent> findByNameAll(String examinee_name) {
        List<WaveStudent> listWaveStudent = waveMapping.findByNameAll(examinee_name);
        return listWaveStudent;
    }
}
