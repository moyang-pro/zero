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
                    <el-input
                        v-model="article.title"
                        maxlength="100"
                        show-word-limit
                        placeholder="请输入文章标题（100字以内）......"
                    />
                </el-form-item>
                <el-form-item label="内容概述：" prop="des">
                    <el-input
                        v-model="article.des"
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 5 }"
                        maxlength="500"
                        show-word-limit
                        placeholder="请对文章进行简要描述（20~500字）......"
                    />
                </el-form-item>
            </el-form>
        </div>
        <div class="opt-block">
            <el-button round @click="saveBlog">保存草稿</el-button>
            <el-button type="warning" round @click="openPublishDialog">发布文章</el-button>
        </div>
        <div class="dialog-block">
            <el-dialog
                :visible.sync="publishDialog.visible"
                width="30%"
                :close-on-click-modal="false"
            >
                <span slot="title" class="dialog-header">
                    <span>{{ publishDialog.title }}</span>
                </span>
                <el-form class="publish-blog-form" label-position="left" label-width="100px">
                    <el-form-item label="文章封面：">
                        <el-radio-group v-model="blogForm.isCover">
                            <el-radio :label="false">无封面</el-radio>
                            <el-radio :label="true">有封面</el-radio>
                        </el-radio-group>
                        <el-upload
                            action="#"
                            list-type="picture-card"
                            :auto-upload="false"
                            :file-list="coverImgList"
                            v-if="blogForm.isCover"
                        >
                            <i slot="default" class="el-icon-plus"></i>
                            <div slot="file" slot-scope="{ file }">
                                <img
                                    class="el-upload-list__item-thumbnail"
                                    :src="file.url"
                                    alt=""
                                />
                            </div>
                        </el-upload>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button round @click="publishDialogClose">取 消</el-button>
                    <el-button type="warning" round @click="publishBlog">发 布</el-button>
                </span>
            </el-dialog>
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
                htmlContent: '',
                textContent: ''
            },
            blogForm: {
                isCover: false,
                coverUrl: '',
                type: 0,
                tags: [],
                publishType: 0,
                articleInfo: null
            },
            modified: false,
            publishDialog: {
                visible: false,
                title: '发布文章'
            },
            coverImgList: [],
            rulesBlog: {
                title: [
                    {
                        required: true,
                        message: '请输入文章标题100字以内',
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
                        message: '请对文章进行简要描述20~500字',
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
        },
        saveBlog() {
            if (!this.article.htmlContent || !this.article.textContent) {
                this.$message.warning('文章内容不能为空！');
                return;
            }
            this.$refs.blogTitleForm.validate(valid => {
                if (valid) {
                    if (this.modified) {
                        writeBlog(this.article).then(res => {
                            this.modified = false;
                            let id = res.data;
                            this.$router.push({ path: `/blog/read/${id}.html` });
                        });
                    }
                } else {
                    this.$message.warning('文章标题或概述格式错误！');
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
        },
        openPublishDialog() {
            this.publishDialog.visible = true;
        },
        publishBlog() {},
        publishDialogClose() {
            this.publishDialog.visible = false;
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
        if (this.article.textContent) {
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
.dialog-block {
    text-align: left;
}
.dialog-header {
    font-size: 16px;
    font-weight: bolder;
}
</style>
