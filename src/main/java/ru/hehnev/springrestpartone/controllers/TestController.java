package ru.hehnev.springrestpartone.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hehnev.springrestpartone.model.entitys.User;
import ru.hehnev.springrestpartone.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/auth_page")
    public String authPage() {
        return "auth_page welcome";
    }

    @GetMapping("/user_info")
    public String userInfo(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "this USER name: " + user.getUsername() + ", email: " + user.getEmail();
    }

    @GetMapping("/profile_page")
    public String profilePage() {
        return "profile page";
    }
}
