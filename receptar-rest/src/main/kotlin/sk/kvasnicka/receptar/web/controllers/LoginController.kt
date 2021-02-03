package sk.kvasnicka.receptar.web.controllers

import org.springframework.http.MediaType
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sk.kvasnicka.receptar.data.enums.UserRole
import sk.kvasnicka.receptar.data.model.User
import sk.kvasnicka.receptar.services.UserService

@RestController
@RequestMapping("/api/login")
class LoginController(
    val userService: UserService
) {

    @PostMapping(
        value = [""],
        produces = [MediaType.APPLICATION_PROBLEM_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
//    @Secured({UserRole.ADMIN.name})
    fun login(@RequestBody user: User) {
        userService.createUser(user)
    }
}
