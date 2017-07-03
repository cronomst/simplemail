package com.wordsthatfollow.simplemail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author kshook
 */
public class SimpleMailSender
{
    private Config config;
    
    public SimpleMailSender()
    {
        config = null;
    }
    
    public SimpleMailSender(Config config)
    {
        this.config = config;
    }

    public Config getConfig()
    {
        return config;
    }

    public void setConfig(Config config)
    {
        this.config = config;
    }
    
    public void send(String fromAddress, String toAddress, String subject, String body) throws EmailException
    {
        Email email = new SimpleEmail();
        email.setHostName(config.hostName);
        email.setSmtpPort(config.port);
        email.setAuthenticator(new DefaultAuthenticator(config.username, config.password));
        email.setSSLOnConnect(true);
        email.setFrom(fromAddress);
        email.setSubject(subject);
        email.setMsg(body);
        email.addTo(toAddress);
        email.send();
    }
    
}
