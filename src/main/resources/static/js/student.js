function initAccountSetting(){
  getUserInfo();
}
function initCourseGroup(){
  getTeam();
  getMyTeam();
}
function initTeamCreate(){
  getMyTeam();
  getClassList();
  getNoTeam();
}
function initCourseGrade() {
  let myteam=getMyTeam();
  let aRound = getRoundList(); //轮次
  for (let i = 0; i < aRound.length; i++) {
    let aRoundScore = getRoundScoreByTeam(aRound[i].id); //总成绩
    let aSeminar = getSeminarList(aRound[i].id); //总成绩
      for (let k = 0; k < aSeminar.length; k++) {
        let aSeminarScore = getSeminarScoreByTeam(
            1,
            1
        ); //总成绩
      }

  }
}