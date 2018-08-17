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
            $("#product_price").html(product["price"]);
            $("#product_info").html(product["productInfo"]);
            $("#product_onlinetime").html(product["onlineTime"]);
            $("#productState").html(product["productState"]);
            $("#product_cost").html(product["productCost"]);
            $("#product_id").html(product["id"]);
            $("#product_ownerType").html(product["ownerType"]);
            $("#product_userId").html(product["userId"]);
            $("#product_merchantId").html(product["merchantId"]);
            $("#product_imagesAddress").html(product["imagesAddress"]);
            $("#product_starLevel").html(product["starLevel"]);
            $("#product_afterSale").html(product["afterSale"]);
            $("#product_evaluateLabel").html(product["evaluateLabel"]);
            $("#product_productFistImg").html(product["productFistImg"]);
            $("#product_onlineTime").html(product["onlineTime"]);
            $("#product_offlineTime").html(product["offlineTime"]);
            $("#product_saleVolumeHistory").html(product["saleVolumeHistory"]);
            $("#product_saleVolumeMonthly").html(product["saleVolumeMonthly"]);
            $("#product_productProduceAddress").html(product["productProduceAddress"]);
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