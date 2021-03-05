package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Contact;
import com.example.demo.service.ContactService;

@RestController
@RequestMapping("Contact")
public class ContactController {

	@Autowired ContactService contactService;
	
	
	/**
	* Method to get contact of a user
	*
	* @param  id
	* @return List<Contact>
	*/
	@GetMapping(value="/{id}")
	public List<Contact> getContact(@PathVariable Long id) {
		return contactService.getContact(id);
	}
	
	/**
	* Method to add contact for a user
	*
	* @param  contact object
	* @return List<Contact>
	*/
	
	@PostMapping
	public List<Contact> postContact(@RequestBody Contact contact) {
		return contactService.postContact(contact);
	}
	
	/**
	* Method to delete contact of a user
	*
	* @param  id
	* @return List<Contact>
	*/
	@DeleteMapping(value="/{id}")
	public List<Contact> delectContact(@PathVariable Long id) {
		return contactService.deleteContact(id);
	}
	
	/**
	* Method to update contact of a user
	*
	* @param  contact object
	* @return List<Contact>
	*/
	@PutMapping
	public List<Contact> putContact(@RequestBody Contact contact) {
		return contactService.putContact(contact);
	}
}
