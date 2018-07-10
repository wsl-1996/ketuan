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
            url:"http://localhost:8080/ketuan/applet/products/search?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索商品失败！"+data["failedMessage"]);
                }
                else{
                    var products = data["data"]["products"];
                    if(products.length==0){
                        $("#productsList tbody").html("");
                    }
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
            url:"http://localhost:8080/ketuan/applet/products/listall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取商品失败！"+data["failedMessage"]);
                }
                else{
                    var products = data["data"]["products"];
                    if(products.length==0){
                        $("#productsList tbody").html("");
                    }
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
        var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">'+state
            +'          </td>'
            +'          <td class="project-title">'
            +'              <a href="project_detail.html">'+product["productName"]
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
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 下线 </a>'
            +'          </td>'
            +'      </tr>'
        $("#productsList tbody").html("");
        $("#productsList tbody").append(s);
    }
    getAllProducts();

})
