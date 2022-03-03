<template>
    <div class="bm-notice-right">
        <div class="ad-ca-block">
            <a :href="adInfo.adLink">
                <el-image :src="adInfo.adCoverUrl" alt="" fit="fill" class="box-100" lazy />
                <div class="ad-ca-title text-one-line" :title="adInfo.adDesc">
                    <span>{{ adInfo.adTitle }}</span>
                </div>
            </a>
        </div>
        <div style="height: 20px"></div>
        <div class="ad-ca-block">
            <a :href="caInfo.adLink">
                <el-image :src="caInfo.adCoverUrl" alt="" fit="fill" class="box-100" lazy />
                <div class="ad-ca-title text-one-line" :title="caInfo.adDesc">
                    <span>{{ caInfo.adTitle }}</span>
                </div>
            </a>
        </div>
    </div>
</template>

<script>
import { getHomeTopBannerList } from '@/api/blog';
export default {
    name: 'banner',
    data() {
        return {
            bannerList: [],
            adInfo: {
                adCoverUrl: require('@/assets/img/break.jpg'),
                adTitle: '墨阳是一个怎么的人',
                adDesc: '墨阳是一个怎么的人',
                adLink: 'http://www.moyang.pro'
            },
            caInfo: {
                adCoverUrl: require('@/assets/img/lake.jpg'),
                adTitle: '墨阳空间是一个怎样的世界',
                adDesc: '墨阳空间是一个怎样的世界',
                adLink: 'http://www.moyang.pro'
            }
        };
    },
    mounted() {
        this.getBlogBanner();
    },
    methods: {
        getBlogBanner() {
            getHomeTopBannerList().then(res => {
                this.bannerList = res.data;
                if (this.bannerList && this.bannerList.length > 0) {
                    this.adInfo = this.bannerList[0];
                }
                if (this.bannerList && this.bannerList.length > 1) {
                    this.caInfo = this.bannerList[1];
                }
            });
        }
    }
};
</script>

<style scoped>
.bm-notice-right {
    margin: 20px 0 20px 0;
    display: grid;
    grid-template-rows: 270px 20px 270px;
}
.ad-ca-block {
    position: relative;
}
.ad-ca-title {
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    position: absolute;
    z-index: 10;
    bottom: 0;
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    background: rgba(0, 0, 0, 0.3);
    padding: 0 20px;
}
</style>
