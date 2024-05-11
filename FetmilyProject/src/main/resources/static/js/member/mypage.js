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

document.querySelector('.m').addEventListener('click', (e) => {
  if (e.target.checked) {
    document.querySelector('.w').checked = false;
  }
});

document.querySelector('.w').addEventListener('click', (e) => {
  if (e.target.checked) {
    document.querySelector('.m').checked = false;
  }
});

document.querySelector('.t').addEventListener('click', (e) => {
  if (e.target.checked) {
    document.querySelector('.f').checked = false;
  }
});

document.querySelector('.f').addEventListener('click', (e) => {
  if (e.target.checked) {
    document.querySelector('.t').checked = false;
  }
});

const registPet = () => {
  const petName = document.querySelector('.pet_name').value;
  const petType = document.querySelector('.pet_type').value;
  const birth = document.querySelector('.pet_birth').value;
  const weight = document.querySelector('.pet_weight').value;
  let sex;
  let neut;
  const etc = document.querySelector('.pet_etc').value;
  
  if (document.querySelector('.m').checked) {
    sex = '남';
  } else if (document.querySelector('.w').checked) {
    sex = '여';
  }else{
    sex = '';
  }

  if (document.querySelector('.t').checked) {
    neut = 'O';
  } else if (document.querySelector('.f').checked) {
    neut = 'X';
  }else{
    neut = '';
  }

  axios.post('/pet/insert', {
    petName: petName,
    petType: petType,
    birth: birth,
    weight: weight,
    sex: sex,
    neut: neut,
    etc: etc,
  }).then((result) => {
    if(result.data === 1){
      alert('등록되었습니다.');
    }
  }).catch((error) => {
    alert('잠시후 다시 시도해주세요.');
  })
}

const updateAddr = () => {
  const postcode = document.querySelector('#sample4_postcode').value;
  const roadAddress = document.querySelector('#sample4_roadAddress').value;
  const jibunAddress = document.querySelector('#sample4_jibunAddress').value;
  const detailAddress = document.querySelector('#sample4_detailAddress').value;
  const extraAddress = document.querySelector('#sample4_extraAddress').value;

  const addr = `${postcode}_${roadAddress}_${jibunAddress}_${detailAddress}_${extraAddress}`;

  axios.put('/member/update', {
    addr: addr
  }).then((result) => {
    if(result.data === 1){
      alert("저장되었습니다.");
    }
  }).catch((error)=> {
    alert('잠시후 다시 시도해주세요.')
  })
}

const updateMemberInfo = () => {
  const nickname = document.querySelector('.nickname').value;
  const curPassword = document.querySelector('.cur_password').value;
  const changePassword = document.querySelector('.change_password').value;

  axios.patch('/mypage/member/update', {
    nickname: nickname,
    curPassword: curPassword,
    changePassword: changePassword
  }).then((result) => {
    if(result.data === 1){
      alert('수정되었습니다.');
    }
  }).catch((error) => {
    alert('잠시후 다시 시도해주세요.');
  })
}