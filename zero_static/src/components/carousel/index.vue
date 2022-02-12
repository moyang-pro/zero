<template>
    <el-carousel :interval="config.interval" :arrow="config.arrow" :height="config.height">
        <el-carousel-item v-for="item in imgDataList" :key="item.id">
            <el-image :src="item.url" alt="" fit="fill" style="height: 100%;width: 100%;opacity:0.4;"> </el-image>
            <!--            <div :style="{ background: 'url(' + item.url + ')' }"></div>-->
        </el-carousel-item>
    </el-carousel>
</template>

<script>
export default {
    name: 'index',
    props: {
        config: {
            type: Object,
            required: false,
            default: null
        },
        imgDataList: {
            type: Array,
            required: false,
            default: null
        }
    },
    data() {
        return {};
    },
    created() {
        if (!this.config) {
            this.config = this.getDefaultConfig();
        }
        if (!this.imgDataList) {
            this.imgDataList = [
                {
                    id: 1,
                    url: require('@/assets/img/blonde.jpg')
                },
                {
                    id: 2,
                    url: require('@/assets/img/mountains.jpg')
                },
                {
                    id: 3,
                    url: require('@/assets/img/whale.jpg')
                }
            ];
        }
    },
    methods: {
        getDefaultConfig() {
            return {
                interval: 3000,
                arrow: 'hover',
                height: document.body.clientHeight - 60 + 'px'
            };
        }
    }
};
</script>

<style scoped lang="scss">
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 500px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>
