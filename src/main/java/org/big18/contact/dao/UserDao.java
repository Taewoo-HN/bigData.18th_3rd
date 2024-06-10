package org.big18.contact.dao;

import org.big18.contact.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
    //	스프링 JDBC 사용을 위한 빈 등록
    private JdbcTemplate jdbcTemplate;

    public void addUser(UserDto dto) throws Exception {
        // 추가 쿼리 생성
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO USER_info VALUES		");
        sb.append("(?, ?, ?, ?)			");

        String sql = sb.toString();

        // 추가 쿼리 실행
        try {
            jdbcTemplate.update(sql, dto.getUser_id(), dto.getUser_pw(), dto.getUser_name(), dto.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** end addUser() */

    //	ID 존재 여부 체크 메소드
    public int getIdCheck(String userid) {
        StringBuilder sb = new StringBuilder();

//		id 존재 여부 조회
        sb.append("SELECT USER_ID		");
        sb.append("  FROM USER_INFO 	");
        sb.append(" WHERE USER_ID IN ?	");

        String sql = sb.toString();
        String checkid = "";
        try {
            checkid = jdbcTemplate.queryForObject(sql, String.class, userid);
        } catch (Exception e) {
//			결과가 없으면 빈칸 그대로.
            checkid = "";
        }

//		id가 존재하면 리턴 1
        if(checkid.equals(userid)) {
            return 1;
//		존재하지 않으면 리턴 0
        } else {
            return 0;
        }
    } // end getIdCheck()

    //	ID-비번 일치여부 체크 메소드
    public int getIdPassCheck(String userid, String userpw) {
        StringBuilder sb = new StringBuilder();

//		id에 해당하는 비번 조회
        sb.append("SELECT user_pw		");
        sb.append("  FROM user_info			");
        sb.append(" WHERE user_id = ?	");

        String sql = sb.toString();
        String checkpw = "";
        try {
            checkpw = jdbcTemplate.queryForObject(sql, String.class, userid);
        } catch (Exception e) {
            e.printStackTrace();
            checkpw = "";
        }

//		id-pw가 일치하면 리턴 1
        if(checkpw.equals(userpw)) {
            return 1;
//		불일치하면 리턴 0
        } else {
            return 0;
        }
    } // end getIdPassCheck()

    public String getName(String userid) {
        StringBuilder sb = new StringBuilder();
        String user_name = "";

//		id에 해당하는 이름 조회
        sb.append("SELECT user_name		");
        sb.append("  FROM user_info		");
        sb.append(" WHERE user_id = ?	");

        String sql = sb.toString();

        try {
            user_name = jdbcTemplate.queryForObject(sql, String.class, userid);
        } catch (Exception e) {
            e.printStackTrace();
            user_name = "";
        }

        return user_name;
    }
    /** end getName() */
}

