//时间差计算
/*
 * startDate==>开始时间
 * endDate==>结束时间
 * 事例：diffTime(data.createTime,new Date())
 *
 * */
export function diffTime(startDate, endDate) {
    let diff = endDate.getTime() - startDate; //.getTime();//时间差的毫秒数

    //计算出相差天数
    let days = Math.floor(diff / (24 * 3600 * 1000));

    //计算出小时数
    let leave1 = diff % (24 * 3600 * 1000); //计算天数后剩余的毫秒数
    let hours = Math.floor(leave1 / (3600 * 1000));
    //计算相差分钟数
    let leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数
    let minutes = Math.floor(leave2 / (60 * 1000));

    //计算相差秒数
    let leave3 = leave2 % (60 * 1000); //计算分钟数后剩余的毫秒数
    let seconds = Math.round(leave3 / 1000);

    let returnStr = seconds + '秒前';
    if (minutes > 0) {
        returnStr = minutes + '分钟前'; //+ returnStr;
    }
    if (hours > 0) {
        returnStr = hours + '小时前'; // + returnStr;
    }
    if (days > 0) {
        returnStr = days + '天前'; //+ returnStr;
    }
    return returnStr;
}
