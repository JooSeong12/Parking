function updateTime() {
    var currentTime = new Date();
    var year = currentTime.getFullYear();
    var month = (currentTime.getMonth() + 1).toString().padStart(2, '0');
    var date = currentTime.getDate().toString().padStart(2, '0');
    var hours = currentTime.getHours().toString().padStart(2, '0');
    var minutes = currentTime.getMinutes().toString().padStart(2, '0');

    var dateTimeString = year + '-' + month + '-' + date + ' ' + hours + ':' + minutes;

    document.getElementById('current-time').value = dateTimeString;
}

document.addEventListener("DOMContentLoaded", function() {
    updateTime();
    setInterval(updateTime, 1000);
});

function submitForm() {

    // 폼 요소 가져오기
    var form = document.getElementById("frm");
    var carNumberInput = document.getElementById("number");
    if((/^9[1-7]|^9[0-9][0-7]/.test(carNumberInput.value))){
         alert("대형 화물차는 등록불가합니다.")
         document.getElementById("number").focus(); // 입력 필드로 포커스 이동
                             return false;
                             }

    // 폼 제출
    form.submit();
}