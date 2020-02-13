package com.naver.plt.users;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UserController {
	@Autowired
	UserDAO dao;
	
	@RequestMapping("/main")
	public String homePage() {
		return "main";
	}
	
	@RequestMapping("/users")
	public ModelAndView getAllUsers() {
		List<UserVO> list = dao.getAllUser();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		return mv;
	}
	
	@RequestMapping("/mypage")
	public String myPage() {
		return "mypage";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}
	
	// login start
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping( value = "/login" ,method= RequestMethod.POST )
	public ModelAndView login(HttpServletRequest request, UserVO vo) {
			ModelAndView mv = new ModelAndView();
			
			UserVO user = null;
			user = dao.loginUser(vo);
			if(user!=null) {
				
				if(user.getRole().equals("user"))
					mv.setViewName("main_user");
				else 
					mv.setViewName("main_admin");
				
		        HttpSession httpSession = request.getSession(true);
		        httpSession.setAttribute("user", user);
			}
			else {
				mv.setViewName("login");
			}
	
		return mv;
	}
	// login end
	
	// sign up start
	@RequestMapping(value="/join")
	public String join() {
		return "signup";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String suinUp(UserVO vo) {
		UserVO user = null;
		user = dao.getOneUser(vo);
		if(user==null) {
			dao.insertUser(vo);
			return "signup_success";
		}else {
			return "inserterror";
		}
	}
	// sign up end
	
}
