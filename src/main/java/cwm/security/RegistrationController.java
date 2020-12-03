package cwm.security;


import cwm.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }


    @ModelAttribute(name="registrationForm")
    public RegistrationForm addRegistrationFormToModel(){
        return new RegistrationForm();
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, Errors errors){
        if (errors.hasErrors())
            return "/registration";

        try {
            userRepo.save(registrationForm.toUser(passwordEncoder));
        }catch(DataIntegrityViolationException e){
            errors.rejectValue("username", "invalidUsername", "Username not available. Please choose another username");
        return "/registration";
        }
        return "redirect:/login";
    }


}
