document.addEventListener('DOMContentLoaded', () => {
document.querySelectorAll('li').forEach((element) => {
  element.addEventListener('click', (e) => {
    const text = e.target.innerText;
    document.querySelector('.content_title').innerText = text;
    contentLoad(text);
  })
})

const contentLoad = (text) => {
  const content_body = document.querySelector('.etc');

  switch(text){
    case '주문내역':
      content_body.innerHTML = '';
      orderHistory(content_body);
      break;
    case '배송지 관리':
      content_body.innerHTML = '';
      delivery(content_body);
      break;
    case '좋아요 누른 상품':
      content_body.innerHTML = '';
      likeProduct(content_body);
      break;
    case '고객센터':
      content_body.innerHTML = '';
      serviceCenter(content_body);
      break;
    case '내가 쓴 게시글':
      content_body.innerHTML = '';
      myPost(content_body);
      break;
    case '댓글 단 게시글':
      content_body.innerHTML = '';
      myReview(content_body);
      break;
    case '좋아요 누른 게시글':
      content_body.innerHTML = '';
      likePost(content_body);
      break;
    case '내 반려동물':
      content_body.innerHTML = '';
      myPet(content_body);
      break;
    case '후원중인 동물':
      content_body.innerHTML = '';
      supportPet(content_body);
      break;
  }
}

const orderHistory = (content_body) => {
  content_body.innerHTML = `<div>주문내역입니다.</div>`;
}

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

const likeProduct = (content_body) => {
  content_body.innerHTML = `<div>좋아요 상품입니다.</div>`;
}

const serviceCenter = (content_body) => {
  content_body.innerHTML = `<div>고객센터입니다.</div>`;
}

const myPost = (content_body) => {
  content_body.innerHTML = `<div>내가 쓴 게시글입니다.</div>`;
}

const myReview = (content_body) => {
  content_body.innerHTML = `<div>내가 쓴 댓글입니다.</div>`;
}

const likePost = (content_body) => {
  content_body.innerHTML = `<div>좋아요 게시글입니다.</div>`;
}

const myPet = (content_body) => {
  content_body.innerHTML = `<div>내 반려동물입니다.</div>`;
}

const supportPet = (content_body) => {
  content_body.innerHTML = `<div>후원중인 동물입니다.</div>`;
}
})

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