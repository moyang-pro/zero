<template>
    <div class="login">
        <ZeroHeader></ZeroHeader>
        <div class="login-card vh-card">
            <div class="login-left-card">
                <div class="block-left"></div>
                <div class="block">
                    <div class="tab-login" v-show="loginType === 0">
                        <el-tabs v-model="way" @tab-click="handleClick" id="login-tab-code">
                            <el-tab-pane label="微信扫码登录" name="qrcode">
                                <div class="login-title">
                                    <span>请使用微信扫一扫登录</span>
                                    |
                                    <span class="qrcode-refresh" style="cursor: pointer">
                                        刷新
                                        <i class="el-icon-refresh" style="cursor: pointer"></i>
                                    </span>
                                </div>
                                <div class="img-qrcode">
                                    <el-card style="margin: 0;padding: 0;cursor: pointer;box-shadow: unset;" body-style="padding:0;margin:0;box-sizing: border-box;">
                                        <img style="width: 210px; height:210px" title="点击刷新二维码" :src="require('@/assets/mp-qrcode.jpg')" alt="公众号二维码" />
                                    </el-card>
                                </div>
                            </el-tab-pane>
                            <el-tab-pane label="墨阳扫码登录" name="password" v-if="false">
                                <div class="login-title">
                                    <span>请使用墨阳小程序扫一扫登录</span>
                                    |
                                    <span class="qrcode-refresh" style="cursor: pointer">
                                        刷新
                                        <i class="el-icon-refresh" style="cursor: pointer"></i>
                                    </span>
                                </div>
                                <div class="img-qrcode">
                                    <el-card style="margin: 0;padding: 0;cursor: pointer;box-shadow: unset;" body-style="padding:0;margin:0;box-sizing: border-box;">
                                        <img style="width: 210px; height:210px" title="点击刷新二维码" :src="require('@/assets/mp-qrcode.jpg')" alt="公众号二维码" />
                                    </el-card>
                                </div>
                            </el-tab-pane>
                        </el-tabs>
                    </div>
                    <div v-show="loginType === 1">
                        <div class="account-login-title">
                            <span>
                                <i class="logo-img">
                                    <span class="svg-container">
                                        <svg-icon icon-class="account-blue" />
                                    </span>
                                </i>
                                墨阳账号登录
                            </span>
                        </div>
                        <div class="account-login-form">
                            <el-form ref="formLogin" :model="accountInfo" :rules="ruleCustom">
                                <el-form-item prop="account">
                                    <el-input type="text" placeholder="墨阳账号emy" v-model="accountInfo.account" />
                                </el-form-item>
                                <el-form-item prop="password">
                                    <el-input type="password" placeholder="密码" v-model="accountInfo.password" />
                                </el-form-item>
                                <el-form-item style="margin-bottom: 14px">
                                    <div id="c1"></div>
                                </el-form-item>

                                <el-form-item>
                                    <el-button style="height: 45px;width: 100%" type="primary" @click="handleSubmit('formLogin')">
                                        确认登录
                                    </el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                    <div class="other-login-way" v-if="loginType === 0">
                        <span style="margin: 10px;color: #97a8be">
                            其他登录方式
                        </span>
                        <span class="way-item" title="邮箱">
                            <i class="logo-img">
                                <span class="svg-container">
                                    <svg-icon icon-class="email-black" />
                                </span>
                            </i>
                            邮箱登录
                        </span>
                        <span class="way-item" title="QQ">
                            <i class="logo-img">
                                <span class="svg-container">
                                    <svg-icon icon-class="QQ" />
                                </span>
                            </i>
                            QQ登录
                        </span>
                        <span class="way-item" title="扫码" @click="loginType = 1">
                            <i class="logo-img">
                                <span class="svg-container">
                                    <svg-icon icon-class="account" />
                                </span>
                            </i>
                            密码登录
                        </span>
                    </div>
                    <div class="other-login-way" v-if="loginType === 1" v-show="false">
                        <span style="margin: 10px;color: #97a8be">
                            其他登录方式
                        </span>
                        <span class="way-item" title="邮箱">
                            <i class="logo-img">
                                <span class="svg-container">
                                    <svg-icon icon-class="email-black" />
                                </span>
                            </i>
                            邮箱登录
                        </span>
                        <span class="way-item" title="QQ">
                            <i class="logo-img">
                                <span class="svg-container">
                                    <svg-icon icon-class="QQ" />
                                </span>
                            </i>
                            QQ登录
                        </span>
                        <span class="way-item" title="扫码" @click="loginType = 0">
                            <i class="logo-img">
                                <span class="svg-container">
                                    <svg-icon icon-class="qrcode" />
                                </span>
                            </i>
                            扫码登录
                        </span>
                    </div>
                    <div class="extra-block">
                        <div style="border-top:1px #e4e4e4 dashed;width:100%;margin-top: 10px" />
                        <div class="content-area-button">
                            <router-link to="/register">立即注册</router-link>
                            <el-divider direction="vertical" v-if="false"></el-divider>
                            <router-link to="/account/recover" v-if="false">
                                忘记账号
                            </router-link>
                            <el-divider direction="vertical"></el-divider>
                            <router-link to="/register">忘记密码</router-link>
                            <el-divider direction="vertical" v-if="false"></el-divider>
                            <router-link to="/register" v-if="false">子账号登录</router-link>
                        </div>
                        <div style="border-top:1px #e4e4e4 dashed;width:100%;" />
                        <div class="login-tip">
                            <span>关于墨阳空间登录</span>
                            <p class="moyang-room-tip">
                                初次使用的用户可以使用微信扫码关注“
                                <span style="font-weight: bold;color: #20a0ff;cursor: pointer">
                                    墨阳人生
                                </span>
                                ”公众号自动授权登录，也可使用邮箱、QQ和墨阳账号注册登录，
                                <span style="font-weight: bold">
                                    墨阳账号是墨阳系列产品的用户唯一标识。
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <el-divider direction="vertical" class="login-card-divider"></el-divider>
            <div class="login-right-card">
                <div class="block">
                    <div class="show-card-title">
                        <h2 style="font-size: 22px">
                            墨阳空间-你的创业工作空间
                        </h2>
                        <div class="text-work">
                            <ul style="display:inline-block ;text-align: start;color: #97a8be;margin: auto">
                                <li>模块一：创业思想指导，创业理论学习</li>
                                <li>模块二：寻找创业合伙人</li>
                                <li>模块三：创造产品，管理企业</li>
                            </ul>
                        </div>
                    </div>
                    <div class="img-show">
                        <img :src="require('@/assets/working.jpg')" alt="slogan图" style="height: 180px;width: 280px" />
                    </div>
                    <div class="slogan">
                        <div class="slogan-box">
                            <span class="slogan-box-li" v-show="slogan">
                                {{ slogan }}
                            </span>
                        </div>
                        <p class="slogan-bottom" title="点击开始创业">
                            >>>开启创业之门
                        </p>
                    </div>
                </div>
                <div class="block-right"></div>
            </div>
        </div>
        <ZeroFooter></ZeroFooter>
    </div>
</template>

<script>
import ZeroHeader from '@/components/header/index.vue';
import ZeroFooter from '@/components/footer/index.vue';
import { delSpace } from '@/utils/validate';

export default {
    name: 'Home',
    components: {
        ZeroHeader,
        ZeroFooter
    },
    data() {
        const validateAccount = (rule, value, callback) => {
            value = value.trim();
            this.accountInfo.account = delSpace(this.accountInfo.account);
            if (value === '') {
                callback(new Error('请输入墨阳账号'));
            } else {
                if (!this.accountExist()) {
                    callback(new Error('该账号不存在'));
                }
                callback();
            }
        };
        const validatePassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };
        return {
            windowHeight: 0,
            way: 'qrcode',
            slogan: '因为有你，我们仍在进步！',
            slogans: '没有昨天的今天，怎么会成就你最美的明天' + '如果没有创造，设计毫无意义',
            loginType: 1,
            accountInfo: {
                account: '',
                password: ''
            },
            ruleCustom: {
                account: [
                    {
                        validator: validateAccount,
                        trigger: 'blur'
                    }
                ],
                password: [
                    {
                        validator: validatePassword,
                        trigger: 'blur'
                    }
                ]
            },
            redirect: undefined,
            otherQuery: undefined
        };
    },
    created() {
        this.initPage();
        this.checkedCallBack();
    },
    methods: {
        handleClick() {
            console.log(this.windowHeight);
        },
        initPage() {
            this.windowHeight = window.innerHeight;
        },
        getLoginQrcode() {
            return require('@/assets/mp-qrcode.jpg');
        },
        getSloganImg() {
            return require('@/assets/working.jpg');
        },
        accountExist() {
            return true;
        },
        checkedCallBack(res) {
            console.log(res);
        },
        /**
         * 表单提交
         */
        handleSubmit() {
            const loginInfo = {
                username: this.accountInfo.account,
                password: this.accountInfo.password,
                platCode: this.$store.state.app.platCode
            };
            this.$store.dispatch('user/login', loginInfo).then(() => {
                this.$router.push({ path: this.redirect || `/blog/profile/${loginInfo.username}`, query: this.otherQuery });
            });
        },
        getOtherQuery(query) {
            return Object.keys(query).reduce((acc, cur) => {
                if (cur !== 'redirect') {
                    acc[cur] = query[cur];
                }
                return acc;
            }, {});
        }
    },
    watch: {
        $route: {
            handler: function(route) {
                const query = route.query;
                if (query) {
                    this.redirect = query.redirect;
                    this.otherQuery = this.getOtherQuery(query);
                }
            },
            immediate: true
        }
    }
};
</script>
<style>
.login-card {
    display: flex;
    width: 100%;
    height: 100vh;
    text-align: center;
}
.login-left-card {
    width: 50%;
    display: flex;
}
.block-left {
    width: 40%;
}
.block-right {
    width: 50%;
}
.block {
    margin: auto 10px;
    height: 70%;
    width: 50%;
}
.login-card-divider {
    height: 60%;
    margin: auto 10px;
}
.qrcode-refresh:hover {
    color: rgb(12, 156, 239);
}
.login-title {
    margin-top: 20px;
    margin-bottom: 20px;
}
.img-qrcode {
    width: 220px;
    height: 220px;
    margin: auto;
}
.other-login-way {
    display: flex;
    margin-top: 10px;
}
.way-item {
    margin: 10px 5px;
    cursor: pointer;
    font-size: 14px;
}
.way-item:hover {
    color: rgb(12, 156, 239);
}
.content-area-button {
    height: 60px;
    display: flex;
    justify-content: space-around;
    align-items: center;
}
.content-area-button a:hover {
    color: rgb(12, 156, 239);
}
.login-tip {
    margin: 25px 0;
    text-align: left;
}
.moyang-room-tip {
    font-size: 14px;
    line-height: 22px;
    color: #97a8be;
}
.login-right-card {
    width: 50%;
    display: flex;
}
.show-card-title {
    width: 100%;
    height: 20%;
}
.text-work {
    width: 100%;
    margin: 15px 0;
}

.text-work li {
    line-height: 22px;
}
.img-show {
    width: 280px;
    height: 180px;
    position: relative;
    margin: auto;
}
.img-show:after {
    position: absolute;
    content: '';
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    box-shadow: 0 0 5px 5px #ffffff inset;
}
.slogan {
    width: 100%;
    margin-top: 20px;
}
.slogan-box {
    width: 80%;
    height: 50px;
    margin: auto;
    text-align: center;
}
.slogan-box-li {
    font-size: 20px;
}
.slogan-bottom {
    margin-right: 20%;
    margin-top: -5px;
    text-align: right;
    color: #20a0ff;
    cursor: pointer;
}
</style>
<style scoped>
.account-login-title {
    height: 50px;
    font-size: 20px;
    font-weight: bold;
}
.account-login-form {
    width: 75%;
    margin: 10px auto;
}
</style>
<style lang="scss">
#login-tab-code {
    .el-tabs__item {
        width: 186px;
        padding: 0;
        font-size: 20px;
    }
    .el-tabs__active-bar {
        width: 186px;
    }
}
</style>
