
package cwm.web;

//import cwm.Category;
//import cwm.data.AFormRepository;
import cwm.Recipe;
import cwm.data.RecipeRepository;
//import cwm.data.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("display")

public class TableController {


    private final RecipeRepository repo;

    public TableController(RecipeRepository repo) {
        this.repo = repo;
    }


    @GetMapping
    public String displayTable() {
        return "display";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Recipe> recipes = (List<Recipe>) repo.findAll();
        model.addAttribute("recipe", recipes);

    }
}










