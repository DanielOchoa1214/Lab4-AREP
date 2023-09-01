let getWithFetch = () => {
    let nameVar = document.getElementById("get-name").value;
    fetch("/hello?name=" + nameVar).then(res => res.json())
    .then((res) => {
        let searchDiv = document.getElementById("getrespmsg");
        showSearch(res, searchDiv)
    })
};

let postWithFetch = () => {
    let nameVar = document.getElementById("post-name").value;
    fetch("/hello", {
        method: "POST",
        body: JSON.stringify({name: nameVar})
    }).then((res) => res.json()).then((res) => {
        let searchDiv = document.getElementById("postrespmsg");
        showSearch(res, searchDiv)
    })
};

let createHTMLElement = (tag, content, container) => {
    let element = document.createElement(tag);
    element.innerText = content;
    container.appendChild(element);
};

let createImage = (src, container) => {
    let element = document.createElement("img");
    element.src = src;
    container.appendChild(element);
};

let showSearch = (res, searchDiv) => {
    searchDiv.innerHTML = "";
    let container = document.createElement("div");
    createHTMLElement("h2", res.Title, container);
    createHTMLElement("h3", res.Year, container);
    createHTMLElement("p", `Director: ${res.Director}`, container);

    createHTMLElement("p", `Genre: ${res.Genre}`, container);
    createHTMLElement("p", `Rating: ${res.Rated}`, container);

    createImage(res.Poster, container);

    createHTMLElement("p", `Plot: ${res.Plot}`, container);
    searchDiv.appendChild(container);
    searchDiv.classList.remove("display-none");
}