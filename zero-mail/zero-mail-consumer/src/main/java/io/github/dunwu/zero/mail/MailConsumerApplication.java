package io.github.dunwu.zero.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MailConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailConsumerApplication.class, args);
    }

}
