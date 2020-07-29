package com.sq.resume.service;

import com.sq.resume.bean.Contact;
import com.sq.resume.bean.ContactListParam;
import com.sq.resume.bean.ContactListResult;

public interface ContactService extends BaseService<Contact> {
    ContactListResult list(ContactListParam param);
}
