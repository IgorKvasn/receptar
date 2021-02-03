package sk.kvasnicka.receptar.services.security

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails

class SaltedDaoAuthenticationProvider(
    securityPasswordEncoder: SecurityPasswordEncoder,
    securitySaltSource: SecuritySaltSource
) :
    DaoAuthenticationProvider() {
    private val securityPasswordEncoder: SecurityPasswordEncoder
    private val securitySaltSource: SecuritySaltSource

    override fun additionalAuthenticationChecks(
        userDetails: UserDetails,
        authentication: UsernamePasswordAuthenticationToken
    ) {
        if (userDetails is LoggedUserDetail) {
            val isValid: Boolean = securityPasswordEncoder.isPasswordValid(
                userDetails.password, authentication.credentials.toString(),
                securitySaltSource.getSalt(userDetails)
            )
            if (!isValid) {
                throw BadCredentialsException("Incorrect username or password")
            }
            return
        }
        super.additionalAuthenticationChecks(userDetails, authentication)
    }

    init {
        this.securityPasswordEncoder = securityPasswordEncoder
        this.securitySaltSource = securitySaltSource
    }
}
