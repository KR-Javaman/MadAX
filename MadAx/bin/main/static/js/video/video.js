const formElement = document.querySelector("#formTest");
const resultElement = document.querySelector(".result");
const videoChunk = document.querySelector("button");

// formElement.addEventListener("submit", async (e) => {
//   e.preventDefault();

// const formData = new FormData(formElement);

// const response = await fetch("/shorts/video", {
//   method: "POST",
//   body: formData,
// });

// if (response.ok) {
//   const result = await response.text();
//   resultElement.textContent = result;
// } else {
//   // throw new Error(`${response.status} ${response.statusText}`);
// }
// formElement.addEventListener("submit", () => {
const sendVideoChunks = () => {
  const chunkSize = 1024 * 1024;
  const file = document.querySelector("#upload-file").files[0];
  const resultElement = document.querySelector(".result");

  const totalChunks = Math.ceil(file.size / chunkSize);
  let currentChunk = 0;

  const sendChunk = () => {
    const start = chunkSize * currentChunk;
    const end = Math.min(start + chunkSize, file.size);
    const chunk = file.slice(start, end);

    const formData = new FormData();
    formData.append("chunk", chunk, file.name);
    formData.append("chunkNumber", currentChunk);
    formData.append("totalChunks", totalChunks);

    fetch("/shorts/video", {
      method: "POST",
      body: formData,
    })
      .then((resp) => {
        if (resp.status === 206) {
          resultElement.textContent =
            Math.round((currentChunk / totalChunks) * 100) + "%";
          currentChunk++;
          if (currentChunk < totalChunks) {
            sendChunk();
          }
        } else if (resp.status === 200) {
          resp.text().then((data) => (resultElement.textContent = data));
        }
      })
      .catch((e) => {
        console.log("error chunk");
      });
  };
  sendChunk();
};
