package enotes.controller;

import enotes.dto.user.UserDto;
import enotes.entity.user.User;
import enotes.entity.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private static final String REGISTRATION_PAGE = "registration";

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserDto());
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String addUser(UserDto user, Model model) {
        if (userService.getByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("user", user);
            model.addAttribute("isExisting", true);
            return REGISTRATION_PAGE;
        }

        User userEntity = new User();
        user.setPassword(user.getDecryptedPassword());
        BeanUtils.copyProperties(user, userEntity, "id", "registration", "role", "version");
        userService.defaultSave(userEntity);
        return "redirect:/login";
    }
}
