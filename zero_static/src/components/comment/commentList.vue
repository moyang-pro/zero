<template>
    <div class="comment-list-block">
        <div class="comment-list-item" v-for="item in commentList" :key="item.id">
            <div class="cmt-user-info">
                <el-avatar class="cmt-user-avatar" :src="item.fromAvatar" alt="" />
                <div class="cmt-user-title">
                    <div class="name">{{ item.fromName }}</div>
                    <div class="date">{{ item.cmtTime }}</div>
                </div>
            </div>
            <div class="cmt-content">{{ item.content }}</div>
            <div class="cmt-opt-line">
                <span class="cmt-opt-like div-text-no-click" @click="likeClick(item)">
                    <svg-icon icon-class="liked" class="cmt-icon" v-if="item.isLike"></svg-icon>
                    <svg-icon icon-class="like" class="cmt-icon" v-else></svg-icon>
                    <span :class="item.isLike ? 'cmt-active-text' : ''">{{
                        item.likeCount > 0 ? item.likeCount + '人赞' : '赞'
                    }}</span>
                </span>
                <span class="cmt-opt-reply div-text-no-click" @click="showCommentInput(item)">
                    <svg-icon icon-class="reply" class="cmt-icon"></svg-icon>
                    <span>回复</span>
                </span>
            </div>
            <div class="cmt-reply-box">
                <div class="cmt-reply-item" v-for="reply in item.replyList" :key="reply.id">
                    <div class="cmt-user-info">
                        <el-avatar class="cmt-user-avatar" :src="reply.fromAvatar" alt="" />
                        <div class="cmt-user-title">
                            <div class="name">{{ reply.fromName }}</div>
                            <div class="date">{{ reply.cmtTime }}</div>
                        </div>
                    </div>
                    <div class="cmt-reply-content">
                        <span class="cmt-reply-to-name">@{{ reply.toName }}</span>
                        {{ reply.content }}
                    </div>
                    <div class="cmt-reply-opt-line">
                        <span class="cmt-opt-like div-text-no-click" @click="likeClick(reply)">
                            <svg-icon icon-class="liked" class="cmt-icon" v-if="reply.isLike"></svg-icon>
                            <svg-icon icon-class="like" class="cmt-icon" v-else></svg-icon>
                            <span :class="reply.isLike ? 'cmt-active-text' : ''">{{
                                reply.likeCount > 0 ? reply.likeCount + '人赞' : '赞'
                            }}</span>
                        </span>
                        <span class="cmt-opt-reply div-text-no-click" @click="showCommentInput(item, reply)">
                            <svg-icon icon-class="reply" class="cmt-icon"></svg-icon>
                            <span>回复</span>
                        </span>
                    </div>
                </div>
                <div class="cmt-write-reply" v-if="item.replyList.length > 0" @click="showCommentInput(item)">
                    <span class="cmt-add-reply"> <i class="el-icon-edit"></i>添加新回复</span>
                </div>
                <el-collapse-transition>
                    <div class="cmt-reply-input-wrapper" v-if="showItemId === item.id">
                        <el-input
                            class="write-reply-input"
                            v-model="inputComment"
                            type="textarea"
                            :rows="3"
                            autofocus
                            placeholder="请输入评论内容，@提及用户，Enter换行"
                        >
                        </el-input>
                        <div class="cmt-write-reply-opt">
                            <el-button size="mini" @click="cancel" round>取消</el-button>
                            <el-button size="mini" type="warning" round @click="commitComment">
                                确定
                            </el-button>
                        </div>
                    </div>
                </el-collapse-transition>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'commentList',
    props: {
        comments: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            inputComment: '',
            showItemId: -1,
            commentList: [
                {
                    id: 'comment0001', //主键id
                    isLike: true,
                    cmtTime: '2018-07-05 08:30', //评论时间
                    ownerId: 'talents100020', //文章的id
                    fromId: 'errhefe232213', //评论者id
                    fromName: '犀利的评论家', //评论者昵称
                    fromAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', //评论者头像
                    likeCount: 3, //点赞人数
                    content: '非常靠谱的程序员', //评论内容
                    replyList: [
                        //回复，或子评论
                        {
                            id: '34523244545', //主键id
                            isLike: false,
                            commentId: 'comment0001', //父评论id，即父亲的id
                            fromId: 'observer223432', //评论者id
                            fromName: '夕阳红', //评论者昵称
                            likeCount: 0,
                            fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', //评论者头像
                            toId: 'errhefe232213', //被评论者id
                            toName: '犀利的评论家', //被评论者昵称
                            toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', //被评论者头像
                            content: '赞同，很靠谱，水平很高', //评论内容
                            cmtTime: '2018-07-05 08:35' //评论时间
                        },
                        {
                            id: '34523244546',
                            commentId: 'comment0001',
                            fromId: 'observer567422',
                            likeCount: 1,
                            fromName: '清晨一缕阳光',
                            fromAvatar:
                                'http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg',
                            toId: 'observer223432',
                            toName: '夕阳红',
                            toAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg',
                            content: '大神一个！',
                            cmtTime: '2018-07-05 08:50'
                        }
                    ]
                },
                {
                    id: 'comment0002',
                    cmtTime: '2018-07-05 08:30',
                    ownerId: 'talents100020',
                    fromId: 'errhefe232213',
                    fromName: '毒蛇郭德纲',
                    fromAvatar: 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg',
                    likeCount: 0,
                    content: '从没见过这么优秀的人',
                    replyList: []
                }
            ]
        };
    },
    methods: {
        /**
         * 点赞
         */
        likeClick(item) {
            if (item.isLike) {
                item.likeCount--;
            } else {
                item.likeCount++;
            }
            item.isLike = !item.isLike;
        },
        /**
         * 点击评论按钮显示输入框
         * item: 当前大评论
         * reply: 当前回复的评论
         */
        showCommentInput(item, reply) {
            if (reply) {
                this.inputComment = '@' + reply.fromName + ' ';
            } else {
                this.inputComment = '';
            }
            this.showItemId = item.id;
        },
        /**
         * 点击取消按钮
         */
        cancel() {
            this.showItemId = -1;
        },

        /**
         * 提交评论
         */
        commitComment() {
            console.log(this.inputComment);
        }
    }
};
</script>

<style scoped>
.comment-list-block {
    text-align: start;
}
.comment-list-item {
    margin-top: 10px;
}
.cmt-user-info {
    display: flex;
    justify-content: flex-start;
}
.cmt-user-avatar {
    height: 36px;
    width: 36px;
}
.cmt-user-title {
    font-size: 13px;
    color: #606266;
    padding: 0 10px;
}
.cmt-content {
    margin-top: 5px;
    min-height: 30px;
    font-size: 15px;
    padding-left: 46px;
}
.cmt-opt-line {
    margin-left: 46px;
    margin-bottom: 10px;
    font-size: 14px;
    color: #909399;
    padding-bottom: 10px;
    border-bottom: 1px solid #e4e7ed;
}
.cmt-opt-like {
    margin-right: 10px;
    cursor: pointer;
}
.cmt-opt-reply {
    margin: 0 10px;
    cursor: pointer;
}
.cmt-icon {
    margin-right: 3px;
}
.cmt-active-text {
    color: #00a4ff;
}
.cmt-reply-box {
    margin-left: 46px;
}
.cmt-reply-to-name {
    color: #00a4ff;
    margin-left: 5px;
    margin-right: 5px;
    font-size: 14px;
}
.cmt-reply-content {
    margin-top: 5px;
    min-height: 30px;
    font-size: 15px;
}
.cmt-reply-opt-line {
    margin-bottom: 10px;
    padding-bottom: 10px;
    font-size: 14px;
    color: #909399;
    border-bottom: 1px solid #e4e7ed;
}
.cmt-write-reply {
    margin: 5px 0;
}
.cmt-add-reply {
    cursor: pointer;
    color: #ebb563;
}
.cmt-write-reply-opt {
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;
}
</style>
