$(document).ready(function() {
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    var productid = getQueryString("productid");
    $.ajax({
        url : "http://localhost:8080/ketuan/backmanage/getproductinfo?productid="+productid,//请求地址
        dataType : "json",//数据格式
        type : "get",//请求方式
        async : false,//是否异步请求
        success : function(data) {   //如何发送成功
            var html = "";
            var product = data["data"]["productdetails"];
            $("#product_name").html(product["productName"]);
            //Document.getElementById("product_ price").innerHTML = product["price"];
            $("#product_price").html(product["price"]);
            $("#product_info").html(product["productInfo"]);
            $("#product_onlinetime").html(product["onlineTime"]);
            $("#productState").html(product["productState"]);
            // for(var i=0;i<data.length;i++){    //遍历data数组
            //     var ls = data[i];
            //     html +="<li><a href='second page text.html?newsid="+ls.news_id+"'class='infNews_wrod_a'><span>"+ls.news_name+"</span></a><span class='date'>"+ls.news_time+"</span></li>";
            // }
            // $("#ulul").html(html); //在html页面id=ulul的标签里显示html内容
        },
        error:function (e) {
            console.error(e.toString());
        }
    })
})