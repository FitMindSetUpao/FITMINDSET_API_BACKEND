package Grupo05.FitMindSet.dto.request;

public class NotificationPreferencesDTO {
    private String email;
    private String frequency;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
