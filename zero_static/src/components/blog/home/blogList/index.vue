<template>
    <el-tabs @tab-click="handleClick" v-model="activeTab">
        <el-tab-pane :name="blogTabEnum.TAB_LAST" :loading="loading">
            <span slot="label"><i class="el-icon-collection-tag"></i> 最新</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :articleInfo.sync="item" :key="item.id"> </ArticleItem>
            </div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_POP" :loading="loading">
            <span slot="label"><i class="el-icon-magic-stick"></i> 最热</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :articleInfo="item" :key="item.id"> </ArticleItem>
            </div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_CITY" v-if="false">
            <span slot="label"><i class="el-icon-data-line"></i> 行业精选</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :articleInfo="item" :key="item.id"> </ArticleItem>
            </div>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import ArticleItem from '@/components/blog/home/blogList/item';
import BlogTabEnum from '@/utils/enum/blogTabEnum';
import { getHomeBlogList } from '@/api/blog';
import PublicUtils from '@/utils/PublicUtils';
export default {
    name: 'index',
    components: {
        ArticleItem
    },
    data() {
        return {
            activeTab: BlogTabEnum.TAB_LAST,
            blogTabEnum: BlogTabEnum,
            articleList: [],
            page: {
                pageIndex: 1,
                pageSize: 15,
                total: 0
            },
            loading: false
        };
    },
    methods: {
        handleClick() {
            sessionStorage.setItem('bmTab', this.activeTab); // 本地存储一份
            this.getBlogHomeList();
        },
        getBlogHomeList() {
            this.loading = true;
            let pageRequest = PublicUtils.getPageRequest(this.page.pageIndex, this.page.pageSize, '', this.activeTab);
            this.articleList = [];
            getHomeBlogList(pageRequest)
                .then(res => {
                    console.log(res);
                    this.articleList = res.data;
                    this.loading = false;
                })
                .catch(() => {
                    this.loading = false;
                });
        }
    },
    created() {
        this.activeTab = sessionStorage.getItem('bmTab');
        this.getBlogHomeList();
    }
};
</script>

<style scoped></style>
