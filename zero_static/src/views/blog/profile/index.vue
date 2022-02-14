<template>
    <div>
        <div class="blog-main-content">
            <div class="title-block">
                <div class="user-show-block">
                    <el-card class="blog-user-card blog-card" :shadow="cardShadow">
                        <el-row>
                            <el-col :span="10">
                                <el-avatar size="large" :src="userInfo.avatar" class="blog-avatar"></el-avatar>
                            </el-col>
                            <el-col :span="12" :offset="2">
                                <p class="user-nick text-one-line" :title="userInfo.nick">
                                    {{ userInfo.nick }}
                                </p>
                                <div class="text-one-line">
                                    <span>墨阳账号：</span>
                                    <span class="user-account">{{ userInfo.emy }}</span>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row> </el-row>
                    </el-card>
                </div>
                <div class="tool-block">
                    <el-card class="blog-tool-card blog-card blog-right-card" :shadow="cardShadow"> </el-card>
                </div>
            </div>
            <div class="notebook-block">
                <div class="user-preference-block">
                    <el-card class="blog-preference-card blog-card" :shadow="cardShadow"> </el-card>
                </div>
                <div class="article-block">
                    <articleCard></articleCard>
                </div>
            </div>
            <div class="float-button-block">
                <el-button type="warning" icon="el-icon-edit" circle size="large" class="fab-write-button" @click="writeBlog()"></el-button>
            </div>
        </div>
    </div>
</template>

<script>
import articleCard from '@/views/blog/common/articleCard';
export default {
    name: 'UserProfile',
    components: {
        articleCard
    },
    data() {
        return {
            emy: '',
            cardShadow: 'always',
            userInfo: {
                nick: '一米阳光',
                emy: '',
                avatar: require('@/assets/img/avatar.png')
            }
        };
    },
    created() {
        this.emy = this.$route.params.emy;
        console.log('当前用户：emy ' + this.emy);
        this.getMineInfo();
    },
    methods: {
        writeBlog() {
            this.$router.push('/blog/write');
        },
        getMineInfo() {
            const nick = this.$store.state.user.nick;
            const avatar = this.$store.state.user.avatar;
            this.userInfo.nick = nick || '一米阳光';
            this.userInfo.avatar = avatar || require('@/assets/img/avatar.png');
            this.userInfo.emy = this.$store.state.user.name;
        }
    }
};
</script>

<style scoped lang="scss">
@import './src/styles/zero/blog/blog.scss';
.title-block {
    display: flex;
    justify-content: flex-start;
}
.notebook-block {
    width: 100%;
    display: flex;
    justify-content: flex-start;
    margin: 20px 0;
}

.blog-user-card {
    width: 420px;
    height: 240px;
}
.blog-tool-card {
    height: 240px;
    margin-left: 30px;
}

.blog-preference-card {
    width: 420px;
    min-height: 720px;
}
.blog-avatar {
    width: 140px;
    height: 140px;
}
.blog-card {
    padding: 0;
    border-radius: 20px;
}

.user-nick {
    font-family: STXingkai, Hiragino Sans GB, Arial, Microsoft YaHei, Verdana, Roboto, Noto, Helvetica Neue, sans-serif;
    font-size: 24px;
    cursor: pointer;
}
.float-button-block {
    text-align: end;
}
.fab-write-button {
    height: 60px;
    width: 60px;
    font-size: 24px;
    position: relative;
    bottom: 160px;
    left: 45px;
    box-shadow: 0 2px 12px 0 #cdcdcb;
    //box-shadow: 1px 1px 2px 1px ;
}
</style>
