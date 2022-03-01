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
                    <div class="ad-ca-block">
                        <a :href="adInfo.link">
                            <el-image :src="adInfo.url" alt="" fit="fill" class="box-100" />
                            <div class="ad-ca-title">
                                <span>{{ adInfo.title }}</span>
                            </div>
                        </a>
                    </div>
                    <div style="height: 20px"></div>
                    <div class="ad-ca-block">
                        <a :href="caInfo.link">
                            <el-image :src="caInfo.url" alt="" fit="fill" class="box-100" />
                            <div class="ad-ca-title">
                                <span>{{ caInfo.title }}</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="bm-main-show">
                    <HomeBlog></HomeBlog>
                </div>
                <div class="bm-count-show">
                    <div class="bm-count-block-title"><span>博客统计数据</span></div>
                    <UserPop class="bm-slide-bar"></UserPop>
                </div>
            </div>
        </el-main>
        <el-footer class="zero-footer-container">
            <ZeroFooter />
        </el-footer>
        <el-backtop :visibility-height="220" :bottom="80" :right="20" class="toTop">
            <div><svg-icon icon-class="backtop" style="font-size: 25px;"></svg-icon></div>
        </el-backtop>
    </el-container>
</template>

<script>
import ZeroHeader from '@/components/header/index.vue';
import ZeroFooter from '@/components/footer/index';
import BlogNotice from '@/components/blog/home/notice/index';
import HomeBlog from '@/components/blog/home/blogList/index';
import UserPop from '@/components/blog/home/count/userPop';
export default {
    name: 'index',
    components: {
        ZeroHeader,
        ZeroFooter,
        BlogNotice,
        HomeBlog,
        UserPop
    },
    data() {
        return {
            adInfo: {
                url: require('@/assets/img/break.jpg'),
                title: 'Windows环境下配置蘑菇博客环境',
                link: 'http://www.baidu.com'
            },
            caInfo: {
                url: require('@/assets/img/lake.jpg'),
                title: 'Windows环境下配置蘑菇博客环境',
                link: 'http://www.baidu.com'
            },
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

.bm-notice-show {
    margin: 20px;
}
.bm-notice-right {
    margin: 20px 0 20px 0;
    display: grid;
    grid-template-rows: 270px 20px 270px;
}
.ad-ca-block {
    position: relative;
}
.ad-ca-title {
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    position: absolute;
    z-index: 10;
    bottom: 0;
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    background: rgba(0, 0, 0, 0.3);
}
.bm-main-show {
    margin: 20px;
}
.bm-count-show {
    margin: 20px;
}
.bm-count-block-title {
    height: 40px;
    font-size: 16px;
    font-weight: 600;
}
.toTop {
    height: 50px;
    width: 50px;
    background-color: #e6a23c;
    color: #f9f9f9;
}
.bm-slide-bar {
    margin-top: 15px;
    text-align: left;
}
</style>
