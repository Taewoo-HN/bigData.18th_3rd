package org.big18.contact.dao;


import org.big18.contact.dto.ContactDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContactDao {
    private final JdbcTemplate jdbcTemplate;

    public ContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertOne(ContactDto contact) throws Exception {
        int result = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("insert into phonebook3 ");
        sb.append("values(((SELECT nvl(max(P_ID),0)+1 FROM PHONEBOOK3))");
        sb.append(",?,?,?,?)");
        String sql = sb.toString();
        result = jdbcTemplate.update(sql, contact.getName(), contact.getPhone_num(), contact.getAddress(), contact.getGubun_cd());

        return result;
    }

    public List<ContactDto> selectAll() throws Exception {

        List<ContactDto> phoneArray;
        StringBuilder sql = new StringBuilder();
        sql.append("Select p.P_ID, p.NAME, p.PHONE_NUM, p.ADDRESS, g.GUBUN_NM ");
        sql.append("	from phonebook3 p 									  ");
        sql.append("	join user_info ui on ui.user_id = p.user_id 		  ");
        sql.append("	join gubun g on p.gubun_cd = g.gubun_cd				  ");
        String queryString = sql.toString();

        phoneArray = jdbcTemplate.query(queryString, new RowMapper<ContactDto>() {
            public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactDto dto = new ContactDto();
                dto.setP_id(rs.getString("p_id"));
                dto.setName(rs.getString("name"));
                dto.setPhone_num(rs.getString("phone"));
                dto.setAddress(rs.getString("address"));
                dto.setGubun_nm(rs.getString("gubun_nm"));
                return dto;
            }
        });
        return phoneArray;
    }

    public int updateOne(ContactDto contact) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("update phonebook3 p ");
        sb.append(" SET p.NAME = ?		");
        sb.append(" , p.PHONE_NUM = ?	");
        sb.append(" , p.ADDRESS = ?		");
        sb.append(" , p.GUBUN_CD = ?	");
        sb.append(" WHERE p_id = ?		");
        String sql = sb.toString();

        int result = jdbcTemplate.update(sql, contact.getName(), contact.getPhone_num(), contact.getAddress(), contact.getP_id());

        return result;
    }
    public int deleteOne(String pid) throws Exception {
        String sql = "delete from phonebook3 where p_id = ?";
        return jdbcTemplate.update(sql, pid);
    }
}
