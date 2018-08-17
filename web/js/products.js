$(function(){

    $("#loading-example-btn").on("click",function (e){
        getAllProducts();
    })

    $("#searchBtn").on("click",function (e){
        var key = $("#searchKey")[0].value;
        if(key==""||key==undefined){
            getAllProducts();
            return
        }
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/productsearch?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索商品失败！"+data["failedMessage"]);
                }
                else{
                    var products = data["data"]["products"];
                        $("#productsList tbody").html("");
                    for(var i=0;i<products.length;i++){
                        appendTr(products[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("搜索团购失败！"+JSON.stringify(e));
            }
        })
    })

    function getAllProducts() {
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/productlistall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取商品失败！"+data["failedMessage"]);
                }
                else{
                    var products = data["data"]["products"];
                        $("#productsList tbody").html("");
                    for(var i=0;i<products.length;i++){
                        appendTr(products[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("获取商品失败！"+JSON.stringify(e));
            }
        })
    }

    function appendTr(product){
        var state = "已上线";
        switch (product["productState"]){
            case 0:
                state="未上线";
                break;
            case 1:
                state="已上线";
                break;
            case 2:
                state="已下线";
                break;
        }
        // var type = "固定团";
        // if(product["groupType"]==1){
        //     type = "动态团";
        // }
        var s = '<tr id="'+product["id"]+'">'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">'+state+'</span>'
            +'          </td>'
            +'          <td class="project-title">'
            +'              <a href="products_detail.html?productid='+product["id"]+'">'+product["productName"]
            +'              </a>'
            +'              <br/>'
            +'              <small>上线时间：'+product["onlineTime"]+'</small>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>历史销量:</small>'+product["saleVolumeHistory"]
            +'              <br/>'
            +'              <small>本月销量:</small>'+product["saleVolumeMonthly"]
            +'          </td>'
            + '         <td class="project-people">'
            +'          </td>'
            +'          <td class="project-actions">'
            +'              <span class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 删除</span>'
            +'              <button id="product_offline" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 下线 </button>'
            +'          </td>'
            +'      </tr>'
        /*$("#productsList tbody").html("");*/
        $("#productsList tbody").append(s);

           //把上线改为下线
        for (var i=0;i<$("#productsList tbody tr").length;i++) {
            $("#productsList tbody tr").eq(i).children('td').last().children('button').on("click", function (e) {
               var productId=$(this).parent(".project-actions").parent('tr').attr("id");
                var val= $(this).parent('.project-actions').prevAll('.project-status').children('span').text();
                if (val=="未上线") {
                    return;
                }else{
                   var productState=0;
                    $.ajax(
                        {
                            url: "http://localhost:8080/ketuan/backmanage/productOffline",
                            data: {
                                productState: productState,
                                productId:productId
                            },
                            type: "GET",
                            dataType: "json",
                            success: function (data) {
                                if (data = "1") {
                                    alert("添加成功")
                                } else {
                                    alert("添加失败")
                                }
                            }
                        }
                    )

                }
                })





        }





    }
    getAllProducts();




})
