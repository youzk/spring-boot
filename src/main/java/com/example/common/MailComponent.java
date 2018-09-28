package com.example.common;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

    @Autowired
    private JavaMailSender mailSender;
	
    @Value("${mail.fromMail.addr}")
    private String from;
    
    /**
     * 发送简单的邮件
     * com.example.common 
     * 方法名：sendSimpleMail
     * 创建人：yzk 
     * 时间：2018年9月6日-下午4:16:37 
     * @param to
     * @param subject
     * @param content void
     * @exception 
     * @since  1.0.0
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            System.out.println("简单邮件已经发送");
        } catch (Exception e) {
        	System.out.println("发送简单邮件时发生异常！");
            
        }

    }
    
    
    /**
     * 发送html格式邮件
     * com.example.common 
     * 方法名：sendHtmlMail
     * 创建人：yzk 
     * 时间：2018年9月6日-下午4:47:05 
     * @param to
     * @param subject
     * @param content void
     * @exception 
     * @since  1.0.0
     */
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            System.out.println("html邮件发送成功");
        } catch (MessagingException e) {
        	System.out.println("发送html邮件时发生异常！");
        }
    }
    
    
    /**
     * 发送带附件的邮件
     * com.example.common 
     * 方法名：sendAttachmentsMail
     * 创建人：yzk 
     * 时间：2018年9月6日-下午5:25:32 
     * @param to
     * @param subject
     * @param content
     * @param filePath void
     * @exception 
     * @since  1.0.0
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);//添加多个附件可以使用多条 helper.addAttachment(fileName, file)

            mailSender.send(message);
            System.out.println("带附件的邮件已经发送。");
        } catch (MessagingException e) {
        	System.out.println("发送带附件的邮件时发生异常！");
        }
    }
    
    /**
     * 发送带静态资源的邮件
     * com.example.common 
     * 方法名：sendInlineResourceMail
     * 创建人：yzk 
     * 时间：2018年9月6日-下午5:27:16 
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId void
     * @exception 
     * @since  1.0.0
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            String fileName = rscPath.substring(rscPath.lastIndexOf(File.separator)+1);
            System.out.println(fileName);
            helper.addAttachment(fileName, res);//添加多个附件可以使用多条 helper.addAttachment(fileName, file)
            helper.addInline(rscId, res);

            mailSender.send(message);
            System.out.println("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
        	System.out.println("发送嵌入静态资源的邮件时发生异常！");
        }
    }
}
