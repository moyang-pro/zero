<template>
    <div style="background-color: #eee;">
        <div class="blog-main-content">
            <div class="zero-blog-wrapper">
                <div class="blog-left-block">
                    <recommend ref="recommendCard" />
                    <toolbox />
                </div>
                <div class="blog-middle-block">
                    <el-card :shadow="cardShadow" class="article-read-card">
                        <div slot="header" class="blog-title-header">
                            <h1 class="blog-title-h1 text-one-line">{{ blogInfo.title }}</h1>
                            <div class="article-opt-part">
                                <div class="opt-tag-start">
                                    <el-tag
                                        :type="tagTypeArr[index % 5]"
                                        size="mini"
                                        :hit="true"
                                        v-for="(item, index) in blogInfo.tags"
                                        :key="item"
                                        class="article-tag"
                                        >{{ item }}</el-tag
                                    >
                                    <articleCount :article-info="blogInfo" />
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
                                <div class="copy-right text-one-line">
                                    <strong>版权</strong>
                                    <span v-if="blogInfo.articleType === blogEnum.TYPE_REPRINT">
                                        本文为转载文章，转载请注明来自
                                        <a class="href-link-text" :href="blogInfo.quote">{{ blogInfo.quote }}</a>
                                    </span>
                                    <span v-else-if="blogInfo.articleType === blogEnum.TYPE_TRANSLATION">
                                        本文为翻译文章，转载请注明来自
                                        <a class="href-link-text" :href="blogInfo.quote">{{ blogInfo.quote }}</a>
                                    </span>
                                    <span v-else>
                                        本文为墨阳空间原创文章，转载无需和我联系，但请注明来自
                                        <a class="href-link-text" href="http://www.moyang.pro">http://www.moyang.pro</a>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <!--class : markdown-body 自定义代码高亮语法 v-highlight-->
                        <div class="blog-content-info markdown-body" v-highlight v-html="blogInfo.htmlContent">
                            {{ blogInfo.htmlContent }}
                        </div>
                    </el-card>
                    <comment-box />
                </div>
                <div class="blog-right-block author-info-block">
                    <tocCard ref="tocCard" :toc-arr.sync="tocArr"></tocCard>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { deleteBlog, getOneBlog } from '@/api/blog';
import NumberUtils from '@/utils/NumberUtils';
import BlogEnum from '@/utils/enum/blogEnum';
const { Loading } = require('element-ui');
import articleCount from '@/components/blog/article/count';
import tocCard from '@/components/blog/article/toc/tocCard';
import recommend from '@/components/blog/article/recommend/index';
import toolbox from '@/components/blog/article/toolbox/index';
import commentBox from '@/components/comment/index';
export default {
    name: 'read',
    components: {
        articleCount,
        tocCard,
        recommend,
        toolbox,
        commentBox
    },
    data() {
        return {
            tagTypeArr: ['primary', 'danger', 'warning', 'success', 'info'],
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
            tocArr: [],
            blogEnum: BlogEnum
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
            let loading = Loading.service({
                lock: true,
                text: '博客文章加载中……',
                background: 'rgba(0, 0, 0, 0)'
            });
            getOneBlog(id)
                .then(res => {
                    this.blogInfo = res.data;
                    loading.close();
                    // 生成目录
                    this.markToc();
                    this.isMine = this.blogInfo.author === this.$store.state.user.name;
                })
                .catch(err => {
                    loading.close();
                    console.log('moyang blog:', err);
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
        markToc() {
            this.tocArr = this.blogInfo.htmlContent.match(/<[hH][1-6]>.*?<\/[hH][1-6]>/g); // 通过正则的方式
            if (this.tocArr) {
                this.tocArr.forEach((item, index) => {
                    let _toc = `<div id='toc-${index}'>${item} </div>`;
                    this.blogInfo.htmlContent = '<div>' + this.blogInfo.htmlContent.replace(item, _toc) + '</div>';
                });
                this.$refs.tocCard.toToc(this.tocArr);
            }
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
</style>
