package rs.ac.uns.ftn.isa.fisherman.dto;

public class SubscriptionDto {

    protected Long id;
    protected String clientUsername;

    public SubscriptionDto(Long id, String clientUsername) {
        this.id = id;
        this.clientUsername = clientUsername;
    }

    public SubscriptionDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
