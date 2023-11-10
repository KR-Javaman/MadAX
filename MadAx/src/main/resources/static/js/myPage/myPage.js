const checkObj = {

    "newPw" : false,
    "newPwConfirm" : false
};


// ============================================================================================

/*  마이페이지(비밀번호) 수정 + 마이페이지(탈퇴 페이지) 
    - 비밀번호/비밀번호 확인 유효성 검사 */

const newPw = document.getElementById("newPw");
const newPwConfirm = document.getElementById("newPwConfirm");


// 마이페이지 (비밀번호 수정)
// 새 비밀번호 입력 시 유효성 검사
newPw.addEventListener('input', () => {

    if(newPw.value.trim().length == 0){
        newPw.value = ""; // 띄어쓰지 못넣게 하기
        alert("비밀번호를 입력해주세요.");
        checkObj.newPw = false; // 빈칸 == 유효 X
        
        return;
    }

    const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

    // 입력한 비밀번호가 유효한 경우
    if(regEx.test(newPw.value)){
        checkObj.newPw = true; 
        
            // 비밀번호 == 비밀번호 확인  (같을 경우)
            if(newPw.value == newPwConfirm.value){
                alert("비밀번호가 일치합니다");
                checkObj.newPwConfirm = true;
                
            } else{ // 다를 경우
                checkObj.newPwConfirm = false;
            }
         }
            
    else{ // 유효하지 않은 경우
    
        checkObj.newPw = false; 
    } 
    
});

// 마이페이지 (비밀번호 수정)
// 새 비밀번호 확인 유효성 검사
newPwConfirm.addEventListener('input', ()=>{

    if(checkObj.newPw){ // 비밀번호가 유효하게 작성된 경우에

        // 비밀번호 == 비밀번호 확인  (같을 경우)
        if(newPw.value == newPwConfirm.value){
            alert("비밀번호가 일치합니다");
            checkObj.newPwConfirm = true;
            
        } else{ // 다를 경우
       
            checkObj.newPwConfirm = false;
        }

    } else { // 비밀번호가 유효하지 않은 경우
        checkObj.newPwConfirm = false;
    }
});


//-----------------------------------------------------------------



//--------------------------------------------------------------

document.getElementById("changePwFrm").addEventListener("submit", e => {

    if(currentPw.value.trim().length == 0){
        currentPw.value = ""; // 띄어쓰기 못넣게 하기
        alert("현재 비밀번호를 입력해주세요.");
        e.preventDefault();
        currentPw.focus();
        
        return;
    }


    if(newPw.value.trim().length == 0){
        newPw.value = ""; // 띄어쓰지 못넣게 하기
        alert("새 비밀번호를 입력해주세요.");
        e.preventDefault();
        newPw.focus();
        checkObj.newPw = false; // 빈칸 == 유효 X
        
        return;
    }

    if(newPwConfirm.value.trim().length == 0){
        newPwConfirm.value = ""; // 띄어쓰지 못넣게 하기
        alert("비밀번호 확인을 입력해주세요.");
        e.preventDefault();
        newPwConfirm.focus();
        checkObj.newPwConfirm = false; // 빈칸 == 유효 X
        
        return;
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

