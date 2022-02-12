<template>
    <el-card class="blog-article-card blog-card blog-right-card" :shadow="cardShadow">
        <el-tabs v-model="tabActiveName" @tab-click="handleTabClick">
            <el-tab-pane name="articles">
                <span slot="label"><i class="el-icon-document"></i>{{ tabNames.articles }}</span>
                <div v-for="item in articlesInfo" :key="item.id">
                    <articleItem :article-info.sync="item"> </articleItem>
                </div>
                <div style="margin-top: 20px;text-align: end">
                    <el-pagination
                        @current-change="handleCurrentChange"
                        :hide-on-single-page="true"
                        :current-page.sync="page.pageIndex"
                        :page-size="page.pageSize"
                        layout="total, prev, pager, next"
                        :total="total"
                    >
                    </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane name="events">
                <span slot="label"><i class="el-icon-date"></i>{{ tabNames.events }}</span>
            </el-tab-pane>
        </el-tabs>
    </el-card>
</template>

<script>
import { getMyBlogList } from '@/api/blog';
import articleItem from '@/components/blog/article/list-item/index';
import PublicUtils from '@/utils/PublicUtils';
export default {
    name: 'articleCard',
    components: {
        articleItem
    },
    data() {
        return {
            cardShadow: 'always',
            total: 0,
            articlesInfo: [],
            tabActiveName: 'articles',
            tabNames: {
                articles: '文章',
                events: '事件'
            },
            page: {
                pageSize: 5,
                pageIndex: 1
            }
        };
    },
    created() {
        this.getBlogListMine();
    },
    methods: {
        getBlogListMine() {
            let pageRequest = PublicUtils.getPageRequest(this.page.pageIndex, this.page.pageSize);
            getMyBlogList(pageRequest).then(res => {
                this.articlesInfo = res.list;
                this.total = res.total;
            });
        },
        handleTabClick() {
            //this.tabActiveName = name;
        },
        handleCurrentChange(value) {
            this.page.pageIndex = value;
            this.getBlogListMine();
        }
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.blog-article-card {
    min-height: 720px;
    margin-left: 30px;
}

.blog-card {
    padding: 0;
}
</style>
