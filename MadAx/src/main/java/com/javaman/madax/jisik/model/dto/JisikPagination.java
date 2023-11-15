package com.javaman.madax.jisik.model.dto;

public class JisikPagination {
	
	private int currentPage;      // 현재 페이지 번호
	private int listCount;         // 전체 게시글 수

	private int limit = 10;         // 한 페이지 목록에 보여지는 게시글 수
	private int pageSize = 10;      // 보여질 페이지 번호 개수

	private int maxPage;         // 마지막 페이지 번호
	private int startPage;         // 보여지는 맨 앞 페이지 번호 
	private int endPage;         // 보여지는 맨 뒤 페이지 번호

	private int prevPage;         // 이전 페이지 모음의 마지막 번호 
	private int nextPage;         // 다음 페이지 모음의 시작 번호 
	
	public JisikPagination(int currentPage, int listCount) {
		this.currentPage = currentPage;
		this.listCount = listCount;
		
		calcJisikPagination(); // 객체 생성 시 계산 수행해서 모든 필드 초기화
		
	}
	
	public JisikPagination(int currentPage, int listCount, int limit, int pageSize) {
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pageSize = pageSize;
		
		calcJisikPagination();
		
	}
		
	public int getCurrentPage() {
		return currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public int getLimit() {
		return limit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}
	
	
	/* listCount, currentPage, limit, pageSize 변경 시 다시 계산 필요!!! */

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		calcJisikPagination();
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
		calcJisikPagination();
	}

	public void setLimit(int limit) {
		this.limit = limit;
		calcJisikPagination();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calcJisikPagination();
	}

	
	
	
	@Override
	public String toString() {
		return "JisikPagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}

	/** 필드에 들어갈 값 메서드
	 * 
	 */
	
	private void calcJisikPagination() {
		
		// maxPage : 마지막 페이지 번호 == 총 페이지 수
		// 한 페이지에 게시글이 10개씩 보여질 경우 
		// 전체 게시글 수 : 100개 -> 페이지 : 10페이지
		
		// ceil 올림, 
		maxPage = (int)Math.ceil( (double)listCount/ limit );
		
		// startPage : 페이지 번호 목록에서 제일 앞 숫자
		
		// 페이지 번호 목록이 10개씩 보여질 경우
		// 현재 페이지 1~10 -> startPage = 1
		// 현재 페이지 11~20 -> startPage = 11
		// 현재 페이지 20~30 -> startPage = 21
		
		startPage = (currentPage - 1) / pageSize * pageSize + 1;
		
		// endPage : 페이지 번호 목록에서 제일 마지막 숫자
		
				// 페이지 번호 목록이 10개씩 보여질 경우
				// 현재 페이지 1~10 -> endPage =  10
				// 현재 페이지 11~20 -> endPage = 20
				// 현재 페이지 20~30 -> endPage = 30
		
		endPage = startPage + pageSize - 1;
		
		// 전체 페이지 수 : 6;
		// startPage = 1;
		// endPage = 10;
		
		if(endPage > maxPage) endPage = maxPage;
		
	    // prevPage : 이전 페이지 모음의 마지막 번호 
	    // nextPage : 다음 페이지 모음의 시작 번호
		
	    if(currentPage <= pageSize)   prevPage = 1;
	    else
	         prevPage = startPage - 1;
	      
	    if(endPage == maxPage) nextPage = maxPage;
	    else               nextPage = endPage + 1;
	}
	
	
}
