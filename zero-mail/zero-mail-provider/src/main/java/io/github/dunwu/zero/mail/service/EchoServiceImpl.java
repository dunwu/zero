package io.github.dunwu.zero.mail.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
@Service(version = "1.0.1")
@RestController
public class EchoServiceImpl implements EchoService {

    @Override
    @GetMapping(value = "/echo")
    public String echo(@RequestParam String param) {
        System.out.println("SpringEchoService dubbo echo invoke: " + param);
        return "Spring Cloud Alibaba Dubbo echo: " + param;
    }

}
