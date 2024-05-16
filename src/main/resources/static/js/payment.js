function updatePrice() {
    var isMember = document.getElementById("member").value;
    var priceField = document.getElementById("priceField");
    var receiptNumber = document.getElementById("bill").value;
    var setVigo = document.getElementById("setVigo1");

    if (isMember === true) {
        priceField.value = "0";
        setVigo.value = "회원"
    }else{
        setVigo.value = "비회원"
    }
    setVigo.value = isMember;
}

function billCheck() {
    var billInput = document.getElementById("bill");
    var billValue = billInput.value;

    if (billValue.includes("영화관") || billValue.includes("식당")) {
        billDiscount();
    }
}

function billDiscount(){
    var billInput = document.getElementById("bill");
    var billValue = billInput.value;
    var priceField = document.getElementById("priceField");
    var priceValue = parseInt(priceField.value);
    var realPrice = document.getElementById("realPrice");
    var realValue = parseInt(realPrice.value);
    var discountPrice
    var button = document.getElementById("billSubmit");
    var setVigo = document.getElementById("setVigo2");

    if (billValue.includes("영화관")) {
        discountPrice = 24000;
        setVigo.value = "영화관"
    }else if (billValue.includes("식당")){
        discountPrice = 12000;
        setVigo.value = "식당"
    }

    if(priceValue !== realPrice){
        discountPrice = discountPrice/2
    }

    var discountedPrice = priceValue - discountPrice;

    priceField.value = discountedPrice;
    button.disabled = true;
    billInput.disabled = true;
}

document.addEventListener("DOMContentLoaded", function() {
    updatePrice();
});


