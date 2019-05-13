package io.github.dunwu.zero.mail.service;

import io.github.dunwu.zero.mail.dto.MailDTO;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-04-28
 */
public interface MailService {
    void send(MailDTO mailDTO);
}
