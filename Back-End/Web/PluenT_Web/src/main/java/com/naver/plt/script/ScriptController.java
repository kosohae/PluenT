package com.naver.plt.script;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.plt.users.UserVO;

@Controller
public class ScriptController {
	
	@Autowired
	ScriptDAO dao;
	
	UserVO us;
	
	@RequestMapping("/newscript")
	public String insertScript() {
		return "newscript";
	}
	
	@RequestMapping(value="/newscript" , method= RequestMethod.POST)
	public String insert(HttpSession session ,ScriptVO vo) {
		System.out.println("come");
		us = (UserVO)session.getAttribute("user");
		System.out.println(us.getUser_id());
		vo.setUser_id( us.getUser_id() );
		dao.insertScript(vo);
		return "redirect: /plt/scriptlist";
	}
	
	@RequestMapping(value="/scriptlist")
	public ModelAndView insert(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		us = (UserVO)session.getAttribute("user");
		String user_id = us.getUser_id();
		List<ScriptVO> scriptList = dao.getUserScript(user_id);
		mav.addObject("scriptlist",scriptList);
		mav.setViewName("script_list");
		return mav;
	}
	
	@RequestMapping(value="/onescript")
	public ModelAndView getOne(String script_id) {
		ModelAndView mav = new ModelAndView();
		ScriptVO sc = dao.getIdScript(script_id);
		mav.addObject("script",sc);
		mav.setViewName("onescript");
		return mav;
	}
	
	
	
	
}
