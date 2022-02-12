<template>
    <div class="home">
        <el-container class="root-container">
            <el-header class="zero-header-container">
                <ZeroHeader />
            </el-header>
            <el-main class="zero-main-container">
                <div class="home-content">
                    <Carousel :config.sync="carouselConfig" :img-data-list.sync="imgDataList"></Carousel>
                    <div class="home-card-block">
                        <el-card class="wiki-card" shadow="always">
                            <div @click.stop="goBlogSys">
                                <h3>知识系统</h3>
                            </div>
                        </el-card>
                        <el-card class="wiki-card" shadow="always">
                            <div @click.stop="goBlogSys">
                                <h3>人力系统</h3>
                            </div>
                        </el-card>
                        <el-card class="wiki-card" shadow="always">
                            <div @click.stop="goBlogSys">
                                <h3>产品系统</h3>
                            </div>
                        </el-card>
                    </div>
                </div>
            </el-main>
            <el-footer class="zero-footer-container">
                <ZeroFooter />
            </el-footer>
        </el-container>
    </div>
</template>

<script>
// @ is an alias to /src
import ZeroHeader from '@/components/header/index.vue';
import ZeroFooter from '@/components/footer/index';
import Carousel from '@/components/carousel/index';
export default {
    name: 'Home',
    components: {
        ZeroHeader,
        ZeroFooter,
        Carousel
    },
    data() {
        return {
            carouselConfig: {
                interval: 3000,
                arrow: 'hover',
                height: document.body.clientHeight - 60 + 'px'
            },
            imgDataList: [
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
            ]
        };
    },
    methods: {
        goBlogSys() {
            this.$router.push('/blog/home');
        }
    }
};
</script>
<style scoped lang="scss">
@import 'src/styles/zero/home.scss';

.home-card-block {
    padding: 20px;
    display: flex;
    justify-content: center;
}
.wiki-card {
    width: 30%;
    min-height: 300px;
    margin: 10px 20px;
    cursor: pointer;
}
</style>
