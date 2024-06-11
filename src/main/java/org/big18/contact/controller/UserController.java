package org.big18.contact.controller;

import jakarta.servlet.http.HttpSession;
import org.big18.contact.dao.UserDao;
import org.big18.contact.dto.UserDto;
import org.big18.contact.service.SessionController;
import org.big18.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionController sessionController;

    private List<UserDto> ulist = new ArrayList<>();

//    @GetMapping("/")
//    public String Mainpage(HttpSession session, Model model) {
//        if(sessionController.isLogin(session)) {
//            model.addAttribute("user", userDao.getUser(session.getAttribute("user_id").toString()));
//        }
//    }

}
