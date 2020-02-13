package com.naver.plt;

//import java.text.DateFormat;
//import java.util.Date;
import java.util.Locale;

import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static PythonInterpreter interpreter;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		interpreter = new PythonInterpreter();
//        interpreter.exec("from java.lang import System");
//        interpreter.exec("s = 'Hello World'");
//        interpreter.exec("System.out.println(s)");
//        interpreter.exec("print(s)");
//        interpreter.exec("print(s[1:-1])");
		
		return "home";
	}
	
}
