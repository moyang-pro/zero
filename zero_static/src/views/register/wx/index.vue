<template>
    <div class="content-wx-register">
        <div class="wx-register-title">
            <i class="wx-img svg-item">
                <span class="svg-container">
                    <svg-icon icon-class="weixin" />
                </span>
            </i>
            <span>微信快速注册</span>
        </div>
        <div class="wx-register-card">
            <div class="layout-left-block"></div>
            <el-divider
                direction="vertical"
                class="register-card-divider"
            ></el-divider>
            <div class="layout-right-block">
                <div class="right-block-title">
                    <span>微信扫码立即注册</span>
                </div>
                <div class="wx-mp-register-img">
                    <el-card
                        style="margin: 0;padding: 0;cursor: pointer;box-shadow: unset;"
                        body-style="padding:0;margin:0;box-sizing: border-box;"
                    >
                        <img
                            style="width: 180px; height:180px"
                            :src="require('@/assets/mp-qrcode.jpg')"
                            alt="公众号二维码"
                        />
                    </el-card>
                </div>
                <div class="right-block-tip">
                    <p>
                        扫码后「关注」服务号即可进入注册流程，
                        若已关注则立即开始注册
                    </p>
                </div>
                <div class="right-block-bottom">
                    <div class="other-register-way">
                        <span
                            class="other-register-way-text"
                            @click="showOtherRegisterWay = true"
                        >
                            其他登录方式>>
                        </span>
                        <span v-show="showOtherRegisterWay">
                            <i class="svg-item">
                                <span class="svg-container">
                                    <svg-icon
                                        icon-class="email-blue"
                                        class="svg-item-li"
                                    />

                                    <svg-icon
                                        icon-class="QQ-color"
                                        class="svg-item-li"
                                    />

                                    <svg-icon
                                        icon-class="account-blue"
                                        class="svg-item-li"
                                    />
                                </span>
                            </i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'WxRegister',
    data() {
        return {
            showOtherRegisterWay: false
        }
    }
}
</script>
<style scoped>
.wx-register-title {
    height: 72px;
    border-bottom: #e5e5e5 1px solid;
    position: relative;
    background-color: #fff;
    font-size: 18px;
    padding: 24px;
}
.wx-register-card {
    background-color: #fff;
    padding-bottom: 40px;
    min-height: 600px;
    display: flex;
}
.layout-left-block {
    width: 50%;
}
.layout-right-block {
    width: 50%;
}
.register-card-divider {
    height: 320px;
    margin: 10%;
}
.wx-mp-register-img {
    width: 180px;
    height: 180px;
    margin: 0 60% 10% 5%;
}
.right-block-title {
    text-align: center;
    margin: 20% 60% 10% 10%;
}
.right-block-tip {
    font-size: 14px;
    color: #97a8be;
    margin-right: 50%;
    min-height: 60px;
}
.right-block-bottom {
    margin-top: 80px;
    bottom: 0;
    margin-right: 10px;
    min-height: 40px;
}
.other-register-way {
    cursor: pointer;
    float: right;
}
.other-register-way-text:hover {
    color: #20a0ff;
}
.svg-item {
    display: inline-block;
    line-height: 20px;
    font-size: 32px;
}
.svg-item-li {
    margin-left: 12px;
    margin-bottom: -5px;
}
</style>
