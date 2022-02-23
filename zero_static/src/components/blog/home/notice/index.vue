<template>
    <div class="blog-home-notice">
        <el-carousel :interval="config.interval" :arrow="config.arrow" :height="config.height">
            <el-carousel-item v-for="item in dataList" :key="item.id">
                <el-image :src="item.url" alt="" fit="fill" style="height: 100%;width: 100%;opacity:0.9;"> </el-image>
                <div class="bm-carousel-title">
                    <span>{{ item.title }}</span>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
export default {
    name: 'index',
    data() {
        return {
            config: {
                interval: 3000,
                arrow: 'never',
                height: '560px'
            },
            dataList: [
                {
                    id: 1,
                    url: require('@/assets/img/blonde.jpg'),
                    title: 'Windows环境下配置蘑菇博客环境'
                },
                {
                    id: 2,
                    url: require('@/assets/img/mountains.jpg'),
                    title: 'Windows环境下配置蘑菇博客环境'
                },
                {
                    id: 3,
                    url: require('@/assets/img/whale.jpg'),
                    title: 'Windows环境下配置蘑菇博客环境',
                    link: 'http://www.baidu.com'
                }
            ]
        };
    }
};
</script>

<style scoped lang="scss">
.bm-carousel-title {
    color: #fff;
    font-size: 22px;
    cursor: pointer;
    position: absolute;
    z-index: 10;
    bottom: 0;
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    background: rgba(0, 0, 0, 0.3);
}
</style>
