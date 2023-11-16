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
const updateBtn = document.getElementsByClassName("updateBtn");
for(let i=0; i<updateBtn.length; i++){
  updateBtn[i].addEventListener("click",()=>{
    location.href = `/editBoard/${boardCode}/${boardNo}/update`;
  });
}



/* ==================게시글 삭제 버튼 클릭======================= */
const deleteBtn = document.getElementsByClassName("deleteBtn");
for(let i=0; i<deleteBtn.length; i++){
  deleteBtn[i].addEventListener("click",()=>{
    location.href = `/editBoard/${boardCode}/${boardNo}/delete`;
  });
}




/* ====================글쓰기 버튼========================== */
const writeBtn = document.getElementById("writeBtn");

writeBtn.addEventListener("click", ()=>{
  location.href = `/editBoard/${boardCode}/insert`;
});