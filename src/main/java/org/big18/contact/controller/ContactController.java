package org.big18.contact.controller;

import jakarta.servlet.http.HttpSession;
import org.big18.contact.dao.ContactDao;
import org.big18.contact.dao.UserPhoneDao;
import org.big18.contact.dto.ContactDto;
import org.big18.contact.dto.GubunDto;
import org.big18.contact.service.ContactService;
import org.big18.contact.service.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
    public class ContactController {
    @Autowired
    ContactDao cdao;
    @Autowired
    UserPhoneDao updao;
    @Autowired
    ContactService service;
    @Autowired
    SessionController scontroller;

    //	메인 페이지 메소드
    @GetMapping("/main")
    public String index(HttpSession session, Model model) {
//		연락처 목록을 가져올 리스트
        ArrayList<ContactDto> list = new ArrayList<>();
//		세션 확인하고 로그인 안 되어 있으면 로그인 페이지로
        if (session.getAttribute("isLogin") == null) {
            model = scontroller.notLoginError(model);
            return "message";
        }

        try {
//			상단에 적어줄 유저명과 ID 모델에 저장
            model = scontroller.getUserInfo(session, model);
            String userid = (String) model.getAttribute("user_id");
//			연락처 리스트 가져오기
            list = service.getAllContact(userid);
            model.addAttribute("contactList", list);
        } catch (Exception e) {
            e.printStackTrace();
//			에러 발생시 에러 메시지 출력 => 로그아웃
            model.addAttribute("title", "연락처 읽어오기 실패!");
            model.addAttribute("message", "연결에 문제가 발생했습니다. 자동 로그아웃합니다.");
            model.addAttribute("icon", "error");
            model.addAttribute("searchUrl", "/logout");
            return "message";
        }
        return "main";
    }



    /** 추가 페이지 띄워주는 메소드 */
        @GetMapping("/insert")
        public String insertPage(HttpSession session, Model model) {
//		선택 박스에 넣어줄 그룹 테이블 정보를 읽어오는 리스트
            ArrayList<GubunDto> gubunlist = new ArrayList<>();
//		세션 확인하고 로그인 안 되어 있으면 로그인 페이지로
            if(session.getAttribute("isLogin") == null) {
                model = scontroller.notLoginError(model);
                return "message";
            }
            try {
//			상단에 적어줄 유저명과 ID 모델에 저장
                model = scontroller.getUserInfo(session, model);
//			그룹 테이블 전체 목록 읽어서 모델에 실어주기
                gubunlist = cdao.getAllGubun();
                model.addAttribute("gubunsList", gubunlist);
            } catch (Exception e) {
                e.printStackTrace();
//			에러 발생시 에러 메시지 출력 => 메인 페이지로
                model.addAttribute("title", "구분 정보 읽어오기 실패!");
                model.addAttribute("message", "메인 페이지로 돌아갑니다.");
                model.addAttribute("icon", "error");
                model.addAttribute("searchUrl", "/error");
                return "message";
            }

            return "add";
        } // end insertPage()

        //	추가 처리 메소드
        @PostMapping("/insertproc")
        public String addContact(@ModelAttribute ContactDto dto, HttpSession session, Model model) {
//		세션 확인하고 로그인 안 되어 있으면 로그인 페이지로
            if(session.getAttribute("isLogin") == null) {
                model = scontroller.notLoginError(model);
                return "message";
            }

            try {
//			추가 서비스 메소드 실행
                String userid = (String)session.getAttribute("user_id");
                service.addContact(userid, dto);
//			추가 성공시 성공 메시지 띄우고 메인 페이지로
                model.addAttribute("title", "Congratulation! 연락처 추가 성공!");
                model.addAttribute("message", "메인 페이지로 돌아갑니다.");
                model.addAttribute("icon", "success");
                model.addAttribute("searchUrl", "/main");
                return "message";
            } catch (Exception e) {
                e.printStackTrace();
//			추가 실패할 때는 다시 입력받기
                model.addAttribute("title", "ERROR: 연락처 추가 실패");
                model.addAttribute("message", "추가에 실패했습니다. 다시 시도해 주세요.");
                model.addAttribute("icon", "error");
                model.addAttribute("searchUrl", "redirect:/insert");
                return "message";
            }
        } // end addContact();

        //	수정 페이지 띄우는 메소드
        @GetMapping("/edit/{p_id}")
        public String editPage(@PathVariable String p_id, HttpSession session, Model model) {
//		선택 박스에 넣어줄 그룹 테이블 정보를 읽어오는 리스트
            ArrayList<GubunDto> gubunlist = new ArrayList<>();
//		세션 확인하고 로그인 안 되어 있으면 로그인 페이지로
            if(session.getAttribute("isLogin") == null) {
                model = scontroller.notLoginError(model);
                return "message";
            }

            try {
//			상단에 적어줄 유저명과 ID 모델에 저장
                model = scontroller.getUserInfo(session, model);

//			수정 페이지의 각 칸을 채워줄 초기 정보 dto 가져오기
                ContactDto dto = service.getOneContact(p_id);
                dto.setP_id(p_id);
                model.addAttribute("contact", dto);

//			그룹 테이블 전체 목록 읽어서 모델에 실어주기
                gubunlist = cdao.getAllGubun();
                model.addAttribute("gubunList", gubunlist);
                return "edit"; // forwarding
            } catch (Exception e) {
                e.printStackTrace();
//			에러 발생시 에러 메시지 출력 => 메인 페이지로
                model.addAttribute("message", "에러: 유저 정보 로딩 실패!");
                model.addAttribute("message", "메인 페이지로 돌아갑니다.");
                model.addAttribute("icon", "error");
                model.addAttribute("searchUrl", "/main");
                return "message";
            }
        } // end editPage()

        //	수정 처리 메소드
        @PostMapping("/update")
        public String updateContact(@ModelAttribute ContactDto dto , HttpSession session, Model model) {
            try {
                //		연락처 수정 메소드 호출
                service.updateContact(dto);
                //		수정 성공시 성공 메시지 띄우고 메인 페이지로
                model.addAttribute("title", "연락처 수정 성공!");
                model.addAttribute("message", "메인 페이지로 돌아갑니다.");
                model.addAttribute("icon", "success");
                model.addAttribute("searchUrl", "/main");
                return "message";
            } catch (Exception e) {
                e.printStackTrace();
//			수정 실패할 때는 에러 메시지 띄우고 메인으로
                model.addAttribute("message", "에러: 연락처 수정에 실패했습니다");
                model.addAttribute("message", "메인 페이지로 돌아갑니다.");
                model.addAttribute("icon", "error");
                model.addAttribute("searchUrl", "/main");
                return "message";
            }
        } // end updateContact()

        //	삭제 처리 메소드
        @GetMapping("/delete/{p_id}")
        public String delContact(@PathVariable String p_id, Model model) {

//		삭제 메소드 실행
            String status = service.delContact(p_id);

//		삭제 성공시 성공 메시지 띄우고 메인 페이지로
            if(status.equals("complete")){
                model.addAttribute("title", "연락처가 삭제되었습니다!");
                model.addAttribute("message", "메인 페이지로 돌아갑니다.");
                model.addAttribute("icon", "success");
                model.addAttribute("searchUrl", "/main");
                return "message";
//		삭제 실패할 때는 실패 메시지 띄우고 메인으로
            } else {
                model.addAttribute("title", "에러: 연락처 삭제 오류!");
                model.addAttribute("message", "삭제 실패했습니다. 다시 한번 부탁드립니다.");
                model.addAttribute("icon", "error");
                model.addAttribute("searchUrl", "/main");
                return "message";
            }
        } // end delContact()
    }
