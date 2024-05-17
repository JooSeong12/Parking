function yearBox(){
    var yearResult = document.getElementById("year").value;

    if(2020<=yearResult<=2024){
        document.getElementById("month").disabled=false;
        document.getElementById("day").disabled=true;
    } else {
        document.getElementById("month").disabled=true;
    }

}

function monthBox(){
    var monthResult = document.getElementById("month").value;

    if(1<=monthResult<=12){
        document.getElementById("day").disabled=false;
    } else {
        document.getElementById("day").disabled=true;
    }
}

function submitForm() {
    var form = document.getElementById("record_form");
    var yearResult = document.getElementById("year");
    var monthResult = document.getElementById("month");
    var dayResult = document.getElementById("day");

    // 연도 값이 숫자인지 확인하고, 아니면 null로 설정
    if (isNaN(parseInt(yearResult.value)) || yearResult.value.trim() === "") {
        yearResult.value = null;
    }
    // 월 값이 숫자인지 확인하고, 아니면 null로 설정
    if (isNaN(parseInt(monthResult.value))) {
        monthResult.value = null;
    }
    // 일 값이 숫자인지 확인하고, 아니면 null로 설정
    if (isNaN(parseInt(dayResult.value))) {
        dayResult.value = null;
    }

    // 폼 제출
    form.submit();
}

