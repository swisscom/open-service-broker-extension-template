package com.example.sb

import com.swisscom.cloud.sb.broker.binding.BindRequest
import com.swisscom.cloud.sb.broker.binding.BindResponse
import com.swisscom.cloud.sb.broker.binding.UnbindRequest
import com.swisscom.cloud.sb.broker.model.DeprovisionRequest
import com.swisscom.cloud.sb.broker.model.ProvisionRequest
import com.swisscom.cloud.sb.broker.model.ServiceDetail
import com.swisscom.cloud.sb.broker.provisioning.DeprovisionResponse
import com.swisscom.cloud.sb.broker.provisioning.ProvisionResponse
import com.swisscom.cloud.sb.broker.util.servicedetail.ServiceDetailsHelper
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

import static com.example.sb.MyServiceDetailKey.HOST
import static com.example.sb.MyServiceDetailKey.PASSWORD
import static com.example.sb.MyServiceDetailKey.USERNAME

@CompileStatic
@Slf4j
class ServiceProvider implements com.swisscom.cloud.sb.broker.services.common.ServiceProvider {
    @Autowired
    private ServiceConfig config

    @PostConstruct
    void init(){
        log.info("Example config:${config}")
    }

    @Override
    ProvisionResponse provision(ProvisionRequest request) {
        // This is where actions for provisioning would be handled.
        // Values below like such as 'user' would be returned by the actual service
        // Service service instance is specified by a guid
        def serviceDetails = [ServiceDetail.from(USERNAME,'user'),
                              ServiceDetail.from(PASSWORD,'pw'),
                              ServiceDetail.from(HOST,'localhost')]
        // the service details that are returned in the provision response will be persisted in a db and can be
        // retrieved during binding/unbinding/deprovisioning
        return new ProvisionResponse(isAsync: false,details: serviceDetails)
    }

    @Override
    DeprovisionResponse deprovision(DeprovisionRequest request) {
        // This is where actions for deprovisioning would be handled.
        // Service service instance is specified by a guid
        return new DeprovisionResponse(isAsync: false)
    }

    @Override
    BindResponse bind(BindRequest request) {
        // Create binding, usually a set of unique credentials would be created at this point, the binding is identified
        // by a guid

        def credentials = new Credentials(username: ServiceDetailsHelper.from(request.serviceInstance).getValue(USERNAME),
                password: ServiceDetailsHelper.from(request.serviceInstance).getValue(PASSWORD),
                host: ServiceDetailsHelper.from(request.serviceInstance).getValue(HOST),
                custom: config.value1)

        return new BindResponse(credentials:credentials)
    }

    @Override
    void unbind(UnbindRequest request) {
        // Delete the credentials that were created for the binding specificied by a guid
    }
}
