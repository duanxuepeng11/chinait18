package com.qianfeng.Service.Inter;

import com.qianfeng.domain.ErrorLV;
import com.qianfeng.domain.ErrorXZ;

import java.util.List;

public interface ErrorService {

    public List<ErrorLV> errorXQ();

    public List<ErrorXZ> getData();

}
