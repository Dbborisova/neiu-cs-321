package cwm.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="recipe.book")
@Data
public class RecipeProperties {

    private int pageSize=20;
}
