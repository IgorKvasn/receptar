package sk.kvasnicka.receptar.services.security

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.security.core.context.SecurityContextHolder
import sk.kvasnicka.receptar.data.enums.UserRole

class SecurityContextHolder {

    companion object{
        private val log: Logger = LogManager.getLogger(SecurityContextHolder::class.java)

        fun getLoggedUserDetails(): LoggedUserDetail? {
            return getLoggedUserDetails(true)
        }

        fun getLoggedUserDetails(logEmptyContext: Boolean): LoggedUserDetail? {
            val securityContext = SecurityContextHolder.getContext() ?: return null
            val authentication = securityContext.authentication ?: return null
            if (authentication.details is LoggedUserDetail) {
                return authentication.details as LoggedUserDetail
            }
            if (authentication.principal is LoggedUserDetail) {
                return authentication.principal as LoggedUserDetail
            }
            if (logEmptyContext) {
                log.warn("LoggedUserDetails not found in authentication $authentication")
            }
            return null
        }

        fun hasRole(role: UserRole): Boolean {
            return hasAnyRole(role)
        }

        fun hasAnyRole(vararg roles: UserRole): Boolean {
            val details: LoggedUserDetail = getLoggedUserDetails() ?: return false
            return details.hasAnyRole(*roles)
        }
    }


}
