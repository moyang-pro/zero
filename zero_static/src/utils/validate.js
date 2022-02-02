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
