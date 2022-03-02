<template>
    <div class="blog-home-notice">
        <el-carousel :interval="config.interval" :arrow="config.arrow" :height="config.height">
            <el-carousel-item v-for="item in topList" :key="item.id">
                <el-image
                    :src="item.coverUrl"
                    alt=""
                    fit="fill"
                    class="bm-carousel-img"
                    @click.stop="showArticle(item.id)"
                >
                </el-image>
                <div class="bm-carousel-title text-one-line" @click.stop="showArticle(item.id)">
                    <span class="">{{ item.title }}</span>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
import { getHomeTopBlogList } from '@/api/blog';
export default {
    name: 'index',
    data() {
        return {
            config: {
                interval: 8000,
                arrow: 'never',
                height: '560px'
            },
            topList: [
                {
                    id: 1,
                    coverUrl: require('@/assets/img/blonde.jpg'),
                    title: 'Windows环境下配置蘑菇博客环境Windows环境下配置蘑菇博客环境Windows环境下配置蘑菇博客环境'
                },
                {
                    id: 2,
                    coverUrl: require('@/assets/img/mountains.jpg'),
                    title: 'Windows环境下配置蘑菇博客环境'
                },
                {
                    id: 3,
                    coverUrl: require('@/assets/img/whale.jpg'),
                    title: 'Windows环境下配置蘑菇博客环境',
                    link: 'http://www.baidu.com'
                }
            ]
        };
    },
    methods: {
        getHomeTopBlog() {
            getHomeTopBlogList().then(res => {
                this.topList = res.data;
            });
        },
        showArticle(id) {
            if (!id || !Number.isSafeInteger(id)) {
                this.$message.error('文章ID错误！');
            }
            this.$router.push({ path: `/blog/read/${id}.html` });
        }
    },
    mounted() {
        this.getHomeTopBlog();
    }
};
</script>

<style scoped lang="scss">
.bm-carousel-img {
    height: 100%;
    width: 100%;
    opacity: 0.9;
    cursor: pointer;
}
.bm-carousel-title {
    padding: 0 120px;
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
