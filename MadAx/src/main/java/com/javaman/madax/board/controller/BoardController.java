package com.javaman.madax.board.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.javaman.madax.shorts.model.dto.VideoBoard;
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
	
	
	/**게시글 전체 조회
	 * @param boardCode
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoard(@PathVariable("boardCode") int boardCode,
								Model model,
								@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
								@RequestParam Map<String, Object> paramMap) {
		
		if(paramMap.get("key")==null) {
			
			Map<String, Object> map = service.selectBoard(boardCode, cp);
			model.addAttribute("map",map);
			
		}else {
			Map<String, Object> map = service.searchBoardList(paramMap, cp);
			model.addAttribute("map",map);
		}
			
		
		return "board/boardList";
	}
	
	
	
	/**카테고리 별 조회
	 * @param boardCode
	 * @param categoryCode
	 * @param category2Code
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{categoryCode:[0-9]+}/{categoryCodeTwo:[0-9]+}")
	public String CategoryBoard(@PathVariable("boardCode") int boardCode,
								@PathVariable("categoryCode") int categoryCode,
								@PathVariable("categoryCodeTwo") int categoryCodeTwo,
								Model model,
								@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		
	
		Map<String, Object> map = service.CategoryBoard( boardCode, categoryCode, categoryCodeTwo, cp);
		
		model.addAttribute("map",map);
		
		
		return "board/boardList";
		
	}
	
	
	
	//게시글 상세조회
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String detail(@PathVariable("boardCode")int boardCode,
						@PathVariable("boardNo")int boardNo, 
						Model model,
						RedirectAttributes ra,
						@SessionAttribute(value = "loginMember", required = false) Member loginMember,
						HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		
		
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
	        if (loginMember == null || 
	              loginMember.getMemberNo() != board.getMemberNo()) {

	           
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
	            
	            

	            // 4) 조회 수 증가 성공 시
	            // 쿠키가 적용되는 경로, 수명(당일 23시 59분 59초) 지정

	            if (result > 0) {
	               board.setBoardCount(board.getBoardCount() + 1);
	               // 조회된 board 조회 수와 DB 조회 수 동기화

	               // 적용 경로 설정
	               c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달

	               // 수명 지정
	               Calendar cal = Calendar.getInstance(); // 싱글톤 패턴
	               cal.add(cal.DATE, 1);  //24시간 후의 시간을 기록

	               // 날짜 표기법 변경 객체 (DB의 TO_CHAR()와 비슷)
	               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	               // java.util.Date
	               Date a = new Date(); // 현재 시간
	               //2023-10-31 2:30:14

	               Date temp = new Date(cal.getTimeInMillis()); // 다음날 (24시간 후)
	               // 2023-11-01 2:30:14

	               Date b = sdf.parse(sdf.format(temp)); // 다음날 0시 0분 0초

	               // 다음날 0시 0분 0초 - 현재 시간
	               long diff = (b.getTime() - a.getTime()) / 1000;
	               // -> 다음날 0시 0분 0초까지 남은 시간을 초단위로 반환

	               c.setMaxAge((int) diff); // 수명 설정

	               resp.addCookie(c); // 응답 객체를 이용해서
	                              // 클라이언트에게 전달
	            }
	            
	            
	         }
	        

			
	         if (board.getImageList().size() > 0) {

	        	 BoardImg thumbnail = null;
	             
	             //썸네일이 존재하면
	             if (board.getImageList().get(0).getImgOrder() == 0) {
	                thumbnail = board.getImageList().get(0);
	             }

	             model.addAttribute("thumbnail", thumbnail);
	             model.addAttribute("start", thumbnail != null ? 1 : 0);
	          }
	          

			
			
		}else { //게시글 없을 경우
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
		
		//paramMap에 로그인 회원 번호만 추가
		map.put("memberNo", loginMember.getMemberNo());
		
		
		//paramMap : {boardNo,memberNo, check}
		return service.like(map);  //-1(실패) / 0이상 (성공)
	}
	
	
	

}


