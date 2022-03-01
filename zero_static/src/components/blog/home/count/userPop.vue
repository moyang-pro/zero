<template>
    <el-card class="user-pop-card" :shadow="cardShadow">
        <div class="bm-user-block">
            <div class="bm-card-title-line">
                <h2 class="bm-card-title">
                    用户排行
                    <i
                        class="el-tooltip el-icon-refresh item"
                        aria-describedby="el-tooltip-688"
                        tabindex="0"
                        style="cursor: pointer; float: right;"
                    ></i>
                </h2>
            </div>
        </div>
    </el-card>
</template>

<script>
export default {
    name: 'userPop',
    data() {
        return {
            cardShadow: 'always'
        };
    }
};
</script>

<style lang="scss">
.user-pop-card {
}
.bm-card-title-line {
}
.bm-card-title {
    font-size: 18px;
    color: #282828;
    font-weight: 600;
    text-transform: uppercase;
    padding-bottom: 15px;
    margin: 0 0 25px;
    position: relative;
}
</style>
