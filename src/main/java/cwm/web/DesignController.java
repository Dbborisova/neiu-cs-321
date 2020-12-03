package cwm.web;


import cwm.Recipe;
import cwm.User;
import cwm.data.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/design")

public class DesignController {


    private final RecipeRepository repo;

    @Autowired
    public DesignController(RecipeRepository repo)
    {
        this.repo=repo;
    }

    @GetMapping
    public String showDesignForm(Model model, @AuthenticationPrincipal User user) {
            addUserInfoToModel(model,user);
            model.addAttribute("recipe", new Recipe());
        return "design";
    }
    private void addUserInfoToModel(Model model, User user){
        model.addAttribute("fullName",user.getFullName());
    }


    @PostMapping
    public String processDesign(@Valid @ModelAttribute("recipe") Recipe recipe, Errors errors){
        if(errors.hasErrors())
            return "design";
     repo.save(recipe);
     log.info("Processing..."+recipe);
        return "redirect:/content/display";
    }

    @ModelAttribute(name="recipe")
    public Recipe addCBToModel()
    {
        return new Recipe();
    }




}
