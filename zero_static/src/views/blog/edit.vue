<template>
    <div class="blog-main-content">
        <mavon-editor
            ref="mEditor"
            class="markdown-editor"
            v-model="article.textContent"
            :ishljs="true"
            :subfield="false"
            fontSize="16px"
            @change="blogContentChanged"
            @save="saveContent"
            @imgAdd="addImage"
            @imgDel="delImage"
        />
        <div class="blog-title-block">
            <el-form
                ref="blogTitleForm"
                :model="article"
                label-width="120"
                :rules="rulesBlog"
                label-position="left"
            >
                <el-form-item label="文章标题：" prop="title">
                    <el-input v-model="article.title" placeholder="请输入文章标题......" />
                </el-form-item>
                <el-form-item label="内容概述：" prop="des">
                    <el-input
                        v-model="article.des"
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 5 }"
                        placeholder="请对文章进行简要描述......"
                    />
                </el-form-item>
            </el-form>
        </div>
        <div class="opt-block">
            <el-button round @click="updateBlog">保存草稿</el-button>
            <el-button type="warning" round>发布文章</el-button>
        </div>
    </div>
</template>

<script>
import { getBlog, uploadImg, updateBlog } from '@/api/blog';
import NumberUtils from '@/utils/NumberUtils';
export default {
    name: 'edit',
    data() {
        return {
            article: {
                id: undefined,
                title: '',
                des: '',
                htmlContent: '',
                author: '',
                textContent: ''
            },
            oldContent: '',
            modified: false,
            rulesBlog: {
                title: [
                    {
                        required: true,
                        message: '请输入文章标题',
                        trigger: 'blur'
                    },
                    {
                        type: 'string',
                        max: 100,
                        message: '描述文字最多100个字',
                        trigger: 'blur'
                    }
                ],
                des: [
                    {
                        required: true,
                        message: '请对文章进行简要描述',
                        trigger: 'blur'
                    },
                    {
                        type: 'string',
                        min: 20,
                        max: 500,
                        message: '描述文字至少20个字，最多500个字',
                        trigger: 'blur'
                    }
                ]
            }
        };
    },
    created() {
        let blogId = this.$route.params.id;
        this.getEditBlog(blogId);
    },
    methods: {
        getEditBlog(id) {
            if (!NumberUtils.isValidateId(id)) {
                this.$message.error('获取文章ID错误，请检查访问路径是否正确！');
                this.$router.replace({ path: `/blog` });
                return;
            }
            getBlog(id)
                .then(res => {
                    let blogInfo = res.data;
                    this.article.id = blogInfo.id;
                    this.article.title = blogInfo.title ? blogInfo.title : '';
                    this.article.des = blogInfo.des ? blogInfo.des : '';
                    this.article.htmlContent = blogInfo.htmlContent ? blogInfo.htmlContent : '';
                    this.article.textContent = blogInfo.textContent ? blogInfo.textContent : '';
                    this.article.author = blogInfo.author;
                    this.modified = false;
                    this.oldContent = this.article.textContent;
                })
                .catch(() => {
                    this.article = {};
                });
        },
        blogContentChanged(value, render) {
            // render 为 markdown 解析后的结果
            this.article.htmlContent = render;
            this.modified = !(this.oldContent === value);
        },
        saveContent(value, render) {
            this.article.htmlContent = render;
        },
        updateBlog() {
            let id = this.article.id;
            if (!NumberUtils.isValidateId(id)) {
                this.$message.error('编辑的文章ID错误！');
                return;
            }
            this.$refs.blogTitleForm.validate(valid => {
                if (valid) {
                    if (!this.article.htmlContent || !this.article.textContent) {
                        this.$message.warning('文章内容不能为空！');
                        return;
                    }
                    if (this.modified) {
                        updateBlog(this.article).then(() => {
                            this.modified = false;
                            this.$router.push({ path: `/blog/read/${id}.html` });
                        });
                    } else {
                        this.$router.push({ path: `/blog/read/${id}.html` });
                    }
                } else {
                    this.$message.error('文章标题和概述格式错误！');
                }
            });
        },
        addImage(pos, file) {
            let formData = new FormData();
            formData.append('image', file);
            uploadImg(formData).then(res => {
                if (res) {
                    this.$refs.mEditor.$img2Url(pos, res.data);
                }
            });
        },
        delImage(fileName) {
            console.log('delImage: ' + fileName);
        }
    },
    mounted() {
        window.onbeforeunload = function(e) {
            e = e || window.event;
            // 兼容IE8和Firefox 4之前的版本
            if (e) {
                e.returnValue = '系统可能不会保存你所做的修改';
            }
            // Chrome, Safari, Firefox 4+, Opera 12+ , IE 9+
            return '系统可能不会保存你所做的修改';
        };
    },
    destroyed() {
        window.onbeforeunload = null;
    },
    beforeRouteLeave(to, from, next) {
        if (this.article.textContent && this.modified) {
            const answer = window.confirm('系统可能不会保存你所做的修改');
            if (answer) {
                next();
            } else {
                next(false);
            }
        } else {
            next();
        }
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.markdown-editor {
    min-height: calc(100vh - 426px);
}
.blog-title-block {
    min-height: 120px;
    margin: 20px 0;
}
.opt-block {
    text-align: end;
    margin-bottom: 20px;
}
</style>
