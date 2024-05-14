async function getList() {
  var result = await axios.post(`/shop/cart/info`);

  console.log(result)
  console.log(result.data)
  renderCartItems(result.data);
}

function createCartItemElement(item) {
  var cartItemDiv = document.createElement('div');
  cartItemDiv.classList.add('cart_item_list');

  // 카트 아이템 정보를 이용하여 동적으로 HTML을 생성합니다.
  cartItemDiv.innerHTML = `
        <input type="radio">
        <img src="/assets/shop/${item.item.itemImage}.svg" alt="">
        <div class="cart_item_info">
            <span>${item.item.brand}</span>
            <span>${item.item.itemName}</span>
            <div class="cart_item_option">
                <div class="btn_cart_item_cnt">
                    <span>-</span>
                    <span>1</span>
                    <span>+</span>
                </div>
                <div class="span">
                    <span class="sale_text"></span>
                    <span class="item_price">${(item.item.price).toLocaleString()}원</span>
                    <span class="item_sale_price">${(item.item.price*0.8).toLocaleString()}원</span>
                </div>
            </div>
        </div>
    `;

  // 생성된 카트 아이템을 반환합니다.
  return cartItemDiv;
}

// 카트 아이템 데이터를 순회하며 HTML을 생성하고, 해당 요소를 DOM에 추가합니다.
function renderCartItems(cartItems) {
  var cartItemsContainer = document.getElementById('cartItemsContainer');

  cartItems.forEach(function(item) {
    var cartItemElement = createCartItemElement(item);
    cartItemsContainer.appendChild(cartItemElement);
    var hr = document.createElement("hr");
    cartItemsContainer.appendChild(hr);
  });
}

// renderCartItems 함수를 호출하여 카트 아이템을 렌더링합니다.

window.addEventListener("load", getList);