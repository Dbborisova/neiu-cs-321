package cwm;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Author_Form")
public class AForm implements Serializable {

    private static final long serialVersionUID=1L;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime placedAt;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Pattern(regexp = "[0-9]{5}",message = "Zip code must be 5 digits")
    private String zip;

    @ManyToMany(targetEntity = CB.class)
    private List<CB> cbs=new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(CB design){ this.cbs.add(design);}

    @PrePersist
    void placeAt(){
        this.placedAt=LocalDateTime.now();
    }
}
