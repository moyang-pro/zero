<template>
    <div class="comment-box">
        <el-card class="comment-card" :shadow="cardShadow">
            <div class="comment-title">
                <span class="text-title-comment">评论</span>
            </div>
            <div class="comment-body-input">
                <div class="comment-user">
                    <el-avatar :src="userInfo.authorAvatar" class="cmt-user-avatar"></el-avatar>
                </div>
                <div class="input-comment">
                    <el-input
                        type="textarea"
                        maxlength="1000"
                        placeholder="请输入评论内容，@提及用户，Enter换行"
                        v-model="commentInfo.content"
                        show-word-limit
                        :autosize="{ minRows: 3 }"
                    >
                    </el-input>
                </div>
            </div>
            <div>
                <el-button size="mini " round type="warning"> 发表评论</el-button>
            </div>
            <commentList :comments="data"></commentList>
        </el-card>
    </div>
</template>

<script>
import commentList from '@/components/comment/commentList';
export default {
    name: 'index',
    components: {
        commentList
    },
    data() {
        return {
            cardShadow: 'always',
            userInfo: {
                emy: '墨阳',
                authorAvatar: require('@/assets/img/avatar.png')
            },
            commentInfo: {
                content: ''
            },
            mentions: false,
            retUserList: [
                {
                    name: 'moyang',
                    nick: '墨阳'
                }
            ],
            tributeOptions: {
                values: []
            },
            data: [
                {
                    id: 'comment0001', //主键id
                    date: '2018-07-05 08:30', //评论时间
                    ownerId: 'talents100020', //文章的id
                    fromId: 'errhefe232213', //评论者id
                    fromName: '犀利的评论家', //评论者昵称
                    fromAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', //评论者头像
                    likeNum: 3, //点赞人数
                    content: '非常靠谱的程序员', //评论内容
                    reply: [
                        //回复，或子评论
                        {
                            id: '34523244545', //主键id
                            commentId: 'comment0001', //父评论id，即父亲的id
                            fromId: 'observer223432', //评论者id
                            fromName: '夕阳红', //评论者昵称
                            fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', //评论者头像
                            toId: 'errhefe232213', //被评论者id
                            toName: '犀利的评论家', //被评论者昵称
                            toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', //被评论者头像
                            content: '赞同，很靠谱，水平很高', //评论内容
                            date: '2018-07-05 08:35' //评论时间
                        },
                        {
                            id: '34523244545',
                            commentId: 'comment0001',
                            fromId: 'observer567422',
                            fromName: '清晨一缕阳光',
                            fromAvatar:
                                'http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg',
                            toId: 'observer223432',
                            toName: '夕阳红',
                            toAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg',
                            content: '大神一个！',
                            date: '2018-07-05 08:50'
                        }
                    ]
                },
                {
                    id: 'comment0002',
                    date: '2018-07-05 08:30',
                    ownerId: 'talents100020',
                    fromId: 'errhefe232213',
                    fromName: '毒蛇郭德纲',
                    fromAvatar: 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg',
                    likeNum: 0,
                    content: '从没见过这么优秀的人',
                    reply: []
                }
            ]
        };
    },
    methods: {
        querySearchUser() {}
    }
};
</script>

<style scoped>
.comment-card {
    margin-top: 20px;
    min-height: 200px;
    border-radius: 20px;
}
.comment-title {
    text-align: start;
    padding: 5px 5px;
    border-bottom: 1px solid #ebeef5;
    font-weight: bold;
}
.text-title-comment {
    font-size: 20px;
}
.comment-body-input {
    text-align: start;
    display: flex;
    justify-content: start;
    margin: 20px 0 10px;
}
.comment-user {
    margin: 10px 0;
}
.input-comment {
    width: 100%;
    margin-left: 20px;
}
</style>
