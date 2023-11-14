const writeBtn = document.getElementById("writeBtn");

writeBtn.addEventListener("click", ()=>{

  location.href = `/editBoard/${boardCode}/insert`;
  
});

