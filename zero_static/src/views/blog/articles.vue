<template>
    <div>
        <div class="blog-main-content">
            <div class="title-block">
                <div class="user-show-block">
                    <el-card class="blog-user-card blog-card" :shadow="cardShadow">
                        <el-row>
                            <el-col :span="10">
                                <el-avatar
                                    size="large"
                                    :src="userInfo.avatar"
                                    class="blog-avatar"
                                ></el-avatar>
                            </el-col>
                            <el-col :span="12" :offset="2">
                                <p class="user-nick text-one-line" :title="userInfo.nick">
                                    {{ userInfo.nick }}
                                </p>
                                <div class="text-one-line">
                                    <span>墨阳账号：</span>
                                    <span class="user-account">{{ userInfo.emy }}</span>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row> </el-row>
                    </el-card>
                </div>
                <div class="tool-block">
                    <el-card class="blog-tool-card blog-card blog-right-card" :shadow="cardShadow">
                    </el-card>
                </div>
            </div>
            <div class="notebook-block">
                <div class="user-preference-block">
                    <el-card class="blog-preference-card blog-card" :shadow="cardShadow"> </el-card>
                </div>
                <div class="article-block">
                    <el-card
                        class="blog-article-card blog-card blog-right-card"
                        :shadow="cardShadow"
                    >
                        <el-tabs v-model="tabActiveName" @tab-click="handleTabClick">
                            <el-tab-pane name="articles">
                                <span slot="label"
                                    ><i class="el-icon-document"></i>{{ tabNames.articles }}</span
                                >
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
                                <span slot="label"
                                    ><i class="el-icon-date"></i>{{ tabNames.events }}</span
                                >
                            </el-tab-pane>
                        </el-tabs>
                    </el-card>
                </div>
            </div>
            <div class="float-button-block">
                <el-button
                    type="warning"
                    icon="el-icon-edit"
                    circle
                    size="large"
                    class="fab-write-button"
                    @click="writeBlog()"
                ></el-button>
            </div>
        </div>
    </div>
</template>

<script>
import { getMyBlogList } from '@/api/blog';
import articleItem from '@/components/blog/article/list-item/index';
import PublicUtils from '@/utils/PublicUtils';
export default {
    name: 'articles',
    components: {
        articleItem
    },
    data() {
        return {
            cardShadow: 'always',
            userInfo: {
                nick: '一米阳光',
                emy: '',
                avatar: require('@/assets/img/avatar.png')
            },
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
        this.getMineInfo();
    },
    methods: {
        writeBlog() {
            this.$router.push('/blog/write');
        },
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
        },
        getMineInfo() {
            let nick = this.$store.state.user.nick;
            let avatar = this.$store.state.user.avatar;
            this.userInfo.nick = nick ? nick : '一米阳光';
            this.userInfo.avatar = avatar ? avatar : require('@/assets/img/avatar.png');
            this.userInfo.emy = this.$store.state.user.name;
        }
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.title-block {
    display: flex;
    justify-content: flex-start;
}
.notebook-block {
    width: 100%;
    display: flex;
    justify-content: flex-start;
    margin: 20px 0;
}

.blog-user-card {
    width: 420px;
    height: 240px;
}
.blog-tool-card {
    height: 240px;
    margin-left: 30px;
}

.blog-preference-card {
    width: 420px;
    min-height: 720px;
}
.blog-article-card {
    min-height: 720px;
    margin-left: 30px;
}
.blog-avatar {
    width: 140px;
    height: 140px;
}
.blog-card {
    padding: 0;
}

.user-nick {
    font-family: STXingkai, Hiragino Sans GB, Arial, Microsoft YaHei, Verdana, Roboto, Noto,
        Helvetica Neue, sans-serif;
    font-size: 24px;
    cursor: pointer;
}
.float-button-block {
    text-align: end;
}
.fab-write-button {
    height: 60px;
    width: 60px;
    font-size: 24px;
    position: relative;
    bottom: 160px;
    left: 45px;
    box-shadow: 0 2px 12px 0 #cdcdcb;
    //box-shadow: 1px 1px 2px 1px ;
}
</style>
