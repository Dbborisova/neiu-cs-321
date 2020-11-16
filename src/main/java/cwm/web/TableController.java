
package cwm.web;

//import cwm.Category;
//import cwm.data.AFormRepository;
import cwm.Recipe;
import cwm.data.RecipeRepository;
//import cwm.data.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("display")

public class TableController {


    private RecipeRepository repo;

    private RecipeProperties props;
    @Autowired
    public TableController(RecipeRepository repo, RecipeProperties props)
    {
        this.repo=repo;
        this.props=props;

    }


    @GetMapping
    public String displayTable()
    {


        return "display";
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes = repo.findAll(pageable);
        model.addAttribute("recipe", recipes);

    }
}










