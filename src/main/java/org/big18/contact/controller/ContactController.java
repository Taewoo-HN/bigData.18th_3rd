package org.big18.contact.controller;

import org.big18.contact.dao.UserDao;
import org.big18.contact.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    final UserDao userDao;

    public ContactController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String Firstpage() {
        return "index.html";
    }

    @GetMapping("/main")
    public String Mainpage() {
        return "Main.html";
    }

    @RequestMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new UserDto());
        return "/signup";
    }

    @PostMapping("/signup")
    public String HandleSignUp(@ModelAttribute("user") UserDto userDto, BindingResult bs) {
        if (bs.hasErrors()) {
            return "/login";
        }

        UserDto dto = new UserDto();
        dto.setUser_id(userDto.getUser_id());
        dto.setUser_pw(userDto.getUser_pw());
        dto.setUser_name(userDto.getUser_name());
        dto.setUser_mail(userDto.getUser_mail());
        userDao.UserSignUp(dto);

        return "index.html";
    }

}
