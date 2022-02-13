<template>
    <div>
        blog-home
        <el-button @click="goProfilePage">
            个人中心
        </el-button>
    </div>
</template>

<script>
export default {
    name: 'index',
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

<style scoped></style>
