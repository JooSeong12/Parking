function extend() {
    var dateField = document.getElementById("date");
    var extendBox = document.getElementById("extendBox");
    var priceField = document.getElementById("price");
    var carType = document.getElementById("carType").value;
    var hiddenPrice = document.getElementById("hiddenPrice");

    if (extendBox.checked) {
        var currentDate = new Date(dateField.value);
        currentDate.setMonth(currentDate.getMonth() + 1);
        var year = currentDate.getFullYear();
        var month = ("0" + (currentDate.getMonth() + 1)).slice(-2);
        var day = ("0" + currentDate.getDate()).slice(-2);
        var newDate = year + "-" + month + "-" + day;
        dateField.value = newDate;
        var currentPrice = hiddenPrice.value;

        priceChange();

    } else {
        var originalDate = new Date(dateField.value);
        originalDate.setMonth(originalDate.getMonth() - 1);
        var year = originalDate.getFullYear();
        var month = ("0" + (originalDate.getMonth() + 1)).slice(-2);
        var day = ("0" + originalDate.getDate()).slice(-2);
        var originalDateString = year + "-" + month + "-" + day;
        dateField.value = originalDateString;

        priceField.value = "";
    }
}

function priceChange(){
    var priceField = document.getElementById("price");
    var carType = document.getElementById("carType");
    var extendBox = document.getElementById("extendBox");

    var price;


    if (extendBox.checked) {
        //enum에서 data-price라는 필드를 통해 price를 js에 보내서
        //그 값을 필드에 보냄
        var selectedOption = carType.options[carType.selectedIndex];
        price = selectedOption.getAttribute('data-price');


        // 정액제 가격을 입력 필드에 표시
        priceField.value = price;
    }
}


function submitForm() {
    if (extendBox.checked) {
        var hiddenPrice = document.getElementById("hiddenPrice");
        var priceField = document.getElementById("price");
        hiddenPrice.value = parseInt(hiddenPrice.value) + parseInt(priceField.value);
    }
    var carType = document.getElementById("carType");
    var selectedOption = carType.options[carType.selectedIndex];
    var carNumberInput = document.getElementById("number");

        if((/^9[1-7]|^9[0-9][0-7]/.test(carNumberInput.value))){
             alert("대형 화물차는 등록불가합니다.")
             document.getElementById("number").focus(); // 입력 필드로 포커스 이동
                                 return false;
                                 }
         if (selectedOption.text === "경차") {
                if((!/^0[1-9]|^1[0-9][0-9]/.test(carNumberInput.value))){
                             alert("경차는 차량번호 앞 두 자리가 01부터 09 또는 100 부터 199 사이의 값이어야 합니다.")
                            document.getElementById("number").focus(); // 입력 필드로 포커스 이동
                                               return false;
                                                }
                                                }


    // 폼 요소 가져오기
    var form = document.getElementById("frm");

    // 폼 제출
    form.submit();
}
