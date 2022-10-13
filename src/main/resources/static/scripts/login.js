const emailRegEx = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]{1,3}/;
const emailInput = document.getElementById("uname");
const responseText = document.getElementById("responsetext");
const submitButton = document.getElementById("submit");
function isValidEmail() {
    if (!emailRegEx.test(emailInput.value)) {
        emailInput.style.outline = "2px solid red";
        responseText.textContent = "Enter a valid Email";
        submitButton.disabled = true;
    } else {
        emailInput.style.outline = "";
        responseText.textContent = "";
        submitButton.disabled = false;
    }
}
emailInput.addEventListener("input",isValidEmail);