<template>
    <div>
        <div class="blog-head-content"></div>
        <div class="blog-main-content">
            <div class="title-block">
                <div class="user-show-block">
                    <el-card class="blog-user-card blog-card" :shadow="cardShadow">
                        <el-row>
                            <el-col :span="8">
                                <div class="blog-avatar-box">
                                    <el-avatar size="large" :src="userInfo.avatar" class="blog-avatar"></el-avatar>
                                    <span class="text-vip" v-if="userInfo.vipCode">v</span>
                                </div>
                            </el-col>
                            <el-col :span="14" :offset="2">
                                <span class="user-nick text-one-line" :title="userInfo.nick">
                                    {{ userInfo.nick }}
                                </span>
                                <div class="text-one-line">
                                    <span>墨阳账号：</span>
                                    <span class="user-account">{{ userInfo.emy }}</span>
                                </div>
                                <div class="line-count-user">
                                    <div class="article-count ">
                                        <div class="item-count-title">
                                            文章
                                        </div>
                                        <div class="item-count-num">
                                            {{ userInfo.countInfo.articleCount }}
                                        </div>
                                    </div>
                                    <div class="follow-count item-count">
                                        <div class="item-count-title">
                                            关注
                                        </div>
                                        <div class="item-count-num">
                                            {{ userInfo.countInfo.followCount }}
                                        </div>
                                    </div>
                                    <div class="followed-count item-count">
                                        <div class="item-count-title">
                                            粉丝
                                        </div>
                                        <div class="item-count-num">
                                            {{ userInfo.countInfo.followedCount }}
                                        </div>
                                    </div>
                                    <div class="faq-count item-count">
                                        <div class="item-count-title">
                                            问答
                                        </div>
                                        <div class="item-count-num">
                                            {{ userInfo.countInfo.faqCount }}
                                        </div>
                                    </div>
                                </div>
                                <div class="user-rate-box">
                                    <span>活跃度：</span>
                                    <el-rate
                                        v-model="userInfo.rate"
                                        disabled
                                        :max="5"
                                        text-color="#ff9900"
                                        score-template="{value}"
                                    >
                                    </el-rate>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <div class="summary-box text-two-line">
                                {{ userInfo.summary }}
                            </div>
                        </el-row>
                    </el-card>
                </div>
                <div class="tool-block">
                    <toolCard></toolCard>
                </div>
            </div>
            <div class="notebook-block">
                <div class="user-preference-block">
                    <preferenceCard></preferenceCard>
                </div>
                <div class="article-block">
                    <articleCard></articleCard>
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
import articleCard from '@/views/blog/common/articleCard';
import toolCard from '@/views/blog/common/toolCard';
import preferenceCard from '@/views/blog/common/preferenceCard';
export default {
    name: 'UserProfile',
    components: {
        articleCard,
        toolCard,
        preferenceCard
    },
    data() {
        return {
            emy: '',
            cardShadow: 'always',
            userInfo: {
                nick: '一米阳光',
                emy: '',
                avatar: require('@/assets/img/avatar.png'),
                vipCode: 1,
                countInfo: {
                    articleCount: 0,
                    followCount: 0,
                    followedCount: 0,
                    faqCount: 0
                },
                summary: '一抹温柔，毕生追求',
                rate: 0
            }
        };
    },
    created() {
        this.emy = this.$route.params.emy;
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
.blog-main-content {
    padding-top: 0;
}
.blog-head-content {
    height: 80px;
    width: 100%;
}
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
    color: #d9111e;
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
.blog-avatar-box {
    position: relative;
}
.text-vip {
    height: 30px;
    width: 30px;
    background: #333;
    border-radius: 50%;
    font-size: 19px;
    color: gold;
    position: absolute;
    right: 5px;
    bottom: 5px;
    text-align: center;
}
.line-count-user {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
}
.item-count-title {
    font-weight: bold;
    font-size: 18px;
}
.item-count-num {
    font-size: 18px;
}
.user-rate-box {
    display: flex;
    justify-content: flex-start;
}
.summary-box {
    text-align: start;
    margin-top: 10px;
    min-height: 40px;
}
</style>
