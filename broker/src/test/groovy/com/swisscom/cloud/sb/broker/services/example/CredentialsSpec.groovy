package com.swisscom.cloud.sb.broker.services.example

import org.skyscreamer.jsonassert.JSONAssert
import spock.lang.Specification

class CredentialsSpec extends Specification {

    def "Verify json serialization works correctly"() {
        given:
        Credentials credentials = new Credentials(username: 'username',
                                            password:'password',
                                            host:'host',
                                            custom: 'custom')

        and:
        String expected = """{
                                "credentials": {
                                    "username": "username",
                                    "password": "password",
                                    "host": "host",
                                    "custom": "custom"
                                }
                             }"""

        expect:
        JSONAssert.assertEquals(expected, credentials.toJson(), true)
    }
}
