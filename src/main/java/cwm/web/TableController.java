
package cwm.web;


import cwm.Recipe;
import cwm.User;
import cwm.data.RecipeRepository;
import cwm.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/content")

public class TableController {

    private final UserRepository user;
    private final RecipeRepository repo;

    private final RecipeProperties props;
    @Autowired
    public TableController(RecipeRepository repo, RecipeProperties props, UserRepository user)
    {
        this.user=user;
        this.repo=repo;
        this.props=props;

    }


    @GetMapping("/display")
    public String displayTable(Model model, @AuthenticationPrincipal User user)
    {
        addUserInfoToModel(model,user);
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes = repo.findAll(pageable);
        return "display";
    }

    @GetMapping("/appetizer")
    public String displayApp(Model model, @AuthenticationPrincipal User user)
    {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes= repo.findAll(pageable);
        addUserInfoToModel(model,user);
        model.addAttribute("appetizers", getAllAppetizers(recipes));
        return "appetizer";
    }

    @GetMapping("/soup")
    public String displaySoups(Model model, @AuthenticationPrincipal User user)
    {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes=  repo.findAll(pageable);
        addUserInfoToModel(model,user);
        model.addAttribute("soups", getAllSoups(recipes));
        return "soup";
    }

    @GetMapping("/bread")
    public String displayBread(Model model, @AuthenticationPrincipal User user)
    {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes=  repo.findAll(pageable);
        addUserInfoToModel(model,user);
        model.addAttribute("bread", getAllBread(recipes));
        return "bread";
    }

    @GetMapping("/salad")
    public String displaySalads(Model model, @AuthenticationPrincipal User user)
    {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes=  repo.findAll(pageable);
        addUserInfoToModel(model,user);
        model.addAttribute("salads", getAllSalads(recipes));
        return "salad";
    }

    @GetMapping("/maindish")
    public String displayMainDish(Model model, @AuthenticationPrincipal User user)
    {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes= repo.findAll(pageable);
        addUserInfoToModel(model,user);
        model.addAttribute("mainDishes", getAllMainDish(recipes));
        return "maindish";
    }

    @GetMapping("/dessert")
    public String displayDesserts(Model model, @AuthenticationPrincipal User user)
    {
        Pageable pageable= PageRequest.of(0, props.getPageSize());
        List<Recipe> recipes= repo.findAll(pageable);
        addUserInfoToModel(model,user);
        model.addAttribute("desserts", getAllDesserts(recipes));
        return "dessert";
    }


    private void addUserInfoToModel(Model model, User user){
        model.addAttribute("fullName",user.getFullName());
    }

    public List<Recipe> getAllAppetizers(List<Recipe> recipes) {
        List<Recipe> appetizers = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getCategories().equals("Appetizer"))
                appetizers.add(recipe);
        }
        return appetizers;
    }

    public List<Recipe> getAllSoups(List<Recipe> recipes) {
        List<Recipe> soups = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getCategories().equals("Soup"))
                soups.add(recipe);
        }
        return soups;
    }

    public List<Recipe> getAllBread(List<Recipe> recipes) {
        List<Recipe> bread = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getCategories().equals("Bread"))
                bread.add(recipe);
        }
        return bread;
    }

    public List<Recipe> getAllSalads(List<Recipe> recipes) {
        List<Recipe> salads = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getCategories().equals("Salad"))
                salads.add(recipe);
        }
        return salads;
    }

    public List<Recipe> getAllMainDish(List<Recipe> recipes) {
        List<Recipe> mainDishes = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getCategories().equals("Main-Dish"))
                mainDishes.add(recipe);
        }
        return mainDishes;
    }

    public List<Recipe> getAllDesserts(List<Recipe> recipes) {
        List<Recipe> desserts = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getCategories().equals("Dessert"))
                desserts.add(recipe);
        }
        return desserts;
    }
}










