package io.github.dunwu.zero.mail.execute;

import io.github.dunwu.zero.mail.dto.MailDTO;
import io.github.dunwu.zero.mail.service.MailService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
@RestController
public class DubboController {

    public static final String[] TO = new String[] {"forbreak@163.com"};
    public static final String[] CC = new String[] {"forbreak@163.com"};
    public static final String SUBJECT = "Test Email";

    @Reference(version = "1.0.1")
    private MailService mailService;

    @Autowired
    @Lazy
    private DubboFeignMailService dubboFeignMailService;

    @Autowired
    @Lazy
    private FeignMailService feignMailService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${provider.application.name}")
    private String serviceName;

    @GetMapping("/dubbofeign")
    public void dubbofeign() {
        MailDTO mailDTO = new MailDTO();

        String text = new StringBuilder().append("<html>\n")
                                         .append("<body>\n")
                                         .append("<h3>This is a mime message email.</h3>\n")
                                         .append("</body>\n")
                                         .append("</html>")
                                         .toString();
        mailDTO.setFrom("forbreak@163.com");
        mailDTO.setTo(TO);
        mailDTO.setCc(CC);
        mailDTO.setSubject("mail by dubbofeign");
        mailDTO.setText(text);

        dubboFeignMailService.send(mailDTO);
    }

    @GetMapping("/dubbo")
    public void dubbo() {
        MailDTO mailDTO = new MailDTO();

        String text = new StringBuilder().append("<html>\n")
                                         .append("<body>\n")
                                         .append("<h3>This is a mime message email.</h3>\n")
                                         .append("</body>\n")
                                         .append("</html>")
                                         .toString();
        mailDTO.setFrom("forbreak@163.com");
        mailDTO.setTo(TO);
        mailDTO.setCc(CC);
        mailDTO.setSubject("mail by dubbo");
        mailDTO.setText(text);
        mailDTO.setHtml(true);
        mailService.send(mailDTO);
    }

    @GetMapping("/feign")
    public void feign() {
        MailDTO mailDTO = new MailDTO();

        String text = new StringBuilder().append("<html>\n")
                                         .append("<body>\n")
                                         .append("<h3>This is a mime message email.</h3>\n")
                                         .append("</body>\n")
                                         .append("</html>")
                                         .toString();
        mailDTO.setFrom("forbreak@163.com");
        mailDTO.setTo(TO);
        mailDTO.setCc(CC);
        mailDTO.setSubject("mail by feign");
        mailDTO.setText(text);
        mailDTO.setHtml(true);
        mailService.send(mailDTO);
    }


    @GetMapping("/template")
    public void template() {
        MailDTO mailDTO = new MailDTO();

        String text = new StringBuilder().append("<html>\n")
                                         .append("<body>\n")
                                         .append("<h3>This is a mime message email.</h3>\n")
                                         .append("</body>\n")
                                         .append("</html>")
                                         .toString();
        mailDTO.setFrom("forbreak@163.com");
        mailDTO.setTo(TO);
        mailDTO.setCc(CC);
        mailDTO.setSubject("mail by template");
        mailDTO.setText(text);
        mailDTO.setHtml(true);
        restTemplate.getForObject("http://" + serviceName + "/mail?mailDTO={p}", MailDTO.class, mailDTO);
    }

}
