package cwm.web;

//import cwm.AForm;
//import cwm.Category;
import cwm.Recipe;
import cwm.data.RecipeRepository;
//import cwm.data.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")

public class DesignController {

   // private final CategoryRepository categoryRepo;
    private final RecipeRepository cbRepo;

    @Autowired
    public DesignController( RecipeRepository cbRepo)
    {
      // this.categoryRepo=categoryRepo;
        this.cbRepo=cbRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {
       // public void addAttribute(Model model) {
            model.addAttribute("design", new Recipe());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Recipe design, Errors errors){
        if(errors.hasErrors())
            return "design";

     cbRepo.save(design);

        log.info("Processing..."+design);
        return "redirect:/display";
    }

   /* @ModelAttribute
    public void addAttribute(Model model) {
        List<Category> categories = (List<Category>) categoryRepo.findAll();
        Category.Type[] types = Category.Type.values();
        for (Category.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(categories, type));
        }
    }*/

    @ModelAttribute(name="design")
    public Recipe addCBToModel(){
        return new Recipe();
    }

  /* private List<Category> filterByType(List<Category> categories, Category.Type type) {
        return categories
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }*/


}
