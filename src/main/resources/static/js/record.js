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