package collector.gimme;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	
	private Logger logger = Logger.getLogger(AppController.class);
	
	@RequestMapping("/pics")
	@ResponseBody
	public void gather(HttpServletRequest request){
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headerName = headerNames.nextElement();
			String val = request.getHeader(headerName);
			logger.info(String.format("Header (%s,%s)", headerName, val));
		}
		logger.info("Remote Host: " + request.getRemoteHost());
		logger.info("Remote Address: " + request.getRemoteAddr());
		logger.info("Remote Port: " + request.getRemotePort());
	}
}
