package rs.ac.uns.ftn.isa.fisherman.dto;


public class OwnersReportDto {
    private Long id;
    private boolean success;
    private boolean badComment;
    private String comment;
    private boolean approved;
    private String ownersUsername;
    private String clientUsername;

    public OwnersReportDto() {
    }

    public OwnersReportDto(Long id,boolean badComment, String comment,boolean approved,String ownersUsername,String clientUsername,boolean success) {
        this.id=id;
        this.badComment = badComment;
        this.comment = comment;
        this.success=success;
        this.approved=approved;
        this.ownersUsername=ownersUsername;
        this.clientUsername=clientUsername;
    }

    public String getOwnersUsername() {
        return ownersUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnersUsername(String ownersUsername) {
        this.ownersUsername = ownersUsername;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isBadComment() {
        return badComment;
    }

    public void setBadComment(boolean badComment) {
        this.badComment = badComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
