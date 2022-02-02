<template>
    <el-container class="root-container">
        <el-header class="zero-header-container">
            <ZeroHeader />
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
    }
};
</script>

<style scoped></style>
