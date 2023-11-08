package com.javaman.madax.board.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.service.EditBoardService;
import com.javaman.madax.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/editBoard")
@SessionAttributes({"loginMember"})
public class EditBoardController {

	private final EditBoardService service;
	
	
	/**게시글 작성화면으로 전환
	 * @param boardCode
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String insertBoard(@PathVariable("boardCode")int boardCode) {
		
		return "board/boardWrite";
	}
	
	
	
	
	
	
	/**게시글 작성
	 * @param boardCode
	 * @param board
	 * @param ra
	 * @return
	 */
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String insertBoard(@PathVariable("boardCode") int boardCode,
								Board board, RedirectAttributes ra,
								@SessionAttribute("loginMember") Member loginMember) {
		
		
		board.setMemberNo(loginMember.getMemberNo());
		board.setBoardCode(boardCode);
		
		int boardNo = service.insertBoard(board);
		
		if(boardNo > 0) {
			ra.addFlashAttribute("message", "게시글 작성 성공");
			return "redirect:board/boardList";
		}
		
		else {
			ra.addFlashAttribute("message", "게시글 작성 실패");
			return "redirect:boardWrite";
		}
		
		
		
	}
	
}
