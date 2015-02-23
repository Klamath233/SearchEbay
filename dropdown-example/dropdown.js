var dropdown = document.getElementById("dropdown");
var input = document.getElementById("search");

console.log(dropdown);
console.log(input);
input.onkeyup = function() {
  dropdown.style.display = "block";
}

input.onblur = function() {
  dropdown.style.display = "none";
}

input.onfocus = function() {
  if (input.value !== "") {
    dropdown.style.display = "block";
  }
}
