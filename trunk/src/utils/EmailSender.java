/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;
//import java.io.File;
import java.io.FileInputStream;

//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author OKupriianova
 */
public class EmailSender {    
   
    public static void send(String mHeader, String mText, String receiver) throws IOException
    {
    	Properties prop=new Properties();

        FileInputStream in = new FileInputStream("appProperties");

        prop.load(in);
        String smtpServer=prop.getProperty("smtpServer");
        String login=prop.getProperty("login");
        String pass=prop.getProperty("pass");
        String fromAddrStr=prop.getProperty("fromAddr");
        in.close();
/*
    	String smtpServer="smtp.gmail.com";
        String login="resourceinformer";
        String pass="Qwerty1234";
        String fromAddrStr="resourceinformer@gmail.com";
    	
        prop.setProperty("smtpServer", "smtp.gmail.com");
        prop.setProperty("login", "resourceinformer");
        prop.setProperty("pass", "Qwerty1234");
        prop.setProperty("fromAddr", "resourceinformer@gmail.com");
        
        File propFile=new File("appProperties");
        propFile.createNewFile();
        
        FileOutputStream out = new FileOutputStream("appProperties");
        prop.store(out, "---EmailSender Properties---");
        out.close();
        */
        
        javax.mail.Session session=javax.mail.Session.getDefaultInstance(prop,null);

        MimeMessage message=new MimeMessage(session);
        try {
            message.setSubject(mHeader);

        message.setText(mText);
        Address fromaddr=new InternetAddress(fromAddrStr);
        message.addFrom(new Address[]{fromaddr});        
        
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        
        message.setSentDate(new GregorianCalendar().getTime());
        message.saveChanges();
        Transport transport = session.getTransport("smtps");
        //transport.connect("smtp.gmail.com", "resourceinformer", "Qwerty1234");
        transport.connect(smtpServer, login, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
    
    public static void send(String mHeader, String mText, List<String> receiversList) throws IOException
    {
    	Properties prop=new Properties();

        FileInputStream in = new FileInputStream("appProperties");

        prop.load(in);
        String smtpServer=prop.getProperty("smtpServer");
        String login=prop.getProperty("login");
        String pass=prop.getProperty("pass");
        String fromAddrStr=prop.getProperty("fromAddr");
        in.close();
/*
    	String smtpServer="smtp.gmail.com";
        String login="resourceinformer";
        String pass="Qwerty1234";
        String fromAddrStr="resourceinformer@gmail.com";
    	
        prop.setProperty("smtpServer", "smtp.gmail.com");
        prop.setProperty("login", "resourceinformer");
        prop.setProperty("pass", "Qwerty1234");
        prop.setProperty("fromAddr", "resourceinformer@gmail.com");
        
        File propFile=new File("appProperties");
        propFile.createNewFile();
        
        FileOutputStream out = new FileOutputStream("appProperties");
        prop.store(out, "---EmailSender Properties---");
        out.close();
        */
        
        javax.mail.Session session=javax.mail.Session.getDefaultInstance(prop,null);

        MimeMessage message=new MimeMessage(session);
        try {
            message.setSubject(mHeader);

            message.setText(mText);
            Address fromaddr=new InternetAddress(fromAddrStr);
            message.addFrom(new Address[]{fromaddr});        
        
            for (String receiver:receiversList)
            	message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        
        message.setSentDate(new GregorianCalendar().getTime());
        message.saveChanges();
        Transport transport = session.getTransport("smtps");
        //transport.connect("smtp.gmail.com", "resourceinformer", "Qwerty1234");
        transport.connect(smtpServer, login, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
}
