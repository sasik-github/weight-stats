package sasik.stats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sasik.stats.mapper.UserMapper;
import sasik.stats.repositories.UserRepository;
import sasik.stats.vo.UserRegistrationRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public RegistrationController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationRequest());
        return "users/registration";
    }

    @PostMapping
    public String processRegistrationForm(@ModelAttribute("user") @Valid UserRegistrationRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return "users/registration";
        }

        userRepository.save(userMapper.requestToEntity(request));
        return "redirect:/login";
    }
}
