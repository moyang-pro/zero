<template>
    <div class="register">
        <ZeroHeader></ZeroHeader>
        <div class="register-card vh-card">
            <div class="register-title">
                <h1>注册墨阳账号</h1>
            </div>
            <div class="register-main-content">
                <router-view />
            </div>
            <div class="register-to-login">
                <router-link style="color: #20a0ff;" to="/login">
                    登录已有墨阳账号>
                </router-link>
            </div>
        </div>
        <ZeroFooter></ZeroFooter>
    </div>
</template>

<script>
import ZeroHeader from '@/components/header/index'
import ZeroFooter from '@/components/footer/index'
export default {
    name: 'Register',
    components: {
        ZeroHeader,
        ZeroFooter
    }
}
</script>

<style scoped>
.register-card {
    min-height: 980px;
}
.register-title {
    display: flex;
    height: 14%;
}
.register-title h1 {
    margin: auto;
    font-size: 32px;
    font-weight: 400;
    max-width: 1180px;
}
.register-main-content {
    width: 60%;
    margin: auto;
    max-height: 650px;
}
.register-to-login {
    height: 50px;
    margin: 20px auto;
}
</style>
