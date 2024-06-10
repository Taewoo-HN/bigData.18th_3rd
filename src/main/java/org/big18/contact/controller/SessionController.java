package org.big18.contact.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class SessionController {

    public void checkSession(HttpSession session, Model model) {
        if (session.getAttribute("user_id") == null) {
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "/login");
            return;
        }
        session.setAttribute("user_name", session.getAttribute("user_name"));
    }

}
