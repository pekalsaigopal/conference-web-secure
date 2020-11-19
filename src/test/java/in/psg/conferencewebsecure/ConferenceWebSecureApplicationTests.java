package in.psg.conferencewebsecure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ConferenceWebSecureApplicationTests {

    @Autowired
    PasswordEncoder encoder;

    @Test
    void contextLoads() {
        System.out.println(encoder.encode("pass"));
        //$2a$10$f9cdwpabuDkvFeSvbqWzD.NQNKi/9An7NO/rNKcgWXqfmWwxMijKO
    }

}
