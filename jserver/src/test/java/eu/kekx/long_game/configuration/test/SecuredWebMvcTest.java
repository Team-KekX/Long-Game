package eu.kekx.long_game.configuration.test;

import eu.kekx.long_game.configuration.security.JwtConfig;
import eu.kekx.long_game.configuration.security.PasswordConfig;
import eu.kekx.long_game.configuration.security.SecurityConfig;
import eu.kekx.long_game.service.security.CustomUserDetailsService;
import eu.kekx.long_game.service.security.JwtService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebMvcTest
@Import({
    SecurityConfig.class,
    JwtService.class,
    JwtConfig.class,
    CustomUserDetailsService.class,
    PasswordConfig.class
})
public @interface SecuredWebMvcTest {
    @AliasFor(annotation = WebMvcTest.class, attribute = "value")
    Class<?>[] value() default {};
}
