package sk.kvasnicka.receptar.server.config

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import sk.kvasnicka.receptar.data.enums.UserRole
import sk.kvasnicka.receptar.data.model.User
import sk.kvasnicka.receptar.services.UserService
import sk.kvasnicka.receptar.services.security.SecurityPasswordEncoder
import kotlin.math.roundToInt


@Configuration
@ComponentScan("sk.kvasnicka.receptar.*")
class SpringConfig(
    val userService: UserService
) {
    private val log: Logger = LogManager.getLogger(SpringConfig::class.java)

    @Bean
    fun initApplication() {
        if (!userService.hasAdminRole()) {
            log.info("creating default user with ADMIN role")
            val salt = (Math.random() * 10000).roundToInt().toString()
            val defaultUser = User(
                "admin",
                SecurityPasswordEncoder.encodePassword("heslo", salt),
                salt,
                true,
                listOf(UserRole.ROLE_ADMIN),
                null
            );
            userService.createUser(defaultUser)
        } else {
            log.debug("at least one ADMIN role exists")
        }
    }
}
