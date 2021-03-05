package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Contact;


public interface ContactDao extends JpaRepository<Contact, Long>{

	
	List<Contact> findByUserId(Long id);
}
