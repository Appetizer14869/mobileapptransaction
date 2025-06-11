package com.mobileapptransaction.myapp.security.jwt;

import com.mobileapptransaction.myapp.config.SecurityConfiguration;
import com.mobileapptransaction.myapp.config.SecurityJwtConfiguration;
import com.mobileapptransaction.myapp.config.WebConfigurer;
import com.mobileapptransaction.myapp.management.SecurityMetersService;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import tech.jhipster.config.JHipsterProperties;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(
    classes = {
        JHipsterProperties.class,
        WebConfigurer.class,
        SecurityConfiguration.class,
        SecurityJwtConfiguration.class,
        SecurityMetersService.class,
        JwtAuthenticationTestUtils.class,
    }
)
public @interface AuthenticationIntegrationTest {
}
