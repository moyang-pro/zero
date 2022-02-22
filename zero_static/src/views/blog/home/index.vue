<template>
    <el-container class="root-container">
        <el-header class="zero-header-container">
            <ZeroHeader :menuShow="true" :menu-data="menu" />
        </el-header>
        <el-main class="zero-main-container">
            <div class="bm-page-main bm-main">
                <div class="bm-notice-show">
                    <BlogNotice></BlogNotice>
                </div>
                <div class="bm-notice-right">
                    <div class="ad-block"></div>
                    <div class="ca-block"></div>
                </div>
                <div class="bm-main-show">
                    <div class="bm-article-show">
                        <HomeBlogItem></HomeBlogItem>
                    </div>
                </div>
                <div class="bm-count-show"></div>
            </div>
        </el-main>
        <el-footer class="zero-footer-container">
            <ZeroFooter />
        </el-footer>
    </el-container>
</template>

<script>
import ZeroHeader from '@/components/header/index.vue';
import ZeroFooter from '@/components/footer/index';
import BlogNotice from '@/components/blog/home/notice/index';
import HomeBlogItem from '@/components/blog/home/blogList/item';
export default {
    name: 'index',
    components: {
        ZeroHeader,
        ZeroFooter,
        BlogNotice,
        HomeBlogItem
    },
    data() {
        return {
            menu: [
                {
                    code: 'BLOG_HOME',
                    name: '首页',
                    path: '/blog/home',
                    level: 1,
                    leaf: true,
                    sortIndex: 1,
                    iconClass: '',
                    children: null
                },
                {
                    code: 'WIKI_CITY',
                    name: '知识库',
                    path: '/blog/city/wiki',
                    level: 1,
                    leaf: true,
                    sortIndex: 1,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'COURSE_CITY',
                    name: '课程',
                    path: '/blog/city/course',
                    level: 1,
                    leaf: true,
                    sortIndex: 3,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'FAQ_WALL',
                    name: '问答墙',
                    path: '/wall/faq',
                    level: 1,
                    leaf: true,
                    sortIndex: 2,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'INDUSTRY_WAY',
                    name: '创业路',
                    path: '/way/industry',
                    level: 1,
                    leaf: true,
                    sortIndex: 4,
                    iconClass: '',
                    children: [
                        {
                            code: 'WAY_SIGN',
                            name: '行业前沿',
                            path: '/way/sign',
                            level: 2,
                            leaf: true,
                            iconClass: '',
                            children: null
                        },
                        {
                            code: 'WAY_PDT',
                            name: '行业前沿',
                            path: '/way/prediction',
                            level: 2,
                            leaf: true,
                            iconClass: '',
                            children: null
                        }
                    ]
                },
                {
                    code: 'ABOUT',
                    name: '关于我们',
                    path: '/about',
                    level: 1,
                    leaf: true,
                    sortIndex: 5,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'ADMIN',
                    name: '后台管理',
                    path: '/admin/blog',
                    level: 1,
                    leaf: true,
                    sortIndex: 5,
                    iconClass: '',
                    children: []
                }
            ]
        };
    },
    methods: {
        goProfilePage() {
            if (this.$store.state.user.name) {
                this.$router.push({ path: `/blog/profile/${this.$store.state.user.name}` });
            } else {
                this.$message.error('请先登录！');
            }
        }
    }
};
</script>

<style scoped lang="scss">
@import 'src/styles/zero/blog/blog';
.bm-page-main {
    display: grid;
    grid-template-columns: 1020px 420px;
    grid-template-rows: 640px auto;
}
.bm-notice-show {
    margin: 20px;
}
.bm-notice-right {
    margin: 20px 0 20px 0;
}
.ad-block {
    padding: 10px;
    background-color: #e6a23c;
    height: 50%;
    margin-bottom: 10px;
}
.ca-block {
    margin-top: 10px;
    padding: 10px;
    height: 50%;
    background-color: #e6a23c;
}
</style>
