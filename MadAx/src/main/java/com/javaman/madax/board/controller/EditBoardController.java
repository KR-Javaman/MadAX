package com.javaman.madax.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.service.BoardService;
import com.javaman.madax.board.model.service.EditBoardService;
import com.javaman.madax.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/editBoard")
@SessionAttributes({"loginMember"})
public class EditBoardController {

	private final EditBoardService service;
	
	private final BoardService boardService;
	
	
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
								@SessionAttribute("loginMember") Member loginMember,
								@RequestParam("images") List<MultipartFile> images)throws IllegalStateException, IOException {
		
		
		board.setMemberNo(loginMember.getMemberNo());
		board.setBoardCode(boardCode);
	
		int categoryCode = board.getCategoryCode();
		
	
		int boardNo = service.insertBoard(board,images);
		
		if(categoryCode == 1) {
			if(boardNo > 0 ) {
				ra.addFlashAttribute("message","게시글 작성 성공");
				return String.format("redirect:/board/%d/%d",boardCode,boardNo);
			}else {
				ra.addFlashAttribute("message", "게시글 작성 실패");
				return "redirect:insert"; 
			}
		}else{
			if(boardNo > 0 ) {
				ra.addFlashAttribute("message","게시글 작성 성공");
				return "redirect:/board/1/2/1";
			}else {
				ra.addFlashAttribute("message", "게시글 작성 실패");
				return "redirect:insert"; 
			}
		}
	
	
	}
	
	
	/**게시글 삭제
	 * @param boardCode
	 * @param boardNo
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/delete")
	public String deleteBoard( @PathVariable("boardCode") int boardCode,
								@PathVariable("boardNo")int boardNo, 
								Board board,
								@SessionAttribute(value ="loginMember", required = false) Member loginMember,
								RedirectAttributes ra) {	
		
		
		if(loginMember == null) {
			ra.addFlashAttribute("message" ,"로그인 후 이용해주세요");
			return "redirect:/member/login";
		}
		
		
		Map<String, Integer> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		map.put("memberNo", loginMember.getMemberNo());
		
		
		int result = service.delete(map);
		
		int categoryCode = board.getCategoryCode();
		
		String path = null;
		String message = null;
		
		if(categoryCode == 1) {
			if(result > 0) {
				
				message = "삭제되었습니다";
				path = "redirect:/board/1/1/1";
			
			}else {
			message = "삭제 실패";
			path = "redirect:/";
			
			}
		}else {
			if(result > 0) {
				
				message = "삭제되었습니다";
				path = "redirect:/board/1/2/1";
			
			}else {
			message = "삭제 실패";
			path = "redirect:/";
			
		}
		}
		
		
		ra.addFlashAttribute("message", message);
		
		return path;
			
	}
	
	
	/**게시글 수정화면 전환
	    * @param boardCode
	    * @param boardNo
	    * @param model
	    * @return
	    */
	   @GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	   public String updateBoard(@PathVariable("boardCode")int boardCode,
	                       @PathVariable("boardNo")int boardNo,
	                       Model model) {
	      
	      
	      Map<String, Object> map = new HashMap<>();
	      map.put("boardCode", boardCode);
	      map.put("boardNo", boardNo);
	
	      
	      Board board = boardService.detail(map);
	      model.addAttribute("board",board);
	      
	      return "board/boardUpdate";
	   }
	   
	   
	   /**게시글 수정
	    * @param boardCode
	    * @param boardNo
	    * @param board
	    * @param querystring
	    * @param deleteOrder
	    * @param images
	    * @param ra
	    * @return
	    * @throws IllegalStateException
	    * @throws IOException
	    */
	   @RequestMapping("/{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	   public String updateBoard(@PathVariable("boardCode")int boardCode,
	                     @PathVariable("boardNo")int boardNo,
	                   

	                     Board board,
	                     @RequestParam("images") List<MultipartFile> images,
	                     RedirectAttributes ra) throws IllegalStateException, IOException {
	      
	      
	      board.setBoardCode(boardCode);
	      board.setBoardNo(boardNo);
	      
	     
	      
	     int result = service.updateBoard(board,images);
	     
	  
	    	 if(result > 0) {
	 	         ra.addFlashAttribute("message","게시글 수정 성공");
	 	         return String.format("redirect:/board/%d/%d",boardCode,boardNo);
	 	      }else {
	 	         
	 	         ra.addFlashAttribute("message", "게시글 수정 실패");
	 	         return "redirect:update";
	 	      }
	     
	     
	     
	     }   
	   
	   
	
	
}
