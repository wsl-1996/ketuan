$(function(){
    $("#createBtn").on("click",function (e) {
        console.info("创建产品");
        var productname = $("#product_name")[0].value;
        var productinfo = $("#product_info")[0].value;
        var productcode = $("#product_classifyCode")[0].value;
        var productlable = $("#product_lable")[0].value;
        //var productownertype = $()
        var productownerid = $("#ownerid")[0].value;
        var productcount = $("#productcount")[0].value;
        var productprice = $("#productprice")[0].value;
        var productcost = $("#product_cost")[0].value;
        var productproduceadd = $("#productproduceadd")[0].value;
        var productpackstand = $("#productpackstand")[0].value;
        var productaftersale = $("#productaftersale")[0].value;
        $.ajax({
            url:"http://localhost:8080/ketuan/applet/products/create",
            type:"POST",
            data:{
                "productname":productname,
                "productinfo":productinfo,
                "productcode":productcode,
                "productlable":productlable,
                "productownertype":0,
                "productownerid":productownerid,
                "productcount":productcount,
                "productprice":productprice,
                "productcost":productcost,
                "productproduceadd":productproduceadd,
                "productpackstand":productpackstand,
                "productaftersale":productaftersale,
                "productFirstImg":firstImg,
                "productSlideimgs":slideImgs,
                "productContentimgs":contentImgs
            },
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("添加商品失败！"+data["failedMessage"]);
                }
                else{
                    alert("商品添加成功！");
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("商家添加失败！"+JSON.stringify(e));
            }

        })
    })

})
