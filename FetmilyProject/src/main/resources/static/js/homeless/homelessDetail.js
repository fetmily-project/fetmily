document.addEventListener('DOMContentLoaded', () => {
    processDataFromUrl();
});

// 다른 페이지에서 JSON 데이터를 받아 처리하는 함수
function processDataFromUrl() {
    const queryString = window.location.search;

    const urlParams = new URLSearchParams(queryString);

    const jsonDataStr = urlParams.get('data');

    const jsonData = JSON.parse(decodeURIComponent(jsonDataStr));


    // jsonData 객체에서 필요한 데이터를 추출하여 사용
    const noticeNo = jsonData.noticeNo;
    const infoDetail = jsonData.infoDetail;

    // 여기서부터는 추출한 데이터를 활용하여 원하는 동작을 수행할 수 있습니다.
    const detailContainer = document.querySelector('.detail-container');
    detailContainer.innerHTML = infoDetail;
}

document.addEventListener('click', (e) => {
    if(e.target.classList.contains('category') || e.target.classList.contains('category-button')){
        window.history.back();
    }
});