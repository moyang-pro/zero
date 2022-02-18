<template>
    <el-container class="root-container">
        <el-header class="zero-header-container">
            <ZeroHeader :menuShow="true" :menu-data="menu" :fixed="true" />
        </el-header>
        <el-main class="zero-main-container">
            <router-view />
        </el-main>
        <el-footer class="zero-footer-container">
            <ZeroFooter />
        </el-footer>
    </el-container>
</template>

<script>
// @ is an alias to /src
import ZeroHeader from '@/components/header/index.vue';
import ZeroFooter from '@/components/footer/index';

export default {
    name: 'index',
    components: {
        ZeroHeader,
        ZeroFooter
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
                    code: 'BLOG_ARCHIVE',
                    name: '归档',
                    path: '/blog/archive',
                    level: 1,
                    leaf: true,
                    sortIndex: 1,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'BLOG_CATEGORY',
                    name: '分类',
                    path: '/blog/category',
                    level: 1,
                    leaf: true,
                    sortIndex: 3,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'BLOG_TAG',
                    name: '标签',
                    path: '/blog/tag',
                    level: 1,
                    leaf: true,
                    sortIndex: 2,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'BLOG_MESSAGE',
                    name: '交流区',
                    path: '/blog/message',
                    level: 1,
                    leaf: true,
                    sortIndex: 5,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'BLOG_ABOUT',
                    name: '关于我',
                    path: '/about/me',
                    level: 1,
                    leaf: true,
                    sortIndex: 5,
                    iconClass: '',
                    children: []
                }
            ]
        };
    }
};
</script>

<style lang="scss" scoped></style>
