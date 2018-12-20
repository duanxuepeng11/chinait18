package com.qianfeng.Service.Inter;

import com.qianfeng.domain.VIPStudent;
import com.qianfeng.utils.PageBean;

public interface VipStudentService {

    public PageBean<VIPStudent> findAll(Integer pageCode, Integer pageSizes, String select_value);

}
