const delivery = (content_body) => {
  content_body.innerHTML = `<div class="delivery">
                    <input type="text" id="sample4_postcode" placeholder="우편번호">
                    <input type="button" class="btn" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
                    <input type="text" id="sample4_roadAddress" placeholder="도로명주소">
                    <input type="text" id="sample4_jibunAddress" placeholder="지번주소"><br>
                    <span id="guide" style="color:#999;display:none"></span>
                    <input type="text" id="sample4_detailAddress" placeholder="상세주소">
                    <input type="text" id="sample4_extraAddress" placeholder="참고항목">
                    <button class="del_submit_btn" onclick='updateAddr()'>저장</button>
                </div>`;
}



// 비밀번호 수정
const changePassword = () => {
  const curPassword = document.querySelector('.cur_password').value;
  const password = document.querySelector('.change_password').value;

  if(curPassword === password){
    axios.patch('/mypage/member/updatePassword', {
      password: password
    }).then((result) => {
      if(result.data === 1){
        alert('수정되었습니다.');
      }
    }).catch((error) => {
      alert('잠시후 다시 시도해주세요.');
    })
  } else {
    alert("비밀번호가 일치하지 않습니다.");
  }
}

// 주소 수정
const updateAddr = () => {
  const postcode = document.querySelector('#sample4_postcode').value;
  const roadAddress = document.querySelector('#sample4_roadAddress').value;
  const jibunAddress = document.querySelector('#sample4_jibunAddress').value;
  const detailAddress = document.querySelector('#sample4_detailAddress').value;
  const extraAddress = document.querySelector('#sample4_extraAddress').value;

  const addr = `${postcode}_${roadAddress}_${jibunAddress}_${detailAddress}_${extraAddress}`;

  axios.put('/mypage/member/updateAddr', {
    addr: addr
  }).then((result) => {
    if(result.data === 1){
      alert("저장되었습니다.");
    }
  }).catch((error)=> {
    alert('잠시후 다시 시도해주세요.')
  })
}