package org.big18.contact.controller;

import org.big18.contact.dao.UserDao;
import org.big18.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;


}
