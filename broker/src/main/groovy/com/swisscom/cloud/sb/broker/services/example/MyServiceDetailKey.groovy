package com.swisscom.cloud.sb.broker.services.example

import com.swisscom.cloud.sb.broker.util.servicedetail.AbstractServiceDetailKey
import com.swisscom.cloud.sb.broker.util.servicedetail.ServiceDetailType
import groovy.transform.CompileStatic

@CompileStatic
enum MyServiceDetailKey implements AbstractServiceDetailKey{

    USERNAME('username',ServiceDetailType.USERNAME),
    PASSWORD('password',ServiceDetailType.PASSWORD),
    HOST('host',ServiceDetailType.HOST)

    MyServiceDetailKey(String key, ServiceDetailType serviceDetailType) {
        com_swisscom_cloud_sb_broker_util_servicedetail_AbstractServiceDetailKey__key = key
        com_swisscom_cloud_sb_broker_util_servicedetail_AbstractServiceDetailKey__serviceDetailType = serviceDetailType
    }
}