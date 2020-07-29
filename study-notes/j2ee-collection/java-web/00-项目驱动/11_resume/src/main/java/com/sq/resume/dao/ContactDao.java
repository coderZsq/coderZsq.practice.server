package com.sq.resume.dao;

import com.sq.resume.bean.Contact;
import com.sq.resume.bean.ContactListParam;
import com.sq.resume.bean.ContactListResult;

public interface ContactDao extends BaseDao<Contact> {
    ContactListResult list(ContactListParam param);
}
