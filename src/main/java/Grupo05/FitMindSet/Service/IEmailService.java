package Grupo05.FitMindSet.Service;

import jakarta.mail.MessagingException;

public interface IEmailService {

    void sendMail(EmailDTO emailDTO) throws MessagingException;
    void sendDailyReminder(String recipientEmail) throws MessagingException;
    void sendWeeklyReminder(String recipientEmail) throws MessagingException;
    void sendMonthlyReminder(String recipientEmail) throws MessagingException;

}

