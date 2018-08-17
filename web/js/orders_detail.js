$(document).ready(function() {
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    var orderid = getQueryString("orderid");
    $.ajax({
        url : "http://localhost:8080/ketuan/backmanage/getorderdetails?orderid="+orderid,//请求地址
        dataType : "json",//数据格式
        type : "get",//请求方式
        async : false,//是否异步请求
        success : function(data) {   //如何发送成功
            var html = "";
            var order= data["data"]["orderDetails"][0];
            $("#order_sendName").html(order["sendName"]);
            $("#order_paymethod").html(order["paymethod"]);
            $("#order_productPrice").html(order["productPrice"]);
            $("#order_sendAddress").html(order["sendAddress"]);
            $("#order_productId").html(order["productId"]);
            $("#order_descript").html(order["descript"]);
           // $("#order_state").html(order["orderState"]);
            $("#order_productTitle").html(order["productTitle"]);
            $("#order_sendTel").html(order["sendTel"]);
            $("#order_id").html(order["id"]);
            $("#order_groupId").html(order["groupId"]);
            $("#order_userId").html(order["userId"]);
            $("#order_state").html(order["state"]);
            $("#order_sendZip").html(order["sendZip"]);
            $("#order_orderTime").html(order["orderTime"]);
            $("#order_meno").html(order["meno"]);
            $("#order_totalPrice").html(order["totalPrice"]);
            $("#order_trackCode").html(order["trackCode"]);
            $("#order_trackId").html(order["trackId"]);
            $("#order_payTime").html(order["payTime"]);
            $("#order_deliverTime").html(order["deliverTime"]);
            $("#order_receiptTime").html(order["receiptTime"]);
            $("#order_carriagePrice").html(order["carriagePrice"]);
            $("#order_sums").html(order["sums"]);
            $("#order_typeSpecification").html(order["typeSpecification"]);
            $("#order_outRefundNo").html(order["outRefundNo"]);
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