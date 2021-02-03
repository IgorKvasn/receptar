package sk.kvasnicka.receptar.services.security

import org.springframework.security.core.userdetails.UserDetails


class SecuritySaltSource {
    fun getSalt(user: UserDetails): String {
        if (user !is LoggedUserDetail){
            throw IllegalStateException("user it not LoggedUserDetail")
        }

        return user.user.passwordSalt


    }
}
