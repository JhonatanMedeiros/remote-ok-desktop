package remoteokdesktop.service;

import remoteokdesktop.model.RemoteOkJob;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.rmi.Remote;
import java.util.Properties;

public class EmailService {

    public static void sendEmail(RemoteOkJob job, String emailFrom, String emailTo) {
        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", "smtp.mailtrap.io");
            prop.put("mail.smtp.port", "2525");
            prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

            String username = "1db33e68036ffd";
            String password = "c6c5fcf71b4588";
            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("remoteOkDesktop@remoteOk.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("");

            StringBuilder sb = new StringBuilder()
                    .append(String.format("<html><h3>%s shared a RemoteOK job with you!<h3><br>", emailFrom))
                    .append(String.format("<p>Position: %s</p><br>", job.getPosition()))
                    .append(String.format("<p>Company: %s</p><br>", job.getCompany()))
                    .append(String.format("<p>Job Date: %s</p><br>", job.getDate()))
                    .append(String.format("<p>Job Description: %s</p><br>", job.getDescription()))
                    .append(String.format("<p>Know more in the link below<br><a href=\"%s\">%s</a></p>", job.getUrl(), job.getPosition()));
            String msg = sb.toString();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
