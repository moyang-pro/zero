<template>
    <el-tabs @tab-click="handleClick" v-model="activeTab">
        <el-tab-pane :name="blogTabEnum.TAB_LAST">
            <span slot="label"><i class="el-icon-collection-tag"></i> 最新</span>
            <div class="bm-article-list" ref="bmArticleBlock">
                <ArticleItem v-for="item in articleList" :articleInfo="item" :key="item.id"> </ArticleItem>
            </div>
            <div class="bm-more-item" v-if="articleList.length < page.total">
                <el-button type="warning" size="mini" round @click.stop="queryMore">查看更多</el-button>
            </div>
            <div v-else><span>你已经看完我喽~</span></div>
        </el-tab-pane>
        <el-tab-pane :name="blogTabEnum.TAB_POP">
            <span slot="label"><i class="el-icon-magic-stick"></i> 最热</span>
            <div class="bm-article-list">
                <ArticleItem v-for="item in articleList" :articleInfo="item" :key="item.id"> </ArticleItem>
            </div>
            <div class="bm-more-item">
                <el-button type="warning" size="mini" round>查看更多</el-button>
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
import { showLoading, hideLoading } from '@/utils/loading';
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
                pageSize: 3,
                total: 0
            }
        };
    },
    methods: {
        handleClick() {
            sessionStorage.setItem('bmTab', this.activeTab); // 本地存储一份
            this.articleList = [];
            this.getBlogHomeList();
        },
        getBlogHomeList(isMore = false) {
            showLoading(this.$refs.bmArticleBlock);
            let pageRequest = PublicUtils.getPageRequest(this.page.pageIndex, this.page.pageSize, '', this.activeTab);
            getHomeBlogList(pageRequest)
                .then(res => {
                    if (!isMore) {
                        this.articleList = res.list;
                    } else {
                        if (!!res.list && res.list.length !== 0) {
                            this.articleList.push(...res.list);
                        }
                    }
                    this.page.total = res.total;
                    hideLoading();
                })
                .catch(() => {
                    hideLoading();
                });
        },
        queryMore() {
            this.page.pageIndex++;
            this.getBlogHomeList(true);
        }
    },
    created() {
        let tab = sessionStorage.getItem('bmTab');
        if (tab) {
            this.activeTab = tab;
        }
        this.$nextTick(() => {
            this.articleList = [];
            this.getBlogHomeList();
        });
    }
};
</script>

<style scoped>
.bm-more-item {
}
</style>
