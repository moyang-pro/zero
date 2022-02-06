/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
    return /^(https?:|mailto:|tel:)/.test(path);
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validPhoneFormat(str) {
    const regPhone = /^1[3-9]\d{9}$/;
    return regPhone.test(str);
}
/**
 * @param {string} pwd
 * @returns {Boolean}
 */
export function validPasswordFormat(pwd) {
    const expPwd = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,32}$/;
    return expPwd.test(pwd);
}

export function checkSpaceExist(str) {
    const regSpace = /(^\s+)|[^\s]+[\s]+[^\s]+|(\s+$)/g;
    return regSpace.test(str);
}

export function delSpace(str) {
    const result = str.replace(/(^\s+)|(\s+$)/g, '');
    return result.replace(/\s/g, '');
}

/* 合法uri*/
export function validateURL(text) {
    const urlRegex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/;
    return urlRegex.test(text);
}

/* 小写字母*/
export function validateLowerCase(str) {
    const reg = /^[a-z]+$/;
    return reg.test(str);
}

/* 大写字母*/
export function validateUpperCase(str) {
    const reg = /^[A-Z]+$/;
    return reg.test(str);
}

/* 大小写字母*/
export function validateAlphabets(str) {
    const reg = /^[A-Za-z]+$/;
    return reg.test(str);
}
