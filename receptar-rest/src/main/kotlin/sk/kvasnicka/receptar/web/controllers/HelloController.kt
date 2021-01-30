package sk.kvasnicka.receptar.web.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import sk.kvasnicka.receptar.services.HelloService

@RestController
class HelloController(val helloService: HelloService) {


    @GetMapping("/hello")
    fun helloKotlinService(): String {
        return helloService.getHello()
    }
}
