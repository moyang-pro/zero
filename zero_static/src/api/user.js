import request from '@/utils/request';

export function login(data) {
    return request({
        url: '/sys-member/account/login',
        method: 'post',
        params: data
    });
}

export function getInfo() {
    return request({
        url: '/sys-member/info',
        method: 'get'
    });
}

export function logout() {
    return request({
        url: '/sys-member/logout',
        method: 'post'
    });
}
