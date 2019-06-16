package io.github.dunwu.zero.mail.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 邮件信息实体
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-04-27
 */
public class MailDTO implements Serializable {

    private static final long serialVersionUID = 6247967463273067024L;

    private String from;
    private String replyTo;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private Date sentDate;
    private String subject;
    private String text;
    private String[] filenames;
    private Boolean html;

    public MailDTO() {
        this.html = true;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }

    public Boolean getHtml() {
        return html;
    }

    public void setHtml(Boolean html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "MailDTO{" + "from='" + from + '\'' + ", replyTo='" + replyTo + '\'' + ", to=" + Arrays.toString(to)
            + ", cc=" + Arrays.toString(cc) + ", bcc=" + Arrays.toString(bcc) + ", sentDate=" + sentDate + ", subject='"
            + subject + '\'' + ", text='" + text + '\'' + ", filenames=" + Arrays.toString(filenames) + ", html=" + html
            + '}';
    }
}
