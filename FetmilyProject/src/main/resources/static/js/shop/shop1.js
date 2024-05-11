
  // 서버에서 데이터를 비동기적으로 요청하여 받아오는 함수
  async function getList() {
  const result = await axios.get(`/shop/list/item`);

  renderItems(result.data);

  return result.data;
}



  // 필터링된 아이템을 HTML로 출력하는 함수
  function renderItems(itemList) {
  // 카테고리별, 브랜드별, 신상품 필터링
  var categoryMap = {};
  var brandMap = {};
  var newItems = [];

  var oneWeekAgo = new Date();
  oneWeekAgo.setDate(oneWeekAgo.getDate() - 7);

  itemList.forEach(function (item) {
  // 카테고리별로 그룹화
  if (!categoryMap[item.category]) {
  categoryMap[item.category] = [];
}
  categoryMap[item.category].push(item);

  // 브랜드별로 그룹화
  if (!brandMap[item.brand]) {
  brandMap[item.brand] = [];
}
  brandMap[item.brand].push(item);

  // 등록 일주일 이내의 신상품 필터링
  if (new Date(item.regDate) > oneWeekAgo) {
  newItems.push(item);
}
});

  // 카테고리별 출력
  var categoryListElement = document.getElementById("categoryList");
  renderList(categoryMap, categoryListElement);

  // 브랜드별 출력
  var brandListElement = document.getElementById("brandList");
  renderList(brandMap, brandListElement);

  // 신상품 출력
  var newItemListElement = document.getElementById("newItemList");
  renderList({ 신상품: newItems }, newItemListElement);
}

  // 리스트를 HTML로 출력하는 함수
  function renderList(itemMap, parentElement) {
  Object.keys(itemMap).forEach(function (key) {
    var itemList = itemMap[key];
    var categoryHeading = document.createElement("h3");
    categoryHeading.textContent = key;
    parentElement.appendChild(categoryHeading);
    var itemListUl = document.createElement("ul");

    itemList.forEach(function (item) {
      var listItem = document.createElement("li");
      console.log(item.itemName);
      listItem.textContent = item.itemName;
      itemListUl.appendChild(listItem);
    });

    parentElement.appendChild(itemListUl);
  });
}
