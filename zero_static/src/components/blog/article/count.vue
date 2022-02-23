<template>
    <span class="text-span-line">
        <span class="text-span-item" title="发布时间">
            <i class="el-icon-time right-one-span"></i> {{ recently ? recentTimeDes : articleInfo.createAt }}</span
        >
        <span class="text-span-item" title="浏览量"
            ><i class="el-icon-view right-one-span"></i>{{ articleInfo.readCount }}</span
        >
        <span class="text-span-item" title="浏览人数"
            ><i class="el-icon-user right-one-span"></i>{{ articleInfo.userCount }}</span
        >
        <span class="text-span-item" title="Star数量"
            ><i class="el-icon-star-on right-one-span"></i>{{ articleInfo.collectCount }}</span
        >
    </span>
</template>

<script>
import { diffTime } from '@/utils/DataTimeUtil';
export default {
    name: 'count',
    props: {
        articleInfo: {
            type: Object,
            required: true,
            default: null
        }
    },
    data() {
        return {
            recently: false,
            recentTimeDes: ''
        };
    },
    created() {
        this.handleDateTime();
    },
    methods: {
        handleDateTime() {
            if (new Date(this.articleInfo.createAt) - new Date() <= 24 * 3600 * 1000) {
                this.recently = true;
                this.recentTimeDes = diffTime(new Date(this.articleInfo.createAt), new Date());
            }
        }
    }
};
</script>

<style scoped>
.text-span-line {
    color: #77777e;
    font-size: 13px;
}
.text-span-item {
    margin-left: 20px;
    cursor: pointer;
}
.right-one-span {
    margin-right: 2px;
}
</style>
