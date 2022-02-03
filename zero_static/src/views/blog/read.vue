<template>
    <div class="blog-main-content">
        <div class="left-block"></div>
        <div class="middle-block">
            <el-card>
                <pre v-html="blogInfo.htmlContent"></pre>
            </el-card>
        </div>
        <div class="right-block"></div>
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
            }
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

<style scoped>
.left-block {
    height: 100%;
}
</style>
