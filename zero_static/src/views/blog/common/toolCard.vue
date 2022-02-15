<template>
    <el-card class="blog-tool-card blog-card blog-right-card" :shadow="cardShadow" :body-style="{ padding: '10px' }">
        <div slot="header" class="card-header-title-start">
            <span>墨虹</span>
            <el-button style="float: right; padding: 3px 0" type="text">翻转</el-button>
        </div>
        <div class="message-box-container" ref="messageContainer">
            <el-carousel indicator-position="none" height="200px" :autoplay="false" arrow="never">
                <el-carousel-item v-for="item in 4" :key="item">
                    <div class="box-message-list">
                        <el-card class="blog-message-box" shadow="never"> </el-card>
                        <el-card class="blog-message-box" shadow="never"> </el-card>
                        <el-card class="blog-message-box" shadow="never"> </el-card>
                    </div>
                </el-carousel-item>
            </el-carousel>
        </div>
    </el-card>
</template>

<script>
export default {
    name: 'ToolCard',
    data() {
        return {
            emy: '',
            cardShadow: 'always',

            listMessage: [
                {
                    user: '14',
                    content: '测试留言内容滚动效果1'
                },
                {
                    user: '12',
                    content: '测试留言内容滚动效果2'
                }
            ]
        };
    },
    methods: {
        move() {}
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.blog-card {
    padding: 0;
    border-radius: 20px;
}

.blog-tool-card {
    height: 240px;
    margin-left: 30px;
}
.box-message-list {
    display: flex;
    justify-content: space-between;
    //padding: 0 72px;
}
.blog-message-box {
    height: 180px;
    width: 360px;
    border-radius: 10px;
}
</style>
