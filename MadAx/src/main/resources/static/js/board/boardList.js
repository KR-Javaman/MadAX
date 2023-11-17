const writeBtn = document.getElementById("writeBtn");

writeBtn.addEventListener("click", ()=>{
  location.href = `/editBoard/${boardCode}/insert`;
});



/****검색*****/ 
const options = document.querySelectorAll("#searchKey > option");
const searchQuery = document.getElementById("searchQuery");

(()=>{

  const params = new URL(location.href).searchParams;

  const key = params.get("key"); 
  const query = params.get("query");

  // 검색을 했을 경우
  if(key != null && query != null){
    searchQuery.value = query;

    for(let op of options){
      if(op.value == key){ 
        op.selected = true; 
        break;
      }
    }
  }

})();



// const asdf = document.getElementById("asdf");
// asdf.addEventListener("click", ()=>{
//   fetch("board/{boardCode}/{categoryCode}/{categoryCodeTwo}")
//   .then(response => response.json())
//   .then(data => {
    
//   })
//   .catch(error => console.log(error))




// });










