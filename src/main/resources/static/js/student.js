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

  let aRoundScore = getRoundScoreByCourse(); //总成绩
  for (let j = 0; j < aRoundScore.length; j++) {

    let aSeminarScore = getSeminarScoreByRound(); //总成绩
  }
}