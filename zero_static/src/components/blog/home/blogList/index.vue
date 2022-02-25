<template>
    <el-tabs @tab-click="handleClick" v-model="activeTab">
        <el-tab-pane :name="blogTabEnum.TAB_LAST">
            <span slot="label"><i class="el-icon-collection-tag"></i> 最新</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :article-info="item" :key="item.id"> </ArticleItem>
            </div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_POP">
            <span slot="label"><i class="el-icon-magic-stick"></i> 最热</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :article-info="item" :key="item.id"> </ArticleItem>
            </div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_CITY">
            <span slot="label"><i class="el-icon-data-line"></i> 行业精选</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :article-info="item" :key="item.id"> </ArticleItem>
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
            }
        };
    },
    methods: {
        handleClick() {
            this.articleList = [];
            if (this.activeTab === BlogTabEnum.TAB_LAST) {
                console.log(this.activeTab);
            }
        },
        getBlogHomeList() {
            let pageRequest = PublicUtils.getPageRequest(this.page.pageIndex, this.page.pageSize, '', this.activeTab);
            getHomeBlogList(pageRequest).then(res => {
                console.log(res);
                this.articleList = res.data;
            });
        }
    },
    created() {
        this.getBlogHomeList();
    }
};
</script>

<style scoped></style>
