const insertBtn = document.querySelector("#writeBtn");

if (insertBtn != null) {
  insertBtn.addEventListener("click", () => {
    location.href = `/shorts/edit/insert`;
  });
}

const options = document.querySelectorAll("#searchKey > option");
const search = document.querySelector("#search");

() => {
  const params = new URL(location.href).searchParams;
  const key = params.get("key");
  const query = params.get("query");

  if (key != null && query != null) {
    search.value = query;

    for (let option of options) {
      if (option.value == key) {
        option.selected == true;
        break;
      }
    }
  }
};

// const listOptions = document.querySelectorAll("#video-list-type > option");

// () => {
//   for (let listOption of listOptions) {
//     if (listOption.selected) {
//       listOption.value == obj.key;
//       break;
//     }
//   }
//   const obj = { listOption: listOption.value };
// };
