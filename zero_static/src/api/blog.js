import request from '@/utils/request';

export function writeBlog(data) {
    return request({
        url: '/blog-article/save',
        method: 'post',
        data: data
    });
}

export function getBlog(id) {
    return request({
        url: '/blog-article/read',
        method: 'get',
        params: { id }
    });
}

export function uploadImg(data) {
    return request({
        url: '/file/blog/upload',
        method: 'post',
        data: data
    });
}
