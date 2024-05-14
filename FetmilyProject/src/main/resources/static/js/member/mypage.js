// 비밀번호 수정
const changePassword = () => {
  const curPassword = document.querySelector('.cur_password').value;
  const password = document.querySelector('.change_password').value;

  if(curPassword === password){
    axios.put('/mypage/member/updatePassword', {
      password: password
    }).then((result) => {
      if(result.data === 1){
        alert('수정되었습니다.');
        document.querySelector('.cur_password').value = '';
        document.querySelector('.change_password').value = '';
        window.location.href="/mypage/member/info";
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
      document.querySelector('#sample4_postcode').value = '';
      document.querySelector('#sample4_roadAddress').value = '';
      document.querySelector('#sample4_jibunAddress').value = '';
      document.querySelector('#sample4_detailAddress').value = '';
      document.querySelector('#sample4_extraAddress').value = '';
      window.location.href="/mypage/member/info";
    }
  }).catch((error)=> {
    alert('잠시후 다시 시도해주세요.')
  })
}