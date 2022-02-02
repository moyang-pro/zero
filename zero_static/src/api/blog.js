import request from '@/utils/request';

export function writeBlog(data) {
    return request({
        url: '/sys-member/account/login',
        method: 'post',
        params: data
    });
}

export function uploadImg(data) {
    return request({
        url: '/file/blog/upload',
        method: 'post',
        data: data
    });
}
