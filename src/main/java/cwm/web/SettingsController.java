package cwm.web;

import cwm.User;
import cwm.data.RecipeRepository;
import cwm.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class SettingsController {

    private final UserRepository userRepo;

    public SettingsController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/settings")
    public String displayUserInfo(Model model, @AuthenticationPrincipal User user){
        List<User> users= (List<User>) userRepo.findAll();
        model.addAttribute("user",users);
        return "settings";

    }

}
