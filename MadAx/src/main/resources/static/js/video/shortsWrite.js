const shortsVideo = document.getElementById("upload-file");

const backupList = new Array(shortsVideo.length);

const upload = (videoInput, order) => {
  const maxSize = 1024 * 1024 * 10;
  const uploadFile = videoInput.files[0];
  const inputVideo = document.querySelector("[name=shortsVideo]");

  if (uploadFile.size > maxSize) {
    alert("파일의 용량이 너무 큽니다.");
    if (backupList[order] == undefined) {
      videoInput.value = "";
    } else {
      const temp = backupList[order].cloneNode(true);

      videoInput.after(temp);
      videoInput.remove();
      videoInput.temp;

      videoInput.addEventListener("change", () => {
        upload(videoInput, order);
      });
    }
    inputVideo.value = "";
    return;
  }
};

const writeForm = document.querySelector("#writeForm");

writeForm.addEventListener("submit", (e) => {
  const title = document.querySelector("[name=videoTitle]");
  const content = document.querySelector("[name=videoContent]");
  const videoFile = document.querySelector("[name=shortsVideo]");

  if (title.value.trim().length == 0) {
    alert("제목을 입력해주세요");
    e.preventDefault();
    title.value = "";
    title.focus();
    return;
  }
  if (content.value.trim().length == 0) {
    alert("내용을 입력해주세요");
    e.preventDefault();
    content.value = "";
    content.focus();
    return;
  }
  var fileCheck = videoFile.value;
  if (!fileCheck) {
    alert("파일을 확인 해주세요");
    return;
  }
});

function handleOnInput(el, maxlength) {
  if (el.value.length > maxlength) {
    el.value = el.value.substr(0, maxlength);
  }
}
