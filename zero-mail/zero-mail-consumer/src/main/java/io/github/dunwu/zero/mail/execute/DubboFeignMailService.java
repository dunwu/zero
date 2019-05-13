package io.github.dunwu.zero.mail.execute;

import io.github.dunwu.zero.mail.dto.MailDTO;
import org.springframework.cloud.alibaba.dubbo.annotation.DubboTransported;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
@FeignClient("${provider.application.name}")
@DubboTransported()
public interface DubboFeignMailService {

    @GetMapping(value = "/mail")
    void send(@RequestParam("mailDTO") MailDTO mailDTO);
}
