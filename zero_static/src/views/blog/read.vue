<template>
    <div class="blog-main-content">
        <div class="zero-blog-wrapper">
            <div class="blog-left-block">
                <el-card shadow="hover" class="article-info-card article-show-card"> </el-card>
            </div>
            <div class="blog-middle-block">
                <el-card shadow="hover" class="article-read-card">
                    <div slot="header" class="blog-title-header text-one-line">
                        <h1>{{ blogInfo.title }}</h1>
                        <div class="article-opt-part">
                            <div class="opt-tag-start">
                                <el-tag
                                    type="success"
                                    v-for="item in tagList"
                                    :key="item.index"
                                    class="article-tag"
                                    >{{ item }}</el-tag
                                >
                            </div>
                            <div class="opt-count-mid"></div>
                            <div class="opt-part-end">
                                <el-button type="text">编辑</el-button>
                                <el-divider direction="vertical"></el-divider>
                                <el-button type="text" class="danger-color">删除</el-button>
                            </div>
                        </div>
                    </div>
                    <!--class : markdown-body 自定义代码高亮语法 v-highlight-->
                    <div
                        class="blog-content-info markdown-body"
                        v-highlight
                        v-html="blogInfo.htmlContent"
                    >
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
import { getBlog } from '@/api/blog';

export default {
    name: 'read',
    data() {
        return {
            blogInfo: {
                id: 0,
                htmlContent: ''
            },
            // 编程语言、技术领域、项目名称
            tagList: ['java语言', '博客', 'ZERO-BLOG']
        };
    },
    created() {
        let blogId = this.$route.params.id;
        this.showBlog(blogId);
    },
    methods: {
        showBlog(id) {
            console.log('blogId: ' + id);
            if (!id) {
                this.$message.error('获取文章ID错误，请检查访问路径是否正确！');
                return;
            }
            getBlog(id)
                .then(res => {
                    this.blogInfo = res.data;
                })
                .catch(() => {
                    this.blogInfo = {};
                });
        }
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
@import './src/styles/zero/blog/markdown.scss';

.zero-blog-wrapper {
    display: flex;
    justify-content: space-between;
}
.article-show-card {
    width: 300px;
    min-height: 400px;
}
.blog-left-block {
}
.article-info-card {
}
//中间card
.blog-middle-block {
    width: calc(100% - 600px);
    margin: 0 10px;
}
.article-read-card .blog-title-header {
    text-align: left;
    width: 60%;
}
.article-opt-part {
    display: inline-block;
    height: 100%;
    min-width: 100px;
    text-align: justify;
    text-align-last: justify;
}
.opt-tag-start {
    display: inline;
}
.article-tag {
    margin-right: 8px;
    cursor: pointer;
}
.opt-count-mid {
    display: inline;
}
.opt-part-end {
    display: inline;
    float: end;
}
.blog-right-block {
}

.article-user-card {
}
</style>
