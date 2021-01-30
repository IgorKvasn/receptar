package sk.kvasnicka.receptar.services

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun getHello(): String {
        return "Ahoj Martinka"
    }
}
