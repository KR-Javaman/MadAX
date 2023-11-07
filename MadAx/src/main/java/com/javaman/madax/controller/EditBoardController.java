package com.javaman.madax.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.model.dto.Board;
import com.javaman.madax.model.service.EditBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/editBoard")
public class EditBoardController {

	private final EditBoardService service;
	
	
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String insertBoard(@PathVariable("boardCode")int boardCode) {
		
		
		
		return "board/boardWrite";
	}
	
	
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String insertBoard(@PathVariable("boardCode") int boardCode,
								Board board, RedirectAttributes ra) {
		
		
		
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
