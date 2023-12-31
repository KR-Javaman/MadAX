/* =========================좋아요 버튼 ======================*/

const boardLike = document.getElementById("boardLike");

boardLike.addEventListener("click", e=>{
  
  //1. 로그인 여부 확인
  
  if(!loginCheck){
    alert("로그인 후 이용해주세요")
    return;
  }

  //2. 기존에 좋아요 상태 확인
  let check;
  
  if(e.target.classList.contains("fa-regular")){
    check = 0;
  }else{
    check = 1;
  }

  //3. ajax 호출

  //1)ajax로 비동기 요청 시 전달할 데이터를 모아둔 객체
  const dataObj = {boardNo : boardNo, check : check};

  fetch("/board/like", {
    method : "POST", 
    headers : {"Content-Type": "application/json"},  //json : JS 객체 모양의 문자열
    body : JSON.stringify(dataObj) 
    
  })
  .then(resp => resp.text())
  .then(count => {
    
    if(count == -1 ){
      console.log("좋아요 처리 실패");
      return;
    }

    e.target.classList.toggle("fa-regular");
    e.target.classList.toggle("fa-solid");

    
    e.target.nextElementSibling.innerText = count;

  })
  .catch(e => console.log(e));
});




/* ==================게시글 삭제 버튼 클릭======================= */


  const deleteBtn = document.getElementById("deleteBtn");

  if(deleteBtn != null){

  deleteBtn.addEventListener("click", ()=>{
    location.href = `/editBoard/${boardCode}/${boardNo}/delete`;
  });
  
  } 



/* ==================게시글 수정 버튼 클릭======================= */


  const updateBtn = document.getElementById("updateBtn");
  if(updateBtn != null){
  updateBtn.addEventListener("click", ()=>{
    location.href = `/editBoard/${boardCode}/${boardNo}/update`;
  });
}



/* ==================목록으로 버튼 클릭======================= */

  const goToListBtn = document.getElementById("goToListBtn");
  if(goToListBtn != null){
  goToListBtn.addEventListener("click", ()=>{
    location.href = `/board/${boardCode}/1/1`;
  });
  
}




