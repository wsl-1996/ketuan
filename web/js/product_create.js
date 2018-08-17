$(function(){

    var val=$("#product_lable").val();
    $("#label_1").on("click",function (e) {
        var label_before1=$("#product_lable").val();//先获取之前输入的值
        var label_1=$("#label_1")[0].innerHTML;
        $("#product_lable").val(label_before1+" "+label_1);//点击标签一，input出现包邮
    })
    $("#label_2").on("click",function (e) {
        var label_before2=$("#product_lable").val();
        var  label_2=$("#label_2")[0].innerHTML;
        $("#product_lable").val(label_before2+" "+label_2);
    })
    $("#label_3").on("click",function (e) {
        var label_before3=$("#product_lable").val();
        var label_3=$("#label_3")[0].innerHTML;
        $("#product_lable").val(label_before3+" "+label_3);
    })
    $("#label_4").on("click",function (e) {
        var label_before4=$("#product_lable").val();
        var label_4=$("#label_4")[0].innerHTML;
        $("#product_lable").val(label_before4+" "+label_4);
    })

//商品产地地址的获取
    $('#distpicker').on("change",function (data) {
        var address_before1=$("#productproduceadd").val();
        var province1= $('#province option:selected').val();
        var city1= $('#city option:selected').val();
        var district1= $('#district option:selected').val();
        $("#productproduceadd").val(address_before1+province1+city1+district1);

        //var address_before2=$("#productproduceadd").val();
       //var nothing1= $('#productproduceadd').value;
        var province2= $('#province option:selected').val();
        var city2= $('#city option:selected').val();
        var district2= $('#district option:selected').val();
        $("#productproduceadd").val(province2+city2+district2);

        //var address_before3=$("#productproduceadd").val();
        var province3= $('#province option:selected').val();
        var city3= $('#city option:selected').val();
        var district3= $('#district option:selected').val();
        $("#productproduceadd").val(province3+city3+district3);

    })



    //发货地址的获取值
   $('#distpicker2').on("change",function (data) {
       var address_before1=$("#productshipment").val();    //当我选中省级时，input显示三级下拉值
       var province1= $('#province2 option:selected').val();
       var city1= $('#city2 option:selected').val();
       var district1= $('#district2 option:selected').val();
       $("#productshipment").val(address_before1+province1+city1+district1);

       var province2= $('#province2 option:selected').val();
       var city2= $('#city2 option:selected').val();
       var district2= $('#district2 option:selected').val();
       $("#productshipment").val(province2+city2+district2);

       //var address_before3=$("#productproduceadd").val();
       var province3= $('#province2 option:selected').val();
       var city3= $('#city2 option:selected').val();
       var district3= $('#district2 option:selected').val();
       $("#productshipment").val(province3+city3+district3);
    })



    //供货人的选择和获取
    $("#ownerid").on("click",function (e) {
        $("#product_create_search").show();
      //该input标签的记忆功能取消或者不取消
        $("#ownerid").attr('placeholder',null);
    })

    $("#searchBtn").on("click", function (e) {
        var key = $("#searchKey")[0].value;
        if (key == "" || key == undefined) {
            getAllUsers();
            return
        }
        $.ajax({
            url: "http://localhost:8080/ketuan/backmanage/usersearch?key=" + key,
            type: "GET",
            dataType: "json",
            success: function (data) {
                console.info(JSON.stringify(data));
                if (data["failed"]) {
                    alert("搜索用户失败！请试着搜索商户！" + data["failedMessage"]);
                }
                else {
                    var users = data["data"]["users"];
                    $("#searchList tbody").html("");
                    for (var i = 0; i < users.length; i++) {
                        appendUserTr(users[i]);
                    }
                }
            },
            error: function (e) {
                console.error(JSON.stringify(e));
                alert("搜索用户失败！" + JSON.stringify(e));
            }
        })
    })

    $("#searchBtn").on("click", function (e) {
        var key = $("#searchKey")[0].value;
        if (key == "" || key == undefined) {
            getAllMerchants();
            return
        }
        $.ajax({
            url: "http://localhost:8080/ketuan/backmanage/merchantsearch?key=" + key,
            type: "GET",
            dataType: "json",
            success: function (data) {
                console.info(JSON.stringify(data));
                if (data["failed"]) {
                    alert("搜索商家失败！" + data["failedMessage"]);
                }
                else {
                    var merchants = data["data"]["merchants"];
                    $("#searchList tbody").html("");
                    for (var i = 0; i < merchants.length; i++) {
                        appendMerchantTr(merchants[i]);
                    }
                }
            },
            error: function (e) {
                console.error(JSON.stringify(e));
                alert("搜索商家失败！请试着搜索用户！" + JSON.stringify(e));
            }
        })
    })

    function getAllMerchants() {
        $.ajax({
            url: "http://localhost:8080/ketuan/backmanage/merchantlistall",
            type: "GET",
            dataType: "json",
            success: function (data) {
                console.info(JSON.stringify(data));
                if (data["failed"]) {
                    alert("获取商家失败！" + data["failedMessage"]);
                }
                else {
                    var merchants = data["data"]["merchants"];
                    $("#searchList tbody").html("");
                    for (var i = 0; i < merchants.length; i++) {
                        appendMerchantTr(merchants[i]);
                    }
                }
            },
            error: function (e) {
                console.error(JSON.stringify(e));
                alert("获取商家失败！" + JSON.stringify(e));
            }
        })
    }

    function getAllUsers() {
        $.ajax({
            url: "http://localhost:8080/ketuan/backmanage/userlistall",
            type: "GET",
            dataType: "json",
            success: function (data) {
                console.info(JSON.stringify(data));
                if (data["failed"]) {
                    alert("获取用户失败！" + data["failedMessage"]);
                }
                else {
                    var users = data["data"]["users"];
                    $("#searchList tbody").html("");
                    for (var i = 0; i < users.length; i++) {
                        appendUserTr(users[i]);
                    }
                }
            },
            error: function (e) {
                console.error(JSON.stringify(e));
                alert("获取用户失败！" + JSON.stringify(e));
            }
        })
    }

    function appendUserTr(user) {
        var sex = "男";
        switch (user["sex"]) {
            case 0:
                sex = "男";
                break;
            case 1:
                sex = "女";
                break;
            case 2:
                sex = "未知";
                break;
        }
        var s = '<tr class="user_tr" id="' + user["id"] + '">'
            + '          <td class="project-status">'
            + '             <span class="label label-primary">' + sex
            + '          </td>'
            + '          <td class="project-title">'
            + '              <img alt="image" class="img-circle" src="' + user["headImgUrl"] + '">'
            + '              <a href="#">' + user["nickname"]
            + '              </a>'
            + '          </td>'
            + '          <td class="project-completion">'
            + '              <small>' + user["province"] + '</small>'
            + '              <small>' + user["city"] + '</small>'
            + '          </td>'
            + '          <td class="project-people">'
            + '              <small>业绩：</small>' + user["controbution"]
            + '          </td>'
            + '      </tr>'
        /*$("#usersList tbody").html("");*/
        $("#searchList tbody").append(s);
            $("#searchList tbody tr").on("click", getid);
    }
    
    function getid(data) {
        var currentId = $(this).attr("id");
        $("#ownerid").val(currentId);
        $("#product_create_search").hide();
    }

    function appendMerchantTr(merchant) {
        var s = '<tr id="' + merchant["id"] + '" class="merchant_tr">'
            + '          <td class="project-status">'
            + '             <span class="label label-primary">@'
            + '          </td>'
            + '          <td class="project-title">'
            + '              <a href="#">' + merchant["name"]
            + '              </a>'
            + '          </td>'
            + '          <td class="project-completion">'
            + '              <small>联系电话：</small>' + merchant["phone"]
            + '          </td>'
            + '         <td class="project-people">'
            + '          </td>'
            + '      </tr>'

        // for (var i=0;i<$("#product_createList tbody tr").length;i++) {
        //
        // }
        $("#searchList tbody").append(s);
            $("#searchList tbody tr").on("click", getid);

    }

   // $("#product_createList tbody tr").live('click', function (){alert("执行了");})



    $("#input_productCreate input").on("click",function () {
        var name_choose=$('input:radio[name="a"]:checked').val();
        if(name_choose==1) {
            //选择个人;
            getAllUsers();
        }
        if(name_choose==0) {
            //radio选择商家
            getAllMerchants();
        }
    })

    $("#createBtn").on("click",function (e) {
        console.info("创建产品");


         //商品供货量的约束
        var productcount1=$("#productcount").val();
        if(productcount1==null){
           alert("请输入商品供货量信息！");
        }else if (!(/(^[0-9]*[1-9][0-9]*$)/.test(productcount1))){
            alert("商品供货量输入的不是正整数");return false;
        }


        //零售价和成本价的约束
        var productprice1=$("#productprice").val();
        if(productprice1==null){
            alert("请输入商品零售价信息！");
        }else if (!(/(^[0-9]*[1-9][0-9]*$)/.test(productprice1))){
            alert("商品的零售价输入的不是正整数");return false;
        }
        var product_cost1=$("#product_cost").val();
        if(product_cost1==null){
            alert("请输入商品成本价信息！");
        }else if (!(/(^[0-9]*[1-9][0-9]*$)/.test(product_cost1))){
            alert("商品的成本价输入的不是正整数");return false;
        }


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
