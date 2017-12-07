package com.emu.bloodbank;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emu.bloodbank.model.Login;
import com.emu.bloodbank.model.Seek;
import com.emu.bloodbank.model.User;
import com.emu.bloodbank.service.SeekService;
import com.emu.bloodbank.service.UserService;
import com.emu.bloodbank.values.Value;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SeekService seekService;
	
	//run the home.jsp view
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelAndView model) {
		model.setViewName("home");
		logger.info("home.jsp run");
		return "home";
	}
	
	//run the login.jsp view
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.addObject("loginUser", new Login());
		model.setViewName("login");
		logger.info("login.jsp run");
		return model;
	}
	

	//run the register.jsp view and put the necessary values for user to use
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(ModelAndView model) {
		model.addObject("user", new User());
		model.addObject("city", new Value().getCities());
		model.addObject("bloodType", new Value().getBloodTypes());
		model.addObject("birthYear", new Value().getYears());
		model.addObject("donor", new Value().getIsDonor());
		model.addObject("sex", new Value().getSexes());
		model.setViewName("register");
		logger.info("register.jsp run");
		return model;
	}
	
	//run the result.jsp file if the registration successful
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String result(ModelAndView model) {
		model.setViewName("result");
		logger.info("result.jsp run");
		return "result";
	}
	
	//run the error.jsp file if error occurs
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String error(ModelAndView model) {
		model.setViewName("error");
		logger.info("error.jsp run");
		return "error";
	}
	
	//run panel.jsp view if the login successful else redirect to error page
	@RequestMapping(value="/panel", method=RequestMethod.POST)
	public String checkLogin(@Valid @ModelAttribute("loginUser") Login loginUser, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "redirect:/error";
		
		User user = userService.checkLogin(loginUser.getLoginEmail(), loginUser.getLoginPassword());
		
		if(user == null)
			return "redirect:/error";
		else {
			model.addAttribute("user", user); //user accepted, pass user to panel
			AppClass.setUser(user);
			logger.info("panel.jsp run");
			return "panel";
		}
	}
	
	//run the seekResult.jsp view
	@RequestMapping(value="/seekResult", method=RequestMethod.GET)
	public String seekResult(ModelAndView model) {
		model.setViewName("seekResult");
		logger.info("seekResult.jsp run");
		return "seekResult";
	}
	
	//run the seekBlood.jsp view and pass the necessary values to view
	@RequestMapping(value="/seekBlood", method=RequestMethod.GET)
	public ModelAndView seekBlood(ModelAndView model) {
		model.addObject("seek", new Seek());
		model.addObject("city", new Value().getCities());
		model.addObject("bloodType", new Value().getBloodTypes());
		model.setViewName("seekBlood");
		logger.info("seekBlood.jsp run");
		return model;
	}
	
	//run the changeStatus.jsp view and pass the necessary values to view
	@RequestMapping(value= {"/changeStatus"}, method = RequestMethod.GET)
	public ModelAndView changeStatus(ModelAndView model) {
		model.addObject("user", new User());
		model.addObject("isDonor", new Value().getIsDonor());
		model.setViewName("changeStatus");
		logger.info("changeStatus.jsp run");
		return model;
	}
	
	//run the settings.jsp view and pass the necessary values to view
	@RequestMapping(value ="/settings", method = RequestMethod.GET)
	public ModelAndView settings(ModelAndView model) {
		model.addObject("userEmail", AppClass.getUser().getUserEmail());
		model.addObject("user", new User());
		model.addObject("city", new Value().getCities());
		model.addObject("bloodType", new Value().getBloodTypes());
		model.addObject("birthYear", new Value().getYears());
		model.addObject("donor", new Value().getIsDonor());
		model.addObject("sex", new Value().getSexes());
		model.setViewName("settings");
		logger.info("settings.jsp run");
		return model;
	}
	
	//do logout, run the home.jsp file
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(ModelAndView model) {
		model.setViewName("home");
		logger.info("logged out, home.jsp run");
		return "home";
	}
	
	/*** CRUD Operations (Create, Read, Update and Delete) ***/
	
	//insert user
	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public String insertUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors())
			return "redirect:/error";
		
		userService.insertUser(user);
		
		logger.info("Insertion completed!");
		return "redirect:/result";
	}
	
	//insert blood requests of the exists users
	@RequestMapping(value="/insertSeeker", method=RequestMethod.POST)
	public String seek(@Valid @ModelAttribute("seek") Seek seeker, BindingResult bindingResult, ModelMap model) {	
		seeker.setSeekerEmail(AppClass.getUser().getUserEmail());
		seeker.setSeekerName(AppClass.getUser().getUserName());
		seeker.setSeekDate(new Date());
		
		this.seekService.insertSeek(seeker);
	
		logger.info("Seek process completed!");
		return "redirect:/seekResult";
	}
	
	//find a donor (pagination is applied to the list in view)	
	@RequestMapping(value= {"/findDonor", "/findDonor/{page}"}, method=RequestMethod.GET) @SuppressWarnings("unchecked")
	public ModelAndView findDonor(@PathVariable Map<String, String> pathVariablesMap, ModelAndView model, HttpServletRequest request) {
		String page = pathVariablesMap.get("page");
		PagedListHolder<User> pagedList = null;
		
		if(page == null) { //First request, returns first set of the list
			List<User> userList = userService.findDonors();
			pagedList = new PagedListHolder<User>();
			
			pagedList.setSource(userList);
			pagedList.setPageSize(2); //2 person shown per page in the table
			
			request.getSession().setAttribute("userList", pagedList);
		
		}else if("next".equals(page)) { //jumps the next set of the list
			pagedList = (PagedListHolder<User>) request.getSession().getAttribute("userList");
			pagedList.nextPage();
		}
		else if("prev".equals(page)) {//jumps the previous set of the list
			pagedList = (PagedListHolder<User>) request.getSession().getAttribute("userList");
			pagedList.previousPage();
		}
		else {//jumps to clicked specific page 1 2...5...9 10 etc.
			pagedList = (PagedListHolder<User>) request.getSession().getAttribute("userList");
			pagedList.setPage(Integer.parseInt(page));
		}
		
		logger.info("Donors list is shown");

		return new ModelAndView("findDonor");

	}
	
	//see the blood requests (pagination is applied to the list in view)	
	@RequestMapping(value= {"/findSeeker", "/findSeeker/{page}"}, method=RequestMethod.GET) @SuppressWarnings("unchecked")
	public ModelAndView findSeeker(@PathVariable Map<String, String> pathVariablesMap, ModelAndView model, HttpServletRequest request) {
		String page = pathVariablesMap.get("page");
		PagedListHolder<Seek> pagedList = null;
		
		if(page == null) { //First request, returns first set of the list
			List<Seek> seekList = seekService.getAllSeeks();
			pagedList = new PagedListHolder<Seek>();
			
			pagedList.setSource(seekList);
			pagedList.setPageSize(2); //2 person shown per page in the table
			
			request.getSession().setAttribute("seekList", pagedList);
		
		}else if("next".equals(page)) { //jumps the next set of the list
			pagedList = (PagedListHolder<Seek>) request.getSession().getAttribute("seekList");
			pagedList.nextPage();
		}
		else if("prev".equals(page)) {//jumps the previous set of the list
			pagedList = (PagedListHolder<Seek>) request.getSession().getAttribute("seekList");
			pagedList.previousPage();
		}
		else {//jumps to clicked specific page 1 2...5...9 10 etc.
			pagedList = (PagedListHolder<Seek>) request.getSession().getAttribute("seekList");
			pagedList.setPage(Integer.parseInt(page));
		}
		
		logger.info("Blood Request list is shown");
		return new ModelAndView("findSeeker");
	}
		
	//search a donor by city and blood type
	@RequestMapping(value= {"/findDonor/srcDonorResult"}, method=RequestMethod.POST)	
	public String srcDonorResult(@RequestParam("city") String city, @RequestParam("bloodType") String bloodType, Model model) {
		List<User> searchResultList = userService.searchDonor(city, bloodType);
			
		if(searchResultList == null)
			return "redirect:/error";
			
		model.addAttribute("result", searchResultList);
		logger.info("Search result is shown");
		return "searchResult"; 
	}
	
	//search for the blood requests by city and blood type
	@RequestMapping(value= {"/findSeeker/srcSeekerResult"}, method=RequestMethod.POST)	
	public String srcSeekerResult(@RequestParam("city") String city, @RequestParam("bloodType") String bloodType, Model model) {	
		List<Seek> searchResultList = seekService.searchSeeker(city, bloodType);
		
		if(searchResultList == null)
			return "redirect:/error";
		
		model.addAttribute("result", searchResultList);
		logger.info("Search result is shown");
		return "seekerSearchResult"; 
	}
	
	
	//see personal blood requests and delete if need
	@RequestMapping(value= {"/history", "/history/{page}"}, method=RequestMethod.GET) @SuppressWarnings("unchecked")
	public ModelAndView history(@PathVariable Map<String, String> pathVariablesMap, ModelAndView model, HttpServletRequest request) {
		String page = pathVariablesMap.get("page");
		PagedListHolder<Seek> pagedList = null;
		
		if(page == null) { //First request, returns first set of the list
			List<Seek> seekList = seekService.getSeeks(AppClass.getUser().getUserEmail());
			pagedList = new PagedListHolder<Seek>();
			
			pagedList.setSource(seekList);
			pagedList.setPageSize(2); //2 person shown per page in the table
			
			request.getSession().setAttribute("seekList", pagedList);
		
		}else if("next".equals(page)) { //jumps the next set of the list
			pagedList = (PagedListHolder<Seek>) request.getSession().getAttribute("seekList");
			pagedList.nextPage();
		}
		else if("prev".equals(page)) {//jumps the previous set of the list
			pagedList = (PagedListHolder<Seek>) request.getSession().getAttribute("seekList");
			pagedList.previousPage();
		}
		else {//jumps to clicked specific page 1 2...5...9 10 etc.
			pagedList = (PagedListHolder<Seek>) request.getSession().getAttribute("seekList");
			pagedList.setPage(Integer.parseInt(page));
		}
		
		logger.info("Blood Requests log results are shown");
		return new ModelAndView("history");
	}
	
	//update the user info
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, @RequestParam("newpass") String pass, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors())
			return "redirect:/error";
		
		userService.updateUser(user.getUserEmail(), user.getUserPassword(), user.getCity(), pass);
		
		logger.info("Update completed!");
		return "redirect:/result";
	}
	
	//delete personal blood request by id
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		this.seekService.deleteSeek(id);
		logger.info("Delete completed!");
		return "redirect:/history";
	}
	
	//delete user by email, if user deleted blood requests will be deleted also
	@RequestMapping(value="/deleteMe/{userEmail}/", method=RequestMethod.GET)
	public String deleteMe(@PathVariable String userEmail) {
		this.seekService.delSeek(userEmail);
		this.userService.deleteUser(userEmail);
		logger.info("Delete completed!");
		return "redirect:/";
	}
	
	//change user status: is the user available for donation or not
	@RequestMapping(value= {"/changeMyStatus"}, method = RequestMethod.POST)
	public String changeMyStatus(@ModelAttribute("user") User user,  BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors())
			return "redirect:/error";
				
		this.userService.changeUserStatus(user.getUserEmail(), user.getDonor(), user.getUserPassword());
	
		logger.info("Status updated!");
		return "changedStatus";
	}
}
