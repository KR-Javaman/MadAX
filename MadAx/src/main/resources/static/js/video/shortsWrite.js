// const formElement = document.querySelector("#formTest");
// const resultElement = document.querySelector(".result");
// const videoChunk = document.querySelector("button");

// // formElement.addEventListener("submit", async (e) => {
// //   e.preventDefault();

// // const formData = new FormData(formElement);

// // const response = await fetch("/shorts/video", {
// //   method: "POST",
// //   body: formData,
// // });

// // if (response.ok) {
// //   const result = await response.text();
// //   resultElement.textContent = result;
// // } else {
// //   // throw new Error(`${response.status} ${response.statusText}`);
// // }
// // formElement.addEventListener("submit", () => {
// const sendVideoChunks = () => {
//   const chunkSize = 1024 * 1024;
//   const file = document.querySelector("#upload-file").files[0];
//   const resultElement = document.querySelector(".result");

//   const totalChunks = Math.ceil(file.size / chunkSize);
//   let currentChunk = 0;

//   const sendChunk = () => {
//     const start = chunkSize * currentChunk;
//     const end = Math.min(start + chunkSize, file.size);
//     const chunk = file.slice(start, end);

//     const formData = new FormData();
//     formData.append("chunk", chunk, file.name);
//     formData.append("chunkNumber", currentChunk);
//     formData.append("totalChunks", totalChunks);

//     fetch("/shorts/video", {
//       method: "POST",
//       body: formData,
//     })
//       .then((resp) => {
//         if (resp.status === 206) {
//           resultElement.textContent =
//             Math.round((currentChunk / totalChunks) * 100) + "%";
//           currentChunk++;
//           if (currentChunk < totalChunks) {
//             sendChunk();
//           }
//         } else if (resp.status === 200) {
//           resp.text().then((data) => (resultElement.textContent = data));
//         }
//       })
//       .catch((e) => {
//         console.log("error chunk");
//       });
//   };
//   sendChunk();
// };

// -----------------------------------------------------------------------------
// const uploadFile = document.querySelector("#upload-file");
// const button = document.querySelector("button");

//   var checkSize = "N";
//   if (uploadFile.files[0].size > 10485760) {
//     if (confirm("파일의 용량이 너무 큽니다. 최적하 하시겠습니까?")) {
//       checkSize = "Y";
//     }
//   }

//   var formData = new FormData();
//   formData.append("video", uploadFile.files[0]);
//   formData.append("checkSize", checkSize);

//   $.ajax({
//     type: "POST",
//     url: "upload",
//     async: false,
//     enctype: "multipart/form-data",
//     contentType: false,
//     processData: false,
//     dataType: "JSON",
//     data: formData,
//     success: function (result) {
//       console.log(result);
//     },
//     error: function (error) {
//       console.log(error);
//     },
//   });
// };

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
  // var fileCheck = uploadFile.value;
  // if (!fileCheck) {
  //   alert("파일을 확인 해주세요");
  //   return false;
  // }
  // if (uploadFile.size > maxSize) {
  //   alert("파일의 용량이 너무 큽니다.");
  //   inputVideo.value = "";
  //   return;
  // }
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
