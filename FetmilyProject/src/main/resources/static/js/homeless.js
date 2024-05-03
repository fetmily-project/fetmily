async function get1(){
    const url = 'http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic'; /*URL*/
    let queryParams = '?' + encodeURIComponent('serviceKey') + '='+'mTASxcdx8qmF%2Bu4K2Zd1AJpWqdByb4qdUTnED9o8FNIKO1JPPBqQrVrsmFkMK3MnDpYRL6YKGaoqT5oT2ouD2g%3D%3D'; /*Service Key*/
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('3'); /**/
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
    let homelessList;
    const info = document.querySelector('body');
    await axios.get(url+queryParams)
        .then(res => {
            homelessList = res.data.response.body.items.item;
            console.log(homelessList)
        })
        .catch();
    for(let i=0; i<homelessList.length; i++){
        homelessList[i].sexCd="F"? "여" : "남";
        info.innerHTML+=`성별: ${homelessList[i].sexCd}<br>`;
        info.innerHTML+=`나이: ${homelessList[i].age}<br>`;
        info.innerHTML+=`품종: ${homelessList[i].kindCd}<br>`;
        info.innerHTML+=`체중: ${homelessList[i].weight}<br>`;
        info.innerHTML+=`색상: ${homelessList[i].colorCd}<br>`;
        homelessList[i].neuterYn="Y"? "완료" : "미완료";
        info.innerHTML+=`중성화여부: ${homelessList[i].neuterYn}<br>`;
        info.innerHTML+="<br>";
        info.innerHTML+= `공고번호: ${homelessList[i].noticeNo}<br>`;
        info.innerHTML+= `발견장소: ${homelessList[i].happenPlace}<br>`;
        info.innerHTML+= `공고시작일: ${homelessList[i].noticeSdt.substr(0, 4)}년 ${homelessList[i].noticeSdt.substr(4,2)}월 ${homelessList[i].noticeSdt.substr(6,2)}일<br> `;
        info.innerHTML+= `공고종료일: ${homelessList[i].noticeEdt.substr(0, 4)}년 ${homelessList[i].noticeEdt.substr(4,2)}월 ${homelessList[i].noticeEdt.substr(6,2)}일<br> `;
        info.innerHTML+=`특징: ${homelessList[i].specialMark}<br>`;
        info.innerHTML+=`보호소: ${homelessList[i].careNm}<br>`;
        info.innerHTML+=`보호소전화번호: ${homelessList[i].careTel}<br>`;
        info.innerHTML+=`보호장소: ${homelessList[i].careAddr}<br>`;
        info.innerHTML+="<br>";
        info.innerHTML+=`관할기관: ${homelessList[i].orgNm}<br>`;
        info.innerHTML+=`담당자: ${homelessList[i].chargeNm}<br>`;
        info.innerHTML+=`담장자연락처: ${homelessList[i].officetel}<br>`;

        // document.write("<img src=`${homelessList[i].popfile}`>");
        info.innerHTML+=`<img src="${homelessList[i].popfile}" width="250" height="200"><br>`;

    }
}
get1()