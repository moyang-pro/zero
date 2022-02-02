import request from '@/utils/request';

export function login(data) {
    return request({
        url: '/sys-member/account/login',
        method: 'post',
        params: data
    });
}

export function getInfo(token) {
    return request({
        url: '/vue-admin-template/user/info',
        method: 'get',
        params: { token }
    });
}

export function logout() {
    return request({
        url: '/vue-admin-template/user/logout',
        method: 'post'
    });
}
