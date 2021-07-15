import request from '@/utils/request'

export function getCheckCode(params) {
    return request({
        url: '/sys-member/getCheckCode',
        method: 'get',
        params
    })
}
