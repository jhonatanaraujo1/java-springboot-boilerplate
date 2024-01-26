package exemple.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Qualifier("email")
    private final JavaMailSender javaMailSender;

    @Value("${email.fromto}")
    private String emailFrom;

    public void sendHtmlEmailSmtp(String subject, String body, String... to){
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(to[0]);
            message.setFrom(emailFrom, emailFrom);
            message.setSubject(subject);
            message.setText(body, true);
        };
        javaMailSender.send(preparator);
    }
}
