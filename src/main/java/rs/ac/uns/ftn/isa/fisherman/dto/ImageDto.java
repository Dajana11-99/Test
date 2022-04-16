package rs.ac.uns.ftn.isa.fisherman.dto;

public class ImageDto {
    private Long id;
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public ImageDto(){
    }

    public ImageDto(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
