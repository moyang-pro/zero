<template>
    <el-card class="blog-preference-card blog-card" :shadow="cardShadow">
        <div slot="header" class="card-header-title-start">
            <span>墨风</span>
            <el-button style="float: right; padding: 3px 0" type="text">翻转</el-button>
        </div>
    </el-card>
</template>

<script>
export default {
    name: 'preferenceCard',
    data() {
        return {
            emy: '',
            cardShadow: 'always'
        };
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.blog-preference-card {
    width: 420px;
    min-height: 720px;
}
</style>
