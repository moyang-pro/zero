<template>
    <el-card :shadow="cardShadow" class="article-info-card">
        <div slot="header" class="card-header-title-start">
            <span>相关推荐</span>
            <el-button style="float: right; padding: 3px 0" type="text">换一批</el-button>
        </div>
        <div class="recommend-article-box">
            <div class="recommend-article-item" v-for="articleItem in recommendList" :key="articleItem.id">
                <div class="recommend-article-title text-one-line">
                    <span>{{ articleItem.title }}</span>
                </div>
                <div class="recommend-article-line">
                    <span>
                        <span class="ra-author-avatar-box">
                            <el-avatar :src="articleItem.authorAvatar" class="ra-author-avatar"></el-avatar>
                            <span class="ra-author-text-vip" v-if="articleItem.vipCode > 0">v</span>
                        </span>
                    </span>
                    <el-divider direction="vertical"></el-divider>
                    <span>
                        点赞
                        <span v-if="articleItem.likeCount < 1000"> {{ articleItem.likeCount }}</span>
                        <span v-else> 12k+</span>
                    </span>
                    <el-divider direction="vertical"></el-divider>
                    <span>
                        评论
                        <span v-if="articleItem.commentCount < 1000"> {{ articleItem.commentCount }}</span>
                        <span v-else> 999+</span>
                    </span>
                    <el-divider direction="vertical"></el-divider>
                    <span>
                        浏览量
                        <span v-if="articleItem.clickCount < 1000"> {{ articleItem.clickCount }}</span>
                        <span v-else> 999+</span>
                    </span>
                </div>
            </div>
        </div>
    </el-card>
</template>

<script>
export default {
    name: 'index',
    data() {
        return {
            cardShadow: 'always',
            recommendList: [
                {
                    blogId: 1,
                    title: 'java从入门到精通',
                    author: '忍者物理得到的',
                    vipCode: 1,
                    authorAvatar: require('@/assets/img/avatar.png'),
                    likeCount: 1000,
                    commentCount: 15,
                    clickCount: 10144
                },
                {
                    blogId: 2,
                    title: 'java从入门到精通2',
                    author: 'moyang2',
                    vipCode: 1,
                    authorAvatar: require('@/assets/img/avatar.png'),
                    likeCount: 1200,
                    commentCount: 1700,
                    clickCount: 1055
                }
            ]
        };
    }
};
</script>

<style scoped>
.card-header-title-start {
    text-align: start;
}
.article-info-card {
    width: 300px;
    min-height: 500px;
    border-radius: 10px;
}
.recommend-article-item {
    margin-bottom: 20px;
}
.recommend-article-title {
    text-align: start;
    font-size: 16px;
    margin-bottom: 10px;
}
.recommend-article-line {
    font-size: 12px;
    text-align: start;
    display: flex;
    justify-content: space-between;
    line-height: 30px;
    height: 30px;
    align-items: center;
    color: #97a8be;
}
.ra-author-avatar-box {
    display: flex;
    justify-content: space-between;
    position: relative;
    max-width: 80px;
}
.ra-author-avatar {
    height: 30px;
    width: 30px;
    margin-right: 5px;
}
.ra-author-text-vip {
    height: 14px;
    width: 14px;
    background: #333;
    border-radius: 50%;
    font-size: 10px;
    color: gold;
    position: absolute;
    left: 18px;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
