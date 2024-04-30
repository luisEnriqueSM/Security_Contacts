package com.training.security.contacts.service;

import com.training.security.contacts.exception.ContactNotFoundException;
import com.training.security.contacts.pojo.Contact;
import com.training.security.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact getContactById(String id) {
        return contactRepository.getContact(findIndexById(id));
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteContact(findIndexById(id)); 
    }


    private int findIndexById(String id) {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ContactNotFoundException(id));
    }

}
