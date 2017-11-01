package com.example.sb

import com.swisscom.cloud.sb.broker.functional.BaseFunctionalSpec
import com.swisscom.cloud.sb.broker.services.common.ServiceProviderLookup
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(classes=[com.example.sb.ServiceProvider.class,com.swisscom.cloud.sb.broker.ServiceBroker.class])
@ContextConfiguration(classes = [App.class])
class ServiceProviderFunctionalSpec extends BaseFunctionalSpec {

    def setup(){
        serviceLifeCycler.createServiceIfDoesNotExist("example", ServiceProviderLookup.findInternalName(ServiceProvider))
    }

    def cleanupSpec(){
        serviceLifeCycler.cleanup()
    }

    def "provision and bind service instance"(){
        given:
        serviceLifeCycler.createServiceInstanceAndServiceBindingAndAssert()

        expect:
        serviceLifeCycler.getCredentials()
    }

    def "unbind and deprovision  service instance" (){
        expect:
        serviceLifeCycler.deleteServiceBindingAndServiceInstaceAndAssert()
    }

}