package org.big18.contact.service;


import jakarta.servlet.http.HttpSession;
import org.big18.contact.dao.UserDao;
import org.big18.contact.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    @Autowired
    UserDao dao;

    //	로그인 처리 메소드
    public String login(String userid, String userpw
            , HttpSession session) {

//		id 존재 여부 메소드 호출
        int idCheck = dao.getIdCheck(userid);

        if (idCheck == 0) {
            return "NO ID";
        }

        // id, 비번이 일치하는지 확인하는 메소드 호출
        int pwCheck = dao.getIdPassCheck(userid, userpw);
        if (pwCheck == 0) {
            return "NO PW";
        }

//		참일 경우 세션에 저장

        session.setMaxInactiveInterval(3600);

        String username = dao.getName(userid);
        session.setAttribute("user_id", userid);
        session.setAttribute("user_name", username);
//		로그인 상태 세션에 저장
        session.setAttribute("isLogin", "true");

        return "OK";
    } // end Login()

    //	회원가입 메소드
    public String signUp(String userid, String userpw
            , String username, String email) {
        UserDto dto = new UserDto();
//		id 중복여부 체크
        int idcheck = dao.getIdCheck(userid);
//		중복이면 "ID 중복" 리턴
        if (idcheck == 1) {
            return "IN USE";
        }

//		중복이 아니면 DB에 추가(회원가입)
        try {
            dto.setUser_id(userid);
            dto.setUser_pw(userpw);
            dto.setUser_name(username);
            dto.setEmail(email);
            dao.addUser(dto);
//			예외 발생시 에러 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

        return "OK";
    } // end Signup()
}


