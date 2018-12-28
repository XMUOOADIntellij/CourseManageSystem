function convertTime(sDateTime) {
    if(sDateTime!=="") {
        let aDateTime = sDateTime.split(" ", 2);
        let sDate = aDateTime[1];
        let sTime = aDateTime[0];
        let aDate = sDate.split("/", 3);
        return aDate[2] + '-' + aDate[0] + '-' + aDate[1] + 'T' + sTime + ":00";
    }
    else return sDateTime;
}
function reconvertTime(sDateTime) {
    if(sDateTime!=="") {
        let aDateTime = sDateTime.split("T", 2);
        let sDate = aDateTime[0];
        let sTime = aDateTime[1];
        let aDate = sDate.split("-", 3);
        return sTime+' '+aDate[1] + '/' + aDate[2] + '/' + aDate[0];
    }
    else return sDateTime;
}