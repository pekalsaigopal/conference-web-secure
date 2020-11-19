package in.psg.conferencewebsecure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConferenceSecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()
                .antMatchers("/assets/css/**","/assets/js/**","/images/**").permitAll()
                .antMatchers("/index**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/",true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=ebank,dc=com")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(passwordEncoder())
//                .passwordAttribute("userPassword");

        /*
        Test the app using the below credentials
        User : riemann ,gauss ,euler ,euclid
        Password: password
         */

        auth
                .ldapAuthentication()
                .contextSource()
                .url("ldap://ldap.forumsys.com:389/" + "dc=example,dc=com")
                .managerDn("cn=read-only-admin,dc=example,dc=com")
                .managerPassword("password")
                .and()
                .userDnPatterns(" uid={0}");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
