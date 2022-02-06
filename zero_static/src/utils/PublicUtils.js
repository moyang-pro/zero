class PublicUtils {
    getPageRequest(index, size, keyword, data) {
        console.log('keyword:', keyword);
        if (!keyword || !data) {
            return {
                pageIndex: index,
                pageSize: size
            };
        }
        return {
            pageIndex: index,
            pageSize: size,
            keyword: keyword,
            data: data
        };
    }
}

export default new PublicUtils();
