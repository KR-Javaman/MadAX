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




/* 프로필 이미지 미리보기, 제거 */
const profileImg = document.querySelector("#profileImg"); // img 태그
let imageInput = document.querySelector("#imageInput"); // input 태그


let statusCheck = -1;

let backupInput; // 

if(imageInput != null) { // #imageInput 존재할 때 

    /* 프로필 이미지 변경(선택) 시 수행할 함수 */
    const changeImageFn = e => {

        console.log(e.target); // input태그
        console.log(e.target.value); // value -> 업로드 파일 경로가 나옴(fakepath 형태로 출력) // C:\fakepath\sample5.jpg

        /* 이게 중요! 파일의 정보가 담긴 배열이 담겨있기 때문에!*/
        console.log(e.target.files); // 업로드된 파일의 정보가 담긴 배열 반환
                                     // *실제 파일 *

        console.log(e.target.files[0]); // 업로드된 파일 중 첫 번째 파일

        const uploadFile = e.target.files[0]; 

        // ---------- 파일을 한번 선택한 후 취소했을 때 ---------------
        if(uploadFile == undefined) { //취소를 눌러서 files[0]에 파일이 없어졌을 때
            console.log("파일 선택이 취소됨");

            // 1) backup한 요소를 복제
            const temp = backupInput.cloneNode(true);

            // 2) 화면에 원본 input을 temp로 바꾸는 작업
            imageInput.after(temp); // 원본 다음에 temp 추가
            imageInput.remove(); // 원본을 화면에서 제거
            imageInput = temp; // temp를 imageInput 변수에 대입

            // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
            imageInput.addEventListener("change", changeImageFn);

            return; 
        }


        // ----------- 선택된 파일의 크기가 지정된 크기를 초과하는 경우 -----------
        const maxSize = 1024 * 1024 ; // 1MB (byte 단위)

        if(uploadFile.size > maxSize) { 
            alert("1MB 이하의 이미지만 업로드 가능합니다");

            if(statusCheck == -1) { // 이미지 변경이 없었을 때 

                // 최대 크기를 초과해도 input에 value가 남기 때문에 
                // 이를 제거하는 코드가 필요하다! 
                imageInput.value = ''; // value 삭제
                                    // 동시에 files도 삭제됨
                statusCheck = -1; // 선택 없음 상태

            } else { // 기존 이미지가 있었을 때 
                // 1) backup한 요소를 복제
                const temp = backupInput.cloneNode(true);

                // 2) 화면에 원본 input을 temp로 바꾸는 작업
                imageInput.after(temp); // 원본 다음에 temp 추가
                imageInput.remove(); // 원본을 화면에서 제거
                imageInput = temp; // temp를 imageInput 변수에 대입

                // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
                imageInput.addEventListener("change", changeImageFn);
                
                statusCheck = 1;

            }
            return;
        }

        // ----------- 선택된 이미지 파일을 읽어와 미리 보기 만들기 --------------

        // JS에서 파일 읽는 객체 
        // -> 파일을 읽고 클라리언트 컴퓨터에 파일을 저장할 수 있음
        const reader = new FileReader();

        // 매개변수에 작성된 파일을 읽어서 
        // 파일을 나타내는 URL 형태로 변경
        // -> FileReader.result 필드에 저장되어 있음
        reader.readAsDataURL(uploadFile);

        // 파일을 다 읽었을 때 
        reader.onload = e => {
            // console.log(reader.result); // 읽은 파일의 URL

            // img태그의 src 속성의 속성 값으로
            // 읽은 파일의 URL을 대입

            profileImg.setAttribute("src", reader.result);

            statusCheck = 1; // 새 이미지 선택한 경우

            // 파일이 추가된 input을 backup 해두기
            backupInput = imageInput.cloneNode(true);
        }

    }

    /* 이미지 선택 버튼을 클릭하여 선택된 파일이 변했을 때 함수 수행  */

    // change 이벤트 : input의 이전 값과 현재 값이 다를 때 발생
    imageInput.addEventListener("change", changeImageFn);

    // ---------- 프로필 이미지 변경 form태그 제출 시 동작 ------------

    const profileFrm = document.getElementById("profileFrm");

    profileFrm.addEventListener("submit", e => {

        let flag = true;

        // 1) 로그인한 회원의 프로필이 있음 -> 없어짐
        if(loginMemberProfileImg != null && statusCheck == 0) flag = false;
        
        // 2) 로그인한 회원의 프로필이 없음 -> 있음
        if(loginMemberProfileImg == null && statusCheck == 1) flag = false;
        
        // 3) 로그인한 회원의 프로필이 있음 -> 변경
        if(loginMemberProfileImg != null && statusCheck == 1) flag = false;

        if(flag){ //flag가 true인 경우 수행
            e.preventDefault(); // form 태그 제출 기본 (동작)이벤트를 막기(제거)
            alert("위의 프로필 이미지를 변경 후 클릭해주세요.");
        }

    });


};

//--------------------------------------------------------------------
// 백그라운드 이미지 



/* 프로필 이미지 미리보기, 제거 */
const backgroundImg = document.querySelector("#backgroundImg"); // img 태그
let backgroundInput = document.querySelector("#backgroundInput"); // input 태그



if(backgroundInput != null) { // #backgroundInput 존재할 때 

    /* 프로필 이미지 변경(선택) 시 수행할 함수 */
    const changeImageFn = e => {

        console.log(e.target); // input태그
        console.log(e.target.value); // value -> 업로드 파일 경로가 나옴(fakepath 형태로 출력) // C:\fakepath\sample5.jpg

        /* 이게 중요! 파일의 정보가 담긴 배열이 담겨있기 때문에!*/
        console.log(e.target.files); // 업로드된 파일의 정보가 담긴 배열 반환
                                     // *실제 파일 *

        console.log(e.target.files[0]); // 업로드된 파일 중 첫 번째 파일

        const uploadFile = e.target.files[0]; 

        // ---------- 파일을 한번 선택한 후 취소했을 때 ---------------
        if(uploadFile == undefined) { //취소를 눌러서 files[0]에 파일이 없어졌을 때
            console.log("파일 선택이 취소됨");

            // 1) backup한 요소를 복제
            const temp = backupInput.cloneNode(true);

            // 2) 화면에 원본 input을 temp로 바꾸는 작업
            backgroundInput.after(temp); // 원본 다음에 temp 추가
            backgroundInput.remove(); // 원본을 화면에서 제거
            backgroundInput = temp; // temp를 backgroundInput 변수에 대입

            // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
            backgroundInput.addEventListener("change", changeImageFn);

            return; 
        }


        // ----------- 선택된 파일의 크기가 지정된 크기를 초과하는 경우 -----------
        const maxSize = 1024 * 1024 * 10 ; // 10MB (byte 단위)

        if(uploadFile.size > maxSize) { 
            alert("10MB 이하의 이미지만 업로드 가능합니다");

            if(statusCheck == -1) { // 이미지 변경이 없었을 때 

                // 최대 크기를 초과해도 input에 value가 남기 때문에 
                // 이를 제거하는 코드가 필요하다! 
                backgroundInput.value = ''; // value 삭제
                                    // 동시에 files도 삭제됨
                statusCheck = -1; // 선택 없음 상태

            } else { // 기존 이미지가 있었을 때 
                // 1) backup한 요소를 복제
                const temp = backupInput.cloneNode(true);

                // 2) 화면에 원본 input을 temp로 바꾸는 작업
                backgroundInput.after(temp); // 원본 다음에 temp 추가
                backgroundInput.remove(); // 원본을 화면에서 제거
                backgroundInput = temp; // temp를 backgroundInput 변수에 대입

                // 복제본은 이벤트가 복제 안되니까 다시 이벤트를 추가
                backgroundInput.addEventListener("change", changeImageFn);
                
                statusCheck = 1;

            }
            return;
        }

        // ----------- 선택된 이미지 파일을 읽어와 미리 보기 만들기 --------------

        // JS에서 파일 읽는 객체 
        // -> 파일을 읽고 클라리언트 컴퓨터에 파일을 저장할 수 있음
        const reader = new FileReader();

        // 매개변수에 작성된 파일을 읽어서 
        // 파일을 나타내는 URL 형태로 변경
        // -> FileReader.result 필드에 저장되어 있음
        reader.readAsDataURL(uploadFile);

        // 파일을 다 읽었을 때 
        reader.onload = e => {
            // console.log(reader.result); // 읽은 파일의 URL

            // img태그의 src 속성의 속성 값으로
            // 읽은 파일의 URL을 대입

            backgroundImg.setAttribute("src", reader.result);

            statusCheck = 1; // 새 이미지 선택한 경우

            // 파일이 추가된 input을 backup 해두기
            backupInput = backgroundInput.cloneNode(true);
        }

    }

    /* 이미지 선택 버튼을 클릭하여 선택된 파일이 변했을 때 함수 수행  */

    backgroundInput.addEventListener("change", changeImageFn);

    const deleteBackgroundImage = document.querySelector("#deleteImage"); // 초기화 버튼

    deleteBackgroundImage.addEventListener("click", () => {
        backgroundImg.setAttribute("src", defaultBackgroundImage);
        backgroundInput.value = "";

        backupInput.value = "";

        statusCheck = 0; // 있었는데 없어짐. (x버튼 누른 후)

        let flag = true;

        // 1) 로그인한 회원의  백그라운드가 있음 -> 없어짐
        if(loginMemberbackgroundImg != null && statusCheck == 0) flag = false;
        if(flag) {
            e.preventDefault();
            alert("이미지가 초기화 됩니다.");
        }

        
    });


    // ---------- 프로필 이미지 변경 form태그 제출 시 동작 ------------

    const backgroundFrm = document.getElementById("backgroundFrm");

    backgroundFrm.addEventListener("submit", e => {

        let flag = true;

        // 1) 로그인한 회원의  백그라운드가 있음 -> 없어짐
        if(loginMemberbackgroundImg != null && statusCheck == 0) flag = false;
        
        // 2) 로그인한 회원의 백그라운드가 없음 -> 있음
        if(loginMemberbackgroundImg == null && statusCheck == 1) flag = false;
        
        // 3) 로그인한 회원의 백그라운드가 있음 -> 변경
        if(loginMemberbackgroundImg != null && statusCheck == 1) flag = false;

        if(flag){ //flag가 true인 경우 수행
            e.preventDefault(); // form 태그 제출 기본 (동작)이벤트를 막기(제거)
            alert("위의 배경화면 이미지를 변경 후 클릭해주세요");
        }

    });


};




