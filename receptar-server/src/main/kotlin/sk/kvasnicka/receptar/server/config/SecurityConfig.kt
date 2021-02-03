package sk.kvasnicka.receptar.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import sk.kvasnicka.receptar.server.security.RESTAuthenticationFailureHandler
import sk.kvasnicka.receptar.server.security.RESTAuthenticationSuccessHandler
import sk.kvasnicka.receptar.services.LoggedUserDetailService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder
import sk.kvasnicka.receptar.services.security.SaltedDaoAuthenticationProvider
import sk.kvasnicka.receptar.services.security.SecurityPasswordEncoder
import sk.kvasnicka.receptar.services.security.SecuritySaltSource
import java.lang.Exception


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class ServerSecurityConfig(
    val authenticationFailureHandler: RESTAuthenticationFailureHandler,
    val authenticationSuccessHandler: RESTAuthenticationSuccessHandler,
    val loggedUserDetailService: LoggedUserDetailService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        //TODO CSRF

        http.csrf().disable()

        //TODO vyhodit permitAll()

        http.authorizeRequests().anyRequest().permitAll();

        http.formLogin()
            .loginProcessingUrl("/api/login/")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler);

        http.addFilterBefore(BasicAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter::class.java)
    }

    override fun userDetailsService(): UserDetailsService {
        return loggedUserDetailService;
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
            val provider = SaltedDaoAuthenticationProvider(SecurityPasswordEncoder(), SecuritySaltSource())
            provider.setUserDetailsService(userDetailsService())
            auth.authenticationProvider(provider)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }
}
