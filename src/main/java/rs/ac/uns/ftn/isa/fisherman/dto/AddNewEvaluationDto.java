package rs.ac.uns.ftn.isa.fisherman.dto;

public class AddNewEvaluationDto {
    private Long reservationId;
    private String commentForTheEntity;
    private Double gradeForTheEntity;
    private String commentForTheEntityOwner;
    private Double gradeForTheEntityOwner;
    private String clientUsername;
    private boolean quickReservation;

    public AddNewEvaluationDto(Long reservationId, String commentForTheEntity, Double gradeForTheEntity, String commentForTheEntityOwner, Double gradeForTheEntityOwner, String clientUsername, boolean quickReservation) {
        this.reservationId = reservationId;
        this.commentForTheEntity = commentForTheEntity;
        this.gradeForTheEntity = gradeForTheEntity;
        this.commentForTheEntityOwner = commentForTheEntityOwner;
        this.gradeForTheEntityOwner = gradeForTheEntityOwner;
        this.clientUsername = clientUsername;
        this.quickReservation = quickReservation;
    }

    public boolean isQuickReservation() {
        return quickReservation;
    }

    public void setQuickReservation(boolean quickReservation) {
        this.quickReservation = quickReservation;
    }

    public AddNewEvaluationDto(){}

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCommentForTheEntity() {
        return commentForTheEntity;
    }

    public void setCommentForTheEntity(String commentForTheEntity) {
        this.commentForTheEntity = commentForTheEntity;
    }

    public Double getGradeForTheEntity() {
        return gradeForTheEntity;
    }

    public void setGradeForTheEntity(Double gradeForTheEntity) {
        this.gradeForTheEntity = gradeForTheEntity;
    }

    public String getCommentForTheEntityOwner() {
        return commentForTheEntityOwner;
    }

    public void setCommentForTheEntityOwner(String commentForTheEntityOwner) {
        this.commentForTheEntityOwner = commentForTheEntityOwner;
    }

    public Double getGradeForTheEntityOwner() {
        return gradeForTheEntityOwner;
    }

    public void setGradeForTheEntityOwner(Double gradeForTheEntityOwner) {
        this.gradeForTheEntityOwner = gradeForTheEntityOwner;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
