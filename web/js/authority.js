$(function(){

    $("#loading-example-btn").on("click",function (e){
        getAllAuthoritys();
    })

    $("#searchBtn").on("click",function (e){
        var key = $("#searchKey")[0].value;
        if(key==""||key==undefined){
            getAllAuthoritys();
            return
        }
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/authoritySearch?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索管理员失败！"+data["failedMessage"]);
                }
                else{
                    var authoritys = data["data"]["authoritys"];
                    $("#authoritysList tbody").html("");
                    for(var i=0;i<authoritys.length;i++){
                        appendTr(authoritys[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("搜索商家失败！"+JSON.stringify(e));
            }
        })
    })

    function getAllAuthoritys() {
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/authorityListall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取商家失败！"+data["failedMessage"]);
                }
                else{
                    var authoritys = data["data"]["authoritys"];
                    $("#authoritysList tbody").html("");
                    for(var i=0;i<authoritys.length;i++){
                        appendTr(authoritys[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("获取商家失败！"+JSON.stringify(e));
            }
        })
    }

    function appendTr(authority){
        var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">'+authority["authorityName"]
            +'          </td>'
            +'          <td class="project-title">'
            +'           <small>管理员ID号码：</small><a href="authority_detail.html?authorityid='+'">'+authority["authorityId"]
            +'              </a>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>公司地址：</small>'+'深圳市腾讯科技有限公司'
            +'          </td>'
            + '         <td class="project-people">'
            +'          </td>'
            +'          <td class="project-actions">'
            +'             <button class="btn btn-white btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-folder"></i>修改权限</button>'
            +'              <button class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>删除</button>'
            +'          </td>'
            +'      </tr>'

        $("#authoritysList tbody").append(s);
    }
    getAllAuthoritys();

})