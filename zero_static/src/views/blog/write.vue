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
        />
    </div>
</template>

<script>
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
            console.log('saveBlog: ' + data);
        }
    }
};
</script>

<style scoped>
.markdown-editor {
    min-height: 960px;
}
</style>
