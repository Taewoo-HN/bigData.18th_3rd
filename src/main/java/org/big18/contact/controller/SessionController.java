package org.big18.contact.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class SessionController {

    public void checkSession(HttpSession session, Model model) {
        if (session.getAttribute("user_id") == null) {
        }

    }

}
