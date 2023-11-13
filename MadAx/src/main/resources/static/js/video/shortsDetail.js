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
