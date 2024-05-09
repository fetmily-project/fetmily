document.querySelectorAll('li').forEach((element) => {
  element.addEventListener('click', (e) => {
    const text = e.target.innerText;
    document.querySelector('.content_title').innerText = text;
    contentLoad(text);
  })
})

const contentLoad = (text) => {
  const content_body = document.querySelector('.etc');
  const del = document.querySelector('.delivery');
  switch(text){
    case '주문내역':
      orderHistory(content_body);
      del.style.display = 'none';
      break;
    case '배송지 관리':
      content_body.innerHTML = '';
      del.style.display = 'block';
      break;
    case '좋아요 누른 상품':
      likeProduct(content_body);
      del.style.display = 'none';
      break;
    case '고객센터':
      serviceCenter(content_body);
      del.style.display = 'none';
      break;
    case '내가 쓴 게시글':
      myPost(content_body);
      del.style.display = 'none';
      break;
    case '댓글 단 게시글':
      myReview(content_body);
      del.style.display = 'none';
      break;
    case '좋아요 누른 게시글':
      likePost(content_body);
      del.style.display = 'none';
      break;
    case '내 반려동물':
      myPet(content_body);
      del.style.display = 'none';
      break;
    case '후원중인 동물':
      supportPet(content_body);
      del.style.display = 'none';
      break;
  }
}

const orderHistory = (content_body) => {
  content_body.innerHTML = `<div>주문내역입니다.</div>`;
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

