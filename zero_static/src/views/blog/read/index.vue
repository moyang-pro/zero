<template>
    <div class="blog-main-content">
        <div class="zero-blog-wrapper">
            <div class="blog-left-block">
                <el-card shadow="hover" class="article-info-card article-show-card">
                    <div class="toc-nav-box">
                        <div class="toc-nav-title">
                            <span>目录</span>
                        </div>
                        <div>
                            <el-tree
                                :data="tocTree"
                                default-expand-all
                                node-key="id"
                                highlight-current
                                :expand-on-click-node="false"
                            >
                                <span class="custom-tree-node" slot-scope="{ node, data }">
                                    <span>{{ node.label }}</span>
                                </span>
                            </el-tree>
                        </div>
                    </div>
                </el-card>
            </div>
            <div class="blog-middle-block">
                <el-card shadow="hover" class="article-read-card">
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
                    </div>
                    <!--class : markdown-body 自定义代码高亮语法 v-highlight-->
                    <div class="blog-content-info markdown-body" v-highlight v-html="blogInfo.htmlContent">
                        {{ blogInfo.htmlContent }}
                    </div>
                </el-card>
            </div>
            <div class="blog-right-block author-info-block">
                <el-card shadow="hover" class="article-user-card article-show-card"> </el-card>
            </div>
        </div>
    </div>
</template>

<script>
import { deleteBlog, getMyBlog } from '@/api/blog';
import NumberUtils from '@/utils/NumberUtils';

import toToc from '@/utils/blog/TocUtil';

export default {
    name: 'read',
    data() {
        return {
            blogInfo: {
                id: 0,
                htmlContent: '',
                author: ''
            },
            isMine: false,
            // 编程语言、技术领域、项目名称
            tagList: ['java语言', '博客', 'ZERO-BLOG'],
            tocList: [],
            tocTree: []
        };
    },
    created() {
        let blogId = this.$route.params.id;
        this.showBlog(blogId);
    },
    methods: {
        showBlog(id) {
            if (!NumberUtils.isValidateId(id)) {
                this.$message.error('获取文章ID错误，请检查访问路径是否正确！');
                this.$router.replace({ path: `/blog/profile/${this.$store.state.user.name}` });
                return;
            }
            getMyBlog(id)
                .then(res => {
                    this.blogInfo = res.data;
                    console.log(this.blogInfo.htmlContent);
                    this.toToc(this.blogInfo.htmlContent);
                    this.isMine = this.blogInfo.author === this.$store.state.user.name;
                })
                .catch(() => {
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
        toToc(html) {
            const tocs = html.match(/<[hH][1-6]>.*?<\/[hH][1-6]>/g); // 通过正则的方式
            console.log('tocs', tocs);
            tocs.forEach((item, index) => {
                let _toc = `<div name='toc-title' id='${index}'>${item} </div>`;
                html = html.replace(item, _toc);
            });
            console.log('html ', html);
            this.tocList = toToc(tocs);
            console.log('tocs', this.tocList);
        }
    },
    watch: {
        $route: {
            handler: function(val, oldVal) {
                const data = document.getElementsByClassName(`toc-link-${val.hash}`)[0];
                this.tocList.forEach(list => {
                    data === list ? list.classList.add('active') : list.classList.remove('active');
                });
            },
            // 深度观察监听
            deep: true
        }
    }
};
</script>

<style scoped lang="scss">
@import 'src/styles/zero/blog/blog';
@import 'src/styles/zero/blog/markdown';

.zero-blog-wrapper {
    display: flex;
    justify-content: space-between;
}
.article-show-card {
    width: 300px;
    min-height: 400px;
}
.blog-left-block {
    margin-bottom: 40px;
}
.article-info-card {
}
.toc-nav-title {
    text-align: start;
    padding: 5px 5px;
    border-bottom: 1px solid #ebeef5;
    font-weight: bold;
    font-size: 18px;
}
//中间card
.blog-middle-block {
    width: calc(100% - 600px);
    margin: 0 10px 40px 10px;
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

.article-user-card {
}
</style>
