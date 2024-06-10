package org.big18.contact.service;

import org.big18.contact.dao.ContactDao;
import org.big18.contact.dao.UserPhoneDao;
import org.big18.contact.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactService {

    @Autowired
    private ContactDao cDao;
    @Autowired
    private UserPhoneDao uDao;

}
