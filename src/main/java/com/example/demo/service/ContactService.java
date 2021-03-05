package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Contact;
import com.example.demo.dao.ContactDao;

@Service
public class ContactService {

	
	@Autowired
	ContactDao contactDao;
	
	/**
	* Method to get contact by id
	* @param  id
	* @return List<Contact>
	*/
	public List<Contact> getContact(Long id){
		return contactDao.findByUserId(id);
	}
	
	/**
	* Method to save a new contact, here returning a list of contact because we can use it to trigger frontend client to rerender
	* Since after deletion store changes
	* @param  contact object
	* @return List<Contact>
	*/
	public List<Contact> postContact(Contact contact){
		contactDao.save(contact);
		return contactDao.findByUserId(contact.getUserId());
	}
	
	/**
	* Method to delete contact by id, here returning a list of contact because we can use it to trigger frontend client to rerender
	* Since after deletion store changes
	* @param  id
	* @return List<Contact>
	*/
	public List<Contact> deleteContact(Long id){
		Contact contact = contactDao.findById(id).get();
		contactDao.deleteById(id);
		return contactDao.findByUserId(contact.getUserId());
	}
	
	/**
	* Method to update contact by id, here returning a list of contact because we can use it to trigger frontend client to rerender
	* Since after deletion store changes
	*/
	public List<Contact> putContact(Contact contact){
		contactDao.save(contact);
		return contactDao.findByUserId(contact.getUserId());
	}
	
	
	
}
