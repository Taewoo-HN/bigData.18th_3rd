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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserDao dao;
    @Autowired
    UserService userservice;
    @Autowired
    SessionController scontroller;

    //	기본 페이지 접속시 메소드
    @GetMapping("/")
    public String Main(HttpSession session, Model model) {
//		세션 확인하고 로그인 되어 있으면 메인 페이지로
        if (session.getAttribute("islogin") != null) {
            model = scontroller.alreadyLogin(model);
            return "message";
//		로그인이 안 되어 있으면 로그인 페이지로
        } else {
            model = scontroller.notLoginError(model);
            return "message";
        }
    }

    //	로그인 페이지 띄우는 메소드
    @GetMapping("/login")
    public String LoginPage(HttpSession session, Model model) {
//		세션 확인하고 로그인 되어 있으면 메인 페이지로
        if (session.getAttribute("islogin") != null) {
            model = scontroller.alreadyLogin(model);
            return "message";
        }
        return "Main";
    }

    //	회원가입 페이지 띄우는 메소드
    @GetMapping("/signup")
    public String SignupPage() {
        return "signup";
    }

    //	로그인 처리 메소드
    @PostMapping("/loginproc")
    public String Login(@ModelAttribute UserDto dto, HttpSession session, Model model) {
//		로그인 여부 상태 체크
        String status = userservice.login(dto.getUser_id(), dto.getUser_pw(), session);

//		에러 메시지에 따른 처리
//		id가 존재하지 않을 떄
        if (status.equals("NO ID")) {
            model.addAttribute("title", "경고: 존재하지 않는 ID입니다.");
            model.addAttribute("message", "다시 로그인해 주세요");
            model.addAttribute("icon", "error");
            model.addAttribute("searchUrl", "/login");
//		id는 있지만 비밀번호가 틀릴 때
        } else if (status.equals("NO PW")) {
            model.addAttribute("title", "경고: 비밀번호가 틀립니다.");
            model.addAttribute("message", "다시 로그인해 주세요.");
            model.addAttribute("icon", "error");
            model.addAttribute("searchUrl", "/login");
//		로그인에 성공했을 때
        } else {
            model.addAttribute("title", "로그인 성공!");
            model.addAttribute("message", "환영합니다.");
            model.addAttribute("icon", "success");
            model.addAttribute("searchUrl", "/index");
        }

//		메시지 페이지로 포워딩
        return "message";
    } // end Login();

    //	회원가입 처리 메소드
    @PostMapping("/signupproc")
    public String signUp(@ModelAttribute UserDto dto, HttpSession session, Model model) {

//		회원가입 가능 여부(ID 중복 여부 등) 체크
//		유효성 검사는 회원가입 html 페이지에서 가입버튼 누르기 전에 전부 시행
        String status = userservice.signUp(dto.getUser_id(), dto.getUser_pw(), dto.getUser_name(), dto.getEmail());

//		에러 메시지에 따른 처리
//		이미 가입된 ID일 때
        if (status.equals("IN USE")) {
            model.addAttribute("title", "경고: 이미 가입된 ID입니다.");
            model.addAttribute("message", "다시 가입해 주세요.");
            model.addAttribute("icon", "error");
            model.addAttribute("searchUrl", "/signup");
//		예외 발생시 : 404 에러로 처리
        } else if (status.equals("ERROR")) {
            model.addAttribute("title", "에러: 404 Not Found");
            model.addAttribute("message", "시스템 에러가 발생했습니다. 다시 시도해 주세요.");
            model.addAttribute("icon", "error");
            model.addAttribute("searchUrl", "redirect:/signup");
//		회원가입에 성공했을 때
        } else {
            model.addAttribute("title", "회원가입에 성공했습니다.");
            model.addAttribute("message", "회원이 되신걸 환영합니다. 로그인해 주세요.");
            model.addAttribute("icon", "success");
            model.addAttribute("searchUrl", "/login");
        }
//		메시지 페이지로 포워딩
        return "message";
    }

    //	로그아웃 메소드
    @GetMapping("/logout")
    public String logoutProc(HttpSession session, Model model) {
        // 세션 제거
        session.removeAttribute("isLogin");
        session.removeAttribute("userid");
        session.removeAttribute("username");

//		로그아웃 메시지 띄우고 로그인 페이지로 보내기
        model.addAttribute("title", "로그아웃이 완료되었습니다.");
        model.addAttribute("message", "안녕히 가십시오.");
        model.addAttribute("icon", "success");
        model.addAttribute("searchUrl", "/login");

        return "message";
    }
}

