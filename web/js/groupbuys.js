$(function(){

    $("#loading-example-btn").on("click",function (e){
        getAllGroups();
    })

    $("#searchBtn").on("click",function (e){
        var key = $("#searchKey")[0].value;
        if(key==""||key==undefined){
            getAllGroups();
            return
        }
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/groupsearch?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索团购失败！"+data["failedMessage"]);
                }
                else{
                    var groups = data["data"]["groups"];
                    $("#groupList tbody").html("");
                    for(var i=0;i<groups.length;i++){
                        appendTr(groups[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("搜索团购失败！"+JSON.stringify(e));
            }
        })
    })

    function getAllGroups() {
        $.ajax({
            url:"http://localhost:8080/ketuan/backmanage/grouplistall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取团购失败！"+data["failedMessage"]);
                }
                else{
                    var groups = data["data"]["groups"];
                    $("#groupList tbody").html("");
                    for(var i=0;i<groups.length;i++){
                        appendTr(groups[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("获取团购失败！"+JSON.stringify(e));
            }
        })
    }

    function appendTr(group){
        var state = "正在团购中";
        switch (group["groupState"]){
            case 0:
                state="正在团购中";
                break;
            case 1:
                state="团购已结束";
                break;
            case 2:
                state="团购已结束";
                break;
        }
        var type = "固定团";
        if(group["groupType"]==1){
            type = "动态团";
        }
        var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">'+state
            +'          </td>'
            +'          <td class="project-title">'
            +'              <a href="groupbuys_detail.html?groupid='+group["id"]+'">'+group["groupName"]
            +'              </a>'
            +'              <br/>'
            +'              <small>上线时间：'+group["endTime"]+'</small>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>成团数量:</small>'+group["groupCount"]
            +'              <br/>'
            +'              <small>当前参团数:</small>'+group["offeredCount"]
            +'          </td>'
            + '         <td class="project-people">'
            +'              <small>团购类型</small>'
            +'              <span class="label label-primary">'+type+'</span>'
            +'          </td>'
            +'          <td class="project-actions">'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 下线 </a>'
            +'          </td>'
            +'      </tr>'
        /*$("#groupList tbody").html("");*/
        $("#groupList tbody").append(s);
    }
    getAllGroups();

})
