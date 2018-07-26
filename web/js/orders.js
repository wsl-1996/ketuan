$(function(){

    $("#loading-example-btn").on("click",function (e){
        getAllOrders();
    })

    $("#searchBtn").on("click",function (e){
        var key = $("#searchKey")[0].value;
        if(key==""||key==undefined){
            getAllOrders();
            return
        }
        $.ajax({
            url:"http://localhost:8080/ketuan/applet/orders/search?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索商家失败！"+data["failedMessage"]);
                }
                else{
                    var orders = data["data"]["orders"];
                    if(orders.length==0){
                        $("#ordersList tbody").html("");
                    }
                    for(var i=0;i<merchants.length;i++){
                        appendTr(merchants[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("搜索商家失败！"+JSON.stringify(e));
            }
        })
    })

    function getAllOrders() {
        $.ajax({
            url:"http://localhost:8080/ketuan/applet/orders/listall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取商家失败！"+data["failedMessage"]);
                }
                else{
                    var orders = data["data"]["orders"];
                    if(orders.length==0){
                        $("#ordersList tbody").html("");
                    }
                    $("#ordersList tbody").html("");
                    for(var i=0;i<orders.length;i++){
                        appendTr(orders[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("获取商家失败！"+JSON.stringify(e));
            }
        })
    }

    function appendTr(order){
        var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">@'
            +'          </td>'
            +'          <td class="project-title">'
            +'              <a href="orders_detail.html?orderid='+order["id"]+'">'+order["send_name"]
            +'              </a>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>支付方式：</small>'+order["paymethod"]
            +'          </td>'
            + '         <td class="project-people">'
            +'          </td>'
            +'          <td class="project-actions">'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 下线 </a>'
            +'          </td>'
            +'      </tr>'

        $("#ordersList tbody").append(s);
    }
    getAllorders();

})
