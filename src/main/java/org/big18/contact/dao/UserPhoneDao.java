package org.big18.contact.dao;

import org.big18.contact.dto.ContactUserCon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserPhoneDao {
    ContactUserCon joinUser;

    private final JdbcTemplate;

    public UserPhoneDao(JdbcTemplate jdbcTemplate) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select  ");
    }
}
