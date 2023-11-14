package com.javaman.madax.board.model.exception;

public class BoardUpdateException extends Exception {
	
	
	public BoardUpdateException() {
		super("게시글 수정 중 예외발생");
	}
	
	public BoardUpdateException(String message) {
		super(message);
	}
}
