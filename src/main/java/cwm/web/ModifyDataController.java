package cwm.web;

import cwm.Recipe;
import cwm.User;
import cwm.data.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/modify")
public class ModifyDataController {

    private RecipeRepository repo;

    @Autowired
    public ModifyDataController(RecipeRepository repo)
    {
        this.repo=repo;
    }

    @GetMapping("/{recipeId}")
    public String updateData(@PathVariable("recipeId") long id, Model model){
        Recipe recipe=repo.findById(id).get();
        model.addAttribute("recipe",recipe);
        return "update-recipe";
    }
    @GetMapping("/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") long id, Model model){
        Recipe recipe=repo.findById(id).get();
        repo.delete(recipe);
        model.addAttribute("recipe",repo.findAll());
        return "redirect:/content/display";
    }

    @PostMapping("/update/{recipeId}")
    public String processUpdateRecipe(@PathVariable("recipeId") long id, @Valid @ModelAttribute("recipe") Recipe recipe, Errors errors){
        if(errors.hasErrors())
            return "update-recipe";
        Recipe newRecipe=repo.findById(id).get();
        newRecipe.setCategories(recipe.getCategories());
        newRecipe.setName(recipe.getName());
        newRecipe.setIngredients(recipe.getIngredients());
        newRecipe.setDs(recipe.getDs());
        newRecipe.setTime(recipe.getTime());
        newRecipe.setCal(recipe.getCal());
        repo.save(newRecipe);
        log.info("Processing...."+ newRecipe);
        return "redirect:/content/display";
    }



}
