let info_str;
let info_list = []; // info_list를 배열로 변경
let infoDetail_str; // Detail 정보
let infoDetail_list = []; // Detail 정보
let currentPage=1;  // currentPage를 전역 변수로 이동
let homelessList;
document.addEventListener("DOMContentLoaded", () => {
    async function get1(){
        const url = 'http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '='+'mTASxcdx8qmF%2Bu4K2Zd1AJpWqdByb4qdUTnED9o8FNIKO1JPPBqQrVrsmFkMK3MnDpYRL6YKGaoqT5oT2ouD2g%3D%3D'; /*Service Key*/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('100'); /**/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
        try {
            const res = await axios.get(url + queryParams);
            homelessList = res.data.response.body.items.item;

            // 중복 제거
            homelessList = _.uniqBy(homelessList, 'noticeNo');

            homelessList.forEach((item) => {

                let info_str = '';
                info_str += `<img src="${item.popfile}" width="300" height="300" style="border-radius: 20px;"><br>`;
                info_str += `<div class="post-container-expl" data-noticeNo="${item.noticeNo}">`; // 백틱 제거
                item.sexCd === "F" ? info_str += `성별: 여<br>` : info_str += `성별: 남<br>`;
                info_str += `나이: ${item.age}<br>`;
                info_str += `품종: ${item.kindCd}<br>`;
                info_str += `체중: ${item.weight}<br>`;
                info_str += `색상: ${item.colorCd}<br>`;
                item.neuterYn === "Y" ? info_str += `중성화여부: 완료<br>` : info_str += `중성화여부: 미완료<br>`;
                info_str += `</div>`
                info_list.push(info_str);

                let infoDetail_str = '';
                infoDetail_str += `<div class="info-detail-container">`;
                infoDetail_str += `<div class="info-detail-image">`;
                infoDetail_str += `<img src="${item.popfile}" style="width: 541px; height:511px; border-radius: 20px;"><br>`;
                infoDetail_str += `</div>`;
                infoDetail_str += `<div class="info-detail-content">`;
                item.sexCd === "F" ? infoDetail_str += `성별: 여<br>` : infoDetail_str += `성별: 남<br>`;
                infoDetail_str += `나이: ${item.age}<br>`;
                infoDetail_str += `품종: ${item.kindCd}<br>`;
                infoDetail_str += `체중: ${item.weight}<br>`;
                infoDetail_str += `색상: ${item.colorCd}<br>`;
                item.neuterYn === "Y" ? infoDetail_str += `중성화여부: 완료<br>` : infoDetail_str += `중성화여부: 미완료<br>`;
                infoDetail_str += "<br>";
                infoDetail_str += `공고번호: ${item.noticeNo}<br>`;
                infoDetail_str += `발견장소: ${item.happenPlace}<br>`;
                infoDetail_str += `공고시작일: ${item.noticeSdt.substr(0, 4)}년 ${item.noticeSdt.substr(4,2)}월 ${item.noticeSdt.substr(6,2)}일<br> `;
                infoDetail_str += `공고종료일: ${item.noticeEdt.substr(0, 4)}년 ${item.noticeEdt.substr(4,2)}월 ${item.noticeEdt.substr(6,2)}일<br> `;
                infoDetail_str += `특징: ${item.specialMark}<br>`;
                infoDetail_str += `보호소: ${item.careNm}<br>`;
                infoDetail_str += `보호소전화번호: ${item.careTel}<br>`;
                infoDetail_str += `보호장소: ${item.careAddr}<br>`;
                infoDetail_str += "<br>";
                infoDetail_str += `관할기관: ${item.orgNm}<br>`;
                infoDetail_str += `담당자: ${item.chargeNm}<br>`;
                infoDetail_str += `담장자연락처: ${item.officetel}<br>`;
                infoDetail_str += `</div>`;
                infoDetail_str += `</div>`;

                infoDetail_list.push(infoDetail_str);
            });

            setPageButtons();
            setPageOf(currentPage);

            /**
             * 페이지 번호 버튼 클릭 리스너
             */
            pageNumberButtons.forEach((numberButton) => {
                numberButton.addEventListener('click', (e) => {
                    currentPage = +e.target.innerHTML;
                    console.log(currentPage);
                    setPageOf(currentPage);
                    moveSelectedPageHighlight();
                });
            });
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }
    get1();

    const COUNT_PER_PAGE = 6; // 페이지 당 보여줄 게시물 수
    const numberButtonWrapper = document.querySelector('.number-button-wrapper'); // 페이지네이션 버튼 wrapper
    const ul = document.querySelector('#homelessCategory'); // 게시물을 담을 unordered list
    const prevButton = document.querySelector('.prev-button'); // 이전 페이지 버튼
    const nextButton = document.querySelector('.next-button'); // 이후 페이지 버튼
    prevButton.innerHTML = "< 이전";
    nextButton.innerHTML = "이후 >";
    let pageNumberButtons; // 페이지 버튼들
// 초기 페이지 번호
    /**
     * 필요한 페이지 번호 개수 구하기
     * @returns {number} - 필요한 페이지 번호 개수
     */
    const getTotalPageCount = () => {
        return Math.ceil(info_list.length / COUNT_PER_PAGE);
    };
    /**
     * 필요한 페이지 번호 수에 맞게 페이지 버튼 구성하기
     */
    const setPageButtons = () => {
        numberButtonWrapper.innerHTML = '';
        for (let i = 1; i <= getTotalPageCount(); i++) {
            numberButtonWrapper.innerHTML += `<span class="number-button"> ${i} </span`;
        }
        numberButtonWrapper.firstChild.classList.add('selected');
        pageNumberButtons = document.querySelectorAll('.number-button');
    };
    /**
     * 페이지에 해당하는 게시물 ul에 넣어주기
     * @param {number} pageNumber - 이동할 페이지 번호
     */
    function setPageOf(pageNumber) {
        ul.innerHTML = '';
        const startIndex = COUNT_PER_PAGE * (pageNumber - 1);
        const endIndex = COUNT_PER_PAGE * pageNumber;
        for (let i = startIndex; i < endIndex && i < info_list.length; i++) {
            const li = document.createElement('li');
            const postContainer = document.createElement('div');
            postContainer.className = 'post-container';
            // 공고번호를 data-noticeNo 속성으로 추가
            // postContainer.setAttribute('data-noticeNo', homelessList[i].noticeNo);
            postContainer.innerHTML = info_list[i];
            console.log(postContainer);
            li.append(postContainer);
            ul.append(li);
        }
    }
// post-container-expl를 클릭했을 때 다른 페이지로 이동하는 클릭 이벤트 핸들러 추가
    document.addEventListener('click', (e) => {
        // 클릭된 요소가 post-container인지 확인
        if (e.target.classList.contains('post-container-expl')) {
            // post-container-expl를 클릭한 경우
            const noticeNo = e.target.getAttribute('data-noticeNo'); // 공고번호 가져오기

            console.log(noticeNo);
            // noticeNo를 가지고 있는 detail을 찾아라
            // const index = homelessList.findIndex((item) => item.noticeNo === noticeNo);
            //
            // const infoDetail = index !== -1 ? infoDetail_list[index] : null; // 인덱스로부터 자세한 정보 가져오기
            const infoDetail = infoDetail_list.find((detail) => detail.includes(noticeNo));
            console.log(infoDetail);

            const jsonData = {
                noticeNo: noticeNo,
                infoDetail: infoDetail
            };

            const jsonDataStr = JSON.stringify(jsonData);

            // 현재 페이지를 변경하면서 데이터를 전달
            window.location.href = '/homeless/homelessDetail?data=' + encodeURIComponent(jsonDataStr);
        }
    });
    /**
     * 페이지 이동에 따른 css 클래스 적용
     */
    const moveSelectedPageHighlight = () => {
        // pageNumberButtons = document.querySelectorAll('.number-button'); // 페이지 버튼들
        pageNumberButtons.forEach((numberButton) => {
            if (numberButton.classList.contains('selected')) {
                numberButton.classList.remove('selected');
            }
        });
        pageNumberButtons[currentPage - 1].classList.add('selected');

    };
    /**
     * 이전 버튼 클릭 리스너
     */
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage -= 1;
            setPageOf(currentPage);
            moveSelectedPageHighlight();
        }
    });
    /**
     * 이후 버튼 클릭 리스너
     */
    nextButton.addEventListener('click', () => {
        if (currentPage < getTotalPageCount()) {
            currentPage += 1;
            setPageOf(currentPage);
            moveSelectedPageHighlight();
        }
    });


    const loading_page=document.getElementById("load");
    window.onload = function(){
        setTimeout(function() {
            loading_page.style.display ='none';
        }, 3000);

    }

});
