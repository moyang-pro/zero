<template>
    <div class="blog-main-content">
        <mavon-editor
            ref="mEditor"
            class="markdown-editor"
            v-model="content"
            :ishljs="true"
            :subfield="false"
            fontSize="18px"
            @change="blogContentChanged"
            @save="saveBlog"
            @imgAdd="addImage"
            @imgDel="delImage"
        />
    </div>
</template>

<script>
import {uploadImg, writeBlog} from '@/api/blog';

export default {
    name: 'write',
    data() {
        return {
            content: ''
        };
    },
    methods: {
        blogContentChanged(data, htmlData) {
            console.log('blogContentChanged: ' + data + htmlData);
        },
        saveBlog(data) {
            writeBlog();
            console.log('saveBlog: ' + data);
        },
        addImage(fileName, file) {
            console.log('addImage: ' + fileName + file);
            let formdata = new FormData();
            formdata.append('image', file);
            uploadImg(formdata)
                .then(res => {
                    console.log(res);
                    this.$message.success('上传图片成功');
                })
                .catch(err => {
                    this.$message.error(err.message);
                });
        },
        delImage(fileName) {
            console.log('delImage: ' + fileName);
        }
    }
};
</script>

<style scoped>
.markdown-editor {
    min-height: 960px;
}
</style>
