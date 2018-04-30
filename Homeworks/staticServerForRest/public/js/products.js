function getGoods() {
    $.ajax({
        url: 'http://localhost:8080' + '/products?token=' + getCookie("Auth-Token"),
        type: 'get',
        success: function (data, textStatus, request) {
            const table = document.getElementById("goods_table");
            for (let i = 0; i < data.length; i++) {
                let row = table.insertRow(i + 1);
                const cellId = row.insertCell(0);
                const cellName = row.insertCell(1);
                const cellAmount = row.insertCell(1);
                cellId.innerHTML = data[i]["id"];
                cellName.innerHTML = data[i]["name"];
                cellAmount.innerHTML = data[i]["amount"];
            }
        }
    })
}

function add(name, amount) {
    var json = '{ "name": "' + name + '" ,"amount":"' + amount + '"}';
    console.log(json);
    $.ajax({
        url: 'http://localhost:8080/products',
        type: 'post',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function (data, textStatus, request) {
            token = data["value"];
            document.cookie = "Auth-Token=" + token;
            if (token !== null) {
                window.location = '/products.html';
            }
        }
    })
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}