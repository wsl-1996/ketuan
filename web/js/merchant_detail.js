// $(function(){
//

//
//     function initMerchantDetail() {
//         var merchantid = getQueryString("merchantid");
//         $.ajax({
//             url:"http://localhost:8080/ketuan/applet/merchants/getdetail?merchantid="+merchantid,
//             success:function (data) {
//
//             },
//             error:function (e) {
//
//             }
//         })
//     }
    // initMerchantDetail();


    $(document).ready(function() {
        function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
        var merchantid = getQueryString("merchantid");
        $.ajax({
            url : "http://localhost:8080/ketuan/backmanage/merchantgetdetail?merchantid="+merchantid,//请求地址
            dataType : "json",//数据格式
            type : "get",//请求方式
            async : false,//是否异步请求
            success : function(data) {   //如何发送成功
                var html = "";
                var merchant = data["data"]["merchant"];
                $("#merchant_name").html(merchant["name"]);
                $("#merchant_discription").html(merchant["discription"]);
                $("#merchant_phone").html(merchant["phone"]);
                $("#merchant_address").html(merchant["address"]);
                $("#merchant_accountname").html(merchant["accountname"]);
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

    // $("#createBtn").on("click",function (e) {
    //     console.info("创建产品");
    //     var productname = $("#product_name")[0].value;
    //     var productinfo = $("#product_info")[0].value;
    //     var productcode = $("#product_classifyCode")[0].value;
    //     var productlable = $("#product_lable")[0].value;
    //     //var productownertype = $()
    //     var productownerid = $("#ownerid")[0].value;
    //     var productcount = $("#productcount")[0].value;
    //     var productprice = $("#productprice")[0].value;
    //     var productcost = $("#product_cost")[0].value;
    //     var productproduceadd = $("#productproduceadd")[0].value;
    //     var productpackstand = $("#productpackstand")[0].value;
    //     var productaftersale = $("#productaftersale")[0].value;
    //     $.ajax({
    //         url:"http://localhost:8080/ketuan/applet/products/create",
    //         type:"POST",
    //         data:{
    //             "productname":productname,
    //             "productinfo":productinfo,
    //             "productcode":productcode,
    //             "productlable":productlable,
    //             "productownertype":0,
    //             "productownerid":productownerid,
    //             "productcount":productcount,
    //             "productprice":productprice,
    //             "productcost":productcost,
    //             "productproduceadd":productproduceadd,
    //             "productpackstand":productpackstand,
    //             "productaftersale":productaftersale,
    //             "productFirstImg":firstImg,
    //             "productSlideimgs":slideImgs,
    //             "productContentimgs":contentImgs
    //         },
    //         dataType: "json",
    //         success:function (data) {
    //             console.info(JSON.stringify(data));
    //             if(data["failed"]){
    //                 alert("添加商品失败！"+data["failedMessage"]);
    //             }
    //             else{
    //                 alert("商品添加成功！");
    //             }
    //         },
    //         error:function (e) {
    //             console.error(JSON.stringify(e));
    //             alert("商家添加失败！"+JSON.stringify(e));
    //         }
    //
    //     })
    // })

//})
