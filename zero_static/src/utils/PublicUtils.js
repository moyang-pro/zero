class PublicUtils {
    getPageRequest(index, size, keyword, data) {
        return {
            pageIndex: index,
            pageSize: size,
            keyword: keyword ? keyword : '',
            data: data ? data : null
        };
    }
}

export default new PublicUtils();
