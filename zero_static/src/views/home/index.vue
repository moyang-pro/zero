<template>
    <div class="home">
        <el-container class="root-container">
            <el-header class="zero-header-container">
                <ZeroHeader :menuShow="true" :menuData="menu" />
            </el-header>
            <el-main class="zero-main-container">
                <div class="home-content">
                    <Carousel :config.sync="carouselConfig" :img-data-list.sync="imgDataList"></Carousel>
                    <div class="home-card-block">
                        <div class="home-card-item-left flip-card" ref="flipCardWiki">
                            <el-card class="wiki-card card-item" shadow="always">
                                <div @click.stop="goBlogSys">
                                    <h3>知识系统</h3>
                                </div>
                            </el-card>
                            <el-card class="wiki-card-back card-item" shadow="always">
                                <div @click.stop="goBlogSys">
                                    <h3>知识系统back</h3>
                                </div>
                            </el-card>
                        </div>
                        <div class="home-card-item-middle flip-card" ref="flipCardFriend">
                            <el-card class="wiki-card card-item" shadow="always">
                                <div @click.stop="goBlogSys">
                                    <h3>人力系统</h3>
                                </div>
                            </el-card>
                            <el-card class="wiki-card-back card-item" shadow="always">
                                <div @click.stop="goBlogSys">
                                    <h3>人力系统back</h3>
                                </div>
                            </el-card>
                        </div>
                        <div class="home-card-item-right flip-card" ref="flipCardProduct">
                            <el-card class="wiki-card card-item" shadow="always">
                                <div @click.stop="goBlogSys">
                                    <h3>产品系统</h3>
                                </div>
                            </el-card>
                            <el-card class="wiki-card-back card-item" shadow="always">
                                <div @click.stop="goBlogSys">
                                    <h3>产品系统back</h3>
                                </div>
                            </el-card>
                        </div>
                    </div>
                    <div class="home-doing-block">
                        <el-timeline>
                            <el-timeline-item timestamp="2018/4/12" placement="top">
                                <el-card>
                                    <h4>更新 Github 模板</h4>
                                    <p>王小虎 提交于 2018/4/12 20:46</p>
                                </el-card>
                            </el-timeline-item>
                            <el-timeline-item timestamp="2018/4/3" placement="top">
                                <el-card>
                                    <h4>更新 Github 模板</h4>
                                    <p>王小虎 提交于 2018/4/3 20:46</p>
                                </el-card>
                            </el-timeline-item>
                            <el-timeline-item timestamp="2018/4/2" placement="top">
                                <el-card>
                                    <h4>更新 Github 模板</h4>
                                    <p>王小虎 提交于 2018/4/2 20:46</p>
                                </el-card>
                            </el-timeline-item>
                        </el-timeline>
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
            menu: [
                {
                    code: 'HOME',
                    name: '首页',
                    path: '/home',
                    level: 1,
                    leaf: true,
                    sortIndex: 1,
                    iconClass: '',
                    children: null
                },
                {
                    code: 'WIKI_SYS',
                    name: '知识系统',
                    path: '/blog/home',
                    level: 1,
                    leaf: true,
                    sortIndex: 1,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'IDEA_SYS',
                    name: '创意中心',
                    path: '/idea/home',
                    level: 1,
                    leaf: true,
                    sortIndex: 3,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'TEAM_SYS',
                    name: '团队构建',
                    path: '/team/home',
                    level: 1,
                    leaf: true,
                    sortIndex: 2,
                    iconClass: '',
                    children: []
                },
                {
                    code: 'ERP_SYS',
                    name: '企业管理中心',
                    path: '/erp/home',
                    level: 1,
                    leaf: true,
                    sortIndex: 4,
                    iconClass: '',
                    children: [
                        {
                            code: 'PROCESS',
                            name: '流程中心',
                            path: '/erp/process',
                            level: 2,
                            leaf: true,
                            iconClass: '',
                            children: null
                        }
                    ]
                },
                {
                    code: 'ABOUT',
                    name: '关于我们',
                    path: '/about',
                    level: 1,
                    leaf: true,
                    sortIndex: 5,
                    iconClass: '',
                    children: []
                }
            ],
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
            this.$refs.flipCardWiki.classList.toggle('is-flipped');
            //this.$router.push('/blog/home');
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
    perspective: 600px;
}
.home-card-item-left {
    width: 30%;
    min-height: 300px;
    transform-style: preserve-3d;
    transform-origin: top right;
    transition: transform 1s;
    margin: 10px 20px;
    cursor: pointer;
}
.home-card-item-middle {
    width: 30%;
    min-height: 300px;
    transform-style: preserve-3d;
    transform-origin: top right;
    transition: transform 1s;
    margin: 10px 20px;
    cursor: pointer;
}

.home-card-item-right {
    width: 30%;
    min-height: 300px;
    transform-style: preserve-3d;
    transform-origin: top right;
    transition: transform 1s;
    margin: 10px 20px;
    cursor: pointer;
}
.card-item {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
}
.flip-card.is-flipped {
    transform: translateX(-100%) rotateY(-180deg);
}

.wiki-card {
}
.wiki-card-back {
    transform: rotateY(180deg);
}
</style>
