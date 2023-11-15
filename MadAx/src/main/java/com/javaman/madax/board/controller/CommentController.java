package com.javaman.madax.board.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javaman.madax.board.model.dto.Comment;
import com.javaman.madax.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
	
	
	private final CommentService service;
	
	
	
	/**특정 게시글의 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping(value = "comment", produces = "application/json")
	public List<Comment> select(int boardNo){
		
		
		return service.select(boardNo);
	}
	
	
	/**댓글 등록
	 * @param comment : 커맨드 객체
	 * @return
	 */
	@PostMapping("comment")
	public int Insert(@RequestBody Comment comment) {
		
		
		return service.Insert(comment);
	}
	
	
	
	/**댓글 수정
	 * @param comment
	 * @return result
	 */
	@PutMapping("comment")
	public int update(@RequestBody Comment comment) {
		
		return service.update(comment);
	}
	
	
	
	/**댓글 삭제
	 * @param commentNo
	 * @return result
	 */
	@DeleteMapping("comment")
	public int delete(@RequestBody int commentNo) {
		
		return service.delete(commentNo);
	}
	
	
	
	
	
	
	
	

}




