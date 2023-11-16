/* 글쓰기 유효성 검사 */
const writeSubmit = document.getElementById("writeSubmit");
writeSubmit.addEventListener("submit", e => {
  const title = document.getElementById("title");
  const content = document.getElementById("content");

  if(title.value.trim().length == 0){

    alert("제목을 입력해주세요");

    e.preventDefault();
    title.value = '';
    title.focus();

    return;
  }

  if(content.value.trim().length == 0){

    alert("내용을 입력해주세요");

    e.preventDefault();
    title.value = '';
    title.focus();

    return;
  }

});



function handleOnInput(el, maxlength) {
  if(el.value.length > maxlength)  {
    el.value 
      = el.value.substr(0, maxlength);
  }
}
