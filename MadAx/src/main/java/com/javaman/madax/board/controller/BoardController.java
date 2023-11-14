package com.javaman.madax.board.controller;


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


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController{
	
private final BoardService service;
	
	
	/**게시글 조회
	 * @param boardCode
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoard(@PathVariable("boardCode") int boardCode,
								Model model,
								@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
			
		Map<String, Object> map = service.selectBoard(boardCode, cp);
		
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
						HttpServletRequest req, HttpServletResponse resp) {
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		Board board = service.detail(map);
		
		String path = null;
		
		if(board != null) { //게시글이 있을 때
			model.addAttribute("board",board);
			
			path = "board/boardDetail";
			
			
			if(loginMember != null) {
				
				map.put("memberNo", loginMember.getMemberNo());
				int likeCheck = service.likeCheck(map);
				
				
				if(likeCheck == 1) {
					model.addAttribute("likeCheck" , "on");
				}
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


