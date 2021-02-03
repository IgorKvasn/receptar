package sk.kvasnicka.receptar.services

import org.springframework.stereotype.Service
import sk.kvasnicka.receptar.data.dao.UserDao
import sk.kvasnicka.receptar.data.enums.UserRole
import sk.kvasnicka.receptar.data.model.User

@Service
class UserService(
    val userDao: UserDao
) {

    fun createUser(user: User): User {
        return userDao.save(user);
    }

    fun hasAdminRole(): Boolean {
        return userDao.countByRolesContaining(UserRole.ROLE_ADMIN) != 0
    }

}
