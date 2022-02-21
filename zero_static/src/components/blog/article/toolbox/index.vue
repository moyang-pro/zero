<template>
    <div class="tool-box-left">
        <div class="tool-box" :class="fixedToolBox ? 'tool-box-fixed' : ''">
            <el-badge :value="likeCount" class="tool-badge-item" :type="likeBtn ? 'primary' : 'info'">
                <div
                    class="article-tool-item div-text-no-click"
                    @mouseover="focusBtn(1)"
                    @mouseleave="focusBtn(0)"
                    @click.prevent="clickLikeBtn()"
                >
                    <svg-icon icon-class="like-focus" v-if="focusBtnIndex === 1 && !likeBtn"></svg-icon>
                    <svg-icon icon-class="liked" v-else-if="likeBtn"></svg-icon>
                    <svg-icon icon-class="like" v-else></svg-icon>
                </div>
            </el-badge>
            <el-badge :value="commentCount" class="item" type="info">
                <div class="article-tool-item div-text-no-click" @mouseover="focusBtn(2)" @mouseleave="focusBtn(0)">
                    <svg-icon icon-class="comment-focus" v-if="focusBtnIndex === 2"></svg-icon>
                    <svg-icon icon-class="comment" v-else></svg-icon>
                </div>
            </el-badge>
            <div class="article-tool-item div-text-no-click" @mouseover="focusBtn(3)" @mouseleave="focusBtn(0)">
                <svg-icon icon-class="share-focus" v-if="focusBtnIndex === 3"></svg-icon>
                <svg-icon icon-class="share" v-else></svg-icon>
            </div>
            <div
                class="article-tool-item div-text-no-click"
                @mouseover="focusBtn(4)"
                @mouseleave="focusBtn(0)"
                @click.prevent="clickCollectBtn()"
            >
                <svg-icon icon-class="collect-focus" v-if="focusBtnIndex === 4 && !collectBtn"></svg-icon>
                <svg-icon icon-class="collected" v-else-if="collectBtn"></svg-icon>
                <svg-icon icon-class="collect" v-else></svg-icon>
            </div>
            <div class="article-tool-item div-text-no-click" @mouseover="focusBtn(5)" @mouseleave="focusBtn(0)">
                <svg-icon icon-class="report-focus" v-if="focusBtnIndex === 5"></svg-icon>
                <svg-icon icon-class="report" v-else></svg-icon>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'index',
    data() {
        return {
            fixedToolBox: false,
            focusBtnIndex: 0,
            likeBtn: false,
            collectBtn: false,
            likeCount: 0,
            commentCount: 0,
            hasLike: false,
            hasCollect: false
        };
    },
    mounted() {
        window.addEventListener('scroll', this.handleScroll, true);
    },
    destroyed() {
        // 离开该页面需要移除这个监听的事件，不然会报错
        window.removeEventListener('scroll', this.handleScroll);
    },
    methods: {
        handleScroll() {
            const scrollY = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
            this.fixedToolBox = scrollY > 200;
        },
        focusBtn(index) {
            this.focusBtnIndex = index;
        },
        clickLikeBtn() {
            if (!this.likeBtn) {
                this.likeCount++;
            } else {
                this.likeCount--;
            }
            this.likeBtn = !this.likeBtn;
        },
        clickCollectBtn() {
            this.collectBtn = !this.collectBtn;
        }
    }
};
</script>

<style lang="scss" scoped>
.tool-box-fixed {
    position: fixed;
    top: 160px;
}
.tool-box-left {
    width: 3rem;
    margin-top: 30px;
    margin-right: 20px;
    float: right;
}
.tool-box {
    width: 3rem;
}
.article-tool-item {
    position: relative;
    margin-bottom: 1.667rem;
    width: 3rem;
    height: 3rem;
    background-color: #fff;
    background-position: 50%;
    background-repeat: no-repeat;
    border-radius: 50%;
    box-shadow: 0 2px 4px lightgrey;
    cursor: pointer;

    font-size: 1.3rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
.article-tool-item:hover {
    -webkit-filter: drop-shadow(1px 1px 2px #ffffff);
}
</style>
