package sk.kvasnicka.receptar.data.dao

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.PagingAndSortingRepository
import sk.kvasnicka.receptar.data.enums.UserRole
import sk.kvasnicka.receptar.data.model.Recipe
import sk.kvasnicka.receptar.data.model.User
import java.util.*

interface UserDao : MongoRepository<User, String> {
    fun findByUsername(username: String): Optional<User>
    fun countByRolesContaining(role: UserRole): Int
}
