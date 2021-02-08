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
    name: 'Index',
    components: {
        ZeroHeader,
        ZeroFooter
    }
}
</script>

<style scoped>
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
    height: 75%;
    width: 60%;
    margin: auto;
    min-height: 600px;
    min-width: 800px;
    max-width: 1180px;
    max-height: 800px;
}
.register-to-login {
    min-height: 50px;
}
</style>
