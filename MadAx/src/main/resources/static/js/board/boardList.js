const writeBtn = document.getElementById("writeBtn");

writeBtn.addEventListener("click", ()=>{
  location.href = `/editBoard/${boardCode}/insert`;
});







/* 검색창에 이전 검색 기록 남겨두기 */
const options = document.querySelectorAll("#searchKey > option");
const searchQuery = document.getElementById("searchQuery");

// 즉시 실행 함수 (해석되자 마자 실행되는 함수, 속도가 빠름)
(()=>{
  // 주소에 있는 파라미터(쿼리스트링) 얻어오기
  const params = new URL(location.href).searchParams;

  const key = params.get("key"); // t, c, tc, w 중 하나
  const query = params.get("query"); // 검색어

  // 검색을 했을 경우
  if(key != null && query != null){
    searchQuery.value = query; // 검색어를 input에 추가

    for(let op of options){
      if(op.value == key){ // option의 value와 key가 일치하면
        op.selected = true; // 해당 옵션 선택
        break;
      }
    }
  }

})();







const writeDate = document.getElementById("writeDate");

writeDate.addEventListener("change", ()=>{

  // fetch() API를 이용한 GET방식 요청
  //GET : 조회(SELECT)
  //쿼리스트링(querystring) : 주소에 담겨진 파라미터를 지칭하는 단어(?key=value&?key=value) -> 띄어쓰기 없어야함
  //then() : 그리고 나서, (앞 동작이)완료된 후에

  ///ajax/selectMemberNo 까지가 요청 주소
  fetch("/board/dateSelect")
  //요청에 대한 응답이 돌아왔을 때 수행
  .then( (response)=>{     //response : 응답이 담겨있는 객체(promise 타입)
    console.log(response); //promise 객체
    return response.text();//promise에 담긴 응답 결과를 text 형태로 파싱 후 반환
  } ) 

  //첫 번째 then()에서 반환된 결과를 이용해 기능을 수행
  .then( (email)=>{ //email : 첫 번째 then에서 파싱된 데이터
    console.log("email:" + email);


  } )

  //비동기 통신 중 예외 발생 시 수행
  .catch( (e)=>{   //e : 예외 관련 정보 객체
    console.log(e);
  } )
});

// writeDate.addEventListener("change" , ()=>{

//   //입력된 값들을 한 번에 저장할 JS객체를 생성
//   const member = {};

//   //JS객체에 Key, value추가
//   //(JS객체는 key가 존재하지 않으면 자동으로 추가되는 특징이 있음)  
//   member.memberEmail = sampleInputList[0].value;
//   member.memberNickname = sampleInputList[1].value;
//   member.memberTel = sampleInputList[2].value;

//   console.log(member); //JS 객체에 key / value 추가 확인

//   //비동기로 샘플 회원 가입

//   /*
//     요청 방식에 따른 용도(REST API 참고)
//     GET == SELECT
//     POST == INSERT
//     PUT == UPDATE
//     DELETE == DELETE
//    */
//   fetch("/ajax/insertMember" ,{
//     method : "POST", //요청 방식
//     headers : {"Content-Type":"application/json"},    //요청 관련 정보 작성(요청 데이터의 형식)
//     body : JSON.stringify(member) //POST,PUT,DELETE 는 body에 데이터를 담아서 전달
//     //JSON.stringify(member) : member객체를 JSON으로 변환(문자열화)
  
//   })
//   .then(response => response.text())
//   .then(result => {
//     if(result > 0){
//       alert("가입 성공");
//       //input 태그 작성 값 모두 삭제
//       sampleInputList.forEach(input => input.value = "");
//     }
//     else{
//       alert("가입 실패");
//     }
//   })
//   .catch(e=>console.log(e));
// });


