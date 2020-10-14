package cwm.web;

import cwm.Category;
import cwm.data.AFormRepository;
import cwm.data.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("display")

public class TableController {


    @Autowired
    private CategoryRepository repo;


    @GetMapping
    public String displayTable()
    {
        return "display";
    }

    @ModelAttribute
    public void addAttributes(Model model)
    {
        model.addAttribute("categories", repo.findAll());

    }









}