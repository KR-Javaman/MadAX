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
  

