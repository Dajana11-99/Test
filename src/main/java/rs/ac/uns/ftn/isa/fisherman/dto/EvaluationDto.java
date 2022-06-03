package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class EvaluationDto {
    protected Long id;
    protected String type;
    protected LocalDateTime date;
    protected String comment;
    protected Double grade;
    protected boolean approved;
    protected String clientUsername;
    protected String ownersUsername;

    public EvaluationDto(Long id, String type, LocalDateTime date, String comment, Double grade, boolean approved, String clientUsername,String ownersUsername) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.comment = comment;
        this.grade = grade;
        this.approved = approved;
        this.clientUsername = clientUsername;
        this.ownersUsername= ownersUsername;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EvaluationDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getOwnersUsername() {
        return ownersUsername;
    }

    public void setOwnersUsername(String ownersUsername) {
        this.ownersUsername = ownersUsername;
    }
}
