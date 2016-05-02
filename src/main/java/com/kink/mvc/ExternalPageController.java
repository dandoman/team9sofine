package com.kink.mvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kink.entity.KinksterEntity;
import com.kink.logic.KinkLogic;

@Controller
@Log4j
public class ExternalPageController {

	@Autowired
	private KinkLogic logic;
	
	@RequestMapping("/index.html")
	public String index(@CookieValue(value = "user-id", required = false) String userId, Model model) {
		if(userId == null) {
			return "redirect:creation";
		}
		
		try {
			KinksterEntity kinksterById = logic.getKinkDao().getKinksterById(userId);
			model.addAttribute("name", kinksterById.getNickname());
			return "index";
		} catch (Exception e){
			return "redirect:creation";
		}
	}
	
	@RequestMapping("/creation")
	public String creation(Model model) {
		return "creation";
	}
}
