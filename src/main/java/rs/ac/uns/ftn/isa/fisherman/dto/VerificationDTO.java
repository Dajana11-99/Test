package rs.ac.uns.ftn.isa.fisherman.dto;

public class VerificationDTO {
    private String activationCode;
    private String email;

    public VerificationDTO(){}

    public VerificationDTO(String activationCode, String email) {
        this.activationCode = activationCode;
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
