<template>
    <div>
        <div class="blog-main-content">
            <div class="title-block">
                <div class="user-show-block">
                    <el-card class="blog-user-card blog-card">
                        <el-row>
                            <el-col :span="10">
                                <el-avatar
                                    size="large"
                                    :src="userInfo.avatar"
                                    class="blog-avatar"
                                ></el-avatar>
                            </el-col>
                            <el-col :span="12" :offset="2">
                                <p class="user-nick text-ell" :title="userInfo.nick">
                                    {{ userInfo.nick }}
                                </p>
                                <div class="text-ell">
                                    <span>墨阳账号：</span>
                                    <span class="user-account">{{ userInfo.emy }}</span>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row> </el-row>
                    </el-card>
                </div>
                <div class="tool-block">
                    <el-card class="blog-tool-card blog-card"> </el-card>
                </div>
            </div>
            <div class="notebook-block">
                <div class="user-preference-block">
                    <el-card class="blog-preference-card blog-card"> </el-card>
                </div>
                <div class="article-block">
                    <el-card class="blog-article-card blog-card"> </el-card>
                </div>
            </div>
            <div class="float-button-block">
                <el-button
                    type="warning"
                    icon="el-icon-edit"
                    circle
                    size="large"
                    class="fab-write-button"
                    @click="writeBlog()"
                ></el-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'articles',
    data() {
        return {
            userInfo: {
                nick: '一束温暖的阳光',
                emy: '1542051400',
                avatar: require('@/assets/img/avatar-default.png')
            },
            articlesInfo: []
        };
    },
    methods: {
        writeBlog() {
            this.$router.push('/blog/write');
        }
    }
};
</script>

<style scoped>
.title-block {
    display: flex;
    justify-content: flex-start;
}
.notebook-block {
    display: flex;
    justify-content: flex-start;
    margin: 20px 0;
}

.blog-user-card {
    width: 420px;
    height: 240px;
}
.blog-tool-card {
    width: 1040px;
    height: 240px;
    margin-left: 30px;
}

.blog-preference-card {
    width: 420px;
    min-height: 720px;
}
.blog-article-card {
    width: 1040px;
    min-height: 720px;
    margin-left: 30px;
}
.blog-avatar {
    width: 140px;
    height: 140px;
}
.blog-card {
    padding: 20px;
}

.user-nick {
    font-family: STXingkai, Hiragino Sans GB, Arial, Microsoft YaHei, Verdana, Roboto, Noto,
        Helvetica Neue, sans-serif;
    font-size: 24px;
    cursor: pointer;
}
.float-button-block {
    text-align: end;
}
.fab-write-button {
    height: 80px;
    width: 80px;
    font-size: 28px;
    position: relative;
    bottom: 160px;
    left: 45px;
    box-shadow: 1px 1px 2px 1px #e6a23c;
}
</style>
