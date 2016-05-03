package com.kink.api;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kink.dao.KinkDao;
import com.kink.request.CreateKinkRequest;
import com.kink.view.KinkView;
import com.kink.view.KinkWithLevelView;

@Controller
@Log4j
@RequestMapping("/api/kink")
public class KinkAPI {
	
	@Autowired
	@Setter
	private KinkDao kinkDao;
	
	@ApiOperation(value = "createKinks")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public KinkView createKink(@RequestBody CreateKinkRequest r){
		r.validate();
		log.info("Create kink request: " + r);
		return kinkDao.createKink(r.getCategory(), r.getName(), r.getDescription());
	}
	
	@ApiOperation(value = "getKinks")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<KinkView> viewKinks(@RequestParam(value = "page", required = false) Integer page){
		int pageNo = (page == null) ? 0 : page;
		return kinkDao.getPageOfKinks(pageNo).stream().map(KinkView::fromEntity).collect(Collectors.toList());
	}
	
	@ApiOperation(value = "getKinksByKinkster")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<KinkWithLevelView> viewKinksByKinkster(@PathVariable("id") String id, @RequestParam(value = "page", required = false) Integer page){
		int pageNo = (page == null) ? 0 : page;
		return kinkDao.getPageOfKinksByKinkster(pageNo,id);
	}
}
