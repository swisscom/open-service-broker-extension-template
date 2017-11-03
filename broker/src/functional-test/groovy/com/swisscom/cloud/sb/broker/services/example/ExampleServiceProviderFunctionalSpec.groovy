package com.swisscom.cloud.sb.broker.services.example

import com.swisscom.cloud.sb.broker.functional.BaseFunctionalSpec
import com.swisscom.cloud.sb.broker.services.common.ServiceProviderLookup

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