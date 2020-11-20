package cwm.web;

//import cwm.AForm;
//import cwm.Category;
import cwm.Recipe;
import cwm.User;
import cwm.data.RecipeRepository;
//import cwm.data.CategoryRepository;
import cwm.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/design")

public class DesignController {


    private final RecipeRepository cbRepo;

    @Autowired
    public DesignController(RecipeRepository cbRepo)
    {


        this.cbRepo=cbRepo;
    }

    @GetMapping
    public String showDesignForm(Model model, @AuthenticationPrincipal User user) {
            addUserInfoToModel(model,user);
            model.addAttribute("design", new Recipe());
        return "design";
    }
    private void addUserInfoToModel(Model model, User user){
        model.addAttribute("fullName",user.getFullName());
    }


    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Recipe design, Errors errors){
        if(errors.hasErrors())
            return "design";


     cbRepo.save(design);

        log.info("Processing..."+design);
        return "redirect:/display";
    }



    @ModelAttribute(name="design")
    public Recipe addCBToModel(){
        return new Recipe();
    }




}
