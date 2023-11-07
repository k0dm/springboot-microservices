package github.com.k0dm.departmentservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RefreshScope
@RestController
class MessageController {

    @Value("\${spring.boot.message}")
    var message:String? = null

    @GetMapping("message")
    fun message() = message
}