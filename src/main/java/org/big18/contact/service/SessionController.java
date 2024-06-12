package org.big18.contact.service;

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

    }//	세션에서 유저 정보 추출해서 모델에 실어주는 클래스

    public Model getUserInfo(HttpSession session, Model model) {
//		상단에 적어줄 유저명과 ID 모델에 저장
        String user_id = (String) session.getAttribute("user_id");
        String user_name = (String) session.getAttribute("user_name");

        model.addAttribute("user_name", user_name);
        model.addAttribute("user_id", user_id);

        return model;
    } // end getUserInfo()

    //	이미 로그인되어 있을 때 메시지를 모델에 실어주는 클래스
    public Model alreadyLogin(Model model) {
        model.addAttribute("title", "이미 로그인되어 있습니다.");
        model.addAttribute("message", "메인 페이지로 이동합니다.");
        model.addAttribute("icon", "info");
        model.addAttribute("searchUrl", "/main");
        return model;
    }

    //	로그인 안 되어 있을 때 에러 메시지를 모델에 실어주는 클래스
    public Model notLoginError(Model model) {
        model.addAttribute("title", "로그인되어 있지 않습니다.");
        model.addAttribute("message", "로그인해 주세요. 로그인 페이지로 이동합니다.");
        model.addAttribute("icon", "error");
        model.addAttribute("searchUrl", "/login");
        return model;
    }

}

