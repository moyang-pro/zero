<template>
    <div class="blog-main-content">
        <mavon-editor
            ref="mEditor"
            class="markdown-editor"
            v-model="content"
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
            <el-button round @click="saveBlog">保存草稿</el-button>
            <el-button type="warning" round>发布文章</el-button>
        </div>
    </div>
</template>

<script>
import { uploadImg, writeBlog } from '@/api/blog';

export default {
    name: 'write',
    data() {
        return {
            article: {
                title: '',
                des: '',
                htmlContent: ''
            },
            content: '',
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
    methods: {
        blogContentChanged(value, render) {
            // render 为 markdown 解析后的结果
            this.article.htmlContent = render;
            this.modified = true;
        },
        saveContent(value, render) {
            this.article.htmlContent = render;
            console.log('saveContent: ' + this.article.htmlContent);
        },
        saveBlog() {
            console.log('saveBlog: {}', this.article);
            this.$refs.blogTitleForm.validate(valid => {
                if (valid) {
                    if (!this.article.htmlContent) {
                        this.$message.warning('文章内容不能为空！');
                        return;
                    }
                    if (this.modified) {
                        writeBlog(this.article).then(res => {
                            this.modified = false;
                            let id = res.data;
                            this.$router.push({ path: `/blog/read/${id}.html` });
                        });
                    }
                } else {
                    this.$message.error('文章标题和概述格式错误！');
                }
            });
        },
        addImage(pos, file) {
            console.log('addImage ' + pos + ' ..................');
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
