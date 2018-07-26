$(document).ready(function() {
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    var merchantid = getQueryString("merchantid");
    $.ajax({
        url : "http://localhost:8080/ketuan/applet/orders/getdetail?orderid="+orderid,//请求地址
        dataType : "json",//数据格式
        type : "get",//请求方式
        async : false,//是否异步请求
        success : function(data) {   //如何发送成功
            var html = "";
            var order = data["data"]["order"];
            $("#order_name").html(order["name"]);
            $("#order_discription").html(order["discription"]);
            $("#order_phone").html(order["phone"]);
            $("#order_address").html(order["address"]);
            $("#order_accountname").html(order["accountname"]);
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