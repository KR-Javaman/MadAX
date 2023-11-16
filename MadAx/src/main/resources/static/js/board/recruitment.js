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
  


/* ==================게시글 삭제 버튼 클릭======================= */

const deleteBtn = document.getElementById("deleteBtn");

deleteBtn.addEventListener("click", ()=>{
  location.href = `/editBoard/${boardCode}/${boardNo}/delete`;
});



/* ==================게시글 수정 버튼 클릭======================= */

const updateBtn = document.getElementById("updateBtn");

updateBtn.addEventListener("click", ()=>{
  location.href = `/editBoard/${boardCode}/${boardNo}/update`;
});



/* ==================목록으로 버튼 클릭======================= */

const goToListBtn = document.getElementById("goToListBtn");

goToListBtn.addEventListener("click", ()=>{
  location.href = `/Board/${boardCode}/${categoryCode}/${categoryCodeTwo}`;
});
