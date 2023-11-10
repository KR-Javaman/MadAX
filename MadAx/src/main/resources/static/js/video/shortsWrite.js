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

// const upload = () => {
//   var fileCheck = uploadFile.value;
//   if (!fileCheck) {
//     alert("파일을 확인 해주세요");
//     return false;
//   }

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

const shortsVideo = document.querySelector("#upload-file");

const backupList = new Array(shortsVideo.length);

const upload = () => {
  var fileCheck = shortsVideo.value;
  if (!fileCheck) {
    alert("파일을 확인 해주세요");
    return;
  }

  if (shortsVideo.files[0].size > 10485760) alert("파일의 용량이 너무 큽니다.");
};

const writeForm = document.querySelector("#writeForm");

writeForm.addEventListener("submit", (e) => {
  const title = document.querySelector("[name=title]");
  const content = document.querySelector("[name=content]");

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
});
