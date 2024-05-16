function updateTime() {
    var currentTime = new Date();
    var year = currentTime.getFullYear();
    var month = currentTime.getMonth() + 1;
    var date = currentTime.getDate();
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();

    // 날짜와 시간을 문자열로 생성
    var dateTimeString = year + '-' + month + '-' + date + ' ' + hours + ':' + minutes;

    // 현재 시간을 입력란에 설정
    document.getElementById('current-time').value = dateTimeString;
}

document.addEventListener("DOMContentLoaded", function() {
    // 페이지가 로드될 때 실행되는 코드
    updateTime();
    setInterval(updateTime, 1000); // 1초마다 시간을 업데이트
});


function submitForm() {

    // 폼 요소 가져오기
    var form = document.getElementById("frm");

    // 폼 제출
    form.submit();
}