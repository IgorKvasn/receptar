package sk.kvasnicka.receptar.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import sk.kvasnicka.receptar.data.dao.UserDao
import sk.kvasnicka.receptar.services.security.LoggedUserDetail

@Service
class LoggedUserDetailService(val userDao: UserDao) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userDao.findByUsername(username)
        return LoggedUserDetail(user.orElseThrow { UsernameNotFoundException(username) });
    }
}
