<template>
    <div class="article-item-card">
        <div class="article-show-item">
            <el-link :underline="false" @click.stop="showArticle(articleInfo.id)">
                <h3 class="text-one-line title-line-width text-title" :title="articleInfo.title">
                    {{ articleInfo.title }}
                </h3>
            </el-link>
            <span
                class="text-two-line title-line-width text-des"
                :title="articleInfo.des"
                @click.stop="showArticle(articleInfo.id)"
                >{{ articleInfo.des }}</span
            >
            <div class="article-item-opt">
                <el-tag type="success">原创</el-tag>
            </div>
        </div>
        <el-divider></el-divider>
    </div>
</template>

<script>
export default {
    name: 'index',
    props: {
        articleInfo: {
            type: Object,
            default: null
        }
    },
    data() {
        return {};
    },
    methods: {
        showArticle(id) {
            if (!id || !Number.isSafeInteger(id)) {
                this.$message.error('文章ID错误！');
            }
            this.$router.push({ path: `/blog/read/${id}.html` });
        }
    }
};
</script>

<style scoped lang="scss">
.article-show-item {
    text-align: left;
    min-height: 60px;
}
.title-line-width {
    width: 100%;
}
.article-item-opt {
    margin-top: 15px;
}
.el-divider--horizontal {
    margin: 10px 0;
}
.text-title {
    cursor: pointer;
}
.text-des {
    color: #555666;
    cursor: pointer;
}
</style>
