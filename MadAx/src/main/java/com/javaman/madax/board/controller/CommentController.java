package com.javaman.madax.board.controller;

import org.springframework.web.bind.annotation.RestController;

import com.javaman.madax.board.model.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService service;

}
