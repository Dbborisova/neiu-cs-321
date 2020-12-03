package cwm.web;

import cwm.User;
import cwm.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/update")
public class ModifyUserController {

    private final UserRepository userRepo;
    private PasswordEncoder passwordEncoder;


    public ModifyUserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/user")
  public String updateUser( @AuthenticationPrincipal User user, Model model){
        addUserToModel(model, user);
        model.addAttribute("user",user);
      return "update-user";
   }
    private void addUserToModel(Model model, User user){
        model.addAttribute("id",user.getId());
        model.addAttribute("name",user.getFullName());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("phone",user.getPhone());
        model.addAttribute("password",user.getPassword());
    }

    @PostMapping
    public String processUpdateUser(@AuthenticationPrincipal User userAP, @Valid @ModelAttribute("user") User user,Errors errors){
        if(errors.hasErrors())
            return "update-user";

       userAP.setPassword(user.getPassword());
       userAP.setUsername(user.getUsername());
       userAP.setFullName(user.getFullName());
       userAP.setPhone(user.getPhone());
          userRepo.save(userAP);
        return "redirect:/settings";

    }


}
