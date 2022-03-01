import { Loading } from 'element-ui';

let loadingCount = 0;
let loading;

const startLoading = element => {
    loading = Loading.service({
        lock: true,
        text: '加载中……',
        background: 'rgba(0, 0, 0, 0.7)',
        target: element
    });
};

const endLoading = () => {
    loading.close();
};

export function showLoading(element) {
    if (loadingCount === 0) {
        startLoading(element);
    }
    loadingCount += 1;
}

export const hideLoading = () => {
    if (loadingCount <= 0) {
        return;
    }
    loadingCount -= 1;
    if (loadingCount === 0) {
        endLoading();
    }
};
