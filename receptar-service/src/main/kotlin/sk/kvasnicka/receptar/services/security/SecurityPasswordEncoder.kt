package sk.kvasnicka.receptar.services.security

import java.security.MessageDigest;
import org.apache.commons.lang3.StringUtils

class SecurityPasswordEncoder {

    fun isPasswordValid(encPass: String, rawPass: String, salt: String): Boolean {
        if (StringUtils.isEmpty(encPass)) return false
        if (StringUtils.isEmpty(rawPass)) return false
        val encPassTest = encodePassword(rawPass, salt)
        return if (StringUtils.isEmpty(encPassTest)) false else encPass == encPassTest
    }

    companion object {
        fun encodePassword(pass: String, salt: String): String {
            val messageDigest = MessageDigest.getInstance("SHA-256")

            var rawPass = pass + salt
            messageDigest.update(rawPass.encodeToByteArray())
            return String(messageDigest.digest())
        }
    }
}
