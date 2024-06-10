package org.big18.contact.service;

import org.big18.contact.dao.ContactDao;
import org.big18.contact.dao.UserPhoneDao;
import org.big18.contact.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ContactService {

@Autowired
    private ContactDao cdao;
    @Autowired
    private UserPhoneDao ucdao;

    public void addContact(String userid, ContactDto dto) {
        try {
//			연락처 테이블에 추가
            cdao.addContact(dto);
//			유저-연락처 관계 테이블에 추가
            ucdao.insertOne(userid, dto.getP_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    } // end addContact()


    //	전체 목록 출력 메소드
    public ArrayList<ContactDto> getAllContact(String userid)
            throws Exception {
        ArrayList<String> idlist = new ArrayList<>();
        ArrayList<ContactDto> conlist = new ArrayList<>();

        try {
//			해당 유저가 추가한 연락처 목록 idlist 받아오기
            idlist = ucdao.getJoin(userid);
//			idlist에 해당하는 연락처 정보 conlist 받아오기
            conlist = cdao.getAllContact(idlist);
        } catch (Exception e) {
//			예외 발생시는 컨트롤러로 throw해서 에러 처리
            e.printStackTrace();
        }
//		conlist를 전송
        return conlist;
    } // end getAllContact()

    //	연락처 하나 가져오는 메소드
    public ContactDto getOneContact(String personid){
        ContactDto dto = new ContactDto();

//		입력받은 personid에 해당하는 원본 연락처 호출
        try {
            dto = cdao.getOneContact(personid);
//		예외 발생시 바로 컨트롤러로 throw해서 에러메시지 띄움
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    } // end getOneContact();

    //	연락처 수정 메소드
    public void updateContact(ContactDto dto) throws Exception {
        try {
//			실제 연락처 수정
            cdao.updateContact(dto);
        } catch (Exception e) {
//			예외 발생시에는 컨트롤러로 던져서 처리
            e.printStackTrace();
        }
    } // end editContact()

    //	연락처 삭제 메소드
    public String delContact(String personid) {
        try {
//		유저-연락처 관계 테이블에서 삭제
            ucdao.deleteOne(personid);
//		연락처 테이블에서 삭제
            cdao.delContact(personid);
            return "complete";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    } // end delContact()

}

}
