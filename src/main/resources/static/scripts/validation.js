const emailRegEx = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]{1,3}/;
const pWordInputs = document.getElementsByName("pword");
const submitButton = document.getElementById("submit");
function toggleRedoutline(array,toggle)
{
    for (let i = 0; i < array.length; i++) {
        array[i].style.outline = toggle?"2px solid red":"";
    }
}

function validatePasswords() {
    if(pWordInputs[0].value.length==0)
    {
        toggleRedoutline(pWordInputs,true);
        document.getElementById("validpw").textContent = "Enter a password";
        return false;
    }
    if (pWordInputs[0].value != pWordInputs[1].value) {
        toggleRedoutline(pWordInputs,true);
        document.getElementById("validpw").textContent = "Passwords do not match";
        return false;
    } else {
        toggleRedoutline(pWordInputs,false);
        document.getElementById("validpw").textContent = "";
        return true;
    }
}
for (let i = 0; i < pWordInputs.length; i++) {
    pWordInputs[i].addEventListener('input', validatePasswords);
}


const emailInputs = document.getElementsByName("uname");
function validateEmail() {
    let responseText = document.getElementById("validemail");
    if (!emailRegEx.test(emailInputs[0].value)) {
        toggleRedoutline(emailInputs,true)
        responseText.textContent = "Enter a valid Email"
        return false;
    }
    else 
    {
        if (emailInputs[0].value != emailInputs[1].value) {
            toggleRedoutline(emailInputs,true)
            responseText.textContent = "Emails do not match.";
            return false;
        } else {
            toggleRedoutline(emailInputs,false)
            responseText.textContent = "";
            return true;
        }
    }
}
for (let i = 0; i < pWordInputs.length; i++) {
    emailInputs[i].addEventListener('input', validateEmail);
}

function checkInputIsValid()
{
    submitButton.disabled = !(validatePasswords()&&validateEmail())
}
submitButton.addEventListener("mouseenter",checkInputIsValid);