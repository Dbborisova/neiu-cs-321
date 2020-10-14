package cwm.web;

import cwm.AForm;
import cwm.Category;
import cwm.CB;
import cwm.data.CBRepository;
import cwm.data.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("aForm")
public class DesignController {

    private final CategoryRepository categoryRepo;
    private final CBRepository cbRepo;

    @Autowired
    public DesignController(CategoryRepository categoryRepo, CBRepository cbRepo)
    {
        this.categoryRepo=categoryRepo;
        this.cbRepo=cbRepo;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    @PostMapping
    public String processDesign(@Valid @ModelAttribute("cb") CB cb, Errors errors, Model model){
        if(errors.hasErrors())
            return "design";

        CB savedCB=cbRepo.save(cb);
        AForm aForm=(AForm) model.getAttribute("aForm");
        aForm.addDesign(savedCB);

        log.info("Processing..."+cb);
        return "redirect:/new/current";
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        List<Category> categories = (List<Category>) categoryRepo.findAll();
        Category.Type[] types = Category.Type.values();
        for (Category.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(categories, type));
        }
    }
    @ModelAttribute(name="cb")
    public CB addCBToModel(){
        return new CB();
    }
    @ModelAttribute(name="aForm")
    public AForm addAFormToModel(){
        return new AForm();
    }


    private List<Category> filterByType(List<Category> categories, Category.Type type) {
        return categories
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
