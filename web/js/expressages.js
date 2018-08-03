$(function(){

    $("#loading-example-btn").on("click",function (e){
        getAllExpressages();
    })

    $("#searchBtn").on("click",function (e){
        var key = $("#searchKey")[0].value;
        if(key==""||key==undefined){
            getAllExpressages();
            return
        }
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/expressagesearch?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索快递失败！"+data["failedMessage"]);
                }
                else{
                    var expressages = data["data"]["expressages"];
                    if(expressages.length==0){
                        $("#expressagesList tbody").html("");
                    }
                    for(var i=0;i<expressages.length;i++){
                        appendTr(expressages[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("搜索快递失败！"+JSON.stringify(e));
            }
        })
    })

    function getAllExpressages() {
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/expressagelistall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取快递失败！"+data["failedMessage"]);
                }
                else{
                    var expressages = data["data"]["expressages"];
                    if(expressages.length==0){
                        $("#expressagesList tbody").html("");
                    }
                    $("#expressagesList tbody").html("");
                    for(var i=0;i<expressages.length;i++){
                        appendTr(expressages[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("获取快递失败！"+JSON.stringify(e));
            }
        })
    }

   /* function initmodar() { //当我点击保存，触发函数。先获取前端的值，连接数据库，把前端的值赋值给后端
        //var productName = $("#productName").value;
        //$("#productName_moda").value = productName;
        $("input").click(function() {
           var a = $("#form-control").value;//被选项目的值value
         varb = $("#form-control option:checked").text;//被选项目显示的值
            expressage["expressageName"]=b;
            alert("被选项目的值："+expressage["expressageName"]+"，被选项目的显示值："+b+"。");
        });
    }
*/

  /*  $("#button").click(function () {
        var expressage_isnew=$("#expressage_isnew").val();

        $.ajax(
            {
                url:"http://localhost:8080/ketuan/backmanage/expressagesearch?key="+key,
                data:{
                    expressage_isnew:'expressage_isnew'
                },
                type:"GET",
                dataType:"jason",
                success:function(data){
                    if(data["failed"]){
                        alert("获取快递失败！"+data["failedMessage"]);
                    }
                    else{
                        expressages.expressageIsnew=expressage_isnew;
                    }
        }
            }
        )

    })*/





    function appendTr(expressage){
        var isnew = "已最新";
        switch (expressage["expressageIsnew"]){
            case 0:
                isnew="已最新";
                break;
            case 1:
                isnew="待更新";
                break;
        }
        var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">@'+isnew
            +'          </td>'
            +'          <td class="project-title">'
            +'              <a href="expressages_detail.html?expressageid='+expressage["id"]+'">'+expressage["expressageName"]
            +'              </a>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>发货起始地：</small>'+expressage["shipAddress"]
            +'          </td>'
            + '         <td class="project-people">'
            +'          </td>'
            +'          <td class="project-actions">'
            +'              <a href="expressages_detail.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 编辑</a>'
            +'              <i class="fa fa-pencil"></i><span id="city-create" class="btn btn-white btn-sm" data-toggle="modal" data-target="#myModal">创建</span>'
            +'          </td>'
            +'      </tr>'










        /*var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">@'+isnew
            +'          </td>'
            +'          <td class="project-title">'
            +'              <select>'+'<option>'+expressage["expressageName"]+'</option>'
            +'<option>中通快递</option>'+'<option>顺丰快递</option>'+'<option>圆通快递</option>'
            +'              </select>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'<select >'+'<option>'+expressage["shipAddress"]+'</option>'
            + '<option class="form-control" id="province1" data-toggle="distpicker"></option>'+'</<select>'
            +'          </td>'
            + '         <td class="project-people">'
            +'          </td>'
            +'          <td class="project-actions">'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 创建 </a>'
            +'              <i class="fa fa-pencil"></i> <span id="city-create" class="btn btn-white btn-sm" data-toggle="modal" data-target="#myModal" onclick="initmodar()">编辑</span>'
            +'          </td>'
            +'      </tr>'
        */
        





        $("#expressagesList tbody").append(s);

    }
    getAllExpressages();





// $("#button").click(
    $("#saveExpressButton").on("click",function (e){
        var expressageIsnew=$("#expressage_isnew").val();
        var expresageName=$("#expresageName").val();
        var shipAddress=$("#expresage_shipAddress").val();
        $.ajax(
            {
                url:"http://localhost:8080/ketuan/backmanage/storeinformation",
                data:{
                    expressageIsnew:expressageIsnew,
                    expresageName:expresageName,
                    shipAddress:shipAddress
                },
                type:"GET",
                dataType:"json",
                success:function(data){
                    if(data="1"){
                        alert("添加成功")
                    }else {
                        alert("添加失败")
                    }
                }
            }
        )
    })









    })
