function updatePrice() {
    var carTypeSelect = document.getElementById("carTypeSelect");
    var priceField = document.getElementById("priceField");
    var carType = carTypeSelect.value;
    var price;
    var carNumberInput = document.getElementById("number");

    //enum에서 data-price라는 필드를 통해 price를 js에 보내서
    //그 값을 필드에 보냄
    var selectedOption = carTypeSelect.options[carTypeSelect.selectedIndex];
    price = selectedOption.getAttribute('data-price');
    // 정액제 가격을 입력 필드에 표시
    priceField.value = price;

     if (selectedOption.text === "경차") {
            // 경차를 선택한 경우 앞 두 자리가 01부터 09 사이의 값인지 확인

                if (!(/^0[1-9]|^1\d{2}/.test(carNumberInput.value))) {
                    // 앞 두 자리가 01부터 09 사이의 값이 아닌 경우
                    alert("경차는 차량번호 앞 두 자리가 01부터 09 또는 100 부터 199 사이의 값이어야 합니다.");
                    carNumberInput.value = ""; // 입력값 초기화
                    document.getElementById("number").focus(); // 입력 필드로 포커스 이동
                    return false;
                }
        }

     if((/^9[1-7]|^9[0-9][0-7]/.test(carNumberInput.value))){
     alert("대형 화물차는 등록불가합니다.")
     document.getElementById("number").focus(); // 입력 필드로 포커스 이동
                         return false;
                         }

}

function resetForm() {
    // 폼 요소들을 가져와서 초기값으로 설정
    document.getElementById("username").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("number").value = "";
    document.getElementById("carTypeSelect").value = "";
    updatePrice(); // 정액제 가격도 초기값으로 업데이트
}
