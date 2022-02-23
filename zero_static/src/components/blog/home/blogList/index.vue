<template>
    <el-tabs @tab-click="handleClick" v-model="activeTab">
        <el-tab-pane :name="blogTabEnum.TAB_LAST">
            <span slot="label"><i class="el-icon-collection-tag"></i> 最新</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :article-info="item" :key="item"> </ArticleItem>
            </div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_POP">
            <span slot="label"><i class="el-icon-magic-stick"></i> 最热</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :article-info="item" :key="item"> </ArticleItem>
            </div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_CITY">
            <span slot="label"><i class="el-icon-data-line"></i> 行业精选</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :article-info="item" :key="item"> </ArticleItem>
            </div>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import ArticleItem from '@/components/blog/home/blogList/item';
import BlogTabEnum from '@/utils/enum/blogTabEnum';
export default {
    name: 'index',
    components: {
        ArticleItem
    },
    data() {
        return {
            activeTab: '',
            blogTabEnum: BlogTabEnum,
            articleList: []
        };
    },
    methods: {
        handleClick() {
            this.articleList = [];
            if (this.activeTab === BlogTabEnum.TAB_LAST) {
                this.articleList = [
                    {
                        articleType: 0,
                        author: '墨阳',
                        clickCount: 0,
                        collectCount: 0,
                        coverUrl: null,
                        des:
                            '我是一个小小程序员我是一个小小程序员我是一个小小程序员我是一个小小程序员我是一个小小程序员',
                        id: 1,
                        readCount: 25,
                        tags: ['java', '后端', 'Spring'],
                        title: '我是一个小小程序员我是一个小小程序员',
                        userCount: 20
                    },
                    {
                        articleType: 0,
                        author: '',
                        clickCount: 0,
                        collectCount: 0,
                        coverUrl: null,
                        des: '',
                        id: 1,
                        readCount: '',
                        tags: '',
                        title: '',
                        userCount: ''
                    }
                ];
            }
        }
    }
};
</script>

<style scoped></style>
