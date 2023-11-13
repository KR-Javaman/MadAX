const writeBtn = document.getElementById("writeBtn");

writeBtn.addEventListener("click", ()=>{

  location.href = `/editBoard/${boardCode}/insert`;
  
});



const ds = document.getElementById("date-Sequence");
const is = document.getElementById("inquiry-Sequence");
const rs = document.getElementById("Recommendation-Sequence");

ds.addEventListener("click", ()=>{

  fetch("board/inquiry")
  





});