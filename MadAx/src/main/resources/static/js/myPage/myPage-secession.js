
const checkObj = {

    "memberPw" : false,
    "memberPwConfirm" : false,
  

  
};


// ============================================================================================


const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");


memberPw.addEventListener("input", () => {

    if(newPw.value.trim().length == 0){
        newPw.value = ""; // 띄어쓰지 못넣게 하기
        checkObj.newPw = false; // 빈칸 == 유효 X
        return;
    }
});



// 마이페이지 (탈퇴 페이지)
// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener('input', ()=>{

    if(checkObj.memberPw){ // 비밀번호가 유효하게 작성된 경우에

        // 비밀번호 == 비밀번호 확인  (같을 경우)
        if(memberPw.value == memberPwConfirm.value){
          
            checkObj.memberPwConfirm = true;
            
        } else{ // 다를 경우
          
            checkObj.memberPwConfirm = false;
        }

    } else { // 비밀번호가 유효하지 않은 경우
        checkObj.memberPwConfirm = false;
    }
});


//--------------------------------------------------------------

document.getElementById("secessionFrm").addEventListener("submit", e => {
if(memberPw.value.trim().length == 0){
    memberPw.value = ""; // 띄어쓰지 못넣게 하기
    checkObj.memberPw = false; // 빈칸 == 유효 X
}

if(memberPw.value != memberPwConfirm.value){
    checkObj.memberPwConfirm = false ;
    
} 
for(let key in checkObj) {

    if(!checkObj[key]) {
        let str;
        switch(key){
            case "newPwConfirm":
            str = "비밀번호가 일치하지 않습니다"; break;
        }

    alert(str);

    document.getElementById(key).focus();
    e.preventDefault();
    return;
    }
}
});

