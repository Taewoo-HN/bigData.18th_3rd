package org.big18.contact.dao;

import org.big18.contact.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int UserSignUp(UserDto user) {
        String sql = "insert into user_info values (?,?,?,?)";
        int result = jdbcTemplate.update(sql, user.getUser_id(), user.getUser_pw(), user.getUser_name(), user.getUser_mail());
        return result;
    }

    public int getIdCheck(String userid) {
        StringBuilder sb = new StringBuilder();

//		id 존재 여부 조회
        sb.append("SELECT USER_ID		");
        sb.append("  FROM USER_info 		");
        sb.append(" WHERE USERID IN ?	");

        String sql = sb.toString();
        String checkid = "";
        try {
            checkid = jdbcTemplate.queryForObject(sql, String.class, userid);
        } catch (Exception e) {
//			결과가 없으면 빈칸 그대로.
            checkid = "";
        }

//		id가 존재하면 리턴 1
        if (checkid.equals(userid)) {
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
        sb.append("  FROM USER_info			");
        sb.append(" WHERE userid = ?	");

        String sql = sb.toString();
        String checkpw = "";
        try {
            checkpw = jdbcTemplate.queryForObject(sql, String.class, userid);
        } catch (Exception e) {
            e.getMessage();
            checkpw = "";
        }

//		id-pw가 일치하면 리턴 1
        if (checkpw.equals(userpw)) {
            return 1;
//		불일치하면 리턴 0
        } else {
            return 0;
        }
    } // end getIdPassCheck()

    public String getName(String userid) {
        StringBuilder sb = new StringBuilder();
        String username = "";

//		id에 해당하는 이름 조회
        sb.append("SELECT user_name		");
        sb.append("  FROM USERS			");
        sb.append(" WHERE userid = ?	");

        String sql = sb.toString();

        try {
            username = jdbcTemplate.queryForObject(sql, String.class, userid);
        } catch (Exception e) {
            e.getMessage();
            username = "";
        }

        return username;
    }
}