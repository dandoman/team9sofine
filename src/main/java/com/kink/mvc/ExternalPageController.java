package com.kink.mvc;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kink.entity.KinksterEntity;
import com.kink.logic.KinkLogic;
import com.kink.view.KinkWithLevelView;

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
	
	@RequestMapping("/create-kink")
	public String createKink(Model model) {
		return "kinkCreation";
	}
	
	@RequestMapping("/ack-kink")
	public String ackKink(@CookieValue(value = "user-id", required = false) String userId, @RequestParam(value = "page", required = false) Integer page, Model model) {
		page = (page == null) ? 0 : page;
		if(userId == null) {
			return "redirect:creation";
		}
		KinksterEntity kinksterById = null;
		try {
			kinksterById = logic.getKinkDao().getKinksterById(userId);
		} catch (Exception e) {
			return "redirect:creation";
		}
		
		List<KinkWithLevelView> pageOfKinksByKinkster = logic.getKinkDao().getPageOfKinksByKinkster(page, kinksterById.getId());
		
		Integer previousPage = (page == 0) ? 0 : page - 1;
		
		model.addAttribute("name", kinksterById.getNickname());
		model.addAttribute("groupId", kinksterById.getGroupId());
		model.addAttribute("kinkWithLevelViews", pageOfKinksByKinkster);
		model.addAttribute("pageNo", page);
		model.addAttribute("nextPage", page + 1);
		model.addAttribute("previousPage", previousPage);
		return "kinkAck";
	}
}
