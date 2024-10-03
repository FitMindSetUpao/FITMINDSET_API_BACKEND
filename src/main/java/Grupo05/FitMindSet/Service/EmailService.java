package Grupo05.FitMindSet.Service;

public class EmailService {

    @Override
    public void sendMail(EmailDTO emailDTO) throws MessagingException {
        sendSimpleMessage(emailDTO.getDestinatario(), emailDTO.getAsunto(), emailDTO.getMensaje());
    }

    @Override
    public void sendDailyReminder(String recipientEmail) throws MessagingException {
        sendSimpleMessage(recipientEmail, "Recordatorio Diario", "Este es tu recordatorio diario.");
    }

    @Override
    public void sendWeeklyReminder(String recipientEmail) throws MessagingException {
        sendSimpleMessage(recipientEmail, "Recordatorio Semanal", "Este es tu recordatorio semanal.");
    }

    @Override
    public void sendMonthlyReminder(String recipientEmail) throws MessagingException {
        sendSimpleMessage(recipientEmail, "Recordatorio Mensual", "Este es tu recordatorio mensual.");
    }
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


    public String generateReminderMessage(String recipientEmail, String goalName, int daysRemaining) {
        return "Hola, te faltan " + daysRemaining + " días para completar tu meta: " + goalName + ". ¡Sigue esforzándote!";
    }


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

}
