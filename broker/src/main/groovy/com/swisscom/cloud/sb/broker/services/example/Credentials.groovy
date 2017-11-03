package com.swisscom.cloud.sb.broker.services.example

import com.swisscom.cloud.sb.broker.binding.BindResponseDto
import groovy.json.JsonBuilder


class Credentials implements BindResponseDto {
    String username
    String password
    String host
    String custom

    @Override
    String toJson() {
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.credentials(
                username: username,
                password: password,
                host: host,
                custom: custom
        )
        return jsonBuilder.toPrettyString()
    }
}
