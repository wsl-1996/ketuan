$(function(){

    $("#loading-example-btn").on("click",function (e){
        getAllUsers();
    })

    $("#searchBtn").on("click",function (e){
        var key = $("#searchKey")[0].value;
        if(key==""||key==undefined){
            getAllUsers();
            return
        }
        $.ajax({
            url:"http://localhost:8080/ketuan/applet/users/search?key="+key,
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("搜索用户失败！"+data["failedMessage"]);
                }
                else{
                    var users = data["data"]["users"];
                    if(users.length==0){
                        $("#usersList tbody").html("");
                    }
                    for(var i=0;i<users.length;i++){
                        appendTr(users[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("搜索用户失败！"+JSON.stringify(e));
            }
        })
    })

    function getAllUsers() {
        $.ajax({
            url:"http://localhost:8080/ketuan/applet/users/listall",
            type:"GET",
            dataType: "json",
            success:function (data) {
                console.info(JSON.stringify(data));
                if(data["failed"]){
                    alert("获取用户失败！"+data["failedMessage"]);
                }
                else{
                    var users = data["data"]["users"];
                    if(users.length==0){
                        $("#usersList tbody").html("");
                    }
                    for(var i=0;i<users.length;i++){
                        appendTr(users[i]);
                    }
                }
            },
            error:function (e) {
                console.error(JSON.stringify(e));
                alert("获取用户失败！"+JSON.stringify(e));
            }
        })
    }

    function appendTr(user){
        var sex = "男";
        switch (user["sex"]){
            case 0:
                sex="男";
                break;
            case 1:
                sex="女";
                break;
            case 2:
                sex="未知";
                break;
        }
        var s = '<tr>'
            +'          <td class="project-status">'
            + '             <span class="label label-primary">'+sex
            +'          </td>'
            +'          <td class="project-title">'
            +'              <img alt="image" class="img-circle" src="'+user["headImgUrl"]+'">'
            +'              <a href="project_detail.html">'+user["nickname"]
            +'              </a>'
            +'          </td>'
            +'          <td class="project-completion">'
            +'              <small>'+user["province"]+'</small>'
            +'              <small>'+user["city"]+'</small>'
            +'          </td>'
            +'          <td class="project-people">'
            +'              <small>业绩：</small>'+user["controbution"]
            +'          </td>'
            +'          <td class="project-actions">'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>'
            +'              <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i> 下线 </a>'
            +'          </td>'
            +'      </tr>'
        $("#usersList tbody").html("");
        $("#usersList tbody").append(s);
    }
    getAllUsers();

})
