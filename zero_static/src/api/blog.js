import request from '@/utils/request';

export function writeBlog(data) {
    return request({
        url: '/blog-article/save',
        method: 'post',
        data: data
    });
}

export function getMyBlogList(page) {
    return request({
        url: '/blog-article/myList',
        method: 'post',
        data: page
    });
}

export function getOneBlog(id) {
    return request({
        url: '/blog-article/read',
        method: 'get',
        params: { id }
    });
}

export function getMyBlog(id) {
    return request({
        url: '/blog-article/read/me',
        method: 'get',
        params: { id }
    });
}

export function updateBlog(data) {
    return request({
        url: '/blog-article/update',
        method: 'post',
        data: data
    });
}

export function deleteBlog(id) {
    return request({
        url: '/blog-article/delete',
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

export function uploadCoverImg(data) {
    return request({
        url: '/file/blog/cover/upload',
        method: 'post',
        data: data
    });
}

export function publishMyBlog(data) {
    return request({
        url: '/blog-article/publish',
        method: 'post',
        data: data
    });
}

export function getHomeTopBlogList() {
    return request({
        url: '/blog-article/page/home/top',
        method: 'get'
    });
}

export function getHomeBlogList(pageReq) {
    return request({
        url: '/blog-article/page/home/list',
        method: 'post',
        data: pageReq
    });
}

export function getHomeTopBannerList() {
    return request({
        url: '/zero-banner/page/blog/banner',
        method: 'get'
    });
}

export function getHomeUserPopList(pageReq) {
    return request({
        url: '/blog-user/page/home/user',
        method: 'post',
        data: pageReq
    });
}

export function getBlogAuthorInfo(author) {
    return request({
        url: '/blog-user/page/blog/author',
        method: 'get',
        params: { author }
    });
}

export function followAuthorOfBlog(author) {
    return request({
        url: '/blog-follower/follow',
        method: 'post',
        params: { author }
    });
}

export function unfollowAuthorOfBlog(author) {
    return request({
        url: '/blog-follower/unfollow',
        method: 'post',
        params: { author }
    });
}
