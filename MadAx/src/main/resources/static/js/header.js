// document.getElementById("nav-li-ul").style.display = "none";
// document.getElementsByClassName("header-menu").style.display = "none";
// const profile = document.querySelector("#memberHeaderProfile");
// const p = document.querySelector(".header-menu");

$("nav li").hover(
  function () {
    $("ul", this).stop().slideDown(200);
    $("ul", this).show();
  },
  function () {
    $("ul", this).stop().slideUp(200);
    $("ul", this).hide();
  }
);

// $("div>a").hover(
//   function () {
//     $("div", this).stop().slideDown(200);
//     $("div", this).show();
//   },
//   function () {
//     $("div", this).stop().slideUp(200);
//     $("div", this).hide();
//   }
// );

// function my() {
//   document.querySelector(".header-menu").classList.toggle("show");
// }
// window.onmouseover = function (e) {
//   if (!e.tartget.matches(".login-area")) {
//     var dropdowns = document.getElementsByClassName("header-menu");
//     var i;
//     for (i = 0; i < dropdowns.length; i++) {
//       var open = dropdowns[i];
//       if (open.classList.contains("show")) {
//         open.classList.remove("show");
//       }
//     }
//   }
// };
// const li = document.querySelector("#nav-li");
// const ul = document.querySelector("#nav-li-ul");

// li.addEventListener("mouseover", () => {
//   ul.stop().slideDown(200);
// });
// li.addEventListener("mouseout", () => {
//   ul.stop().slideUp(200);
// });