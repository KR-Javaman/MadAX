// 댓글 목록 조회
const selectCommentList = () => {

  fetch("/comment?boardNo="+boardNo)  
  .then(response => response.json()) 
  .then(cList => {
      console.log(cList);

      
      const commentList = document.getElementById("commentList"); 
      commentList.innerHTML = "";

      for(let comment of cList){
          const commentRow = document.createElement("li");
          commentRow.classList.add("comment-row");
        
          if(comment.parentNo != 0)  {
            commentRow.classList.add("child-comment");
          }

          if(comment.commentDelFl == 'Y') {
            commentRow.innerText = "삭제된 댓글 입니다";
          }else{
              const commentWriter = document.createElement("p");
              commentWriter.classList.add("comment-writer");
              const profileImg = document.createElement("img");

              if( comment.profileImg != null ){ // 프로필 이미지가 있을 경우
                  profileImg.setAttribute("src", comment.profileImg);
              }else{ // 없을 경우 == 기본이미지
                  profileImg.setAttribute("src", userDefaultImage);
              }

              // 작성자 닉네임
              const memberNickname = document.createElement("span");
              memberNickname.innerText = comment.memberNickname;
              
              // 작성일
              const commentDate = document.createElement("span");
              commentDate.classList.add("comment-date");
              commentDate.innerText =   comment.commentWriteDate;

              // 작성자 영역(p)에 프로필,닉네임,작성일 마지막 자식으로(append) 추가
              commentWriter.append(profileImg , memberNickname , commentDate);

              

              // 댓글 내용
              const commentContent = document.createElement("p");
              commentContent.classList.add("comment-content");
              commentContent.innerHTML = comment.commentContent;

              // 행에 작성자, 내용 추가
              commentRow.append(commentWriter, commentContent);

          
              // 로그인이 되어있는 경우 답글 버튼 추가
              if(loginCheck){
                  // 버튼 영역
                  const commentBtnArea = document.createElement("div");
                  commentBtnArea.classList.add("comment-btn-area");

                  // 답글 버튼
                  const childCommentBtn = document.createElement("button");
                  childCommentBtn.setAttribute("onclick", "showInsertComment("+comment.commentNo+", this)");
                  
                  childCommentBtn.innerText = "답글";

                  // 버튼 영역에 답글 버튼 추가
                  commentBtnArea.append(childCommentBtn);

                  // 로그인한 회원번호와 댓글 작성자의 회원번호가 같을 때만 버튼 추가
                  if( loginMemberNo == comment.memberNo   ){

                      // 수정 버튼
                      const updateBtn = document.createElement("button");
                      updateBtn.innerText = "수정";

                      // 수정 버튼에 onclick 이벤트 속성 추가
                      updateBtn.setAttribute("onclick", "showUpdateComment("+comment.commentNo+", this)");                        


                      // 삭제 버튼
                      const deleteBtn = document.createElement("button");
                      deleteBtn.innerText = "삭제";
                      // 삭제 버튼에 onclick 이벤트 속성 추가
                      deleteBtn.setAttribute("onclick", "deleteComment("+comment.commentNo+")");                       


                      // 버튼 영역 마지막 자식으로 수정/삭제 버튼 추가
                      commentBtnArea.append(updateBtn, deleteBtn);

                  } // if 끝
                  

                  // 행에 버튼영역 추가
                  commentRow.append(commentBtnArea); 
              }
          }

          // 댓글 목록(ul)에 행(li)추가
          commentList.append(commentRow);
      }


  })
  .catch(err => console.log(err));

}


//-------------------------------------------------------------------------------------------------

// 댓글 등록
const addComment = document.getElementById("addComment");
const commentContent = document.getElementById("commentContent");

addComment.addEventListener("click", e => { 


  if(!loginCheck){ 
      alert("로그인 후 이용해주세요.");
      return;
  }

  if(commentContent.value.trim().length == 0){
      alert("댓글을 작성한 후 버튼을 클릭해주세요.");

      commentContent.value = ""; 
      commentContent.focus();
      return;
  }
    const data = {"commentContent" : commentContent.value,
                  "memberNo" : loginMemberNo,
                  "boardNo" : boardNo}; 

  fetch("/comment",{
      method : "POST",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(data) 
  })
  .then(resp => resp.text())
  .then(result => {
      if(result > 0){ 
          alert("댓글이 등록되었습니다.");

          commentContent.value = ""; 

          selectCommentList(); 
 

      } else { 
          alert("댓글 등록에 실패했습니다...");
      }
  })
  .catch(err => console.log(err));
});


/*============================ 댓글 삭제=========================== */
function deleteComment(commentNo){

  if( confirm("정말로 삭제 하시겠습니까?") ){

      fetch("/comment", {
          method : "DELETE",
          headers : {"Content-type" : "application/json"},
          body : commentNo // 값 하나 전달 시에는 JSON 필요 없음
      })
      .then(resp => resp.text())
      .then(result => {
          if(result > 0){
              alert("삭제되었습니다");
              selectCommentList(); // 목록을 다시 조회해서 삭제된 글을 제거
          }else{
              alert("삭제 실패");
          }
      })
      .catch(err => console.log(err));

  }
}

/*========================== 댓글 수정 화면 전환 ===============================*/ 

let beforeCommentRow; // 수정 전 원래 행의 상태를 저장할 변수


function showUpdateComment(commentNo, btn){
    
  const temp = document.getElementsByClassName("update-textarea");  

  if(temp.length > 0){ 

      if(confirm("다른 댓글이 수정 중입니다. 현재 댓글을 수정 하시겠습니까?")){ 

          temp[0].parentElement.innerHTML = beforeCommentRow;
      }else{ 
          return;
      }
  }


  // 1. 댓글 수정이 클릭된 행을 선택
  const commentRow = btn.parentElement.parentElement; // 수정 버튼의 부모의 부모

  // 2. 행 내용 삭제 전 현재 상태를 저장(백업) (문자열)
  //    (전역변수 이용)
  beforeCommentRow = commentRow.innerHTML;


  // 3. 댓글에 작성되어 있던 내용만 얻어오기 -> 새롭게 생성된 textarea 추가될 예정
  
  let beforeContent = commentRow.children[1].innerHTML;

  // 이것도 가능!
  //let beforeContent = btn.parentElement.previousElementSibling.innerHTML;


  // 4. 댓글 행 내부 내용을 모두 삭제
  commentRow.innerHTML = "";

  // 5. textarea 요소 생성 + 클래스 추가  +  **내용 추가**
  const textarea = document.createElement("textarea");
  textarea.classList.add("update-textarea");

  // ******************************************
  // XSS 방지 처리 해제
  beforeContent =  beforeContent.replaceAll("&amp;", "&");
  beforeContent =  beforeContent.replaceAll("&lt;", "<");
  beforeContent =  beforeContent.replaceAll("&gt;", ">");
  beforeContent =  beforeContent.replaceAll("&quot;", "\"");
  
  // ******************************************
  textarea.value = beforeContent; // 내용 추가

  // 6. commentRow에 생성된 textarea 추가
  commentRow.append(textarea);


  // 7. 버튼 영역 + 수정/취소 버튼 생성
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");
  

  const updateBtn = document.createElement("button");
  updateBtn.innerText = "수정";
  updateBtn.setAttribute("onclick", "updateComment("+commentNo+", this)");


  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.setAttribute("onclick", "updateCancel(this)");


  // 8. 버튼영역에 버튼 추가 후 
  //    commentRow(행)에 버튼영역 추가
  commentBtnArea.append(updateBtn, cancelBtn);
  commentRow.append(commentBtnArea);

}


// -----------------------------------------------------------------------------------
// 댓글 수정 취소
function updateCancel(btn){
  // 매개변수 btn : 클릭된 취소 버튼
  // 전역변수 beforeCommentRow : 수정 전 원래 행(댓글)을 저장한 변수

  if(confirm("댓글 수정을 취소하시겠습니까?")){
      btn.parentElement.parentElement.innerHTML = beforeCommentRow;
  }
}

// -----------------------------------------------------------------------------------
// 댓글 수정(AJAX)
function updateComment(commentNo, btn){

  // 새로 작성된 댓글 내용 얻어오기
  const commentContent = btn.parentElement.previousElementSibling.value;

  const data = {
      "commentNo" : commentNo,
      "commentContent" : commentContent
  }

  fetch("/comment", {
      method : "PUT",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result => {
      if(result > 0){
          alert("댓글이 수정되었습니다.");
          selectCommentList();
      }else{
          alert("댓글 수정 실패");
      }
  })
  .catch(err => console.log(err));

}

// -------------------------------------------------------------------------------
// -------------------------------------------------------------------------------

// 답글 작성 화면 추가 
// -> 답글 작성 화면은 전체 화면에 1개만 존재 해야한다!

function showInsertComment(parentNo, btn){
                      // 부모 댓글 번호, 클릭한 답글 버튼


  // ** 답글 작성 textarea가 한 개만 열릴 수 있도록 만들기 **
  const temp = document.getElementsByClassName("commentInsertContent");

  if(temp.length > 0){ // 답글 작성 textara가 이미 화면에 존재하는 경우

      if(confirm("다른 답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?")){
          temp[0].nextElementSibling.remove(); // 버튼 영역부터 삭제
          temp[0].remove(); // textara 삭제 (기준점은 마지막에 삭제해야 된다!)
      
      } else{
          return; // 함수를 종료시켜 답글이 생성되지 않게함.
      }
  }
  
  // 답글을 작성할 textarea 요소 생성
  const textarea = document.createElement("textarea");
  textarea.classList.add("commentInsertContent");
  
  // 답글 버튼의 부모의 뒤쪽에 textarea 추가
  // after(요소) : 뒤쪽에 추가
  btn.parentElement.after(textarea);


  // 답글 버튼 영역 + 등록/취소 버튼 생성 및 추가
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");


  const insertBtn = document.createElement("button");
  insertBtn.innerText = "등록";
  insertBtn.setAttribute("onclick", "insertChildComment("+parentNo+", this)");


  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.setAttribute("onclick", "insertCancel(this)");

  // 답글 버튼 영역의 자식으로 등록/취소 버튼 추가
  commentBtnArea.append(insertBtn, cancelBtn);

  // 답글 버튼 영역을 화면에 추가된 textarea 뒤쪽에 추가
  textarea.after(commentBtnArea);

}


// 답글 취소
function insertCancel(btn){
                  // 취소
  btn.parentElement.previousElementSibling.remove(); // 취소의 부모의 이전 요소(textarea) 제거
  btn.parentElement.remove(); // 취소의 부모 요소(comment-btn-area) 제거
}


// 답글 등록
function insertChildComment(parentNo, btn){
                      // 부모 댓글 번호, 답글 등록 버튼

  // 누가?                loginMemberNo(로그인한 회원의 memberNo )(전역변수)
  // 어떤 내용?           textarea에 작성된 내용
  // 몇번 게시글?         현재 게시글 boardNo (전역변수)
  // 부모 댓글은 누구?    parentNo (매개변수)

  // 답글 내용
  const commentContent = btn.parentElement.previousElementSibling.value;

  // 답글 내용이 작성되지 않은 경우
  if(commentContent.trim().length == 0){
      alert("답글 작성 후 등록 버튼을 클릭해주세요.");
      btn.parentElement.previousElementSibling.value = "";
      btn.parentElement.previousElementSibling.focus();
      return;
  }

  const data = {"commentContent" : commentContent,
                  "memberNo" : loginMemberNo,
                  "boardNo" : boardNo,
                  "parentNo" : parentNo}; // JS객체

  fetch("/comment",{
      method : "POST",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(data) // JS객체 -> JSON 파싱
  })
  .then(resp => resp.text())
  .then(result => {
      if(result > 0){ // 등록 성공
          alert("답글이 등록되었습니다.");
          selectCommentList(); // 비동기 댓글 목록 조회 함수 호출

      } else { // 실패
          alert("답글 등록에 실패했습니다...");
      }
  })
  .catch(err => console.log(err));
}


