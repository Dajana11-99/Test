package com.example.demo.dto;

public class AddNewFishingInstructorEvaluationDto {

    private Long reservationId;
    private String commentForTheFishingInstructor;
    private Double gradeForTheFishingInstructor;
    private String clientUsername;

    public AddNewFishingInstructorEvaluationDto(Long reservationId, String commentForTheFishingInstructor, Double gradeForTheFishingInstructor, String clientUsername) {
        this.reservationId = reservationId;
        this.commentForTheFishingInstructor = commentForTheFishingInstructor;
        this.gradeForTheFishingInstructor = gradeForTheFishingInstructor;
        this.clientUsername = clientUsername;
    }

    public AddNewFishingInstructorEvaluationDto(){}

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCommentForTheFishingInstructor() {
        return commentForTheFishingInstructor;
    }

    public void setCommentForTheFishingInstructor(String commentForTheFishingInstructor) {
        this.commentForTheFishingInstructor = commentForTheFishingInstructor;
    }

    public Double getGradeForTheFishingInstructor() {
        return gradeForTheFishingInstructor;
    }

    public void setGradeForTheFishingInstructor(Double gradeForTheFishingInstructor) {
        this.gradeForTheFishingInstructor = gradeForTheFishingInstructor;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
