package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.dto.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {

    // Método para enviar un correo simple utilizando DTO
    void sendMail(EmailDTO emailDTO) throws MessagingException;

    // Métodos para enviar recordatorios diarios, semanales y mensuales
    void sendDailyReminder(String recipientEmail) throws MessagingException;

    void sendWeeklyReminder(String recipientEmail) throws MessagingException;

    void sendMonthlyReminder(String recipientEmail) throws MessagingException;

    // Método para enviar un correo de eliminación de cuenta
    void sendAccountDeletionEmail(String to);
}
