package sasik.stats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sasik.stats.vo.LoginRequest;

@Controller
public class LoginController
{
    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("user", new LoginRequest());
        return "users/login";
    }

}
