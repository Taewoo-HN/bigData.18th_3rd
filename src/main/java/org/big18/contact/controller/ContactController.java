package org.big18.contact.controller;

import org.big18.contact.dao.ContactDao;
import org.big18.contact.dao.UserDao;
import org.big18.contact.dto.ContactDto;
import org.big18.contact.dto.UserDto;
import org.big18.contact.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController("/api")
public class ContactController {

    private final UserDao userDao;
    private final ContactDao contactDao;
    private final UserService userService;

    public ContactController(UserDao userDao, ContactDao contactDao, UserService userService) {
        this.userDao = userDao;
        this.contactDao = contactDao;
        this.userService = userService;
    }
    @PostMapping("/api/signup")
    public void Signup(@RequestBody UserDto user) {
       userService.signUp(user.getUser_id(), user.getUser_pw(), user.getUser_name(), user.getEmail());
    }

    @GetMapping("/api/login")
    public String Firstpage() {
        return "index.html";
    }

    @GetMapping("/api/main")
    public ContactDto Mainpage() {
        ContactDto contactDto = new ContactDto();
        return contactDto;
    }


}
