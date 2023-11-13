const likeImg = document.querySelector("#img");

likeImg.addEventListener("click", (e) => {
  if (!loginCheck) {
    alert("로그인을 먼저 해주세요");
    return;
  }
  let check;

  if (e.target.classList.contains("fa-regular")) {
    check = 0;
  } else {
    check = 1;
  }

  const dataObj = { boardVideoNo: boardVideoNo, check: check };

  fetch("/shorts/detail/like", {
    method: "post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((count) => {
      if (count == -1) {
        console.log("좋아요 실패");
        return;
      }
      e.target.classList.toggle("fa-regular");
      e.target.classList.toggle("fa-solid");

      e.target.nextElementSibling.innerText = count;
    })
    .catch((e) => {
      console.log(e);
    });
});

//--------------------------------------------
const goListBtn = document.querySelector("#goToListBtn");

if (goListBtn != null) {
  const goListfn = () => {
    const paramMap = new URL(location.href).searchParams;

    const obj = {};

    obj.cp = paramMap.get("cp");
    obj.key = paramMap.get("key");
    obj.query = paramMap.get("query");

    const tempParams = new URLSearchParams();

    for (let key in obj) {
      if (obj[key] != null) tempParams.append(key, obj[key]);
    }
    location.href = `/shorts/main?${tempParams.toString()}`;
  };

  goListBtn.addEventListener("click", goListfn);
}

//-----------------------------------------------
const deleteBtn = document.querySelector("#deleteBtn");

if (deleteBtn != null) {
  deleteBtn.addEventListener("click", () => {
    if (confirm("삭제 하시겠습니까?")) {
      location.href =
        location.pathname.replace("shorts/detail", "shorts/edit") + "/delete";
    }
  });
}

const updateBtn = document.querySelector("#updateBtn");

if (updateBtn != null) {
  updateBtn.addEventListener("click", () => {
    let url = `/shorts/edit/${boardVideoNo}/update${location.search}`;

    location.href = url;
  });
}
