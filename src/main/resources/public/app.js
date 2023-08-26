let getWithFetch = () => {
    let nameVar = document.getElementById("name").value;
    fetch("/hello?name=" + nameVar).then(res => res.json())
    .then((res) => {
        document.getElementById("getrespmsg").innerHTML = "";
        let container = document.createElement("div");
        createHTMLElement("h2", res.Title, container);
        createHTMLElement("h3", res.Year, container);
        createHTMLElement("p", `Director: ${res.Director}`, container);

        createHTMLElement("p", `Genre: ${res.Genre}`, container);
        createHTMLElement("p", `Rating: ${res.Rated}`, container);

        createImage(res.Poster, container);

        createHTMLElement("p", `Plot: ${res.Plot}`, container);
        document.getElementById("getrespmsg").appendChild(container);
        document.getElementById("getrespmsg").classList.remove("display-none");
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