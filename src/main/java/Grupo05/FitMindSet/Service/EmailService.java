package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.dto.EmailDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendVerificationEmail(String to, String verificationLink) {
        String subject = "Tu enlace de acceso";
        String body = "Haz clic en el siguiente enlace para acceder: " + verificationLink;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }

    public void sendPasswordResetEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recuperación de Contraseña");
        message.setText("Para restablecer tu contraseña, por favor sigue el siguiente enlace: " + resetLink);

        emailSender.send(message);
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }


    // Método para enviar correo de registro exitoso
    public void sendRegistrationSuccessEmail(String to) {
        String subject = "Registro Exitoso";
        String body = "¡Bienvenido/a! Tu registro en la plataforma ha sido exitoso.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }

    public void sendAccountDeletionEmail(String to) {
        String subject = "Cuenta Eliminada";
        String body = "Tu cuenta ha sido eliminada exitosamente. Si no fuiste tú, por favor contáctanos inmediatamente.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }

    @Override
    public void sendMail(EmailDTO emailDTO) throws MessagingException {
        sendSimpleMessage(emailDTO.getDestinatario(), emailDTO.getAsunto(), emailDTO.getMensaje());
    }

    @Override
    public void sendDailyReminder(String recipientEmail) throws MessagingException {
        sendSimpleMessage(recipientEmail, "Daily Reminder", "This is your daily reminder.");
    }

    @Override
    public void sendWeeklyReminder(String recipientEmail) throws MessagingException {
        sendSimpleMessage(recipientEmail, "Weekly Reminder", "This is your weekly reminder.");
    }

    @Override
    public void sendMonthlyReminder(String recipientEmail) throws MessagingException {
        sendSimpleMessage(recipientEmail, "Monthly Reminder", "This is your monthly reminder.");
    }
}

