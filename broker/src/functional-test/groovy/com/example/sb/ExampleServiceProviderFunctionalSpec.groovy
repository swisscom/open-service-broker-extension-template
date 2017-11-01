package com.example.sb

import com.swisscom.cloud.sb.broker.functional.BaseFunctionalSpec
import com.swisscom.cloud.sb.broker.services.common.ServiceProviderLookup
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(classes=[ExampleServiceProvider.class,com.swisscom.cloud.sb.broker.ServiceBroker.class],
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = [App.class])
@ComponentScan([ "com.swisscom.cloud.sb", "com.examle.sb"])
class ExampleServiceProviderFunctionalSpec extends BaseFunctionalSpec {

    def setup(){
        serviceLifeCycler.createServiceIfDoesNotExist("example", ServiceProviderLookup.findInternalName(ExampleServiceProvider))
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