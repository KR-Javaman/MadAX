const selectCommentList = () => {
  fetch("/shorts/detail/videoComment?boardVideoNo=" + boardVideoNo)
    .then((resp) => resp.json())
    .then((cList) => {
      console.log(cList);

      const videoCommentList = document.getElementById("commentList");
      videoCommentList.innerHTML = "";

      for (let videoComment of cList) {
        const commentRow = document.createElement("li");
        commentRow.classList.add("comment-row");

        if (videoComment.parentNo != 0)
          commentRow.classList.add("child-comment");
        if (videoComment.commentDelFl == "Y")
          commentRow.innerText = "삭제된 댓글 입니다.";
        else {
          const commentWriter = document.createElement("p");
          commentWriter.classList.add("video-comment-writer");

          const profileImg = document.createElement("img");

          if (videoComment.profileImg != null) {
            profileImg.setAttribute("src", videoComment.profileImg);
          } else {
            profileImg.setAttribute("src", userDefaultImage);
          }

          const memberNickname = document.createElement("span");
          memberNickname.innerText = videoComment.memberNickname;

          const commentWriteDate = document.createElement("span");
          commentWriteDate.classList.add("comment-writer-date");
          commentWriteDate.innerText = videoComment.commentWriteDate;

          commentWriter.append(profileImg, memberNickname, commentWriteDate);

          const commentContent = document.createElement("span");
          commentContent.classList.add("comment-content");
          commentContent.innerHTML = videoComment.commentContent;

          commentRow.append(commentWriter, commentContent);

          if (loginCheck) {
            const commentBtnArea = document.createElement("div");
            commentBtnArea.classList.add("comment-btn-area");

            const childCommentBtn = document.createElement("button");
            childCommentBtn.setAttribute(
              "onclick",
              "showInsertComment(" + videoComment.commentNo + ", this)"
            );

            childCommentBtn.innerText = "답글 달기";

            commentBtnArea.append(childCommentBtn);

            if (loginMemberNo == videoComment.memberNo) {
              const updateBtn = document.createElement("button");
              updateBtn.innerText = "수정";

              updateBtn.setAttribute(
                "onclick",
                "showUpdateComment(" + videoComment.commentNo + ", this)"
              );

              const deleteBtn = document.createElement("button");
              deleteBtn.innerText = "삭제";

              deleteBtn.setAttribute(
                "onclick",
                "deleteComment(" + videoComment + ")"
              );

              commentBtnArea.append(updateBtn, deleteBtn);
            }
            commentRow.append(commentBtnArea);
          }
        }
        videoCommentList.append(commentRow);
      }
    })
    .catch((e) => console.log(e));
};
//----------------------------------------
const addComment = document.getElementById("addComment");
const commentContent = document.getElementById("commentContent");

addComment.addEventListener("click", (e) => {
  if (!loginCheck) {
    alert("로그인을 먼저 해주세요");
    return;
  }
  if (commentContent.value.trim().length == 0) {
    alert("댓글을 먼저 작성해주세요");

    commentContent.value = "";
    commentContent.focus();
    return;
  }

  const dataObj = {
    commentContent: commentContent.value,
    memberNo: loginMemberNo,
    boardVideoNo: boardVideoNo,
  };

  fetch("/shorts/detail/videoComment", {
    method: "post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result > 0) {
        alert("댓글이 등록되었습니다.");

        commentContent.value = "";
        selectCommentList();
      } else {
        alert("댓글을 등록하지 못했습니다.");
      }
    })
    .catch((e) => {
      console.log(e);
    });
});
//------------------------------------
function deleteVideoComment(commentNo) {
  if (confirm("정말로 삭제 하시겠습니까?")) {
    fetch("/shorts/detail/videoComment", {
      method: "delete",
      headers: { "Content-type": "application/json" },
      body: commentNo,
    })
      .then((resp) => {
        resp.text();
      })
      .then((result) => {
        if (result > 0) {
          alert("삭제되었습니다.");
          selectCommentList();
        } else {
          alert("삭제를 실패하였습니다.");
        }
      })
      .catch((e) => console.log(e));
  }
}

let beforeCommentRow;

function showUpdateComment(commentNo, btn) {
  const temp = document.querySelector(".update-textarea");

  if (temp.length > 0) {
    if (confirm("다른 댓글이 수정 중 입니다. 현재 댓글을 수정 하시겠습니까?")) {
      temp[0].parentElement.innerHTML = beforeCommentRow;
    } else {
      return;
    }
  }

  const commentRow = btn.parentElement.parentElement;
  beforeCommentRow = commentRow.innerHTML;

  let beforeContent = commentRow.children[1].innerHTML;

  commentRow.innerHTML = "";

  const textarea = document.createElement("textarea");
  textarea.classList.add("update-textarea");

  beforeContent = beforeContent.replaceAll("&amp;", "&");
  beforeContent = beforeContent.replaceAll("&lt;", "<");
  beforeContent = beforeContent.replaceAll("&gt;", ">");
  beforeContent = beforeContent.replaceAll("&quot;", '"');

  textarea.value = beforeContent;

  commentRow.append(textarea);

  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");

  const updateBtn = document.createElement("button");
  updateBtn.innerText = "수정";
  updateBtn.setAttribute("onclick", "updateComment(" + commentNo + ", this)");

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.setAttribute("onclick", "updateCancel(this)");

  commentBtnArea.append(updateBtn, cancelBtn);
  commentRow.append(commentBtnArea);
}
//------------------------------------------
function updateCancel(btn) {
  if (confirm("댓글을 수정 하시겠습니까?")) {
    btn.parentElement.parentElement.innerHTML = beforeCommentRow;
  }
}
//------------------------------------------
function updateComment(commentNo, btn) {
  const commentContent = btn.parentElement.previousElementSibling.value;

  const dataObj = { commentNo: commentNo, commentContent: commentContent };

  fetch("/shorts/detail/videoComment", {
    method: "put",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result > 0) {
        alert("댓글이 수정되었습니다.");
        selectCommentList();
      } else {
        alert("댓글 수정을 실패하였습니다.");
      }
    })
    .catch((e) => console.log(e));
}
//-----------------------------------------
function showInsertComment(parentNo, btn) {
  const temp = document.getElementsByClassName("commentInsertContent");

  if (temp.length > 0) {
    if (
      confirm(
        "다른 답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?"
      )
    ) {
      temp[0].nextElementSibling.remove();
      temp[0].remove();
    } else {
      return;
    }
  }

  const textarea = document.createElement("textarea");
  textarea.classList.add("commentInsertContent");

  btn.parentElement.after(textarea);

  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");

  const insertBtn = document.createElement("button");
  insertBtn.innerText = "등록";
  insertBtn.setAttribute(
    "onclick",
    "insertChildComment(" + parentNo + ", this)"
  );

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.setAttribute("onclick", "insertCancel(this)");

  commentBtnArea.append(insertBtn, cancelBtn);

  textarea.after(commentBtnArea);
}
//-------------------------------------------------
function insertCancel(btn) {
  btn.parentElement.previousElementSibling.remove();
  btn.parentElement.remove();
}
//---------------------------------------------
function insertChildComment(parentNo, btn) {
  const commentContent = btn.parentElement.previousElementSibling.value;

  if (commentContent.trim().length == 0) {
    alert("답글 작성 후 등록 버튼을 클릭해주세요.");
    btn.parentElement.previousElementSibling.value = "";
    btn.parentElement.previousElementSibling.focus();
    return;
  }

  const dataObj = {
    commentContent: commentContent,
    memberNo: loginMemberNo,
    boardVideoNo: boardVideoNo,
    parentNo: parentNo,
  };

  fetch("/shorts/detail/videoComment", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result > 0) {
        alert("답글이 등록되었습니다.");
        selectCommentList();
      } else {
        alert("답글 등록에 실패했습니다...");
      }
    })
    .catch((err) => console.log(err));
}

// -----------------------------------------------------
const likeImgComment = document.querySelector("#imgComment");

likeImgComment.addEventListener("click", (e) => {
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

  const dataObj = { commentNo: commentNo, check: check };

  fetch("/shorts/detail/videoComment/like", {
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
