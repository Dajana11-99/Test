package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;


@Entity
@Table(name="images")
public class Image {
    @Id
    @SequenceGenerator(name = "image_sequence_generator", sequenceName = "image_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;

    @Column(name="url")
    protected String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Image(){}
    public Image(Long id, String url) {
        this.id = id;
        this.url = url;
    }
}
