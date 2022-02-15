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
                <el-tag type="success" v-if="articleInfo.articleType === 0">原创</el-tag>
                <el-tag type="warning" v-else-if="articleInfo.articleType === 1">转载</el-tag>
                <el-tag type="info" v-else-if="articleInfo.articleType === 2">翻译</el-tag>
                <el-tag type="success" v-else>原创</el-tag>
                <span class="text-span-line">
                    <span class="text-span-item" title="发布时间"
                        ><i class="el-icon-time" style="margin-right: 5px"></i>
                        {{ recently ? recentTimeDes : articleInfo.createAt }}</span
                    >
                    <span class="text-span-item" title="浏览量"
                        ><i class="el-icon-view" style="margin-right: 5px"></i>{{ articleInfo.readCount }}</span
                    >
                    <span class="text-span-item" title="浏览人数"
                        ><i class="el-icon-user" style="margin-right: 5px"></i>{{ articleInfo.userCount }}</span
                    >
                    <span class="text-span-item" title="Star数量"
                        ><i class="el-icon-star-on" style="margin-right: 5px"></i>{{ articleInfo.collectCount }}</span
                    >
                </span>
            </div>
        </div>
        <el-divider></el-divider>
    </div>
</template>

<script>
import { diffTime } from '@/utils/DataTimeUtil';
export default {
    name: 'index',
    props: {
        articleInfo: {
            type: Object,
            default: null
        }
    },
    data() {
        return {
            recently: false,
            recentTimeDes: ''
        };
    },
    created() {
        this.handleDateTime();
    },
    methods: {
        showArticle(id) {
            if (!id || !Number.isSafeInteger(id)) {
                this.$message.error('文章ID错误！');
            }
            this.$router.push({ path: `/blog/read/${id}.html` });
        },
        handleDateTime() {
            if (new Date(this.articleInfo.createAt) - new Date() <= 24 * 3600 * 1000) {
                this.recently = true;
                this.recentTimeDes = diffTime(new Date(this.articleInfo.createAt), new Date());
            }
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
.text-span-line {
    color: #77777e;
    font-size: 13px;
}
.text-span-item {
    margin-left: 20px;
    cursor: pointer;
}
</style>
