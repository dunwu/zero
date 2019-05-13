package io.github.dunwu.zero.mail.service;

import io.github.dunwu.util.mapper.BeanMapper;
import io.github.dunwu.zero.mail.dto.MailDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2019-01-09
 */
@Service(version = "1.0.1")
@RestController("/mail")
public class MailServiceImpl implements MailService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ExecutorService mailExecutorService;

    @Value("${zero.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public MailServiceImpl() {
        mailExecutorService =
            new ThreadPoolExecutor(10, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new NamedThreadFactory("mail-service", Boolean.TRUE));
    }

    public void send(final MailDTO mailDTO, EmailType type) {
        if (type == EmailType.MIME) {
            mailExecutorService.execute(() -> sendMimeMessage(mailDTO));
        } else {
            mailExecutorService.execute(() -> sendSimpleMailMessage(mailDTO));
        }
    }

    public void sendSimpleMailMessage(MailDTO mailDTO) {
        log.debug("发送邮件，subject: {}, from: {}, to: {}", mailDTO.getSubject(), mailDTO.getFrom(), mailDTO.getTo());
        SimpleMailMessage simpleMailMessage = BeanMapper.map(mailDTO, SimpleMailMessage.class);
        if (StringUtils.isEmpty(mailDTO.getFrom())) {
            mailDTO.setFrom(from);
        }
        javaMailSender.send(simpleMailMessage);
    }

    public void sendMimeMessage(MailDTO mailDTO) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);

            if (StringUtils.isEmpty(mailDTO.getFrom())) {
                messageHelper.setFrom("from");
            }
            messageHelper.setFrom(mailDTO.getFrom());
            messageHelper.setTo(mailDTO.getTo());
            messageHelper.setSubject(mailDTO.getSubject());

            mimeMessage = messageHelper.getMimeMessage();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailDTO.getText(), "text/html;charset=UTF-8");

            // 描述数据关系
            MimeMultipart mm = new MimeMultipart();
            mm.setSubType("related");
            mm.addBodyPart(mimeBodyPart);

            // 添加邮件附件
            if (mailDTO.getFilenames() != null) {
                for (String filename : mailDTO.getFilenames()) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    try {
                        attachPart.attachFile(filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mm.addBodyPart(attachPart);
                }
            }
            mimeMessage.setContent(mm);
            mimeMessage.saveChanges();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    @Override
    @GetMapping(value = "/send")
    public void send(MailDTO mailDTO) {
        Future<?> future = mailExecutorService.submit(() -> sendSimpleMailMessage(mailDTO));
        if (future.isDone()) {
            log.debug("发送成功");
        }
    }
}
