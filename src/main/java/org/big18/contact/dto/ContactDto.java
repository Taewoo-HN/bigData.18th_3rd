package org.big18.contact.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactDto {
    private String p_id;
    private String name;
    private String phone_num;
    private String address;
    private String gubun_cd;
    private String gubun_nm;
}
