<template>
    <el-card class="user-pop-card" :shadow="cardShadow">
        <div class="bm-user-block">
            <div class="bm-card-title-line">
                <h2 class="bm-card-title" ref="cardTitle">
                    用户排行
                    <el-tooltip class="item" effect="light" content="每十分钟自动刷新一次" placement="right-start">
                        <i
                            class="el-tooltip el-icon-refresh item"
                            aria-describedby="el-tooltip-688"
                            tabindex="0"
                            @click.stop="refreshUserPopData"
                            style="cursor: pointer; float: right;"
                        ></i>
                    </el-tooltip>
                </h2>
            </div>
            <div class="bm-card-main">
                <div v-for="item in userPopList" :key="item.blogUser" class="ump-line">
                    <el-row>
                        <el-col :span="4">
                            <span class="upc-author-avatar-box">
                                <el-avatar
                                    :src="item.avatar"
                                    class="upc-author-avatar"
                                    :class="item.vipCode > 0 ? 'vip-color' : 'common-color'"
                                ></el-avatar>
                                <span class="upc-author-text-vip" v-if="item.vipCode > 0">v</span>
                            </span>
                        </el-col>
                        <el-col :span="16">
                            <div class="upc-line-mid upc-line-mid-top">
                                <span>{{ item.nickName }}</span>
                                <span style="margin-left: 5px">
                                    <svg-icon icon-class="man" v-if="item.gender === 1"></svg-icon>
                                    <svg-icon icon-class="woman" v-else></svg-icon>
                                </span>
                            </div>
                            <div class="upc-line-mid">
                                <span>
                                    文章
                                    <span v-if="item.articleCount < 1000" class="text-count-num">
                                        {{ item.articleCount }}</span
                                    >
                                    <span v-else class="text-count-num"> 12k+</span>
                                </span>
                                <el-divider direction="vertical"></el-divider>
                                <span>
                                    粉丝
                                    <span v-if="item.followedCount < 1000" class="text-count-num">
                                        {{ item.followedCount }}</span
                                    >
                                    <span v-else class="text-count-num"> 999+</span>
                                </span>
                                <el-divider direction="vertical"></el-divider>
                                <span>
                                    问答
                                    <span v-if="item.faqCount < 1000" class="text-count-num"> {{ item.faqCount }}</span>
                                    <span v-else class="text-count-num"> 999+</span>
                                </span>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div class="upc-line-end">
                                <span class="upc-line-end-item"><i class="el-icon-coin"></i> {{ item.point }}</span>
                            </div>
                        </el-col>
                    </el-row>
                </div>
                <div style="text-align: center">
                    <el-pagination
                        small
                        :hide-on-single-page="true"
                        layout="prev, pager, next"
                        @current-change="handleCurrentChange"
                        :current-page="page.pageIndex"
                        :page-size="page.pageSize"
                        :total="page.total"
                    >
                    </el-pagination>
                </div>
            </div>
        </div>
    </el-card>
</template>

<script>
import PublicUtils from '@/utils/PublicUtils';
import { getHomeUserPopList } from '@/api/blog';
export default {
    name: 'userPop',
    data() {
        return {
            cardShadow: 'always',
            page: {
                pageIndex: 1,
                pageSize: 10,
                total: 20
            },
            userPopList: []
        };
    },
    mounted() {
        this.getUserPopList();
        //循环定时器
        setInterval(() => {
            this.getUserPopList();
        }, 10 * 60 * 1000);
    },
    methods: {
        handleCurrentChange() {
            this.getUserPopList();
        },
        getUserPopList() {
            let pageRequest = PublicUtils.getPageRequest(this.page.pageIndex, this.page.pageSize);
            getHomeUserPopList(pageRequest).then(res => {
                this.userPopList = res.list;
                this.page.total = res.total;
            });
        },
        refreshUserPopData() {
            this.getUserPopList();
        }
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
    margin: 0 0 20px;
    position: relative;
}
.bm-card-title:after {
    content: '';
    background-color: #282828;
    left: 0;
    width: 50px;
    height: 2px;
    bottom: 0;
    position: absolute;
    transition: 0.5s;
}
.upc-author-avatar-box {
    display: flex;
    justify-content: space-between;
    position: relative;
    max-width: 80px;
    cursor: pointer;
}
.upc-author-avatar {
    height: 40px;
    width: 40px;
    margin-right: 5px;
}
.upc-author-text-vip {
    height: 16px;
    width: 16px;
    background: #333;
    border-radius: 50%;
    font-size: 13px;
    color: gold;
    position: absolute;
    left: 26px;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}
.ump-line {
    margin-bottom: 10px;
    cursor: pointer;
}
.common-color {
    border: 2px solid #acaeb3;
}
.vip-color {
    border: 2px solid gold;
}
.upc-line-mid {
    padding-left: 5px;
    font-size: 13px;
}
.upc-line-mid-top {
    font-size: 14px;
    color: #ffa200;
}
.text-count-num {
    color: #00a4ff;
}
.upc-line-end-item {
    height: 40px;
    line-height: 40px;
}
</style>
