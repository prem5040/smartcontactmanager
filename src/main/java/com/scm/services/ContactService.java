package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {

    // save contacts
    Contact save(Contact contact);

    //Update Contacts
    Contact update(Contact contact);

    //List all contacts
    List<Contact> getAll();

    //get contact by id
    Contact getById(String id);

    //Delete Contact
    void delete(String id);

    //Search Contact
    List<Contact> search(String name, String email, String phoneNumber);

    //get contact by Userid
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);


}
