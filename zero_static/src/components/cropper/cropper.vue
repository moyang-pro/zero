<template>
    <div>
        <el-dialog title="图片剪裁" :visible.sync="dialogVisible" append-to-body>
            <div class="cropper-content">
                <div class="cropper" style="text-align:center">
                    <vueCropper
                        ref="cropper"
                        :img="option.img"
                        :outputSize="option.size"
                        :outputType="option.outputType"
                        :info="true"
                        :full="option.full"
                        :canMove="option.canMove"
                        :canMoveBox="option.canMoveBox"
                        :original="option.original"
                        :autoCrop="option.autoCrop"
                        :fixed="option.fixed"
                        :fixedNumber="option.fixedNumber"
                        :centerBox="option.centerBox"
                        :infoTrue="option.infoTrue"
                        :fixedBox="option.fixedBox"
                    ></vueCropper>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="finish" :loading="loading">确认</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios';
import { VueCropper } from 'vue-cropper';
import md5 from 'js-md5';
import { postossImage } from '../../../api/index';
export default {
    name: 'cropper',
    data() {
        return {
            // 裁剪组件的基础配置option
            option: {
                img: '', // 裁剪图片的地址
                info: true, // 裁剪框的大小信息
                outputSize: 0.8, // 裁剪生成图片的质量
                outputType: 'jpeg', // 裁剪生成图片的格式
                canScale: true, // 图片是否允许滚轮缩放
                autoCrop: true, // 是否默认生成截图框
                autoCropWidth: 600, // 默认生成截图框宽度
                autoCropHeight: 300, // 默认生成截图框高度
                fixedBox: false, // 固定截图框大小 不允许改变
                fixed: false, // 是否开启截图框宽高固定比例
                fixedNumber: [7, 5], // 截图框的宽高比例
                full: true, // 是否输出原图比例的截图
                canMoveBox: true, // 截图框能否拖动
                original: true, // 上传图片按照原始比例渲染
                high: true,
                centerBox: true, // 截图框是否被限制在图片里面
                infoTrue: true // true 为展示真实输出图片宽高 false 展示看到的截图框宽高
            },
            picsList: [], //页面显示的数组
            // 防止重复提交
            loading: false,
            dialogVisible: false, // 控制弹框的显示
            fileinfo: [],
            RequestSource: '', // oss直传源地址
            baseurl: '', // 裁剪后的base64地址
            isVictory: false // 识别用户是点否关闭
        };
    },
    components: { VueCropper },
    created() {
        this.$on('is-Show', this.isShow);
    },
    methods: {
        isShow(file) {
            this.fileinfo = file;
            // 传递需要剪裁的地址 以弹框形式打开
            this.$nextTick(() => {
                this.option.img = file.url;
                this.dialogVisible = true;
                this.handlePostSuccess(file.url);
            });
        },
        // 关闭图片剪裁
        cancel() {
            this.dialogVisible = false;
            // 点击取消时 去除已经回显的图片
            this.$parent.dislodge(this.fileinfo);
        },
        // 确认剪裁后触发
        finish() {
            this.$refs.cropper.getCropData(data => {
                this.baseurl = data;
                //将剪裁后base64的图片转化为file格式
                let file = this.convertBase64UrlToBlob(data);
                file.name = this.fileinfo.name;
                //let file = URL.createObjectURL(data).match(/[^blob:]+/g).join('');
                this.Directpass(this.RequestSource, file).then(res => {
                    if (res.status === 200) {
                        this.$parent.handleSuccess({
                            uid: this.fileinfo.uid,
                            imgUrl: res.config.url.split('?')[0]
                        });
                        this.dialogVisible = false;
                        this.isVictory = true;
                        this.loading = true;
                        this.$message('上传成功!');
                        // 防止用户不上传图片 点击关闭时 不能及时删除回显的问题
                        setTimeout(() => {
                            this.isVictory = false;
                        }, 1000);
                    }
                });
            });
        },
        // 上传服务主图
        handlePostSuccess(file) {
            postossImage({
                fileMd5: md5(file)
            }).then(res => {
                this.RequestSource = res.data;
            });
        },
        Directpass(url, file) {
            return new Promise((resolve, reject) => {
                axios
                    .put(url, file, {
                        headers: {
                            'x-oss-meta-author': 'deshe',
                            'Content-Type': 'application/octet-stream'
                        }
                    })
                    .then(res => {
                        resolve(res);
                    })
                    .catch(err => {
                        reject(err);
                    });
            });
        },
        // 将base64的图片转换为file文件
        convertBase64UrlToBlob(urlData) {
            let bytes = window.atob(urlData.split(',')[1]); //去掉url的头，并转换为byte
            //处理异常,将ascii码小于0的转换为大于0
            let ab = new ArrayBuffer(bytes.length);
            let ia = new Uint8Array(ab);
            for (var i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }
            return new Blob([ab], { type: 'image/jpeg' });
        }
    },
    watch: {
        dialogVisible() {
            if (!this.dialogVisible && !this.isVictory) {
                this.loading = false;
                // 点击关闭时 去除已经回显的图片
                this.$parent.dislodge(this.fileinfo);
            } else {
                this.loading = false;
            }
        }
    }
};
</script>

<style scoped lang="scss">
.cropper-content {
    .cropper {
        width: auto;
        height: 300px;
    }
}
</style>
