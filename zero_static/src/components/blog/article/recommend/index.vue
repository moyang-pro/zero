<template>
    <el-card :shadow="cardShadow" class="article-info-card"> </el-card>
</template>

<script>
export default {
    name: 'index',
    data() {
        return {
            cardShadow: 'always'
        };
    }
};
</script>

<style scoped>
.article-info-card {
    width: 300px;
    min-height: 500px;
    border-radius: 10px;
}
</style>
