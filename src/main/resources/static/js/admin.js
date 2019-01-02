//页面跳转 Cookie缓存
function jumpFromTeacherHome(teacherId){
    Cookies.set("teacher",teacherId);
}

//登录页面
function bindAdmin() {
    let ata = { account: $("#account").val(), password: $("#password").val() };
    console.log(ata);
    $.ajax({
        type: "post",
        url: "http://xug98.cn/admin/login",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                window.localStorage.setItem("jwt", data.jwt);
                Cookies.set("id", data.id);
                Cookies.set("account", data.account);
                Cookies.set("role", data.role);
                Cookies.set("name", data.name);
                window.location.href = "./teacher-home.html";
            }
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

//学生管理
function getAllStudent() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/student",
        // url: "../../static/json/student-all.json",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                        <tr>\n' +
                        '                          <td>'+item.studentName+'</td>\n' +
                        '                          <td>'+item.account+'</td>\n' +
                        '                          <td class="text-nowrap">'+item.email+'</td>\n' +
                        '                          <td>\n' +
                        '                            <a href="./student-home-update.html" class="icon"\n' +
                        '                        onclick="jumpFromStudentHome('+item.id+')"\n' +
                        '                              ><i class="fe fe-edit"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="resetStudent('+item.id+')"\n' +
                        '                              ><i class="fe fe-rotate-ccw"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="deleteStudent('+item.id+')"\n' +
                        '                              ><i class="fe fe-trash"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                        </tr>\n';
                });
                content.innerHTML=str;
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//查找学生
function searchStudent() {
    let ata = {
        identity: $("#identity").val()
    };
    if(ata=="") getAllStudent();
    else{
        console.log(ata);
        alert("input");
        $.ajax({
            type: "get",
            url: "http://xug98.cn/student/searchStudent",
            dataType: "json",
            contentType: "application/json",
            success: function(data, textStatus, xhr) {
                // alert("获取成功");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                        <tr>\n' +
                        '                          <td>'+item.name+'</td>\n' +
                        '                          <td>'+item.account+'</td>\n' +
                        '                          <td class="text-nowrap">'+item.email+'</td>\n' +
                        '                          <td>\n' +
                        '                            <a href="./teacher-home-update.html" class="icon"\n' +
                        '                        onclick="jumpFromTeacherHome('+item.id+')"\n' +
                        '                              ><i class="fe fe-edit"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="resetTeacher('+item.id+')"\n' +
                        '                              ><i class="fe fe-rotate-ccw"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="deleteTeacher('+item.id+')"\n' +
                        '                              ><i class="fe fe-trash"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                        </tr>\n';
                });
                content.innerHTML=str;
            },
            statusCode: {
                400: function() {
                    $("#identity").val("");
                    alert("无此用户");
                }
            }
        });
    }

}
//删除学生
function deleteStudent(studentId) {
    var result = confirm("确定删除学生?");
    if (result) {
        $.ajax({
            type: "delete",
            url: "http://xug98.cn/student/" + studentId,
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, xhr) {
                console.log(data);
                alert("success");
                window.location.reload();

            },
            statusCode: {
                400: function () {
                    $("#password").val("");
                }
            }
        });

    }
}
//修改学生
function updateStudent() {
    let ata = {
        account: $("#account").val(),
        email: $("#email").val(),
        name: $("#name").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "http://xug98.cn/student/" + Cookies.get("student") + "/information",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function getStudentInfo() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/student/"+Cookies.get("student")+"/information",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#account").val(data.account);
                $("#email").val(data.email);
                $("#name").val(data.name);
            }
        }
    });
}
//重置学生
function resetStudent() {
    var result = confirm("确定重置学生密码?");
    if (result) {
        console.log(ata);
        alert("input");
        $.ajax({
            type: "put",
            url: "http://xug98.cn/student/" + Cookies.get("student") + "/password",
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, xhr) {
                console.log(data);
                alert("success");
                window.location.href = "./seminar-round.html";
            },
            statusCode: {
                400: function () {
                    $("#password").val("");
                    alert("用户名或密码错误！");
                }
            }
        });
    }
}


//教师管理
function getAllTeacher() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/teacher",
        // url: "../../static/json/teacher-all.json",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                        <tr>\n' +
                        '                          <td>'+item.teacherName+'</td>\n' +
                        '                          <td>'+item.account+'</td>\n' +
                        '                          <td class="text-nowrap">'+item.email+'</td>\n' +
                        '                          <td>\n' +
                        '                            <a href="./teacher-home-update.html" class="icon"\n' +
                        '                        onclick="jumpFromTeacherHome('+item.id+')"\n' +
                        '                              ><i class="fe fe-edit"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="resetTeacher('+item.id+')"\n' +
                        '                              ><i class="fe fe-rotate-ccw"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="deleteTeacher('+item.id+')"\n' +
                        '                              ><i class="fe fe-trash"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                        </tr>\n';
                });
                content.innerHTML=str;
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
    //创建教师用户
function createTeacher() {
    let ata = {
        account:$("#account").val(),
        initial_password: $("#initial_password").val(),
        name: $("#name").val(),
        email:$("#email").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "http://xug98.cn/teacher",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./teacher-home.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
    //查找教师
function searchTeacher() {
    let ata = {
        identity: $("#identity").val()
    };
    if(ata=="") getAllTeacher();
    else{
        console.log(ata);
        alert("input");
        $.ajax({
            type: "get",
            url: "http://xug98.cn/student/searchTeacher",
            dataType: "json",
            contentType: "application/json",
            success: function(data, textStatus, xhr) {
                // alert("获取成功");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                        <tr>\n' +
                        '                          <td>'+item.name+'</td>\n' +
                        '                          <td>'+item.account+'</td>\n' +
                        '                          <td class="text-nowrap">'+item.email+'</td>\n' +
                        '                          <td>\n' +
                        '                            <a href="./teacher-home-update.html" class="icon"\n' +
                        '                        onclick="jumpFromTeacherHome('+item.id+')"\n' +
                        '                              ><i class="fe fe-edit"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="resetTeacher('+item.id+')"\n' +
                        '                              ><i class="fe fe-rotate-ccw"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a href="#" class="icon" onclick="deleteTeacher('+item.id+')"\n' +
                        '                              ><i class="fe fe-trash"></i\n' +
                        '                            ></a>\n' +
                        '                          </td>\n' +
                        '                        </tr>\n';
                });
                content.innerHTML=str;
            },
            statusCode: {
                400: function() {
                    $("#identity").val("");
                    alert("无此用户");
                }
            }
        });
    }
}
    //删除教师
function deleteTeacher(teacherId) {
    var result = confirm("确定删除教师?");
    if (result) {
        $.ajax({
            type: "delete",
            url: "http://xug98.cn/teacher/" + teacherId,
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, xhr) {
                console.log(data);
                alert("success");
                window.location.reload();

            },
            statusCode: {
                400: function () {
                    $("#password").val("");
                }
            }
        });

    }
}
    //修改教师
function updateTeacher() {
    let ata = {
        account: $("#account").val(),
        email: $("#email").val(),
        name: $("#name").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "http://xug98.cn/teacher/" + Cookies.get("teacher") + "/information",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function getTeacherInfo() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/teacher/"+Cookies.get("teacher")+"/information",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#account").val(data.account);
                $("#email").val(data.email);
                $("#name").val(data.name);
            }
        }
    });
}

    //重置老师
function resetTeacher() {
    var result = confirm("确定重置教师密码?");
    if (result) {
        console.log(ata);
        alert("input");
        $.ajax({
            type: "put",
            url: "http://xug98.cn/teacher/" + Cookies.get("teacher") + "/password",
            dataType: "json",
            contentType: "application/json",
            success: function (data, textStatus, xhr) {
                console.log(data);
                alert("success");
                window.location.href = "./seminar-round.html";
            },
            statusCode: {
                400: function () {
                    $("#password").val("");
                    alert("用户名或密码错误！");
                }
            }
        });
    }
}




















