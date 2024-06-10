package org.big18.contact.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserPhoneDao {

    private final JdbcTemplate jdbcTemplate;

    public UserPhoneDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertOne(String user_id, String p_id) throws Exception {
        int result = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("insert into user_phone ");
        sb.append("values(?,?)");
        String sql = sb.toString();
        result = jdbcTemplate.update(sql, user_id, p_id);

        return result;
    }

    public int deleteOne(String p_id) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("delete from user_phone ");
        sb.append("where p_id = ?");
        String sql = sb.toString();
        int result = jdbcTemplate.update(sql, p_id);

        return result;
    }

    public int updateOne(String user_id, String p_id) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("update user_phone ");
        sb.append(" SET p_id = ?		");
        sb.append(" where user_id = ?	");
        String sql = sb.toString();
        int result = jdbcTemplate.update(sql, p_id, user_id);

        return result;
    }

    //	특정 유저가 추가한 연락처 목록 불러오는 메소드
    public ArrayList<String> getJoin(String userid) {
        ArrayList<String> idlist = new ArrayList<>();

        // select 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT P_ID 				");
        sb.append("  FROM USERS_Phone	");
        sb.append(" WHERE USER_ID = ?			");
        sb.append(" ORDER BY P_ID			");

        String sql = sb.toString();

        // 쿼리 실행
        try {
            List<String> list = jdbcTemplate.queryForList(sql, String.class, userid);
            idlist.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idlist;
    } // end getJoin()

    //	유저-연락처 관계 추가 메소드
    public void addJoin(String userid) throws Exception {
        StringBuilder sb = new StringBuilder();
        String sql = "";

        try {
//			방금 추가한 사람의 personid 검색 쿼리 생성 및 실행
            sql = "SELECT nvl(max(PERSONID),0) FROM contact";
            String personid = jdbcTemplate.queryForObject(sql, String.class);

//			ucdao.insertOne() 메소드 호출
            insertOne(userid, personid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end addJoin()

    //	유저-연락처 관계 삭제 메소드
    public void delJoin(String personid) throws Exception {
        // 삭제 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM user_phone	");
        sb.append("WHERE P_ID = ?				");

        String sql = sb.toString();

        // 삭제 쿼리 실행
        try {
            jdbcTemplate.update(sql, personid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end delJoin()
}


