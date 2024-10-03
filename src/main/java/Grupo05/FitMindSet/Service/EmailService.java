package Grupo05.FitMindSet.Service;

public interface EmailService {

    public void sendAccountDeletionEmail(String to) {
        String subject = "Cuenta Eliminada";
        String body = "Tu cuenta ha sido eliminada exitosamente. Si no fuiste tú, por favor contáctanos inmediatamente.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }

}
