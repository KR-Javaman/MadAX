const short = document.getElementsByClassName("short");
const myDIV = document.getElementsByClassName("myDIV");

for(let i=0; i<short.length; i++){
  short[i].addEventListener("click",()=>{
    if(myDIV[i].style.display == 'none'){
      myDIV[i].style.display = 'block';
    }else{
      myDIV[i].style.display = 'none';
    }
  });
}
  




/* ==================게시글 수정 버튼 클릭======================= */




/* ==================게시글 삭제 버튼 클릭======================= */






/* ==================목록으로 버튼 클릭======================= */

const goToListBtn = document.getElementById("goToListBtn");

goToListBtn.addEventListener("click", ()=>{
  location.href = `/Board/${boardCode}/${categoryCode}/${categoryCodeTwo}`;
});

/* ====================글쓰기 버튼========================== */
const writeBtn2 = document.getElementById("writeBtn2");

writeBtn2.addEventListener("click", ()=>{
  location.href = `/editBoard/${boardCode}/insert2`;
});