package com.javaman.madax.myPage.model.service;

import java.io.File; 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.Pagination;
import com.javaman.madax.common.utility.Util;
import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.myPage.model.mapper.MyPageMapper;


@Service
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Value("${my.member.webpath}") // 프로필 이미지 수정할 때 써야함
	private String webpath;
	
	@Value("${my.member.location}") // 프로필 이미지 수정
	private String folderPath;
	
	@Value("${my.background.webpath}") // 프로필 이미지 수정할 때 써야함
	private String webpath2;
	
	@Value("${my.background.location}")
	private String folderPath2;
	
	@Value("${my.video.webpath}")
	private String webPath3;
	@Value("${my.video.location}")
	private String folderPath3;
	
	
	@Override
	public Map<String, Object> selectCommunity(int memberNo, int cp) {
		
		
		RowBounds rowBounds = new RowBounds(0, 12);
				
		//게시글 조회
		List<Board> boardList = mapper.selectCommunity(memberNo, rowBounds);
		
		Map<String , Object> map =  new HashMap<>();
		map.put("boardList", boardList);
		
		return map; 
	}
	
	@Override
	public Map<String, Object> selectJisikin(int memberNo, int cp) {
		
		
		RowBounds rowBounds2 = new RowBounds(0, 12);
				
		//게시글 조회
		List<Board> boardList2 = mapper.selectJisikin(memberNo, rowBounds2);
		
		Map<String , Object> map2 =  new HashMap<>();
		map2.put("boardList2", boardList2);
		
		return map2; 
	}
	
	@Override
	public Map<String, Object> selectVideo(int memberNo, int cp) {
		
		
		RowBounds rowBounds3 = new RowBounds(0, 5);
				
		//게시글 조회
		List<Board> videoList = mapper.selectVideo(memberNo, rowBounds3);
		
		Map<String , Object> map3 =  new HashMap<>();
		map3.put("videoList", videoList);
		
		return map3; 
	}
	
	
	
	
	
	@Override
	public int changeNickname(Member updateMember) {
		
		return mapper.changeNickname(updateMember);
	}
	
	@Override
	public int changePw(String currentPw, String newPw, int memberNo) {
		
		String encPw = mapper.selectMemberPw(memberNo);
		
		if(!bcrypt.matches(currentPw, encPw)) {
			return 0;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("newPw", bcrypt.encode(newPw));
		map.put("memberNo", memberNo);
		
		return mapper.changePw(map);
	}
	
	
	
	@Override
	public int secession2(String memberPw, int memberNo) {
		// 1. 로그인한 회원의 암호화된 비밀번호 조회
				String encPw = mapper.selectMemberPw(memberNo);
				if(!bcrypt.matches(memberPw, encPw)) { // 같지 않으면
					return 0;
				}
				
				// 탈퇴 처리 SQL 수행
				return mapper.secession2(memberNo);
	}
	
	// 프로필 이미지 수정
		@Override
		public int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException {
			
			// 프로필 이미지 변경 실패 대비 (이전 프로필 이미지 경로 저장)
			String backup = loginMember.getProfileImg();
			
			// 변경된 파일명을 저장할 변수 선언
			// - 파일명 변경하는 이유 ! -> 파일명 같으면 이전 파일을 덮어씌우기 때문에! (이전 파일이 사라짐) 
			String rename = null;
			
			if(profileImg.getSize() > 0) { // 업로드된 파일이 있다면
				
				// 1) 파일명 변경 // 파일명 같으면 이전 파일을 덮어씌우니까. 파일명을 변경
				rename = Util.fileRename( profileImg.getOriginalFilename());
				
				// 2) 바뀐 파일명 + 경로를 loginMember 세팅
				loginMember.setProfileImg( webpath + rename );
				
				// /images/member/20231101144523_00001.jpg // 이런 문자열로 만들어짐.
				
				
			} else { // 업로드된 파일이 없다면 -> 기본 이미지로 변경
				loginMember.setProfileImg(null); // 이미지 제거
				
			}
			
			// mapper 호출
			int result = mapper.profile(loginMember);
			
			// DB 업데이트 성공 시 
			// 메모리에 임시 저장된 파일을 지정된 경로에 저장
			if(result > 0) {
				
				if( profileImg.getSize() > 0 ) { // 업로드 파일 있을 때
					
					profileImg.transferTo(new File(folderPath + rename));
					
				}
				
			} else { // DB 업데이트 실패
				
				// loginMember에 backup 해둔 이미지를 세팅
				loginMember.setProfileImg(backup);
				
			}
			
			
			return result;
		}
		
		@Override
		public int background(MultipartFile backgroundImg, Member loginMember) throws IllegalStateException, IOException {

			String backup = loginMember.getBackgroundImg();
						
			String rename = null;
						
						
			if(backgroundImg.getSize() > 0) { // 업로드된 파일이 있다면
							
				// 1) 파일명 변경 // 파일명 같으면 이전 파일을 덮어씌우니까. 파일명을 변경
				rename = Util.fileRename( backgroundImg.getOriginalFilename());
							
				// 2) 바뀐 파일명 + 경로를 loginMember 세팅
				loginMember.setBackgroundImg( webpath2 + rename );
							
				// /images/member/20231101144523_00001.jpg // 이런 문자열로 만들어짐.
							
							
				} else { // 업로드된 파일이 없다면 -> 기본 이미지로 변경
					loginMember.setBackgroundImg(null); // 이미지 제거
							
				}
						
						// mapper 호출
			int result = mapper.background(loginMember);
						
			if(result > 0) {
							
				if( backgroundImg.getSize() > 0 ) { // 업로드 파일 있을 때
						backgroundImg.transferTo(new File(folderPath2 + rename));
					
					}
							
			} else { // DB 업데이트 실패
							
					loginMember.setBackgroundImg(backup);
				}
						
				return result;
		}
		
		@Override
		public int deleteBackground(Member backgroundImg, Member loginMember) throws IllegalStateException, IOException {
			String backup = loginMember.getBackgroundImg();
			
			String rename = null;
			
			
			if(backgroundImg != null) { 
				
				loginMember.setBackgroundImg(null); // 이미지 제거
				
			}
			
			// mapper 호출
			int result = mapper.background(loginMember);
			
			// DB 업데이트 성공 시 
			// 메모리에 임시 저장된 파일을 지정된 경로에 저장
			if(result == 0) {
				
				
				// loginMember에 backup 해둔 이미지를 세팅
				loginMember.setBackgroundImg(backup);
				
			}
			
			
			return result;
		}
	
}
