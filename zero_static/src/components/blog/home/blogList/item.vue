<template>
    <div class="bm-article-card">
        <el-card class="article-show-item">
            <div :class="articleInfo.coverUrl ? 'inline-article' : ''">
                <div class="article-info-show">
                    <el-link :underline="false" @click.stop="showArticle(articleInfo.id)">
                        <h3 class="text-one-line title-line-width text-title" :title="articleInfo.title">
                            {{ articleInfo.title }}
                        </h3>
                    </el-link>
                    <div style="height: 42px">
                        <span
                            class="text-two-line title-line-width text-des"
                            :title="articleInfo.des"
                            @click.stop="showArticle(articleInfo.id)"
                            >{{ articleInfo.des }}</span
                        >
                    </div>

                    <div class="article-item-opt">
                        <span class="ra-author-avatar-box">
                            <el-avatar :src="articleInfo.authorAvatar" class="ra-author-avatar"></el-avatar>
                            <span class="ra-author-text-vip" v-if="articleInfo.vipCode > 0">v</span>
                        </span>

                        <span class="right-10px text-one-line max-width-100px"> {{ articleInfo.author }}</span>

                        <el-tag type="success" v-if="articleInfo.articleType === 0" class="right-10px">原创</el-tag>
                        <el-tag type="warning" v-else-if="articleInfo.articleType === 1" class="right-10px"
                            >转载</el-tag
                        >
                        <el-tag type="info" v-else-if="articleInfo.articleType === 2" class="right-10px">翻译</el-tag>
                        <el-tag type="success" v-else class="right-10px">原创</el-tag>
                        <el-tag
                            :type="tagTypeArr[index % 5]"
                            size="mini"
                            :hit="true"
                            v-for="(item, index) in articleInfo.tags"
                            :key="item"
                            class="article-tag"
                            >{{ item }}</el-tag
                        >
                        <articleCount :article-info="articleInfo" />
                    </div>
                </div>
                <div style="height: 120px;width: 160px" v-if="articleInfo.coverUrl">
                    <el-image :src="articleInfo.coverUrl" alt="" fit="fill" class="box-100" />
                </div>
            </div>
        </el-card>
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
    data() {
        return {
            tagTypeArr: ['primary', 'danger', 'warning', 'success', 'info'],
            defaultUrl: require('@/assets/img/break.jpg')
        };
    },
    mounted() {
        console.log(this.articleInfo);
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
@import 'src/styles/zero/blog/blog';
.article-show-item {
    text-align: left;
    min-height: 60px;
    margin-bottom: 20px;
}
.inline-article {
    display: grid;
    grid-template-columns: auto 160px;
    max-height: 180px;
}
.article-info-show {
    margin-left: 10px;
    padding: 5px;
}
.title-line-width {
    width: 100%;
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

.article-item-opt {
    position: relative;
    margin-top: 15px;
    font-size: 12px;
    text-align: start;
    display: flex;
    justify-content: flex-start;
    line-height: 30px;
    height: 30px;
    align-items: center;
    color: #97a8be;
}
.right-10px {
    margin-right: 10px;
}
.max-width-100px {
    max-width: 100px;
}
.article-tag {
    margin-right: 8px;
    cursor: pointer;
}
.ra-author-avatar-box {
    display: flex;
    justify-content: space-between;
    position: relative;
    max-width: 80px;
    cursor: pointer;
    margin-right: 10px;
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
