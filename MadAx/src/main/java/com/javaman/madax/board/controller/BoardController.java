package com.javaman.madax.board.controller;


import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.service.BoardService;
import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.board.model.dto.BoardImg;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController{
	
private final BoardService service;
	

	/**게시글 전제 조회
	 * @param boardCode
	 * @param model
	 * @param cp
	 * @param paramMap
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{categoryCode:[0-9]+}/{categoryCodeTwo:[0-9]+}")
	public String selectBoard(@PathVariable("boardCode") int boardCode,
								@PathVariable("categoryCode")int categoryCode,
								@PathVariable("categoryCodeTwo")int categoryCodeTwo,
								@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
								@RequestParam Map<String, Object> paramMap, Model model,
								@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		
		
		if(paramMap.get("key")==null && paramMap.get("query") == null) { //검색이 아닐 때 일반 조회
			Map<String, Integer> codeMap = new HashMap<>();
			codeMap.put("boardCode", boardCode);
			codeMap.put("categoryCode", categoryCode);
			codeMap.put("categoryCodeTwo", categoryCodeTwo);
			Map<String, Object> map = service.selectBoard(codeMap, cp);
			model.addAttribute("map",map);
		}else { //검색일 경우
			paramMap.put("boardCode", boardCode);
			Map<String, Object> map = service.searchBoardList(paramMap, cp);
			model.addAttribute("map",map);
		}
		if(categoryCode == 1){
			return "board/boardList";
		}else {
			return "board/recruitment";
		}
	}
	
	//게시글 상세조회
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String detail(@PathVariable("boardCode")int boardCode,
						 @PathVariable("boardNo")int boardNo, 
						 @SessionAttribute(value = "loginMember", required = false) Member loginMember,
						 RedirectAttributes ra,
						 HttpServletRequest req, HttpServletResponse resp, Model model) throws ParseException {
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardCode", boardCode);	
		map.put("boardNo", boardNo);
		
		Board board = service.detail(map);
		
		String path = null;
		
		if(board != null) {  //게시글이 존재할 경우
			model.addAttribute("board",board);
			path = "board/boardDetail";
			
			if(loginMember != null) {
				map.put("memberNo", loginMember.getMemberNo());
				int likeCheck = service.likeCheck(map);
				
				if(likeCheck == 1) {
					model.addAttribute("likeCheck" , "on");
				}
			}
			//조회수
	        if (loginMember == null || loginMember.getMemberNo() != board.getMemberNo()) {
	        	Cookie c = null;
	            Cookie[] cookies = req.getCookies();
	            
	            if (cookies != null) { 

	            	for (Cookie cookie : cookies) {
	            		
		                  if (cookie.getName().equals("readBoardNo")) {
		                     c = cookie;
		                     break;
		                  }
	            	}
	            }
	            int result = 0;
	            if (c == null) {
	            	c = new Cookie("readBoardNo", "|" + boardNo + "|");
	            	result = service.updateBoardCount(boardNo);
	            } else {

	               if (c.getValue().indexOf("|" + boardNo + "|") == -1) {
	            	   c.setValue(c.getValue() + "|" + boardNo + "|");
	            	   result = service.updateBoardCount(boardNo);
	               }
	            }
	            if (result > 0) {
	               board.setBoardCount(board.getBoardCount() + 1);
	               c.setPath("/"); 

	               Calendar cal = Calendar.getInstance();
	               cal.add(cal.DATE, 1);  

	               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	               Date a = new Date(); 
	               Date temp = new Date(cal.getTimeInMillis()); 
	               Date b = sdf.parse(sdf.format(temp)); 
	               long diff = (b.getTime() - a.getTime()) / 1000;
	               c.setMaxAge((int) diff); 
	               resp.addCookie(c); 
	                             
	            }
	         }
	        
	        	if (board.getImageList().size() > 0) {
	        	 BoardImg thumbnail = null;
	             if (board.getImageList().get(0).getImgOrder() == 0) {
	                thumbnail = board.getImageList().get(0);
	             }
	             model.addAttribute("thumbnail", thumbnail);
	             model.addAttribute("start", thumbnail != null ? 1 : 0);
	        	}
	    }else { 
			path = "redirect:/board/" + boardCode;
		}
		return path;
	}
	
	/*좋아요 처리
	 * @param paramMap : boardNo, check(0.1)담긴 맵
	 *  **/
	@PostMapping("like")
	@ResponseBody
	public int like(@RequestBody Map<String, Object> map , @SessionAttribute("loginMember") Member loginMember) {
		
		
		map.put("memberNo", loginMember.getMemberNo());
		return service.like(map);  
	}
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	

}


