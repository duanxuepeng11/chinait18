package com.qianfeng.Service.Inter;

import com.qianfeng.Dao.WaveMapping;
import com.qianfeng.domain.WaveStudent;

import java.util.List;

public interface WaveService {
    /**
     * 通过姓名查找信息
     * @param examinee_name
     * @return
     */
    public List<WaveStudent> findByNameAll(String examinee_name);
}
