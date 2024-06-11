package org.big18.contact.controller;

import org.big18.contact.dao.ContactDao;
import org.big18.contact.dao.UserDao;
import org.big18.contact.dto.UserDto;
import org.big18.contact.service.ContactService;
import org.big18.contact.service.SessionController;
import org.big18.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    private final UserDao userDao;
    @Autowired
    private final ContactDao contactDao;


    private final UserService userService;
    private final ContactService contactService;
    private final SessionController sessionController;



    public ContactController(UserDao userDao, ContactDao contactDao, UserService userService, ContactService contactService, SessionController sessionController) {
        this.userDao = userDao;
        this.contactDao = contactDao;
        this.userService = userService;
        this.contactService = contactService;
        this.sessionController = sessionController;
    }

    @PostMapping("/signup")
    public String Signup(@RequestBody UserDto user) {
       userService.signUp(user.getUser_id(), user.getUser_pw(), user.getUser_name(), user.getEmail());
        return "/login";
    }

    @GetMapping("/login")
    public String Firstpage() {
        return "/main";
    }


    @PostMapping("/login")
    public String Login(@ModelAttribute UserDto user) {
     return "login";
    }

    @GetMapping("/error")
    public String Errorpage( ) {
        return "error404.html";
    }
    @PostMapping("/main")
    public String Mainpage() {
        return "main.html";
    }
}
