package sk.kvasnicka.receptar.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import sk.kvasnicka.receptar.data.enums.UserRole
import org.apache.commons.lang3.RandomStringUtils




@Document(collection = "users")
class User(
    val username: String,
    val password: String,
    passwordSalt: String?,
    val active: Boolean,
    val roles: List<UserRole>,
    @Id var id: String?
) {
    var passwordSalt: String;

    init {
        if (passwordSalt == null){
            this.passwordSalt = RandomStringUtils.random(20, true, true)
        } else {
            this.passwordSalt = passwordSalt;
        }
    }

}
