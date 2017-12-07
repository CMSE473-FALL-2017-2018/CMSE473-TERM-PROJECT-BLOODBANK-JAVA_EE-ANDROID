package com.emu.bloodbank;

import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emu.bloodbank.model.Seek;
import com.emu.bloodbank.model.User;
import com.emu.bloodbank.service.SeekService;
import com.emu.bloodbank.service.UserService;

@Controller
@RequestMapping(value="/api")
public class BloodBankAPI {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SeekService seekService;
	
	@ResponseBody 
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User loginREST(@FormParam("email") String email, @FormParam("password") String password){
		return userService.checkLogin(email, password);
	}
	
	@ResponseBody
	@RequestMapping(value="/searchDonor", method=RequestMethod.POST)
	public List<User> searchDonorREST(@FormParam("city") String city, @FormParam("type") String type){
		return userService.searchDonor(city, type);
	}
	
	@ResponseBody
	@RequestMapping(value="/searchSeeker", method=RequestMethod.POST)
	public List<Seek> searchSeekerREST(@FormParam("city") String city, @FormParam("type") String type){
		return seekService.searchSeeker(city, type);
	}
	
	@ResponseBody
	@RequestMapping(value="/changeStatus", method=RequestMethod.POST)
	public void changeStatusREST(@FormParam("email") String email, @FormParam("donor") String donor, @FormParam("password") String password) {
		userService.changeUserStatus(email, donor, password);
	}
	
	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void userServiceREST(@RequestBody User user) {
		userService.updateUser(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/history/{email}/", method=RequestMethod.GET)
	public List<Seek> seekListREST(@PathVariable("email") String email){
		return seekService.getSeeks(email);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteHistory/{id}", method=RequestMethod.DELETE)
	public void deleteDeviceREST(@PathVariable("id") int id){
		seekService.deleteSeek(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public void registerREST(@RequestBody User user) {
		userService.insertUser(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteMe/{userEmail}/", method=RequestMethod.DELETE)
	public void deleteMeREST(@PathVariable("userEmail") String userEmail) {
		seekService.delSeek(userEmail);
		userService.deleteUser(userEmail);
	}
	
	@ResponseBody
	@RequestMapping(value="/allDonors", method=RequestMethod.GET)
	public List<User> allDonorREST() {
		return userService.findDonors();
	}
	
	@ResponseBody
	@RequestMapping(value="/allSeeks", method=RequestMethod.GET)
	public List<Seek> allSeeksREST() {
		return seekService.getAllSeeks();
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSeeker", method=RequestMethod.POST)
	public void seekREST(@RequestBody Seek seek) {
		seek.setSeekDate(new Date());
		seekService.insertSeek(seek);
	}
	
}
	
