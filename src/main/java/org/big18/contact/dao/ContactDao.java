package org.big18.contact.dao;


import org.big18.contact.dto.ContactDto;
import org.big18.contact.dto.GubunDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ContactDao {
    private final JdbcTemplate jdbcTemplate;

    public ContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //	연락처 추가 메소드
    public void addContact(ContactDto dto) throws Exception {
        // 연락처 테이블 추가 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Phonebook3 VALUES						");
        sb.append("((SELECT nvl(max(P_ID),0)+1 FROM phonebook3)	");
        sb.append(", ?, ?, ?, ?	");

        String sql = sb.toString();

        // 추가 쿼리 실행
        try {
            jdbcTemplate.update(sql, dto.getName(), dto.getPhone_num()
                    , dto.getAddress(), dto.getGubun_cd());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * end addContact()
     */

    //	전체 목록 출력 메소드
    public ArrayList<ContactDto> getAllContact(ArrayList<String> idlist) throws Exception {
        ArrayList<ContactDto> conlist = new ArrayList<>();
        List<ContactDto> list;

        StringBuilder sb = new StringBuilder();

        // select 쿼리 생성
        sb.append("SELECT p.P_ID 				");
        sb.append("     , p.NAME 					");
        sb.append("	    , p.PHONE_NUM 					");
        sb.append("     , p.ADDRESS  				");
        sb.append("     , g.GUBUN_NM  				");
        sb.append("  FROM Phonebook3 p, 				");
        sb.append("		  GUBUN g 					");
        sb.append("	WHERE p.GUBUN_cD = g.GUBUN_cd		");
        sb.append("   AND p.P_ID = ?			");

        String sql = sb.toString();

        try {
            // select 쿼리 실행
            for (int i = 0; i < idlist.size(); i++) {
                // 연락처 시퀀스 추출
                String p_id = idlist.get(i);
                // 쿼리 실행 및 리스트에 추가
                list = jdbcTemplate.query(sql,
                        new RowMapper<ContactDto>() {
                            @Override
                            public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                                ContactDto dto = new ContactDto();
                                dto.setP_id(rs.getString("p_id"));
                                dto.setName(rs.getString("name"));
                                dto.setPhone_num(rs.getString("phone_num"));
                                dto.setAddress(rs.getString("address"));
                                dto.setGubun_nm(rs.getString("gubun_nm"));
                                return dto;
                            }
                        }, p_id);
                conlist.addAll(list);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return conlist;
    }

    /**
     * end getAllContact()
     */

    //	한명 연락처 출력 메소드
    public ContactDto getOneContact(String p_id) throws Exception {
        ContactDto dto = new ContactDto();
        StringBuilder sb = new StringBuilder();

        // select 쿼리 생성
        sb.append("SELECT p.P_ID				");
        sb.append("		, p.NAME					");
        sb.append("     , p.PHONE_num				");
        sb.append("     , p.ADDRESS					");
        sb.append("     , p.GUBUN_cd  				");
        sb.append("     , g.GUBUN_NM				");
        sb.append("  FROM phonebook3 p,				");
        sb.append("       GUBUNS g					");
        sb.append(" WHERE p.GUBUN_cd = g.GUBUN_cd		");
        sb.append("   AND p.P_ID = ?			");

        String sql = sb.toString();

        try {
            // select 쿼리 실행
            dto = jdbcTemplate.queryForObject(sql,
                    new RowMapper<ContactDto>() {
                        @Override
                        public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                            ContactDto dto = new ContactDto();
                            dto.setP_id(rs.getString("p_id"));
                            dto.setName(rs.getString("name"));
                            dto.setPhone_num(rs.getString("phone"));
                            dto.setAddress(rs.getString("address"));
                            dto.setGubun_cd(rs.getString("gubun_cd"));
                            dto.setGubun_nm(rs.getString("gubun_nm"));
                            return dto;
                        }
                    }, p_id);

            return dto;
        } catch (Exception e) {
            e.getMessage();
            return dto;
        }
    }

    /**
     * end getOneContact()
     */

    //	연락처 수정 메소드
    public void updateContact(ContactDto dto) throws Exception {
        // 연락처 테이블 수정 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE Phonebook3				");
        sb.append("   SET NAME = ?				");
        sb.append("     , PHONE = ?				");
        sb.append("     , ADDRESS = ?			");
        sb.append("		, GUBUN_cd = ?			");
        sb.append("WHERE P_ID = ?			");

        String sql = sb.toString();

        // 수정 쿼리 실행
        try {
            jdbcTemplate.update(sql, dto.getName(), dto.getPhone_num()
                    , dto.getAddress(), dto.getGubun_cd(), dto.getP_id());
        } catch (Exception e) {
            e.getMessage();
        }
    } // end editContact()

    //	연락처 삭제 메소드
    public void delContact(String p_id) throws Exception {
        // 연락처 테이블 삭제 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM Phonebook3 	");
        sb.append(" WHERE P_ID = ? 		");

        String sql = sb.toString();

        // 삭제 쿼리 실행
        try {
            jdbcTemplate.update(sql, p_id);
        } catch (Exception e) {
            e.getMessage();
        }
    } // end delContact()


    //	구분명 => 구분코드 읽는 메소드
    public String getGubunCd(String gubun_nm) throws Exception {
        String gubun_cd = "";

//		select 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT gubun_cd 			");
        sb.append("  FROM GUBUN 			");
        sb.append(" WHERE GUBUN_NM = ? 	    ");

        String sql = sb.toString();

//		쿼리 실행
        try {
            gubun_cd = jdbcTemplate.queryForObject(sql, String.class, gubun_nm);
        } catch (Exception e) {
            e.getMessage();
        }

        return gubun_cd;
    }

    /**
     * 구분 end
     */
    public ArrayList<GubunDto> getAllGubun() throws Exception {
        ArrayList<GubunDto> gubunlist = new ArrayList<>();
        List<GubunDto> list;

//		select 쿼리 생성
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM GUBUN		");

        String sql = sb.toString();

//		select 쿼리 실행
        try {
            list = jdbcTemplate.query(sql,
                    new RowMapper<GubunDto>() {
                        @Override
                        public GubunDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                            GubunDto dto = new GubunDto();
                            dto.setGubun_cd(rs.getString("gubun_cd"));
                            dto.setGubun_nm(rs.getString("gubun_nm"));
                            return dto;
                        }
                    });
            gubunlist.addAll(list);
        } catch (Exception e) {
            e.getMessage();
            gubunlist.clear();
        }
        return gubunlist;
    }
}

