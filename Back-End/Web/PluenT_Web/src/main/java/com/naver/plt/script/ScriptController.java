package com.naver.plt.script;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.plt.api.CssApi;
import com.naver.plt.api.NmtApi;
import com.naver.plt.users.UserVO;

@Controller
public class ScriptController {
	
	@Autowired
	ScriptDAO dao;
	
	UserVO us;
	
	@Autowired
	NmtApi nmtApi;
	
	@Autowired
	CssApi cssApi;
	
	// 번역 함수  > API 사용 필수 
//	@RequestMapping("/translation")
//	public ModelAndView translation(ScriptVO vo) {
//		ModelAndView mav = new ModelAndView();
//		System.out.println(vo.getTitle());
//		S
//		return mav;
//	}
	
	// insert strat
	@RequestMapping("/newscript")
	public String insertScript() {
		return "newscript";
	}
	
	@RequestMapping(value="/newscript" , method= RequestMethod.POST)
	public ModelAndView insert(HttpSession session ,
			ScriptVO vo, String button,
			String origin_lang,
			String target_lang,
			String gender
			) 
	{
		ModelAndView mav = new ModelAndView();
		ScriptVO trans = new ScriptVO();
		if(button.equals("음성파일 불러오기")) {
			trans.setTitle(vo.getTitle());
			trans.setContent(vo.getContent());
			trans.setPttime(vo.getPttime());
			trans.setVoice_path(vo.getVoice_path());
			trans.setScript(vo.getScript());
			mav.addObject("trans",trans);
			mav.setViewName("newscript");
			return mav;
		}
		if(button.equals("번역")) {
			
			
			trans.setTitle(vo.getTitle());
			if(vo.getContent().equals("")) {
				// 내용이 없다면 api 돌리지 않기 
				mav.setViewName("newscript");
				return mav;
			}
			trans.setContent(vo.getContent());
			//nmt(String text_ori,  String origin_lang, String target_lang)
			String script_result = 
			nmtApi.nmt(vo.getContent(), origin_lang, target_lang);
			trans.setScript(script_result); 
			
			String vp = cssApi.css(trans.getScript(), target_lang, gender);
			trans.setVoice_path(vp);
			mav.addObject("trans",trans);
			mav.setViewName("newscript");
		}
		else{
			us = (UserVO)session.getAttribute("user");
			//System.out.println(us.getUser_id());
			//System.out.println("voice: "+vo.getVoice_path());
			vo.setUser_id( us.getUser_id() );
			if(vo.getTitle().equals("")) {
				// 만약 제목이 입력이 안되어있었다면,
				vo.setTitle("제목없음");
			}
			dao.insertScript(vo);
			mav.setViewName("redirect: /plt/scriptlist");
		}
		return mav;
	}
	// insert end
	
	
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
