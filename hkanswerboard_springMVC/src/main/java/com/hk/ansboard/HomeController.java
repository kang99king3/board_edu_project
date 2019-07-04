package com.hk.ansboard;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.ansboard.dtos.AnsDto;
import com.hk.ansboard.service.IAnsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IAnsService ansService;
	
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/boardlist.do", method = RequestMethod.GET)
	public String boardList(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록보기 {}.", locale);
		
		//조회한 글의 seq값을 삭제한다.("readcount",seq)
		request.getSession().removeAttribute("readcount");
		
		List<AnsDto> list=ansService.getAllList();
		
		model.addAttribute("list", list );
		
		return "boardlist";
	}
	
	@RequestMapping(value = "/insertForm.do", method = RequestMethod.GET)
	public String insertForm(Locale locale, Model model) {
		logger.info("글추가폼이동 {}.", locale);
		
		return "insertBoard";
	}
	
	@RequestMapping(value = "/insertboard.do", method = RequestMethod.POST)
	public String insertBoard(AnsDto dto, Locale locale, Model model) {
		logger.info("글추가하기 {}.", locale);
		boolean isS=ansService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "글추가실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/boarddetail.do", method = RequestMethod.GET)
	public String boardDetail(HttpServletRequest request, Locale locale, Model model, int seq) {
		logger.info("글상세보기 {}.", locale);
		
//		HttpSession session=request.getSession();
//		session.setAttribute(arg0, arg1);
		
		String rSeq=(String)request.getSession().getAttribute("readcount");
		if(rSeq==null) {
			ansService.readCount(seq);
			//조회수 올렸으면, 세션에 담고
			request.getSession().setAttribute("readcount", seq+"");
		}
	
		AnsDto dto=ansService.getBoard(seq);
		model.addAttribute("dto", dto );
		return "boarddetail";
	}
	
	@RequestMapping(value = "/muldel.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String mulDel(Locale locale, Model model, String[]chk) {
		logger.info("여러글삭제하기 {}.", locale);
		boolean isS=ansService.mulDel(chk);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "여러글삭제실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/updateform.do", method = RequestMethod.GET)
	public String updateForm(Locale locale, Model model, int seq) {
		logger.info("글수정하기폼이동 {}.", locale);
		AnsDto dto=ansService.getBoard(seq);
		model.addAttribute("dto", dto );
		return "boardupdate";
	}
	
	@RequestMapping(value = "/updateboard.do", method = RequestMethod.POST)
	public String updateBoard(AnsDto dto, Locale locale, Model model, int seq) {
		logger.info("글수정하기 {}.", locale);
		boolean isS=ansService.updateBoard(dto);
		if(isS) {
			return "redirect:boarddetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg", "글수정하기 실패");
			return "error";	
		}
	}
	
	@RequestMapping(value = "/replyboard.do", method = RequestMethod.POST)
	public String replyBoard(AnsDto dto, Locale locale, Model model, int seq) {
		logger.info("답글추가하기 {}.", locale);
		boolean isS=ansService.replyBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "답글추가하기 실패");
			return "error";	
		}
	}
	
	//웹페이지에 출력하는 프린터: PrintWriter pw=response.getWriter(), pw.print("<p>a</p>")
	//boardlist.jsp로 "{'dto':{'seq':seq,'id':id,'titile':title....}}"
	@ResponseBody
	@RequestMapping(value = "/contentAjax.do", method = RequestMethod.POST)
	public Map<String, AnsDto> contentAjax(HttpServletRequest request, Locale locale, Model model, int seq) {
		logger.info("글내용미리보기 {}.", locale);
		AnsDto dto=ansService.getBoard(seq);
		Map<String, AnsDto>map=new HashMap<String, AnsDto>();
		map.put("dto", dto);
		return map;
	}
}




