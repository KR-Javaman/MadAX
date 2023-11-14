package com.javaman.madax.board.model.service;

import java.util.List;

import com.javaman.madax.board.model.dto.Comment;

public interface CommentService {

	/**댓글 목록 조회
	 * @param boardNo
	 * @return commentList
	 */
	List<Comment> select(int boardNo);

	
	
	/**댓글 등록
	 * @param comment
	 * @return result
	 */
	int Insert(Comment comment);


	
	/**댓글 수정
	 * @param comment
	 * @return result
	 */
	int update(Comment comment);
	
	
	

	/**댓글 삭제
	 * @param commentNo
	 * @return result
	 */
	int delete(int commentNo);



	
	

}
