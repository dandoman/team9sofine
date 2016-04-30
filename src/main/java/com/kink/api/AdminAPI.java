package com.kink.api;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kink.dao.KinkDao;
import com.kink.request.CreateKinkRequest;
import com.kink.view.KinkView;

@Controller
@Log4j
@RequestMapping("/api/admin")
public class AdminAPI {
	
	@Autowired
	@Setter
	private KinkDao kinkDao;
	
	@ApiOperation(value = "createKinks")
	@RequestMapping(value = "/kinks/add", method = RequestMethod.POST)
	@ResponseBody
	public void createKink(@RequestBody CreateKinkRequest r){
		r.validate();
		log.info("Create kink request: " + r);
		kinkDao.createKink(r.getCategory(), r.getName(), r.getDescription());
	}
	
	@ApiOperation(value = "viewKinks")
	@RequestMapping(value = "/kinks/view")
	@ResponseBody
	public List<KinkView> viewKinks(){
		return kinkDao.getAllKinks().stream().map(KinkView::fromEntity).collect(Collectors.toList());
	}
}
