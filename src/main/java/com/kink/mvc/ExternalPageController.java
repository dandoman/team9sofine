package com.kink.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExternalPageController {

	@RequestMapping("/index.html")
	public String index(Model model) {
		String name = "Daveeee";
		model.addAttribute("name", name);
		return "index";
	}
}
