class NumberUtils {
    isValidateId(id) {
        if (!this.isRealNum(id)) {
            return false;
        }
        return !(id > (2 ^ 63) || id <= 0);
    }

    isRealNum(num) {
        if (num == null || num === '' || !num) {
            return false;
        }
        return !Number.isNaN(num);
    }
}

export default new NumberUtils();
