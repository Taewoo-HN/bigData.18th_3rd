package org.big18.contact.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
