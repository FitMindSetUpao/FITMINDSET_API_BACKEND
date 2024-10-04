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

    // Método para enviar un correo simple
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

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

    // Método para enviar recordatorios según la preferencia del usuario
    public void sendReminderBasedOnPreference(String recipientEmail, String preference) throws MessagingException {
        switch (preference.toLowerCase()) {
            case "diaria":
                sendDailyReminder(recipientEmail);
                break;
            case "semanal":
                sendWeeklyReminder(recipientEmail);
                break;
            case "mensual":
                sendMonthlyReminder(recipientEmail);
                break;
            default:
                throw new IllegalArgumentException("Preferencia inválida");
        }
    }

    // Generar un mensaje personalizado para el recordatorio
    public String generateReminderMessage(String recipientEmail, String goalName, int daysRemaining) {
        return "Hola, te faltan " + daysRemaining + " días para completar tu meta: " + goalName + ". ¡Sigue esforzándote!";
    }

    // Método para enviar un recordatorio personalizado basado en una meta
    public void sendCustomReminder(String recipientEmail, String goalName, int daysRemaining, String preference) throws MessagingException {
        String message = generateReminderMessage(recipientEmail, goalName, daysRemaining);

        switch (preference.toLowerCase()) {
            case "diaria":
                sendSimpleMessage(recipientEmail, "Recordatorio Diario", message);
                break;
            case "semanal":
                sendSimpleMessage(recipientEmail, "Recordatorio Semanal", message);
                break;
            case "quincenal":
                sendSimpleMessage(recipientEmail, "Recordatorio Quincenal", message);
                break;
            case "mensual":
                sendSimpleMessage(recipientEmail, "Recordatorio Mensual", message);
                break;
            default:
                throw new IllegalArgumentException("Preferencia inválida");
        }
    }
}
