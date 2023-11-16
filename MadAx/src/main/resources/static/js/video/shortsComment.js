// const likeClickComment = document.querySelector(".likeClickComment");

const selectCommentList = () => {
  fetch("/videoComment?boardVideoNo=" + boardVideoNo)
    .then((resp) => resp.json())
    .then((cList) => {
      console.log(cList);

      const videoCommentList = document.getElementById("commentList");
      videoCommentList.innerHTML = "";

      for (let videoComment of cList) {
        const commentRow = document.createElement("li");
        commentRow.classList.add("comment-row");

        if (videoComment.parentNo != 0) commentRow.classList.add("child-comment");
        if (videoComment.commentDelFl == "Y") commentRow.innerText = "삭제된 댓글 입니다.";
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
          //-------------------------------------------
          const commentWriter2 = document.createElement("p");
          commentWriter2.classList.add("video-comment-content");

          const commentContent = document.createElement("span");
          commentContent.classList.add("comment-content");
          commentContent.innerText = videoComment.commentContent;

          const likeArea = document.createElement("span");
          likeArea.classList.add("like-area");
          //-------------------------------------------------------
          const heartComment = document.createElement("i");
          heartComment.classList.add("fa-heart");
          heartComment.classList.add("comment-heart");
          if (videoComment.likeClickComment == 0) heartComment.classList.add("fa-regular");
          else heartComment.classList.add("fa-solid");
          //-------------------------------------------------------------------
          heartComment.setAttribute("comment-no", videoComment.commentNo);

          const likeCountComment = document.createElement("span");
          likeCountComment.classList.add("count-heart");
          likeCountComment.innerHTML = videoComment.likeCountComment;

          likeArea.append(heartComment, likeCountComment);

          commentWriter2.append(commentContent, likeArea);
          //-----------------------------------------------------------------------

          commentRow.append(commentWriter, commentWriter2);

          if (loginCheck) {
            const commentBtnArea = document.createElement("div");
            commentBtnArea.classList.add("comment-btn-area");

            const childCommentBtn = document.createElement("button");
            childCommentBtn.setAttribute("onclick", "showInsertComment(" + videoComment.commentNo + ", this)");

            childCommentBtn.innerText = "답글 달기";

            commentBtnArea.append(childCommentBtn);

            if (loginMemberNo == videoComment.memberNo) {
              const updateBtn = document.createElement("button");
              updateBtn.innerText = "수정";

              updateBtn.setAttribute("onclick", "showUpdateComment(" + videoComment.commentNo + ", this)");

              const deleteBtn = document.createElement("button");
              deleteBtn.innerText = "삭제";

              deleteBtn.setAttribute("onclick", "deleteVideoComment(" + videoComment.commentNo + ")");

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

  fetch("/videoComment", {
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
//-------------------------------------------------
function deleteVideoComment(commentNo) {
  if (confirm("정말로 삭제 하시겠습니까?")) {
    fetch("/videoComment", {
      method: "DELETE",
      headers: { "Content-type": "application/json" },
      body: commentNo,
    })
      .then((resp) => resp.text())
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
//----------------------------------------------------
let beforeCommentRow;

function showUpdateComment(commentNo, btn) {
  const temp = document.getElementsByName("update-textarea");

  if (temp.length > 0) {
    if (confirm("다른 댓글이 수정 중 입니다. 현재 댓글을 수정 하시겠습니까?")) {
      temp[0].parentElement.getElementsByClassName("comment-content").innerHTML = beforeCommentRow;
    } else {
      return;
    }
  }

  const commentRow = btn.parentElement.parentElement;
  beforeCommentRow = commentRow.innerHTML;

  let beforeContent = commentRow.children[1].children[0].innerText;

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
  if (confirm("댓글 수정을 취소 하시겠습니까?")) {
    btn.parentElement.parentElement.innerHTML = beforeCommentRow;
  }
}
//------------------------------------------
function updateComment(commentNo, btn) {
  const commentContent = btn.parentElement.previousElementSibling.value;

  const dataObj = { commentNo: commentNo, commentContent: commentContent };

  fetch("/videoComment", {
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
    if (confirm("다른 답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?")) {
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
  insertBtn.setAttribute("onclick", "insertChildComment(" + parentNo + ", this)");

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

  fetch("/videoComment", {
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
const likeComments = document.querySelectorAll(".comment-heart");

for (let likeComment of likeComments) {
  likeComment.addEventListener("click", (e) => {
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

    const commentNo = e.target.getAttribute("comment-no");

    const data = { check: check, commentNo: commentNo };

    fetch("/videoComment/like", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
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
        // selectCommentList();
      })
      .catch((e) => {
        console.log(e);
      });
  });
}
