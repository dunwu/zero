package io.github.dunwu.zero.mail.service;

import io.github.dunwu.util.mapper.BeanMapper;
import io.github.dunwu.zero.mail.dto.MailDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        mailExecutorService = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS,
                                                     new LinkedBlockingQueue<>(),
                                                     new NamedThreadFactory("mail-service", Boolean.TRUE));
        this.javaMailSender = javaMailSender;
    }

    @Override
    @GetMapping(value = "/send")
    public void send(@NotNull MailDTO mailDTO) {
        log.debug("发送邮件，subject: {}, from: {}, to: {}", mailDTO.getSubject(), mailDTO.getFrom(), mailDTO.getTo());

        if (mailDTO.getHtml()) {
            mailExecutorService.execute(() -> sendMimeMessage(mailDTO));
        } else {
            mailExecutorService.execute(() -> sendSimpleMessage(mailDTO));
        }
    }

    private void sendSimpleMessage(MailDTO mailDTO) {
        SimpleMailMessage simpleMailMessage = BeanMapper.map(mailDTO, SimpleMailMessage.class);
        if (StringUtils.isEmpty(mailDTO.getFrom())) {
            mailDTO.setFrom(from);
        }

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.error("发送邮件失败", e);
        }
        log.debug("发送邮件成功");
    }

    private void sendMimeMessage(MailDTO mailDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            if (StringUtils.isEmpty(mailDTO.getFrom())) {
                messageHelper.setFrom("from");
            }
            messageHelper.setFrom(mailDTO.getFrom());
            messageHelper.setTo(mailDTO.getTo());
            messageHelper.setSubject(mailDTO.getSubject());
            messageHelper.setText(mailDTO.getText(), true);

            // 添加邮件附件
            if (mailDTO.getFilenames() != null) {
                for (String filename : mailDTO.getFilenames()) {
                    messageHelper.addAttachment(filename, new File(filename));
                }
            }

            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            log.error("发送邮件失败", e);
        }

        log.debug("发送邮件成功");
    }
}
