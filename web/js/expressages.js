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
                        $("#expressagesList tbody").html("");
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


        /*var s = '<tr>'
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
            +'              <i class="fa fa-pencil"></i><span id="city-create" class="btn btn-white btn-sm change" data-toggle="modal" data-target="#myModal" onclick=\\"xiugai(\'"+atr.id+"\')\\">创建</span>'
            +'          </td>'
            +'      </tr>'*/




        var s = '<tr id="'+expressage["id"]+'">'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">@'+expressage["isNew"]+'</span>'+'<input id="expressageDisplay" type="text" style="display: none"/>'
            +'          </td>'
            +'          <td class="project-title">'
            +'              <span>'+expressage["expressageName"]+'</span>'+'<input id="expressageDisplay" type="text" style="display: none"/>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>发货起始地：</small>'+'<span>'+expressage["shipAddress"]+'</span>'+'<input id="expressageDisplay" type="text" style="display: none"/>'
            +'          </td>'
            + '         <td class="project-people">'
            +'          </td>'
            +'          <td class="project-actions">'
            +'           <button class="btn btn-white btn-sm change" id="change"><i class="fa fa-folder"></i>修改</button>'+'<button  id="que_change" class="btn btn-white btn-sm que_change"  style="display: none"><i class="fa fa-folder"></i>确定</button>'
            +'              <i class="fa fa-pencil"></i><span id="city-create" class="btn btn-white btn-sm" data-toggle="modal" data-target="#myModal" >创建</span>'
            +'          </td>'
            +'      </tr>';





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
       //修改功能
        for (var i=0;i<$("#expressagesList tbody tr").length;i++) {

            $("#expressagesList tbody tr").eq(i).children('td').last().children('button').first().on("click", function (e) {
                var val = $(this).parent('.project-actions').prevAll('.project-completion').children('input').prev('span').text();
                $(this).hide();
                $(this).next('button').show();
                $(this).parent('.project-actions').prevAll('.project-completion').children('input').show();
                $(this).parent('.project-actions').prevAll('.project-completion').children('input').prev('span').hide();
                $(this).parent('.project-actions').prevAll('.project-completion').children('input').val(val);
                // $(this).parent('.project-actions').prevAll('.project-completion').children('input').focus();

                var val1 = $(this).parent('.project-actions').prevAll('.project-title').children('input').prev('span').text();  //没找到当前的元素
                $(this).parent('.project-actions').prevAll('.project-title').children('input').show();
                $(this).parent('.project-actions').prevAll('.project-title').children('input').prev('span').hide();
                $(this).parent('.project-actions').prevAll('.project-title').children('input').val(val1);
                // $(this).parent('.project-actions').prevAll('.project-title').children('input').focus();

                var val2 = $(this).parent('.project-actions').prevAll('.project-status').children('input').prev('span').text();
                $(this).parent('.project-actions').prevAll('.project-status').children('input').show();
                $(this).parent('.project-actions').prevAll('.project-status').children('input').prev('span').hide();
                $(this).parent('.project-actions').prevAll('.project-status').children('input').val(val2);
                //$(this).parent('.project-actions').prevAll('.project-status').children('input').focus();
            })


            $("#expressagesList tbody tr").eq(i).children('td').last().find("#que_change").on("click", function (e) {
                $(this).hide();
                $("#expressagesList tbody tr .project-actions").find("#change").show();     //再获取当前输入的值
                var shipAddress = $(this).parent('.project-actions').prevAll('.project-completion').children('input').val();
                var expresageName = $(this).parent('.project-actions').prevAll('.project-title').children('input').val();
                var expressageIsnew = $(this).parent('.project-actions').prevAll('.project-status').children('input').val().slice(1);
                var expressageId =$(this).parent('.project-actions').parent('tr').attr("id");

                $.ajax(
                    {
                        url:"http://localhost:8080/ketuan/backmanage/expressageChange",
                        data:{
                            expressageId:expressageId,
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
                /*$(this).parent('.project-actions').prevAll('.project-completion').children('input').hide();
                $(this).parent('.project-actions').prevAll('.project-title').children('input').hide();
                $(this).parent('.project-actions').prevAll('.project-status').children('input').hide();

                $(this).parent('.project-actions').prevAll('.project-completion').children('span').text(shipAddress);
                $(this).parent('.project-actions').prevAll('.project-title').children('span').text(expresageName);
                $(this).parent('.project-actions').prevAll('.project-status').children('span').text(expressageIsnew);*/

            })


        }
    }
    getAllExpressages();





//创建快递
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



/*
//搜索快递
    $("#searchBtn").on("click",function(){
        var expressageId=$("#searchKey").val();
        if(!expressageId){
            return;
        }else {
            $("#expressagesList tbody").empty();
            $.get("expressageId:expressageId", function (data) {      //此次的data可能有错，具体指的是什么，下面有个data
                for (var i = 0; i < data.length; i++) {
                    $("#expressagesList tbody").append(
                        '<tr>'
                        + '          <td class="project-status">'
                        + '             <span class="label label-primary">@' + expressage["isNew"] + '</span>' + '<input id="expressageDisplay" type="text" style="display: none"/>'
                        + '          </td>'
                        + '          <td class="project-title">'
                        + '              <span>' + expressage["expressageName"] + '</span>' + '<input id="expressageDisplay" type="text" style="display: none"/>'
                        + '          </td>'
                        + '          <td class="project-completion">'
                        + '              <small>发货起始地：</small>' + '<span>' + expressage["shipAddress"] + '</span>' + '<input id="expressageDisplay" type="text" style="display: none"/>'
                        + '          </td>'
                        + '         <td class="project-people">'
                        + '          </td>'
                        + '          <td class="project-actions">'
                        + '           <button class="btn btn-white btn-sm change" id="change"><i class="fa fa-folder"></i>修改</button>' + '<button  id="que_change" class="btn btn-white btn-sm que_change"  style="display: none"><i class="fa fa-folder"></i>确定</button>'
                        + '              <i class="fa fa-pencil"></i><span id="city-create" class="btn btn-white btn-sm" data-toggle="modal" data-target="#myModal" >创建</span>'
                        + '          </td>'
                        + '      </tr>'
                    );
                }
            })
        }







    })
*/

















})
