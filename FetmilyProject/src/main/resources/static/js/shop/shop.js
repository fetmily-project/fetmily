//
// 서버에서 데이터를 비동기적으로 요청하여 받아오는 함수
async function getList(kind) {
  const result = await axios.get(`/shop/list/`+kind);

  renderItems(result.data);


  return result.data;
}








function renderItems(itemList) {
  // 카테고리별, 브랜드별, 신상품 필터링

  var categoryMap = {};
  var brandMap = {};
  var newItems = [];

  var oneWeekAgo = new Date();
  oneWeekAgo.setDate(oneWeekAgo.getDate() - 7);

  itemList.forEach(function(item) {
    // 카테고리별로 Map에 저장(Key=카테고리)
    if (!categoryMap[item.category]) {
      categoryMap[item.category] = [];
    }
    console.log('item==='+item);
    console.log('categoryMap====='+categoryMap[item.category])
    categoryMap[item.category].push(item);

    console.log(item.category)
    // 브랜드별로 Map에 저장(Key=브랜드)
    if (!brandMap[item.brand]) {
      brandMap[item.brand] = [];
    }
    brandMap[item.brand].push(item);



    // 등록 일주일 이내의 신상품 필터링
    if (new Date(...item.regdate) > oneWeekAgo) {
      newItems.push(item);
    }
  });

  var mealBrands = [];
  for (const [brandName, items] of Object.entries(brandMap)) {
    const mealItems = items.filter(item => item.category === "meal");
    if (mealItems.length > 0) {
      mealBrands.push(brandName);
    }
  }



  getBrandNameList(mealBrands);







  // 카테고리별 출력 (사료, 간식, 장난감)
  renderList(categoryMap, "category");

  // 브랜드별 출력
  // renderBrandList(brandMap);

  // 신상품 출력
  renderList({ "item": newItems }, "new");

  addVisible();
  categoryAddVisible('toy');
  categoryAddVisible('meal');
  categoryAddVisible('snack');
}

function addVisible(){
  const newItemList = document.querySelector('.item_list_new_item')
  const newItems = newItemList.querySelectorAll('.item');
  const btn = document.querySelector('.btn_new_all')


  newItems.forEach((item, index) => {
    if (index >= 8) {
      item.classList.add('new_visible');
    }
  });

  btn.addEventListener('click', function() {
    newItems.forEach(item => {
      if (item.classList.contains('new_visible')) {
        item.style.display = 'block';
      }
    });
  });
}
function categoryAddVisible(keyword){
  const newItemList = document.querySelector('.item_list_category_'+keyword)
  const newItems = newItemList.querySelectorAll('.item');
  const btn = document.querySelector('.btn_'+keyword+'_all')

  newItems.forEach((item, index) => {
    if (index >= 8) {
      item.classList.add(keyword+'_visible');
    }
  });

  btn.addEventListener('click', function() {
    newItems.forEach(item => {
      if (item.classList.contains(keyword+'_visible')) {
        item.style.display = 'block';
      }
    });
  });
}

// function getBrandNameList(mealBrands){
//   const brandLists = document.querySelector(".food_category")
//   mealBrands.forEach(function(item){
//     var brandListLi = document.createElement("li");
//     brandListLi.classList.add("food_brand")
//     var brandListSpan = document.createElement("span");
//     brandListLi.appendChild(brandListSpan);
//     brandListSpan.textContent = item;
//     brandLists.appendChild(brandListLi);
//   })
// }
function getBrandNameList(mealBrands){
  // 기존 브랜드 목록 삭제
  const brandLists = document.querySelector(".food_category");
  const existingBrands = brandLists.querySelectorAll(".food_brand");
  existingBrands.forEach(function(item) {
    item.remove();
  });
  var brandListLi = document.createElement("li");
  brandListLi.classList.add("food_brand");
  var brandListSpan = document.createElement("span");
  brandListLi.appendChild(brandListSpan);
  brandListSpan.textContent = "All";
  brandLists.appendChild(brandListLi);


  // 새로운 브랜드 목록 생성
  mealBrands.forEach(function(item){
    var brandListLi = document.createElement("li");
    brandListLi.classList.add("food_brand");
    var brandListSpan = document.createElement("span");
    brandListLi.appendChild(brandListSpan);
    brandListSpan.textContent = item;
    brandLists.appendChild(brandListLi);
  });

  const btnBrand = document.querySelectorAll(".food_brand")

  btnBrand.forEach(function(item, index) {
    item.addEventListener('click', function() {
      // 각 요소를 클릭했을 때 실행되는 코드 작성

      const spanValue = this.querySelector('span').innerText;
      getBrandList(spanValue);
    });
  });
}




async function getBrandList(brand){
  const result = await axios.get(`/shop/list/brand?brand=`+brand);

  renderBrandList(result.data);
  categoryAddVisible('meal');
}
//

function renderBrandList(brand) {
  var itemLists = document.querySelectorAll(".item_list_category_meal");
  itemLists.forEach(function(itemList) {
    while (itemList.firstChild) {
      itemList.removeChild(itemList.firstChild);
    }
  });

  // for (var key in itemMap) {
  //   var itemList = itemMap[key];
  brand.forEach(function(item) {
    var divItem = document.createElement("div");
    divItem.classList.add("item");
    divItem.addEventListener("click", function() {
      window.location.href = "/shop/item/" + item.itemId;
    })

    var img = document.createElement("img");
    img.src = "/assets/shop/"+item.itemImage+".svg";
    img.alt = "itemImage";
    divItem.appendChild(img); // item div에 img 추가

    var itemListUl = document.createElement("ul");
    var listItemBrand = document.createElement("li");
    var listItemName = document.createElement("li");
    var listItemPrice = document.createElement("li");
    var listItemSalePrice = document.createElement("li");
    var saleText = document.createElement("span");

    itemListUl.classList.add("item_info_list");
    listItemBrand.classList.add("item_info_brand");
    listItemName.classList.add("item_info_name");
    listItemPrice.classList.add("item_info_price");
    listItemSalePrice.classList.add("item_info_sale_price");
    saleText.classList.add("sale_text")
    saleText.textContent = " 20% ";

    listItemBrand.textContent = item.brand;
    listItemName.textContent = item.itemName;
    listItemPrice.textContent = item.price.toLocaleString();
    listItemSalePrice.textContent = (item.price * 0.8).toLocaleString() + "원";

    itemListUl.appendChild(listItemBrand);
    itemListUl.appendChild(listItemName);
    itemListUl.appendChild(listItemPrice);
    listItemSalePrice.prepend(saleText);
    itemListUl.appendChild(listItemSalePrice);
    divItem.appendChild(itemListUl);

    brand.forEach(function(item) {
      var itemListCategory = document.querySelector(".item_list_category_meal");
      itemListCategory.appendChild(divItem); // itemList에 item 추가
    });


  });
  // }
}



// 리스트를 HTML로 출력하는 함수
function renderList(itemMap, keyword) {

  Object.keys(itemMap).forEach(function(key) {

    var itemLists = document.querySelectorAll(".item_list_" + keyword+"_"+key);
    itemLists.forEach(function(itemList) {
      while (itemList.firstChild) {
        itemList.removeChild(itemList.firstChild);
      }
    });

    var itemListCategory = document.querySelector(".item_list_"+keyword+"_"+key);
    var itemList = itemMap[key];
    // console.log(itemMap[key])

    let index = 0;

    itemList.forEach(function(item) {
      // if(itemList.getAttribute('alt')=="category")
      var divItem = document.createElement("div");
      divItem.classList.add("item");
      divItem.addEventListener("click", function() {
        window.location.href = "/shop/item/" + item.itemId;
      })

      // if (index >= 8){
      //   divItem.classList.add(key+"_visible");
      // }


      var img = document.createElement("img");
      img.src = "/assets/shop/"+item.itemImage+".svg";
      img.alt = "itemImage";

      divItem.appendChild(img); // item div에 img 추가
      itemListCategory.appendChild(divItem); //  itemList에 item 추가
      index++;

      var itemListUl = document.createElement("ul");
      var listItemBrand = document.createElement("li");
      var listItemName = document.createElement("li");
      var listItemPrice = document.createElement("li");
      var listItemSalePrice = document.createElement("li");
      var saleText = document.createElement("span");

      itemListUl.classList.add("item_info_list");

      listItemBrand.classList.add("item_info_brand");
      listItemName.classList.add("item_info_name");
      listItemPrice.classList.add("item_info_price");
      listItemSalePrice.classList.add("item_info_sale_price");
      saleText.classList.add("sale_text")
      saleText.textContent = " 20% ";



      listItemBrand.textContent = item.brand;
      listItemName.textContent = item.itemName;
      listItemPrice.textContent = item.price.toLocaleString();
      listItemSalePrice.textContent = (item.price*0.8).toLocaleString()+"원";



      itemListUl.appendChild(listItemBrand);
      itemListUl.appendChild(listItemName);
      itemListUl.appendChild(listItemPrice);
      listItemSalePrice.prepend(saleText);
      itemListUl.appendChild(listItemSalePrice);
      divItem.appendChild(itemListUl);
    });
  });
}

// 페이지 로드 시 서버에서 데이터 요청
document.addEventListener('DOMContentLoaded', () => {
  const btnAll = document.querySelector(".icon_all");
  const btnDog = document.querySelector(".icon_dog");
  const btnCat = document.querySelector(".icon_cat");


  btnAll.addEventListener('click', () => {
    getList("all");
  })
  btnDog.addEventListener('click', () => {
    getList("dog");
  })
  btnCat.addEventListener('click', () => {
    getList("cat");
  })
})

// 페이지 로드 시 서버에서 데이터 요청
window.addEventListener("load", getList('all'));
