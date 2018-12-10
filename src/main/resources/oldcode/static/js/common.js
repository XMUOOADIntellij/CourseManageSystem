/**
 * 默认的cookie写入方法
 * @param name
 * @param value
 */
function setCookie(name,value){
    var Days = 1;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ encodeURI (value) + ";expires=" + exp.toUTCString()+";path=/leasingCalculator";
}
/**
 * 获取Cookie中的值
 * @param objName
 * @returns
 */
function getCookie(objName){//获取指定名称的cookie的值 
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] === objName){
            return decodeURI(temp[1]);
        }
    }
}
