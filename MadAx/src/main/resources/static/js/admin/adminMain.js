// const sort = document.querySelector("#sort");

// sort.addEventListener("change", (e) => {
//   location.href = "selectSort?sort=" + e.target.value;
// });

// const params = new URL(location.href).searchParams;
// const key = params.get("sort");
// if (key != null) {
//   document.querySelector(`option[value='${key}']`).selected = true;
// }
// //------------------------------------------------------
// const memberList = document.querySelector("#memberList");

// const selectList = () => {
//   fetch("/selectList?sort=" + sort)
//     .then((resp) => resp.json())
//     .then((memberList) => {
//       memberList.innerHtml = "";
//       for (let member of memberList) {
//         const tr = document.createElement("tr");
//         const td1 = document.createElement("td");
//         td1.classList.add("table-elements");

//         const td2 = document.createElement("td");
//         td2.classList.add("table-elements");

//         const td2a = document.createElement("a");
//         a.setAttribute(
//           "href",
//           "/admin/selectMember(inputEmail=" + member.memberEmail + ")"
//         );

//         const td3 = document.createElement("td");
//         td3.classList.add("table-elements");

//         td1.innerText = member.memberNo;
//         td2a.innerText = member.memberEmail;
//         td3.innerText = member.memberNickname;

//         td2.append(td2a);

//         tr.append(td1, td2, td3);
//       }
//     })
//     .catch((e) => console.log(e));
// };

const options = document.querySelectorAll("#searchKey > option");
const searchQuery = document.querySelector("#search");

() => {
  const params = new URL(location.href).searchParams;
  const key = params.get("key");
  const query = params.get("query");

  // 검색을 했을 경우
  if (key != null && query != null) {
    searchQuery.value = query;

    for (let op of options) {
      if (op.value == key) {
        op.selected == true;
        break;
      }
    }
  }
};

const goListBtn = document.querySelector("#goToListBtn");

if (goListBtn != null) {
  const goListfn = () => {
    const paramMap = new URL(location.href).searchParams;

    const obj = {};

    obj.key = paramMap.get("inputEmail");

    const tempParams = new URLSearchParams();

    for (let key in obj) {
      if (obj[key] != null) tempParams.append(key, obj[key]);
    }
    location.href = `/admin/main?${tempParams.toString()}`;
  };

  goListBtn.addEventListener("click", goListfn);
}
