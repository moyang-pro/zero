<template>
    <el-card class="blog-preference-card blog-card" :shadow="cardShadow">
        <div slot="header" class="card-header-title-start">
            <span>墨风</span>
            <el-button style="float: right; padding: 3px 0" type="text">翻转</el-button>
        </div>
        <div class="tag-collection-block">
            <div ref="tagLineChart" class="tag-pie-chart-box"></div>
        </div>
        <div class="city-collection-block">
            <div class="city-first-box-header">
                <span>行业精选</span>
            </div>
            <div class="city-first-box"></div>
        </div>
    </el-card>
</template>

<script>
import EChart from '@/utils/EChart';

export default {
    name: 'preferenceCard',
    data() {
        return {
            emy: '',
            cardShadow: 'always',
            tagsData: [
                {
                    label: 'java',
                    count: 3
                },
                {
                    label: 'spring',
                    count: 5
                },
                {
                    label: 'mysql',
                    count: 2
                },
                {
                    label: 'js',
                    count: 5
                },
                {
                    label: 'css',
                    count: 14
                },
                {
                    label: 'html',
                    count: 16
                }
            ]
        };
    },
    created() {
        this.$nextTick(() => {
            this.drawLine();
        });
    },
    mounted() {},
    methods: {
        drawLine() {
            let config = {
                type: 'pie'
            };
            // 基于准备好的dom，初始化echarts实例
            let myChart = this.$echarts.init(this.$refs.tagLineChart);
            console.log(myChart);
            EChart.showLineChart(myChart, config, this.tagsData);
        }
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.blog-preference-card {
    width: 420px;
    min-height: 720px;
}

.tag-pie-chart-box {
    height: 300px;
    width: 100%;
}
.city-first-box-header {
    text-align: start;
    padding: 5px 5px;
    border-bottom: 1px solid #ebeef5;
    font-weight: bold;
}
.city-first-box {
    min-height: 300px;
    width: 100%;
}
</style>
