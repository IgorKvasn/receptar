package sk.kvasnicka.receptar.web.controllers

import org.springframework.http.MediaType
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sk.kvasnicka.receptar.data.enums.UserRole
import sk.kvasnicka.receptar.data.model.User
import sk.kvasnicka.receptar.services.UserService

@RestController
@RequestMapping("/api/users")
class UserController(
    val userService: UserService
) {

    @PostMapping(
        value = [""],
        produces = [MediaType.APPLICATION_PROBLEM_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @PreAuthorize("hasRole(T(sk.kvasnicka.receptar.data.enums.UserRole).ROLE_ADMIN)")
    fun createUser(@RequestBody user: User) {
//        userService.createUser(user);
//        val u = User("1", "admin", "heslo", null, true, listOf(UserRole.ADMIN));
        userService.createUser(user)
    }
}
