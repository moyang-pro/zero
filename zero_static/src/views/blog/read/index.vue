<template>
    <div style="background-color: #eee;">
        <div class="blog-main-content">
            <div class="zero-blog-wrapper">
                <div class="blog-left-block">
                    <el-card :shadow="cardShadow" class="article-info-card article-show-card"> </el-card>
                </div>
                <div class="blog-middle-block">
                    <el-card :shadow="cardShadow" class="article-read-card">
                        <div slot="header" class="blog-title-header">
                            <h1 class="blog-title-h1 text-one-line">{{ blogInfo.title }}</h1>
                            <div class="article-opt-part">
                                <div class="opt-tag-start">
                                    <el-tag
                                        type="warning"
                                        v-for="item in blogInfo.tags"
                                        :key="item.index"
                                        class="article-tag"
                                        >{{ item }}</el-tag
                                    >
                                </div>
                                <div class="opt-part-end" v-show="isMine">
                                    <el-link :underline="false" type="primary" @click.stop="editBlog(blogInfo.id)"
                                        >编辑</el-link
                                    >
                                    <el-divider direction="vertical"></el-divider>
                                    <el-link :underline="false" type="danger" @click.stop="deleteBlog(blogInfo.id)"
                                        >删除</el-link
                                    >
                                </div>
                            </div>
                            <div>
                                <div class="copy-right">
                                    <strong>版权</strong>
                                    <span v-if="blogInfo.articleType === blogEnum.TYPE_DEFAULT">
                                        本文为墨阳空间原创文章，转载无需和我联系，但请注明来自
                                        <a class="href-link-text" href="http://www.moyang.pro">墨阳空间</a>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <!--class : markdown-body 自定义代码高亮语法 v-highlight-->
                        <div class="blog-content-info markdown-body" v-highlight v-html="blogInfo.htmlContent">
                            {{ blogInfo.htmlContent }}
                        </div>
                    </el-card>
                </div>
                <div class="blog-right-block author-info-block">
                    <div :class="fixedToc ? 'fixed-toc' : ''">
                        <el-card :shadow="cardShadow" class="article-user-card article-show-card">
                            <div
                                class="author-box"
                                v-on:mousedown.left="createLoves($event)"
                                v-on:mouseup="removeSmallHeart"
                            >
                                <h1>dd</h1>
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
                                                :class="
                                                    activeIndex === data.id ? 'toc-node-label-active' : 'toc-node-label'
                                                "
                                                >{{ node.label }}</span
                                            >
                                        </span>
                                    </el-tree>
                                </div>
                            </div>
                        </el-card>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { deleteBlog, getOneBlog } from '@/api/blog';
import NumberUtils from '@/utils/NumberUtils';
import miniHeart from '@/components/biubiubiu/index';
import BlogEnum from '@/utils/enum/blogEnum';
const { Loading } = require('element-ui');
export default {
    name: 'read',
    components: {
        miniHeart
    },
    data() {
        return {
            cardShadow: 'always',
            blogInfo: {
                articleStatus: 0,
                articleType: 0,
                author: '',
                clickCount: 0,
                collectCount: 0,
                coverUrl: null,
                createAt: '',
                createBy: '',
                delFlag: 0,
                des: '',
                htmlContent: '',
                id: null,
                publishType: '',
                quote: null,
                readCount: '',
                remarks: '',
                tags: '',
                textContent: '',
                title: '',
                updateAt: '',
                updateBy: '',
                userCount: ''
            },
            isMine: false,
            // 编程语言、技术领域、项目名称
            tagList: ['java语言', '博客', 'ZERO-BLOG'],
            tocList: [],
            tocTree: [],
            activeIndex: 0,
            fixedToc: false,
            blogEnum: BlogEnum
        };
    },
    created() {
        let blogId = this.$route.params.id;
        this.showBlog(blogId);
    },
    mounted() {
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
        showBlog(id) {
            if (!NumberUtils.isValidateId(id)) {
                this.$message.error('获取文章ID错误，请检查访问路径是否正确！');
                this.$router.replace({ path: `/blog/profile/${this.$store.state.user.name}` });
                return;
            }
            let loading = Loading.service({
                lock: true,
                text: '博客文章加载中……',
                background: 'rgba(0, 0, 0, 0)'
            });
            getOneBlog(id)
                .then(res => {
                    this.blogInfo = res.data;
                    loading.close();
                    this.toToc(this.blogInfo.htmlContent);
                    console.log('moyang blog:', this.blogInfo);
                    this.isMine = this.blogInfo.author === this.$store.state.user.name;
                })
                .catch(() => {
                    loading.close();
                    this.$router.replace({ path: `/blog/profile/${this.$store.state.user.name}` });
                    this.blogInfo = {};
                });
        },
        editBlog(id) {
            if (!NumberUtils.isValidateId(id)) {
                this.$message.error('文章ID错误！');
                return;
            }
            this.$router.push({ path: `/blog/edit/${id}.html` });
        },
        deleteBlog(id) {
            if (!NumberUtils.isValidateId(id)) {
                this.$message.error('文章ID错误！');
                return;
            }
            this.$confirm('此操作将永久删除该博客, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
                .then(() => {
                    this.confirmDelete(id);
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
        },
        confirmDelete(id) {
            deleteBlog(id).then(() => {
                this.$router.replace({ path: `/blog/profile/${this.$store.state.user.name}` });
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            });
        },
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
            this.fixedToc = scrollY > 150;
        },
        async toToc(html) {
            const tocArr = html.match(/<[hH][1-6]>.*?<\/[hH][1-6]>/g); // 通过正则的方式
            let arrLevel = [];

            tocArr.forEach((item, index) => {
                let _toc = `<div id='toc-${index}'>${item} </div>`;
                this.blogInfo.htmlContent = '<div>' + this.blogInfo.htmlContent.replace(item, _toc) + '</div>';
            });

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
    },
    watch: {}
};
</script>

<style scoped lang="scss">
@import 'src/styles/zero/blog/blog';
@import 'src/styles/zero/blog/markdown';

.zero-blog-wrapper {
    display: grid;
    grid-template-columns: 300px auto 300px;
    grid-row-gap: 10px;
    grid-column-gap: 10px;
    text-align: end;
}
.article-show-card {
    width: 300px;
    min-height: 200px;
    border-radius: 10px;
}
.blog-left-block {
    margin-bottom: 40px;
}

.article-info-card {
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
    color: #00a4ff;
}
.toc-node-label {
    margin-left: 5px;
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

//中间card
.article-read-card {
    border-radius: 10px;
}
.blog-middle-block {
    min-width: 400px;
    margin-bottom: 40px;
}

.article-read-card .blog-title-header {
    text-align: left;
}
.blog-title-h1 {
    max-width: 80%;
}
.article-opt-part {
    display: grid;
    height: 100%;
    width: 100%;
    grid-template-columns: 60% 40%;
}
.copy-right {
    padding: 10px;
    margin: 20px auto 15px;
    background-color: #ecf8ff;
    border-radius: 4px;
    border-left: 5px solid #50bfff;
    color: #888;
}
.opt-tag-start {
}
.article-tag {
    margin-right: 8px;
    cursor: pointer;
}
.opt-count-mid {
}
.opt-part-end {
    text-align: end;
}
.blog-right-block {
    margin-bottom: 40px;
}
.author-box {
    width: 100%;
    height: 100%;
}
.article-user-card {
}
</style>
