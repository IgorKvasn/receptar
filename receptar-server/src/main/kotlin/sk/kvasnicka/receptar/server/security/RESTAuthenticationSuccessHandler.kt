package sk.kvasnicka.receptar.server.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import sk.kvasnicka.receptar.server.config.JsonJacksonHelper
import sk.kvasnicka.receptar.services.UserService
import sk.kvasnicka.receptar.services.security.SecurityContextHolder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RESTAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest, response: HttpServletResponse,
        authentication: Authentication
    ) {
        clearAuthenticationAttributes(request)

        JsonJacksonHelper.writeObject(response, SecurityContextHolder.getLoggedUserDetails())
    }
}
