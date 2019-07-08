package io.github.dunwu.zero.aaa;

import io.github.dunwu.config.EnableDunwuWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-06-13
 **/
@SpringBootApplication
@EnableDunwuWebConfiguration
public class ZeroAaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeroAaaApplication.class, args);
    }
}
