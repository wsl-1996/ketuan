$(function(){


    $("#createBtn").on("click",function (e) {
        console.info("创建团购");
        var groupbuy_name = $("#groupbuy_name")[0].value;
        var groupbuy_info = $("#groupbuy_info")[0].value;
        var productid = $("#product_id")[0].value;
        var ownerid = $("#ownerid")[0].value;
        //var productownertype = $()
        //var productownerid = $("#ownerid")[0].value;
        var groupbuy_count = $("#groupbuy_count")[0].value;
        var groupbuy_price = $("#groupbuy_price")[0].value;
        var groupbuy_cost = $("#groupbuy_cost")[0].value;
        var groupbuy_deliver_add = $("#groupbuy_deliver_add")[0].value;
        var groupbuy_end_time = $("#groupbuy_end_time")[0].value;
        var groupbuy_start_time = $("#groupbuy_start_time")[0].value;
        // var productpackstand = $("#productpackstand")[0].value;
        // var productaftersale = $("#productaftersale")[0].value;
        $.ajax({
            url:"http://localhost:8080/ketuan/applet/groups/create",
            type:"POST",
            data:{
                "groupbuy_name":groupbuy_name,
                "groupbuy_info":groupbuy_info,
                "productid":productid,
                "ownerid":ownerid,
                "groupbuy_count":groupbuy_count,
                "groupbuy_price":groupbuy_price,
                "groupbuy_cost":groupbuy_cost,
                "groupbuy_deliver_add":groupbuy_deliver_add,
                "productFirstImg":firstImg,
                "productSlideimgs":slideImgs,
                "groupbuy_end_time":groupbuy_end_time,
                "groupbuy_start_time":groupbuy_start_time
            },
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("添加团购失败！"+data["failedMessage"]);
                }
                else{
                    alert("团购创建成功！");
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("团购创建失败！"+JSON.stringify(e));
            }

        })
    })

})
