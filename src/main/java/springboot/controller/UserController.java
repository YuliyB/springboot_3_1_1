package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.service.UserService;
import springboot.model.User;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printUser(ModelMap modelMap){
        modelMap.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/edit";
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
