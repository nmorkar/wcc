package com.cric.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class EMailUtil implements MailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	private VelocityEngine velocityEngine;

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void sendEmail(final String mail_from, final String mail_to, final String mail_subject) throws MailSendException {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");

                message.setTo(mail_to);
                message.setFrom(mail_from);
                message.setSubject(mail_subject);

                Map model = new HashMap();

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mlist_email_template.vm", model);
                message.setText(text, true);
            }
        };
        try {
            this.javaMailSender.send(preparator);
        } catch (MailSendException mse) {
            mse.printStackTrace();
            throw mse;
        }
    }
   

    @Override
    public void send(SimpleMailMessage arg0) throws MailException {
    }

    @Override
    public void send(SimpleMailMessage[] arg0) throws MailException {
    }

}