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
        success : function(data) {
            if(data["failed"]){
                console.error(data["failedMessage"]);
                alert(data["failedMessage"]);
                return;
            }
            var product = data["data"]["productdetails"];
            $("#product_name").html(product["productName"]);
            $("#product_id").html(product["id"]);
            $("#product_price").html("￥"+product["price"]);
            $("#product_cost").html("￥"+product["productCost"]);
            $("#product_info").html(product["productInfo"]);

            $("#product_onlinetime").html(product["onlineTime"]);
            if(product["productState"]==0){
                $("#product_state").html("未上线");
            }
            else if(product["productState"]==1){
                $("#product_state").html("已上线");
            }
            else if(product["productState"]==1){
                $("#product_state").html("已下线");
            }
            $("#product_sale_Volume_History").html(product["saleVolumeHistory"]);
            $("#product_sale_Volume_Monthly").html(product["saleVolumeMonthly"]);
            $("#product_onlineTime").html(product["onlineTime"]);
            $("#product_offlineTime").html(product["offlineTime"]);
            $("#product_parameter").html(product["packStand"]);
            $("#product_afterSale").html(product["afterSale"]);
            $("#product_label").html(product["productLabel"]);

            $("#product_ownerType").html(product["ownerType"]);
            $("#product_userId").html(product["userId"]);
            $("#product_merchantId").html(product["merchantId"]);

            $("#product_imagesAddress").html(product["imagesAddress"]);

            $("#product_starLevel").html(product["starLevel"]);

            $("#product_evaluateLabel").html(product["evaluateLabel"]);

            $("#product_first_img").append('<img style="width: 200px;" src="'+product["productFistImg"]+'">');

            var slideImg = product["productSlideImg"];
            var slideImgs = JSON.parse(slideImg);
            for(var i=0;i<slideImgs.length;i++){
                $("#product_slide_imgs").append('<img style="width: 200px;" src="'+slideImgs[i]+'">');
            }

            var contentImg = product["imagesAddress"];
            var contentImgs = JSON.parse(contentImg);
            for(var i=0;i<contentImgs.length;i++){
                $("#product_detail_imgs").append('<img style="width: 200px;" src="'+contentImgs[i]+'">');
            }
            $("#product_productProduceAddress").html(product["productProduceAddress"]);
        },
        error:function (e) {
            console.error(e.toString());
        }
    })
})