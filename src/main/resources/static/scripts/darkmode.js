const DarkmodeButton = document.getElementById("darkmode");
const styleSheets = document.styleSheets;
function toggleDark() {
    let mode=document.getElementById("mode");
    if(mode.getAttribute("href").match(/LightMode/)){
        mode.setAttribute("href","./styles/SiteWideStyles/DarkMode.css");
        DarkmodeButton.textContent = "Lightmode"
    }else
    {
        if(mode.getAttribute("href").match(/DarkMode/))
        {
            mode.setAttribute("href","./styles/SiteWideStyles/LightMode.css")
            DarkmodeButton.textContent = "Darkmode"
        }
    }
    
}
DarkmodeButton.addEventListener("click", toggleDark);
