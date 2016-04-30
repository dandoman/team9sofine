package com.kink.api;

import lombok.extern.log4j.Log4j;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kink.request.KinksterGroupRequest;
import com.kink.response.JoinGroupResponse;
import com.kink.response.KinksterGroupResponse;

@Controller
@Log4j
@RequestMapping("/api/kinkster")
public class KinksterAPI {
	@ApiOperation(value = "createGroup")
	@RequestMapping(value = "/create-group", method = RequestMethod.POST)
	@ResponseBody
	public KinksterGroupResponse createGroup(@RequestBody KinksterGroupRequest r){
		r.validate();
		log.info("Create group request: " + r);
		return null;
	}
	
	@ApiOperation(value = "joinGroup")
	@RequestMapping(value = "/join-group", method = RequestMethod.POST)
	@ResponseBody
	public JoinGroupResponse joinGroup(@RequestBody KinksterGroupRequest r){
		r.validate();
		log.info("Join group request: " + r);
		return null;
	}
	
	
}
