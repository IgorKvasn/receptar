package sk.kvasnicka.receptar.services.security

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import sk.kvasnicka.receptar.data.enums.UserRole
import sk.kvasnicka.receptar.data.model.User

class LoggedUserDetail(val user: User) : UserDetails {

    var authorities: MutableList<GrantedAuthority>;
    private val log: Logger = LogManager.getLogger(LoggedUserDetail::class.java)

    init {
        this.authorities = mutableListOf();
        for (role in user.roles) {
            try {
                val r = UserRole.valueOf(role.name)
                this.authorities.add(r);
            } catch (e: IllegalArgumentException) {
                log.error("unknown user role: " + role.name)
            }
        }
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        return this.authorities;
    }

    override fun getPassword(): String {
        return user.password;
    }

    override fun getUsername(): String {
        return user.username;
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun isEnabled(): Boolean {
        return user.active;
    }

    fun hasAnyRole(vararg roles: UserRole): Boolean {
        if (roles == null) {
            return false
        }
        for (role in roles) {
            if (role != null && getAuthorities().contains(role.authority)) {
                return true
            }
        }
        return false
    }
}
