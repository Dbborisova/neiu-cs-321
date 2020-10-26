package cwm.web;

import cwm.AForm;
import cwm.User;
import cwm.data.AFormRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String recipeForm(Model model, @AuthenticationPrincipal User user){
        addUserInfoForToModel (model, user);
        return "author_form";
    }

    private void addUserInfoForToModel(Model model, User user) {
        model.addAttribute("fullName", user.getFullName());
        //model.addAttribute("phone", user.getPhone());
    }

    @PostMapping
    public String processForm(@Valid @ModelAttribute("aForm") AForm aForm, Errors errors, SessionStatus sessionStatus,
    @AuthenticationPrincipal User user){
        if(errors.hasErrors())
            return "author_form";

        aForm.setUser(user);
        aFormRepo.save(aForm);

            log.info("Form submitted" +aForm);
            sessionStatus.setComplete();
        return "redirect:/";
    }
}
