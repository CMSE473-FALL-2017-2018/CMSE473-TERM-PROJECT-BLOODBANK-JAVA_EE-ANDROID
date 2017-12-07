package com.emu.bloodbank.dao;

import java.util.List;

import com.emu.bloodbank.model.User;

public interface UserDAO {
	public void insertUser(User user);
	public void deleteUser(String email);
	public void updateUser(User user);
	public User getUser(String email);
	public List<User> getAllUsers();
	public User checkLogin(String email, String password);
	public List<User> findDonors();
	public List<User> searchDonor(String city, String bloodType);	
	public void changeUserStatus(String email, String donor, String password);
	public void updateUser(String email, String password, String city, String newPassword);
	
}
