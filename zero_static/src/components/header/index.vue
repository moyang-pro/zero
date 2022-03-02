<template>
    <div id="zero-header" :class="fixed ? 'zero-header-fixed' : 'zero-header'">
        <div style="margin:0;padding:0;" class="zero-head-inner">
            <div class="zero-logo">
                <a href="/">
                    <i class="logo-img">
                        <span class="svg-container">
                            <svg-icon icon-class="sun" />
                        </span>
                    </i>
                    墨阳空间
                </a>
            </div>
            <div class="head-middle">
                <div class="header-nav-menu">
                    <el-menu
                        v-if="menuShow"
                        default-active="HOME"
                        class="el-menu-zero"
                        mode="horizontal"
                        @select="handleSelectMenu"
                        background-color="#2b303b"
                        text-color="#fff"
                        active-text-color="#ffd04b"
                    >
                        <menu-item v-for="item in menuData" :key="item.code" :item="item" />
                    </el-menu>
                </div>
                <div class="header-search-block" v-if="searchShow"><searchWidget /></div>
                <div style="line-height: 60px;padding: 0 20px">
                    <el-button size="mini" type="warning" round class="el-icon-edit-outline">
                        创作
                    </el-button>
                </div>
                <div class="header-right-user" v-if="userLrShow">
                    <span v-if="!this.hasUser">
                        <span class="text-user-lr" @click.stop="login()">立即登录</span>
                        <el-divider direction="vertical"></el-divider>
                        <span class="text-user-lr" @click.stop="register()">注册</span>
                    </span>
                    <el-dropdown v-else class="avatar-container" trigger="hover">
                        <div class="avatar-wrapper">
                            <div>
                                <img :src="avatar" class="user-avatar" alt="个人头像" @click.stop="goUserProfile" />
                            </div>
                            <div class="header-notice-block">
                                <el-badge :value="noticeCount" class="item">
                                    <svg-icon icon-class="notice"> </svg-icon>
                                </el-badge>
                            </div>
                        </div>

                        <el-dropdown-menu slot="dropdown" class="user-dropdown">
                            <router-link to="/home">
                                <el-dropdown-item>
                                    首页
                                </el-dropdown-item>
                            </router-link>
                            <a target="_blank" href="https://github.com/moyang-pro">
                                <el-dropdown-item>个人中心</el-dropdown-item>
                            </a>
                            <a target="_blank" href="https://github.com/moyang-pro">
                                <el-dropdown-item>Github</el-dropdown-item>
                            </a>
                            <el-dropdown-item divided @click.native.stop="goUserProfile">
                                <span style="display:block;">个人博客</span>
                            </el-dropdown-item>
                            <el-dropdown-item divided @click.native.stop="logout">
                                <span style="display:block;">退出</span>
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </div>
            <div class="head-end">
                <a href="/help">
                    <i class="el-icon-question" title="帮助与文档" />
                    <span title=" 帮助与文档">
                        帮助与文档
                    </span>
                </a>
            </div>
        </div>
    </div>
</template>

<script>
import menuItem from '@/components/menus/menu-item';
import searchWidget from '@/components/search/index';
export default {
    name: 'ZeroHeader',
    components: { menuItem, searchWidget },
    props: {
        fixed: {
            type: Boolean,
            required: false,
            default: false
        },
        menuConfig: {
            type: Object,
            required: false,
            default: null
        },
        menuData: {
            type: Array,
            required: false,
            default: null
        },
        menuShow: {
            type: Boolean,
            required: false,
            default: false
        },
        searchShow: {
            type: Boolean,
            required: false,
            default: true
        },
        userLrShow: {
            type: Boolean,
            required: false,
            default: true
        }
    },
    data() {
        return {
            hasUser: false,
            avatar: '',
            noticeCount: 0
        };
    },
    created() {
        this.initUserInfo();
    },
    methods: {
        initUserInfo() {
            this.hasUser = !!this.$store.state.user.name;
            this.avatar = require('@/assets/img/avatar.png');
        },
        handleSelectMenu() {},
        async logout() {
            await this.$store.dispatch('user/logout');
            await this.$router.push(`/login?redirect=${this.$route.fullPath}`);
        },
        login() {
            this.$router.push(`/login`);
        },
        register() {
            this.$router.push(`/register`);
        },
        goUserProfile() {
            let emy = this.$store.state.user.name;
            this.$router.push({ path: `/blog/profile/${emy}` });
        }
    }
};
</script>

<style lang="scss">
.zero-header-fixed {
    position: fixed;
    top: 0;
    z-index: 999;
    height: 60px;
    background-color: #2b303b;
    padding: 0 20px;
    width: 100%;
}
.zero-header {
    height: 60px;
    background-color: #2b303b;
    padding: 0 20px;
    width: 100%;
}
.zero-head-inner {
    display: flex;
    height: 60px;
    width: 100%;
}
.zero-logo {
    width: 200px;
    height: 60px;
    line-height: 60px;
    color: antiquewhite;
    font-size: 28px;
}

.logo-img {
    width: 60px;
    height: 60px;
    background: url('/zero_static/src/assets/logo.png');
}

.head-middle {
    width: calc(100% - 360px);
    display: flex;
    justify-content: space-between;
}
.head-end {
    width: 160px;
    height: 60px;
    padding: 20px;
    color: antiquewhite;
    font-size: 16px;
}
.head-end span:hover {
    color: rgb(26, 111, 160);
}
.el-menu-zero {
    display: flex;
    justify-content: flex-start;
    line-height: 60px;
    height: 60px;
}
.el-menu.el-menu--horizontal {
    border-bottom: 0;
}
.header-search-block {
    height: 60px;
    line-height: 60px;
    width: 100%;
}
.header-right-user {
    height: 60px;
    line-height: 60px;
    min-width: 120px;
}
.header-right-user {
    color: white;
}
.text-user-lr {
    cursor: pointer;
}
.text-user-lr:hover {
    color: #e6a23c;
}
.avatar-wrapper {
    display: grid;
    grid-template-columns: 40px 60px;
}
.user-avatar {
    width: 40px;
    height: 40px;
    cursor: pointer;
    border-radius: 20px;
}
.header-notice-block {
    font-size: 32px;
    cursor: pointer;
}
.avatar-wrapper .el-badge__content.is-fixed {
    top: 20px;
    right: 15px;
}
.avatar-wrapper .el-badge__content {
    height: 15px;
    line-height: 15px;
    padding: 0 3px;
}
</style>
