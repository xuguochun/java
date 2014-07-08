package cn.gyyx.java;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gyyx.java.entity.GameInfo;
import cn.gyyx.java.entity.ServerInfo;
import cn.gyyx.java.persistence.GameDao;
import cn.gyyx.java.persistence.ServerDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private GameDao gameDao;
	private ServerDao serverDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		gameDao = new GameDao();
		List<GameInfo> list = gameDao.queryList();

		model.addAttribute("gameList", list);
					
		return "home";
	}
		
	

	/*
	 * 根据游戏ID获取区服列表
	 */
	@RequestMapping(value = "/getServerList", method = RequestMethod.GET)
	public @ResponseBody List<ServerInfo> getServerList(
			@RequestParam("gameId") int gameId) {
		serverDao = new ServerDao();
		List<ServerInfo> list = serverDao.queryListByGameId(gameId);
		return list;
	}
}
