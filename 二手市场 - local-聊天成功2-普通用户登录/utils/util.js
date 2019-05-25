const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}



// util.js
//时间戳转换成日期时间
// function js_date_time(unixtime) {
  // var dateTime = new Date(parseInt(unixtime) * 1000)
  // var year = dateTime.getFullYear();
  // var month = dateTime.getMonth() + 1;
  // var day = dateTime.getDate();
  // var hour = dateTime.getHours();
  // var minute = dateTime.getMinutes();
  // var second = dateTime.getSeconds();
  // var now = new Date();
  // var now_new = Date.parse(now.toDateString());  //typescript转换写法
  // var milliseconds = now_new - dateTime;
  // var timeSpanStr = year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
  // return timeSpanStr;
  // var time=Long.parseLong(unixtime);
  // var currenttime = new Date(time);
  
 
// }
// module.exports = {
  // js_date_time: js_date_time
// }


// util.js
//时间戳转换成日期时间
function datetimeFormat_1(longTypeDate) {
  var datetimeType = "";
  var date = new Date();
  date.setTime(longTypeDate);
  datetimeType += date.getFullYear();   //年  
  datetimeType += "-" + getMonth(date); //月   
  datetimeType += "-" + getDay(date);   //日  
  datetimeType +=  "  "+ getHours(date);   //时  
  datetimeType += ":" + getMinutes(date);      //分
  datetimeType += ":" + getSeconds(date);      //分
  return datetimeType;
}
//返回 01-12 的月份值   
function getMonth(date) {
  var month = "";
  month = date.getMonth() + 1; //getMonth()得到的月份是0-11  
  if (month < 10) {
    month = "0" + month;
  }
  return month;
}
//返回01-30的日期  
function getDay(date) {
  var day = "";
  day = date.getDate();
  if (day < 10) {
    day = "0" + day;
  }
  return day;
}
//返回小时
function getHours(date) {
  var hours = "";
  hours = date.getHours();
  if (hours < 10) {
    hours = "0" + hours;
  }
  return hours;
}
//返回分
function getMinutes(date) {
  var minute = "";
  minute = date.getMinutes();
  if (minute < 10) {
    minute = "0" + minute;
  }
  return minute;
}
//返回秒
function getSeconds(date) {
  var second = "";
  second = date.getSeconds();
  if (second < 10) {
    second = "0" + second;
  }
  return second;
}

module.exports = {
  datetimeFormat_1: datetimeFormat_1
}