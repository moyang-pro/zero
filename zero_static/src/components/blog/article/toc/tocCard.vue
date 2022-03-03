<template>
    <div :class="fixedToc ? 'fixed-toc' : ''">
        <el-card :shadow="cardShadow" class="article-user-card article-show-card">
            <div class="author-box">
                <div>
                    <el-row>
                        <el-col :span="8">
                            <div class="author-avatar-box">
                                <el-avatar :src="authorInfo.avatar" class="author-avatar"></el-avatar>
                                <span class="author-text-vip" v-if="authorInfo.vipCode">v</span>
                            </div>
                        </el-col>
                        <el-col :span="14" :offset="2">
                            <div style="height: 30px;line-height: 30px">
                                作者：
                                <span class="author-nick text-one-line" :title="authorInfo.nick">
                                    {{ authorInfo.nick }}
                                </span>
                            </div>
                            <div class="author-summary-box text-two-line">
                                {{ authorInfo.summary }}
                            </div>
                        </el-col>
                    </el-row>
                    <div class="line-count-author">
                        <div class="article-count-author ">
                            <div class="author-count-title">
                                文章
                            </div>
                            <div class="author-count-num">
                                {{ authorInfo.countInfo.articleCount }}
                            </div>
                        </div>
                        <div class="follow-count item-count">
                            <div class="author-count-title">
                                关注
                            </div>
                            <div class="author-count-num">
                                {{ authorInfo.countInfo.followCount }}
                            </div>
                        </div>
                        <div class="followed-count item-count">
                            <div class="author-count-title">
                                粉丝
                            </div>
                            <div class="author-count-num">
                                {{ authorInfo.countInfo.followedCount }}
                            </div>
                        </div>
                        <div class="faq-count item-count">
                            <div class="author-count-title">
                                问答
                            </div>
                            <div class="author-count-num">
                                {{ authorInfo.countInfo.faqCount }}
                            </div>
                        </div>
                    </div>
                    <div class="opt-author-line">
                        <el-button type="info" size="mini" icon="el-icon-message" round @click.stop="msgWithAuthor">
                            <span>私信</span>
                        </el-button>
                        <el-button
                            type="danger"
                            size="mini"
                            round
                            v-if="!authorInfo.followed"
                            @click.stop="followAuthor"
                        >
                            <svg-icon icon-class="follow"></svg-icon>
                            关注
                        </el-button>
                        <el-button type="warning" size="mini" round v-else @click.stop="unFollowAuthor">
                            <svg-icon icon-class="followed"></svg-icon>
                            已关注
                        </el-button>
                    </div>
                </div>
                <mini-heart ref="miniHeart"></mini-heart>
            </div>
        </el-card>
        <el-card :shadow="cardShadow" class="article-toc-card article-show-card">
            <div class="toc-nav-box">
                <div class="toc-nav-title">
                    <span>目录</span>
                </div>
                <div>
                    <el-tree
                        :data="tocTree"
                        default-expand-all
                        node-key="id"
                        :highlight-current="false"
                        icon-class="none-icon"
                        :expand-on-click-node="false"
                    >
                        <span
                            class="custom-tree-node"
                            :class="'toc-title-level-' + data.depth"
                            slot-scope="{ node, data }"
                            @click.stop.prevent="tocTreeClick(data)"
                        >
                            <svg-icon
                                :icon-class="activeIndex === data.id ? 'li-blue' : 'li-black'"
                                class="svgIcon toc-icon"
                                :class="'toc-icon-depth-' + data.depth"
                            />
                            <span
                                class="text-one-line"
                                :class="activeIndex === data.id ? 'toc-node-label-active' : 'toc-node-label'"
                                >{{ node.label }}</span
                            >
                        </span>
                    </el-tree>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
import miniHeart from '@/components/biubiubiu';
import { followAuthorOfBlog, unfollowAuthorOfBlog } from '@/api/blog';
export default {
    //文章目录+作者
    name: 'toc',
    components: {
        miniHeart
    },
    props: {
        tocArr: {
            type: Array,
            required: false,
            default: null
        }
    },
    data() {
        return {
            authorInfo: {
                nick: '一米阳光',
                emy: '',
                avatar: require('@/assets/img/avatar.png'),
                vipCode: 1,
                countInfo: {
                    articleCount: 0,
                    followCount: 0,
                    followedCount: 0,
                    faqCount: 0
                },
                summary: '一抹温柔，毕生追求',
                followed: false
            },
            cardShadow: 'always',
            tocList: [],
            tocTree: [],
            activeIndex: 0,
            fixedToc: false
        };
    },
    mounted() {
        if (this.tocArr && this.tocTree.length === 0) {
            this.toToc(this.tocArr);
        }
        window.addEventListener('scroll', this.handleScroll, true);
    },
    destroyed() {
        // 离开该页面需要移除这个监听的事件，不然会报错
        window.removeEventListener('scroll', this.handleScroll);
    },
    methods: {
        createLoves(evt) {
            this.$refs.miniHeart.createLoves(evt);
        },
        removeSmallHeart() {
            this.$refs.miniHeart.removeSmallHeart();
        },
        followAuthor(evt) {
            // 关注作者
            if (!this.authorInfo.followed) {
                followAuthorOfBlog().then(res => {
                    if (res.data) {
                        this.authorInfo.followed = true;
                    }
                });
            }
            this.createLoves(evt);
            setTimeout(() => {
                this.removeSmallHeart();
            }, 100);
        },
        unFollowAuthor() {
            // 取消关注作者
            if (this.authorInfo.followed) {
                unfollowAuthorOfBlog().then(res => {
                    if (res.data) {
                        this.authorInfo.followed = false;
                    }
                });
            }
        },
        msgWithAuthor() {},
        tocTreeClick(data) {
            this.activeIndex = data.id;
            let selectorId = 'toc-' + data.id;
            const anchor = document.getElementById(selectorId); //获取元素
            if (anchor) {
                let offsetTop = anchor.offsetTop;
                this.$nextTick(() => {
                    window.scrollTo({ behavior: 'smooth', top: offsetTop - 80 });
                });
            }
        },
        handleScroll() {
            const scrollY = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
            this.fixedToc = scrollY > 80;
        },
        async toToc(tocArr) {
            let arrLevel = [];

            tocArr.forEach(item => {
                let itemLevel = item.match(/(?<=<h)\w(?=>)/)[0]; // 匹配h?标签<h?>
                arrLevel.push(itemLevel);
            });
            tocArr.forEach((item, index) => {
                let itemLevel = item.match(/(?<=<h)\w(?=>)/)[0]; // 匹配h?标签<h?>
                let itemText = item.replace(/<[^>]+>/g, ''); // 匹配h标签的文字
                let listItem = {
                    id: index,
                    level: itemLevel,
                    label: itemText,
                    parentId: this.findLeftSmallerFirstIndex(arrLevel, itemLevel, index)
                };
                this.tocList.push(listItem);
            });
            this.tocTree = this.makeTree(this.tocList);
        },
        findLeftSmallerFirstIndex(arr, value, index) {
            index--;
            while (index >= 0) {
                if (arr[index] < value) {
                    return index;
                } else {
                    index--;
                }
            }
            return -1;
        },
        makeTree(data, children = 'children') {
            const root = { depth: -1, [children]: [] };
            const nodeMap = {};
            data.forEach(it => {
                const { id, parentId } = it;
                const parent = nodeMap[parentId] ?? root;
                const node = { ...it, depth: parent.depth + 1 };
                parent.children ??= [];
                parent.children.push(node);
                nodeMap[id] = node;
            });
            return root.children;
        }
    }
};
</script>

<style scoped lang="scss">
.article-show-card {
    width: 300px;
    min-height: 200px;
    border-radius: 10px;
}
.article-toc-card {
    margin-top: 20px;
}
.toc-nav-title {
    text-align: start;
    padding: 5px 5px;
    border-bottom: 1px solid #ebeef5;
    font-weight: bold;
    font-size: 18px;
}
.custom-tree-node {
    display: flex;
    align-items: center;
    cursor: pointer;
    font-weight: normal;
}
.toc-node-label-active {
    margin-left: 5px;
    color: #409eff;
    max-width: 260px;
}
.toc-node-label {
    margin-left: 5px;
    max-width: 260px;
}
.fixed-toc {
    position: fixed;
    top: 65px;
}
.toc-title-level-0 {
    font-weight: 700;
}
.toc-icon {
    font-size: 4px;
    align-items: center;
}
.toc-icon-depth-0 {
    font-size: 6px;
    align-items: center;
}

.author-box {
    width: 100%;
    height: 100%;
    cursor: pointer;
    text-align: left;
}
.author-avatar-box {
    position: relative;
}
.author-avatar {
    width: 80px;
    height: 80px;
}
.author-text-vip {
    height: 25px;
    width: 25px;
    background: #333;
    border-radius: 50%;
    font-size: 16px;
    color: gold;
    position: absolute;
    right: 0;
    bottom: 0;
    text-align: center;
}
.author-nick {
    font-weight: bold;
}
.author-summary-box {
    text-align: start;
    min-height: 40px;
}
.line-count-author {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    text-align: center;
}
.author-count-title {
    font-weight: bold;
    font-size: 16px;
}
.author-count-num {
    font-size: 16px;
}
.opt-author-line {
    margin-top: 10px;
    text-align: center;
}
</style>
