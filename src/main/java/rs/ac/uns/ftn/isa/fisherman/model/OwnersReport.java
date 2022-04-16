package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
@Entity
@Table(name="ownerReports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class OwnersReport {
    @Id
    @SequenceGenerator(name = "ownerReports_sequence_generator", sequenceName = "ownerReports_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ownerReports_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;
    @Column(name="badComment")
    private boolean badComment;
    @Column(name="comment")
    private String comment;
    @Column(name= "approved")
    protected boolean approved;
    @Column(name= "owners_username")
    protected String ownersUsername;
    @Column(name= "clientUsername")
    protected String clientUsername;

    public OwnersReport() {

    }

    public OwnersReport(Long id,boolean badComment, String comment,String ownersUsername,String clientUsername,boolean approved) {
        this.id=id;
        this.badComment = badComment;
        this.comment = comment;
        this.approved=approved;
        this.ownersUsername=ownersUsername;
        this.clientUsername=clientUsername;
    }

    public String getOwnersUsername() {
        return ownersUsername;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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
