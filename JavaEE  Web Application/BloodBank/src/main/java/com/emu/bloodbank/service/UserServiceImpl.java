package com.emu.bloodbank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emu.bloodbank.dao.UserDAO;
import com.emu.bloodbank.model.User;

@Service @Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override @Transactional
	public void insertUser(User user) {
		this.userDAO.insertUser(user);
	}

	@Override @Transactional
	public void deleteUser(String email) {
		this.userDAO.deleteUser(email);
	}

	@Override @Transactional
	public void updateUser(User user) {
		this.userDAO.updateUser(user);
		
	}

	@Override @Transactional
	public User getUser(String email) {
		return this.userDAO.getUser(email);
	}

	@Override @Transactional
	public List<User> getAllUsers() {
		return this.userDAO.getAllUsers();
	}

	@Override @Transactional
	public User checkLogin(String email, String password) {
		return this.userDAO.checkLogin(email, password);
	}

	@Override @Transactional
	public List<User> findDonors() {
		// TODO Auto-generated method stub
		return this.userDAO.findDonors();
	}

	@Override @Transactional
	public List<User> searchDonor(String city, String bloodType) {
		return this.userDAO.searchDonor(city, bloodType);
	}

	@Override @Transactional
	public void changeUserStatus(String email, String donor, String password) {
		this.userDAO.changeUserStatus(email, donor, password);
	}

	@Override @Transactional
	public void updateUser(String email, String password, String city, String newPassword) {
		this.userDAO.updateUser(email, password, city, newPassword);	
	}

}
