package cwm.data;

import cwm.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {


    List<Recipe> findAll(Pageable pageable);
}
