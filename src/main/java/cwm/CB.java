package cwm;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
public class CB {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdAt;

    @NotNull(message="Cookbook name is required")
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity=Category.class)
    @NotEmpty(message="You must choose at least one cookbook category")
    private List<Category> categories;

    @PrePersist
    void createdAt(){
        this.createdAt=LocalDateTime.now();
    }




}
