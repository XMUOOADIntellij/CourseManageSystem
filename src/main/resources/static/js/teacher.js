function updateCookie(name, value) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 6 * 24 * 60 * 60 * 1000); //6�����
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    return true;
}

function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]); return null;
}


//4-teacherHome
//4-0-accountSetting


function jumpcourse(cid){  //����޸Ŀγ�ʱ�����γ�id�Ŵ洢��cookie��
    updateCookie('courseCurrent',cid);
    window.location.href='/teacher/course/'+cid+'/update'; //ҳ����ת
}

function jumpcoursedetail(cid){  //��ʦ�γ��б�ҳ�����γ�����->��ת��ĳ������γ�ҳ��ʱ���ã���cookie��γ�Id
    updateCookie('courseDetail',cid);
    window.location.href='/teacher/course/'+cid;
}

function jumpclassdetail(cid){
    updateCookie('classDetail',cid);
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/class/'+cid;  //����༶����ҳ��
}

function jumpseminardetail(cid){
    // alert("hi");
    updateCookie('seminarDetail',cid);
    window.location.href='/teacher/course/'+getCookie('seminarDetail')+'/seminar/'+cid;  //�������ۿ�����ҳ��
}
function jumptopic(cid){
    updateCookie('topicDetail',cid);
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+cid;  //���뻰������ҳ��
}
function jumpreportdetail(rid){
    updateCookie('groupDetail',rid);
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/score/score'; //��������ҳ��
}
/*---------------------------- teacher/bind--------------------------------------*/
function teabind(){
    var Gender = $("input[type='radio']:checked").val();
    var ata = {
        name:$("#name").val(),
        gender:Gender,
        email:$("#eMail").val(),
        title:$("#title").val(),
        phone:$("#phone").val(),
        avatar: "/avatar/3486.png"
    }
    $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("�ɹ�!");
                window.location.href='/teacher/home'; //��ת����ʦ��Ϣ���Ǹ�ҳ
            }
        },
        statusCode:{
            400: function (){
                alert("��Ϣ���Ϸ�");
            }
        }
    });
}

function logout(){
    if(localStorage.jwt){
        localStorage.removeItem("jwt");
        window.location.href='/login';
    }
    else{
        window.location.href='/login';
    }
}

function teainfomod(){
    var ata = {
        name:$("#name").val(),
        number: $("#idnum").val(),
        email:$("#eMail").val(),
        gender:$("#gender").val(),
        title:$("#title").val(),
        avatar: "/avatar/3486.png",
        // school:$("#school").val(),
        // phone:$("#phone").val()
    }
    $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("�޸ĳɹ�!");
                window.location.href='/teacher/home';
            }
        },
        statusCode:{
            400: function (){
                alert("��Ϣ���Ϸ�");
            }
        }
    });
}



function deletecourse(cid){
    var ata = {id:cid}
    $.ajax({
        type:'delete',
        url: '/course/'+cid,
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        error:function (data,textStatus,xhr){
            console.log(cid);
            alert("wrong");
        },
        success: function (data,textStatus,xhr){
            if(xhr.status == 204){
                alert("�ɹ�");
                document.fe
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            403: function () {
                alert("�û�Ȩ�޲���");
            },
            404: function () {
                alert("δ�ҵ��γ�");
            }
        }
    });
    // courselist();  //ɾ�����ټ���
    window.location.reload();
}

/*----------------------------teacher/course_create-------------------------------*/

function createcourse(){    //TeacherCreateCourse
    var ata = {
        name:$("#courseName").val(),
        description:$("#description").val(),
        startTime:$("#begintime").val(),
        endTime:$("#endtime").val(),
        proportions:{
            report:$("#reportscore").val(),
            presentation:$("#seminarscore").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

    }
    $.ajax({
        type:'post',
        url: '/course',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("�����ɹ�!");
                window.location.href="/teacher/courses";  //����չʾ��ʦ�γ�list��ҳ��
                return "23";  //API��˵�����½��γ̵�ID����֪������η���õ��γ̵�ID
            }
        },
        statusCode: {
            403: function () {  //statuscode unknown
                alert("�û�Ȩ�޲���");
            }
        }
    });
}

/*----------------------------teacher/course_update-------------------------------*/
function getcourseinfo(){  //get course information when updating it
    // console.log(getcourseid());
    $.ajax({
        type:'get',
        url: '/course/'+getCookie("courseCurrent"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // document.cookie = 'username='+data.id; //store username in cookie
                $("input[name='coursename']").attr("value",data.name);
                $("input[name='courseinfo']").attr("value",data.description);
            }
        },
        statusCode:{
            401: function (){ //statuscode unknown
                alert("��ȡ��Ϣʧ��");
            }
        }
    });
}

function courseinfomod(){
    var ata = {
        name:$("#coursename").val(),
        description:$("#description").val(),
        startTime:$("#begintime").val(),
        endTime:$("#endtime").val(),
        proportions:{
            report:$("#reportscore").val(),
            presentation:$("#seminarscore").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

    }
    $.ajax({
        type:'put',
        url: '/course/'+getCookie("courseCurrent"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("�޸ĳɹ�!");
                window.location.href="/teacher/courses";
            }
        },
        statusCode: {
            403: function () {
                alert("�û�Ȩ�޲���");
            }
        }
    });

}

/*----------------------------teacher/course-------------------------------*/
function courseintroduce(){
    $.ajax({
        type:'get',
        url: '/course/'+getCookie("courseDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                $("#courseName").html(data.name);
                $("#courseIntroduction").html(data.description);
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ��γ�");
            }
        }
    });
}
function classlist(){
    $.ajax({
        type:'get',
        url: '/course/'+getCookie("courseDetail")+'/class',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                var content=document.getElementById("classcontent");   //��ȡ��Χ����
                var str="";
                str+='<div class=\"title\">�γ̰༶</div>'
                    +'<div class=\"returnButton\"  onclick=\"window.location.href=\'/teacher/courses\'\">������һҳ</div>'
                    +'<div class=\"line\"></div>'
                    +'<div class=\"blockBody\" id=\"classtitle\">'
                for(var i=0;i<data.length;i++){
                    // if(i==0){
                    // str+='<div class=\"title\">�γ̰༶</div>'
                    // +'<div class=\"returnButton\"  onclick=\"window.location.href=\'/teacher/courses\'\">������һҳ</div>'
                    // +'<div class=\"line\"></div>'
                    // +'<div class=\"blockBody\" id=\"classtitle\">'
                    // }
                    str+='<div class=\"block\" id=\"'+data[i].id+'\" onclick=\"jumpclassdetail(this.id)\"><div class=\"blockFont\">'+data[i].name+'</div></div>'
                    // if(i==data.length-1){
                    //     str+='<div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/class/create\'\"><img class="addImg" src="/img/smalladd.png" alt="���" ></div>'
                    // }
                }
                str+='<div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/class/create\'\"><img class="addImg" src="/img/smalladd.png" alt="���" ></div>'
                content.innerHTML=str;
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ��γ�");
            }
        }
    });

}

function seminarlist(){
    $.ajax({
        type:'get',
        url: '/course/'+getCookie("courseDetail")+'/teacher/seminar',
        // url: '/course/'+1+'/seminar',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                var content=document.getElementById("seminarcontent");   //��ȡ��Χ����
                var str="";
                str+='<div class="title">���ۿ�</div><div class="line"></div><div class="blockBody">';
                for(var i=0;i<data.length;i++){
                    // if(i==0){
                    //  str+='<div class="title">���ۿ�</div><div class="line"></div><div class="blockBody">'
                    // }
                    // alert(data[i].name);
                    str+='<div class="block"  id=\"'+data[i].id+'\" onclick=\"jumpseminardetail(this.id)\"><div class="blockFont">'+data[i].name+'</div></div>'
                    // if(i==data.length-1){
                    //      str+=' <div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/seminar/create\'\"><img class="addImg" src="/img/smalladd.png" alt="���" ></div>\'
                    // }
                }
                str+='<div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/seminar/create\'\"><img class="addImg" src="/img/smalladd.png" alt="���" ></div>';
                content.innerHTML=str;
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ��γ�");
            }
        }
    });
}


/*----------------------------teacher/class_create-------------------------------*/
function backtocourse(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")
}
function createclass(){
    var classtime="";
    classtime=$("#week").val()+$("#day").val()+$("#jie").val();
    var ata = {
        name:$("#classname").val(),
        site:$("#classsite").val(),
        time:classtime,
        proportions:{
            report:$("#report").val(),
            presentation:$("#presentation").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

    }
    // alert("55");
    $.ajax({
        type:'post',
        url: '/course/'+getCookie("courseDetail")+'/class',
        // url: '/course/'+getcoursedetail()+'/class',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("�����ɹ�!");
                backtocourse();  //������ʾ�γ��µİ༶�б�/���ۿ��б�
            }
        },
        statusCode: {
            403: function () {
                alert("�û�Ȩ�޲���");
            },
            404: function () {
                alert("δ�ҵ��γ�");
            }
        }
    });

}

$(function(){  //ѡ���Ͽ�ʱ��select������ʾ(class_update��class_createҳ�涼�õ����)
    $(".smallSelect2").change(function() {
        var selected_value = $(this).val();
        if(selected_value == "����"){
            $(".smallSelect3").empty();
            var option = $("<option>").val("һ����").text("һ����");
            $(".smallSelect3").append(option);
            option=$("<option>").val("���Ľ�").text("���Ľ�");
            $(".smallSelect3").append(option);
        }
        else if(selected_value == "����"){
            $(".smallSelect3").empty();
            var option = $("<option>").val("������").text("������");
            $(".smallSelect3").append(option);
            option=$("<option>").val("�߰˽�").text("�߰˽�");
            $(".smallSelect3").append(option);
        }
        else{
            $(".smallSelect3").empty();
            var option = $("<option>").val("��ʮ��").text("��ʮ��");
            $(".smallSelect3").append(option);
        }
    });
});
/*----------------------------teacher/seminar_create-------------------------------*/
function createseminar(){
    var ata = {
        name:$("#seminarname").val(),
        description:$("#description").val(),
        groupingMethod:$("#groupingMethod > option:selected").val(),
        startTime:$("#startTime").val(),
        endTime:$("#endTime").val()

    }
    $.ajax({
        type:'post',
        url: '/course/'+getCookie("courseDetail")+'/seminar',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("�����ɹ�!");
                backtocourse(); //������ʾ�γ��µİ༶�б�/���ۿ��б�
            }
        },
        statusCode: {
            403: function () {
                alert("�û�Ȩ�޲���");
            }
        }
    });
}
/*----------------------------teacher/class-------------------------------*/
function classinfo(){
    $.ajax({
        type:'get',
        url: '/class/'+getCookie("classDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                $("#upclassname").html(data.name);
                $("#classname").html(data.name);
                $("#site").html(data.site);
                $("#time").html(data.time);
                $("#report").html(data.reportPercentage+'%');
                $("#presentation").html(data.presentationPercentage+'%');
                $("#five").html(data.fivePointPercentage+'%');
                $("#four").html(data.fourPointPercentage+'%');
                $("#three").html(data.threePointPercentage+'%');
                $("#numStudent").html(data.numStudent);
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ��༶");
            }
        }
    });
}
function deleteclass(){
    var ata = {id:getCookie("classDetail")}
    $.ajax({
        type:'delete',
        url: '/class/'+getCookie("classDetail"),
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr){
            if(xhr.status == 204){
                alert("ɾ���ɹ�");
                backtocourse();  //ҳ����ת
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            403: function () {
                alert("�û�Ȩ�޲���");
            },
            404: function () {
                alert("δ�ҵ��༶");
            }
        }
    });
}
function updateclass(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/class/'+getCookie("classDetail")+'/update'
}
/*----------------------------teacher/class_update-------------------------------*/
function backtoclass(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/class/'+getCookie("classDetail")
}


function getclassdetail(){
    $.ajax({
        type:'get',
        url: '/class/'+getCookie("classDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // alert("��ȡ�ɹ�");
                $("input[name='className']").attr("value",data.name);
                $("input[name='numStudent']").attr("value",data.numStudent);
                var week,day,jie;
                week=data.time[0]+data.time[1]+data.time[2];  //�ҵ����ڼ����ַ�
                day=data.time[3]+data.time[4];//�ҵ�����/������ַ�
                jie=data.time[5]+data.time[6]+data.time[7]; //�ҵ��ڴ�(jie)���ַ�
                $(".smallSelect1").val(week);  //�˴��޸���css������
                $(".smallSelect2").val(day);
                $(".smallSelect3").val(jie);
                $("input[name='site']").attr("value",data.site);
                $("input[name='report']").attr("value",data.proportions.report);
                $("input[name='presentation']").attr("value",data.proportions.presentation);
                $("input[name='five']").attr("value",data.proportions.c);
                $("input[name='four']").attr("value",data.proportions.b);
                $("input[name='three']").attr("value",data.proportions.a);
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ��༶");
            }
        }
    });
}

function classinfomod(){
    classtime=$("#week").val()+$("#day").val()+$("#jie").val();
    // alert(classtime);
    var ata = {
        name:$("#className").val(),
        numStudent:$("#numStudent").val(),
        time:classtime,
        site:$("#site").val(),
        roster:$('#file').val(),
        proportions:{
            report:$("#report").val(),
            presentation:$("#presentation").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

    }
    $.ajax({
        type:'put',
        url: '/class/'+getCookie("classDetail"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("�޸ĳɹ�!");
                window.location.href='/teacher/course/'+getCookie("courseDetail")+'/class/'+getCookie("classDetail");
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ��༶");
            }
        }
    });
}

/*----------------------------teacher/seminar-------------------------------*/
function judge_end(end_time){
    var endTime=new Date(end_time);  //���ַ���ת�������ڸ�ʽ
    var currentTime=new Date();  //��ȡϵͳʱ��
    if(endTime>currentTime){
        $("#score").hide();
        // alert("���ۿλ�û����");
    }
    else{
        $("#viewtopic").hide();
        // alert("���ۿ��Ѿ�����");
    }
}
function seminarinfo(){
    $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                $("#seminarname").html(data.name);
                $("#description").html(data.description);
                $("#groupingMethod").html(data.groupingMethod);
                $("#startTime").html(data.startTime);
                $("#endTime").html(data.endTime);
                judge_end(data.endTime);
                $("#seminartitle").html(data.name);  //��������ȡ
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ����ۿ�");
            }
        }
    });
}
function gettopiclist(){
    $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarDetail")+'/topic',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                var content=document.getElementById("topiccontent");   //��ȡ��Χ����
                var str="";
                for(var i=0;i<data.length;i++){
                    str+='<div class="topicBlock" id=\"'+data[i].id+'\" onclick=\"jumptopic(this.id);\"><div class="topicBlockFont">'+data[i].serial+'</div></div>'
                }
                str+=' <div class="topicBlock" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/topic/create\'\"><img class="addImg" src="/img/smalladd.png" alt="���"></div></div>'
                content.innerHTML=str;
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ����ۿ�");
            }
        }
    });
}
function deleteseminar(){
    var ata = {id:getCookie("seminarDetail")}
    $.ajax({
        type:'delete',
        url: '/seminar/'+getCookie("seminarDetail"),
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr){
            if(xhr.status == 204){
                alert("ɾ���ɹ�");
                backtocourse();  //ҳ����ת
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            403: function () {
                alert("�û�Ȩ�޲���");
            },
            404: function () {
                alert("δ�ҵ��༶");
            }
        }
    });
}
function viewtopic(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/status'
}
function score(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/score'
}
function  updateseminar(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/update'
}

/*----------------------------teacher/seminar_update-------------------------------*/
function backtoseminar(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")
}
function getseminardetail(){
    $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // alert("��ȡ�ɹ�");
                $("input[name='seminarName']").attr("value",data.name);
                $("input[name='description']").attr("value",data.description);
                $("input[name='groupingMethod']").attr("value",data.groupingMethod);
                $("input[name='startTime']").attr("value",data.startTime);
                $("input[name='endTime']").attr("value",data.endTime);
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ����ۿ�");
            }
        }
    });
}
function seminarinfomod(){
    var ata = {
        name:$("#seminarname").val(),
        description:$("#description").val(),
        groupingMethod:$("#groupingMethod").val(),
        startTime:$("#begintime").val(),
        endTime:$("#endtime").val()

    }
    $.ajax({
        type:'put',
        url: '/seminar/'+getCookie("seminarDetail"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("�޸ĳɹ�!");
                window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail");
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ����ۿ�");
            }
        }
    });
}
/*----------------------------teacher/topic-------------------------------*/
function topicinfo(){
    $.ajax({
        type:'get',
        url: '/topic/'+getCookie("topicDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                $('#topicserial').html(data.serial);
                $("#topicname").html(data.name);
                $("#description").html(data.description);
                $("#groupLimit").html(data.groupLimit);
                $("#groupMemberLimit").html(data.groupMemberLimit);
                $("#groupLeft").html(data.groupList+" ");
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ�����");
            }
        }
    });
}
function updatetopic(){
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+getCookie('topicDetail')+'/update'
}
function deletetopic(){
    var ata = {id:getCookie("topicDetail")}
    $.ajax({
        type:'delete',
        url: '/topic/'+getCookie("topicDetail"),
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr){
            if(xhr.status == 204){
                alert("ɾ���ɹ�");
                backtoseminar();  //ҳ����ת
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            403: function () {
                alert("�û�Ȩ�޲���");
            },
            404: function () {
                alert("δ�ҵ��༶");
            }
        }
    });
}

/*----------------------------teacher/topic_update-------------------------------*/
function backtotopic(){
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+getCookie('topicDetail')
}
function turntopicupdate(){
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+getCookie('topicDetail')+'/update'
}
function gettopicdetail(){
    $.ajax({
        type:'get',
        url: '/topic/'+getCookie("topicDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                $("#topicname").val(data.name);
                $("#description").html(data.description);
                $("#groupLimit").val(data.groupLimit);
                $("#groupMemberLimit").val(data.groupMemberLimit);
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ�����");
            }
        }
    });
}
function updatetopic(){
    var ata = {
        serial:"A",
        name:$("#topicname").val(),
        description:$("#description").val(),
        groupLimit:$('#groupLimit').val(),
        groupMemberLimit:$("#groupMemberLimit").val(),

    }
    $.ajax({
        type:'put',
        url: '/topic/'+getCookie("topicDetail"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("�޸ĳɹ�!");
                // alert(getCookie("courseDetail"));
                // alert(getCookie("seminarDetail"));
                backtotopic();
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ�����");
            }
        }
    });
}

/*----------------------------teacher/topic_create-------------------------------*/
function createtopic(){
    var ata = {
        serial:$("#topicserial").val(),
        name:$("#topicname").val(),
        description:$("#description").val(),
        groupLimit:$("#groupLimit").val(),
        groupMemberLimit:$("#groupMemberLimit").val()

    }
    $.ajax({
        type:'post',
        url: '/seminar/'+getCookie("seminarDetail")+'/topic',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("�����ɹ�!");
                backtoseminar();  //ҳ����ת
            }
        },
        statusCode: {
            403: function () {
                alert("�û�Ȩ�޲���");
            }
        }
    });
}
/*----------------------------teacher/score-------------------------------*/
function getreportlist(){
    $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarDetail")+'/group',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                // alert("��ȡ�ɹ�");
                var content=document.getElementById("reportcontent");   //��ȡ��Χ����
                var str="";
                for(var i=0;i<data.length;i++){
                    str+="  <tr>\n" +
                        "                    <td>" + data[i].seminarName + "</td>\n" +
                        "                    <td>" + data[i].groupName + "</td>\n" +
                        "                    <td>"+data[i].leaderName+"</td>\n" +
                        "                    <td>"+data[i].presentationGrade+"</td>\n" +
                        "                    <td>���ύ</td>\n" +
                        "                    <td>"+data[i].reportGrade+"</td>\n" +
                        "                    <td>"+data[i].grade+"</td>\n" +
                        "                    <td>\n" +
                        "                         <img src=\"/img/view.png\" alt=\"Ԥ��\" name='report' id=" + data[i].groupName + " onclick='jumpreportdetail(this.id)'>\n" +
                        "                        <img src=\"/img/download.png\" alt=\"����\" name='download' id=" + data[i].groupName + " onclick='jumpreportdetail(this.id)'>\n" +
                        "                    </td>\n" +
                        "                </tr>"
                }
                content.innerHTML=str;
            }
        },
        error: function (data,textStatus,xhr) {
            alert("����");
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ�С��");
            }
        }
    });
}

/*----------------------------teacher/topic_view-------------------------------*/
function find_groupinseminar(){
    $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarDetail")+'/group',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // alert("��ȡ�ɹ�");
                var content=document.getElementById("groupinseminar");
                var str="";
                for(var i=0;i<data.length;i++){
                    str+=" <tr>"+
                        "<td>"+data[i].seminarName+"</td>"+
                        "<td>"+data[i].groupName+"</td>"+
                        "<td>"+data[i].leaderName+"</td>"+
                        "</tr>"
                }
                content.innerHTML=str;
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            404: function () {
                alert("δ�ҵ�����");
            }
        }
    });
}
/*----------------------------teacher/report_score-------------------------------*/
function score_report(){
    var ata = {
        reportGrade:$("#reportgrade").val(),
    }
    $.ajax({
        type:'put',
        url: '/group/'+getCookie("groupDetail")+'/grade/report',
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("��ֳɹ�");
                window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/score'
            }
        },
        statusCode: {
            400: function () {
                alert("�����ID��ʽ");
            },
            403: function () {
                alert("Ȩ�޲���");
            },
            404: function () {
                alert("δ�ҵ�С��");
            }
        }
    });
}