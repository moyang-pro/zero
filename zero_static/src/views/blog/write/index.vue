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
            <el-form ref="blogTitleForm" :model="article" label-width="120" :rules="rulesBlog" label-position="left">
                <el-form-item label="文章标题：" prop="title">
                    <el-input v-model="article.title" maxlength="100" show-word-limit placeholder="请输入文章标题（100字以内）......" />
                </el-form-item>
                <el-form-item label="内容概述：" prop="des">
                    <el-input v-model="article.des" type="textarea" :autosize="{ minRows: 2, maxRows: 5 }" maxlength="500" show-word-limit placeholder="请对文章进行简要描述（20~500字）......" />
                </el-form-item>
            </el-form>
        </div>
        <div class="opt-block">
            <el-button round @click="saveBlog">保存草稿</el-button>
            <el-button type="warning" round @click="openPublishDialog">发布文章</el-button>
        </div>
        <div class="dialog-block">
            <el-dialog :visible.sync="publishDialog.visible" width="30%" :close-on-click-modal="false">
                <span slot="title" class="dialog-header">
                    <span>{{ publishDialog.title }}</span>
                </span>
                <el-form class="publish-blog-form" label-position="left" label-width="100px" ref="publishForm" @submit.native.prevent>
                    <el-form-item label="文章封面：" required>
                        <el-radio-group v-model="blogForm.hasCover">
                            <el-radio :label="false">无封面</el-radio>
                            <el-radio :label="true">有封面</el-radio>
                        </el-radio-group>
                        <el-upload
                            action=""
                            accept="image/jpeg,image/gif,image/png"
                            ref="blogCoverUpload"
                            :show-file-list="false"
                            v-if="blogForm.hasCover"
                            :before-upload="beforeUploadCover"
                            :http-request="uploadCover"
                        >
                            <div :class="coverImgSuccess ? 'cover_head_img' : 'cover_head_img_before'">
                                <img :src="this.blogForm.coverUrl" class="cover-image-show" alt="文章封面" v-if="coverImgSuccess" />
                                <div class="cover-replace-item" v-if="coverImgSuccess" @click="clickReplaceCover">
                                    <i class="el-icon-camera cover-replace"></i>
                                </div>
                                <span class="cover-delete-item" v-if="coverImgSuccess" @click.stop="clickRemoveCover">
                                    X
                                </span>
                                <i class="el-icon-plus plus-img-tag" v-show="!coverImgSuccess"></i>
                            </div>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="文章标签：" required>
                        <el-tag :key="tag" v-for="tag in blogForm.tags" closable :disable-transitions="false" @close="handleRemoveTag(tag)">
                            {{ tag }}
                        </el-tag>
                        <el-input
                            class="input-new-tag"
                            v-if="inputTagVisible"
                            v-model="inputTagValue"
                            ref="saveTagInput"
                            size="small"
                            @keyup.enter.native.passive="handleInputTagConfirm"
                            @blur.prevent="handleInputTagConfirm"
                        >
                        </el-input>
                        <el-button v-else class="button-new-tag" size="small" @click="showTagInput">+ 标签</el-button>
                    </el-form-item>
                    <el-form-item label="文章类型：" required>
                        <el-radio-group v-model="blogForm.type">
                            <el-radio :label="0">原创</el-radio>
                            <el-radio :label="1">转载</el-radio>
                            <el-radio :label="2">翻译</el-radio>
                        </el-radio-group>
                        <el-input placeholder="请填写原文链接" v-model="blogForm.quote" clearable v-if="blogForm.type !== 0" style="width: 90%"> </el-input>
                        <span class="notice" v-if="blogForm.type !== 0">*</span>
                    </el-form-item>
                    <el-form-item label="发布形式：" required>
                        <el-radio-group v-model="blogForm.publishType">
                            <el-radio :label="0">公开</el-radio>
                            <el-radio :label="1">私密</el-radio>
                            <el-radio :label="2">粉丝可见</el-radio>
                        </el-radio-group>
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
import { uploadImg, writeBlog, uploadCoverImg, publishMyBlog } from '@/api/blog';

export default {
    name: 'write',
    data() {
        return {
            inputTagVisible: false,
            inputTagValue: '',
            article: {
                title: '',
                des: '',
                htmlContent: '',
                textContent: ''
            },
            blogForm: {
                hasCover: false,
                coverUrl: '',
                type: 0,
                tags: [],
                publishType: 0,
                quote: '',
                articleInfo: null
            },
            modified: false,
            publishDialog: {
                visible: false,
                title: '发布文章'
            },
            coverImgSuccess: false,
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
            if (!this.article.htmlContent || !this.article.textContent) {
                this.$message.warning('文章内容不能为空！');
                return;
            }
            this.$refs.blogTitleForm.validate(valid => {
                if (valid) {
                    this.publishDialog.visible = true;
                    this.blogForm.articleInfo = this.article;
                } else {
                    this.$message.warning('文章标题或概述格式错误！');
                }
            });
        },
        publishBlog() {
            if (this.blogForm.hasCover && !this.blogForm.coverUrl) {
                this.$message.error('请上传文章封面图片！');
                return;
            }
            if (this.blogForm.type !== 0 && !this.blogForm.quote) {
                this.$message.error('请输入原文链接！');
                return;
            }
            publishMyBlog(this.blogForm)
                .then(res => {
                    this.modified = false;
                    let id = res.data;
                    this.$router.push({ path: `/blog/read/${id}.html` });
                })
                .then(() => {
                    this.modified = true;
                });
        },
        publishDialogClose() {
            this.publishDialog.visible = false;
            // 清空发布信息
        },
        uploadCover(fileReq) {
            if (!fileReq || !fileReq.file) {
                this.$message.error('上传文件内容错误!');
                return false;
            }
            let file = fileReq.file;
            let formData = new FormData();
            formData.append('image', file);
            this.blogForm.coverUrl = '';
            this.coverImgSuccess = false;
            uploadCoverImg(formData)
                .then(res => {
                    this.blogForm.coverUrl = res.data;
                    this.coverImgSuccess = true;
                })
                .catch(() => {
                    this.blogForm.coverUrl = '';
                    this.coverImgSuccess = false;
                });
        },
        beforeUploadCover(file) {
            // 校检文件大小
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isLt2M) {
                this.$message.error('上传文件大小不能超过 2MB!');
                return false;
            }
            return true;
        },
        clickReplaceCover() {
            console.log('clickReplaceCover');
        },
        clickRemoveCover() {
            this.coverImgSuccess = false;
            this.blogForm.coverUrl = '';
        },
        handleRemoveTag(tag) {
            this.blogForm.tags.splice(this.blogForm.tags.indexOf(tag), 1);
            console.log(this.blogForm.tags);
        },
        showTagInput() {
            this.inputTagVisible = true;
            this.$nextTick(() => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },
        handleInputTagConfirm() {
            let inputValue = this.inputTagValue;
            if (this.blogForm.tags.indexOf(inputValue) !== -1) {
                this.$message.warning('标签已存在！');
                return;
            }
            if (inputValue) {
                this.blogForm.tags.push(inputValue);
            }
            this.inputTagVisible = false;
            this.inputTagValue = '';
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
.dialog-block {
    text-align: left;
}
.dialog-header {
    font-size: 16px;
    font-weight: bolder;
}
.cover_head_img_before {
    width: 160px;
    height: 90px;
    position: relative;
    left: 0;
    top: 0;
    cursor: pointer;
    background: #fff;
    border: 1px dashed #bfbfbf;
    border-radius: 4px;
}

.cover_head_img {
    width: 160px;
    height: 90px;
    position: relative;
    left: 0;
    top: 0;
    cursor: pointer;
    background: #fff;
    border-radius: 4px;
}
.cover-image-show {
    height: 100%;
    width: 100%;
    border-radius: 4px;
}
.plus-img-tag {
    line-height: 90px;
}
.cover-replace-item {
    position: absolute;
    top: 0;
    left: 0;
    width: 160px;
    height: 90px;
    background: rgba(101, 101, 101, 0.6);
    color: #ffffff;
    opacity: 0;
    border-radius: 4px;
}
.cover-replace-item:hover {
    opacity: 1;
}
.cover-replace {
    line-height: 90px;
    font-size: 20px;
}

.cover-delete-item {
    z-index: 9;
    position: absolute;
    background: #999;
    color: #fff;
    line-height: 20px;
    right: -8px;
    top: -8px;
    width: 20px;
    height: 20px;
    font-size: 14px;
    text-align: center;
    background-size: contain;
    border-radius: 50%;
    cursor: pointer;
    opacity: 0;
}
.cover-delete-item:hover {
    opacity: 1;
}

.el-tag {
    height: 32px;
    padding: 0 10px;
    line-height: 30px;
    margin-right: 10px;
}
.button-new-tag {
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    vertical-align: bottom;
}

.notice {
    margin: 0 8px;
    padding-top: 4px;
    font-size: 16px;
    color: #fc5531;
}
</style>
