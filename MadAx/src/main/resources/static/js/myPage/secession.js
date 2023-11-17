
    
const memberPw = document.getElementById("memberPw");


document.getElementById("secessionFrm").addEventListener("submit", e => {

    if(memberPw.value.trim().length == 0){
        memberPw.value = ""; // 띄어쓰기 못넣게 하기
        alert("비밀번호를 입력해주세요.");
        e.preventDefault();
        memberPw.focus();
        
        return;
    }




});
