$("nav li").hover(
  function () {
    $("ul", this).stop().slideDown(200);
  },
  function () {
    $("ul", this).stop().slideUp(200);
  }
);

// const li = document.querySelector("#nav-li");
// const ul = document.querySelector("#nav-li-ul");

// li.addEventListener("mouseover", () => {
//   ul.stop().slideDown(200);
// });
// li.addEventListener("mouseout", () => {
//   ul.stop().slideUp(200);
// });
