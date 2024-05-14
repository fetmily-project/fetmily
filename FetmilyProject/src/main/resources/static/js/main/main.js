const slide = document.querySelector(".banner-cont");
let slideWidth = slide.clientWidth;

const prevBtn = document.querySelector(".slide-prev-button");
const nextBtn = document.querySelector(".slide-next-button");

let slideItems = document.querySelectorAll(".banner");

const maxSlide = slideItems.length;

let currSlide = 1;

const pagination = document.querySelector(".slide-pagination");

for(let i = 0; i<maxSlide; i++){
    if(i===0) pagination.innerHTML += `<li class="active">•</li>`;
    else pagination.innerHTML += `<li>•</li>`;
}

const paginationItems = document.querySelectorAll(".slide-pagination > li");

const startSlide = slideItems[0];
const endSlide = slideItems[slideItems.length -1];
const startElem = document.createElement("div");
const endElem = document.createElement("div");

endSlide.classList.forEach((c) => endElem.classList.add(c));
endElem.innerHTML = endSlide.innerHTML;

startSlide.classList.forEach((c) => startElem.classList.add(c));
startElem.innerHTML = startSlide.innerHTML;

slideItems[0].before(endElem);
slideItems[slideItems.length -1].after(startElem);

slideItems = document.querySelectorAll(".banner");

let offset = slideWidth + currSlide;
slideItems.forEach((i) => {
    i.setAttribute("style", `left: ${-offset}px`);
});

function nextMove() {
    currSlide++;
    if(currSlide <= maxSlide){
        const offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    }else{
        currSlide = 0;
        let offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            i.setAttribute("style", `transitio: ${0}s; left: ${-offset}px`);
        });
        currSlide++;
        offset = slideWidth * currSlide;
        setTimeout(() => {
            slideItems.forEach((i) => {
                i.setAttribute("style", `transition: ${0.15}s; left: ${-offset}px`);
            });
        }, 0);
        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    }
}
function prevMove(){
    currSlide--;
    if(currSlide>0){
        const offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`)
        });

        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    }else{
        currSlide = maxSlide + 1;
        let offset = slideWidth * currSlide;

        slideItems.forEach((i) => {
            i.setAttribute("style", `transition: ${0}s; left: ${-offset}px`);
        });
        currSlide--;
        offset = slideWidth * currSlide;
        setTimeout(() => {
            slideItems.forEach((i) => {
                i.setAttribute("style", `transition: ${0.15}s; left: ${-offset}px`);
            });
        }, 0);
        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    }
}

nextBtn.addEventListener("click", () => {
    nextMove();
});
prevBtn.addEventListener("click", () => {
    prevMove();
});

window.addEventListener("resize", () => {
    slideWidth = slide.clientWidth;
});

for(let i = 0; i< maxSlide; i++){
    paginationItems[i].addEventListener("click", () => {
        currSlide = i + 1;
        const offset = slideWidth * currSlide;

        slideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    });
}

let startPoint = 0;
let endPoint = 0;

slide.addEventListener("mousedown", (e) => {
    startPoint = e.pageX;
});

slide.addEventListener("mouseup", (e) => {
    endPoint = e.pageX;
    if(startPoint < endPoint){
        prevMove();
    }else if(startPoint > endPoint){
        nextMove();
    }
});

let loopInterval = setInterval(() => {
    nextMove();
}, 3000);

slide.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
});

slide.addEventListener("mouseout", () => {
    loopInterval = setInterval(() => {
        nextMove();
    }, 3000);
});