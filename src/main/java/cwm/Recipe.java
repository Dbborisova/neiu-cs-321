package cwm;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdAt;

    @NotNull(message="Name is required")
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull(message="Description is required")
    @Size(min=5, message="Description must be at least 5 characters long")
    @Column( length=100000 )
    private String ds;

    @NotNull(message = "Time is required")
    private String time;

    @NotNull(message = "Calories is required")
    private Long cal;

    @NotNull(message = "Pick category!")
    private String categories;

    @NotNull(message = "Ingredients are required")
    private String ingredients;


    @ManyToOne
    private User user;

    @PrePersist
    void createdAt(){
        this.createdAt=LocalDateTime.now();
    }


}
