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

  const brandNameList = [...new Set(Object.keys(brandMap))];
  getBrandNameList(brandNameList);





  // 카테고리별 출력 (사료, 간식, 장난감)
  renderList(categoryMap, "category");

  // 브랜드별 출력
  // renderBrandList(brandMap);

  // 신상품 출력
  renderList({ "item": newItems }, "new");
}


function getBrandNameList(brandNameList){
  const brandLists = document.querySelector(".food_category")
  brandNameList.forEach(function(item){
    var brandListLi = document.createElement("li");
    brandListLi.classList.add("food_brand")
    var brandListSpan = document.createElement("span");
    brandListLi.appendChild(brandListSpan);
    brandListSpan.textContent = item;
    brandLists.appendChild(brandListLi);
  })
}
// function renderBrandList(brandMap){
//   Object.keys(brandMap).forEach(function(key) {
//     const brandLists = document.querySelector(".food_category")
//     // brandLists.forEach(function(brandList) {
//     //   while (brandList.firstChild) {
//     //     brandList.removeChild(brandList.firstChild);
//     //   }
//     // });
//
//     const brandList = brandMap[key];
//     brandList.forEach(function(item){
//       var brandListLi = document.createElement("li");
//       brandListLi.classList.add("food_brand")
//       var brandListSpan = document.createElement("span");
//       brandListLi.appendChild(brandListSpan);
//       brandListSpan.textContent = item.brand;
//       brandLists.appendChild(brandListLi);
//     })
//   })}






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


    itemList.forEach(function(item) {
      // if(itemList.getAttribute('alt')=="category")
      var divItem = document.createElement("div");
      divItem.classList.add("item");

      var img = document.createElement("img");
      img.src = "/assets/shop/"+item.itemImage+".svg";
      img.alt = "itemImage";

      divItem.appendChild(img); // item div에 img 추가
      itemListCategory.appendChild(divItem); //  itemList에 item 추가

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
