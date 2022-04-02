<template>
    <div class="article-item-card">
        <div class="article-show-item">
            <el-link :underline="false" @click.stop="showArticle(articleInfo.id, articleInfo.author)">
                <h3 class="text-one-line title-line-width text-title" :title="articleInfo.title">
                    {{ articleInfo.title }}
                </h3>
            </el-link>
            <span
                class="text-two-line title-line-width text-des"
                :title="articleInfo.des"
                @click.stop="showArticle(articleInfo.id, articleInfo.author)"
                >{{ articleInfo.des }}</span
            >
            <div class="article-item-opt">
                <el-tag type="success" v-if="articleInfo.articleType === 0">原创</el-tag>
                <el-tag type="warning" v-else-if="articleInfo.articleType === 1">转载</el-tag>
                <el-tag type="info" v-else-if="articleInfo.articleType === 2">翻译</el-tag>
                <el-tag type="success" v-else>原创</el-tag>
                <articleCount :article-info="articleInfo" />
            </div>
        </div>
        <el-divider></el-divider>
    </div>
</template>

<script>
import articleCount from '@/components/blog/article/count';
export default {
    name: 'index',
    components: {
        articleCount
    },
    props: {
        articleInfo: {
            type: Object,
            default: null
        }
    },
    methods: {
        showArticle(id, author) {
            if (!id || !Number.isSafeInteger(id)) {
                this.$message.error('文章ID错误！');
            }
            this.$router.push({ path: `/blog/read/${id}.html`, query: { author: author } });
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
    font-size: 14px;
}
</style>
