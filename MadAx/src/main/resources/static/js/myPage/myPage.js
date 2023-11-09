
const checkObj = {

    "memberPw" : false,
    "memberPwConfirm" : false,
  
};


// ============================================================================================


/*  비밀번호/비밀번호 확인 유효성 검사 */
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");


// 비밀번호 입력 시 유효성 검사
memberPw.addEventListener("input", () => {

    // 비밀번호가 입력되지 않은 경우
    if(memberPw.value.trim().length == 0){
        memberPw.value = ""; // 띄어쓰지 못넣게 하기

        checkObj.memberPw = false; // 빈칸 == 유효 X
        return;
    }

    const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

    // 입력한 비밀번호가 유효한 경우
    if(regEx.test(memberPw.value)){
        checkObj.memberPw = true; 
        
        if(memberPwConfirm.value.trim().length == 0){

        }
        

        else{
            // 비밀번호 == 비밀번호 확인  (같을 경우)
            if(memberPw.value == memberPwConfirm.value){
                
                checkObj.memberPwConfirm = true;
                
            } else{ // 다를 경우
              
                checkObj.memberPwConfirm = false;
            }
        }

        
    } else{ // 유효하지 않은 경우
        
        checkObj.memberPw = false; 
    }
});


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


