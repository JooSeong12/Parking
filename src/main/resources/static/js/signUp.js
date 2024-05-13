function updatePrice() {
    var carTypeSelect = document.getElementById("carTypeSelect");
    var priceField = document.getElementById("priceField");
    var carType = carTypeSelect.value;
    var price;

    //enum에서 data-price라는 필드를 통해 price를 js에 보내서
    //그 값을 필드에 보냄
    var selectedOption = carTypeSelect.options[carTypeSelect.selectedIndex];
    price = selectedOption.getAttribute('data-price');

    // 정액제 가격을 입력 필드에 표시
    priceField.value = price;
}

function resetForm() {
    // 폼 요소들을 가져와서 초기값으로 설정
    document.getElementById("username").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("number").value = "";
    document.getElementById("carTypeSelect").value = "";
    updatePrice(); // 정액제 가격도 초기값으로 업데이트
}
