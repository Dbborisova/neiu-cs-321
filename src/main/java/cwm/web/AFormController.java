package cwm.web;

import cwm.AForm;
import cwm.data.AFormRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/new")
@SessionAttributes("aForm")
public class AFormController {
    private AFormRepository aFormRepo;

    public AFormController(AFormRepository aFormRepo){
        this.aFormRepo=aFormRepo;
    }

    @GetMapping("/current")

    public String recipeForm(){
        return "author_form";
    }

    @PostMapping
    public String processForm(@Valid @ModelAttribute("aForm") AForm aForm, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors())
            return "author_form";

        aFormRepo.save(aForm);

            log.info("Form submitted" +aForm);
            sessionStatus.setComplete();
        return "redirect:/";
    }
}
