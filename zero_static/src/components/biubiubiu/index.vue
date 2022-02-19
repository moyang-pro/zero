<template>
    <div>
        <!-- 点击biubiubiu小心心  start-->
        <div
            v-for="(item, index) in loves"
            v-bind:key="index"
            v-bind:ref="loves[index].count"
            class="img"
            v-bind:style="item.top + item.left + item.scale + item.opacity"
        >
            <div class="left" v-bind:style="item.childBgc"></div>
            <div class="right" v-bind:style="item.childBgc"></div>
            <div class="under" v-bind:style="item.childBgc"></div>
            <div class="text">{{ text[loves[index].textIndex] }}</div>
        </div>
        <!-- 点击biubiubiu小心心  stop-->
    </div>
</template>

<script>
export default {
    data() {
        const text = ['xxx~', '我喜欢你~', '我爱你~', '哎呀呀~', 'mua~', '大美女', '想你', '咦~'];
        const loves = [];
        const bubbles = [];
        // count:1,top:'200px',left:'200px',translateY:'-40px',transform:0.5,opacity:0.5
        return {
            text: text, //小心心上边显示的文字
            textIndex: 0, //小心心数组下标
            loves: loves, //小心心样式
            count: 0, //创建元素个数
            bubbles: bubbles
        };
    },
    methods: {
        // 创建小心心
        createLoves(event) {
            let x = 0;
            let y = 0;
            let index = 0; //text文本下标
            let color =
                'background-color:rgb(' +
                parseInt(Math.random() * 255) +
                ',' +
                parseInt(Math.random() * 255) +
                ',' +
                parseInt(Math.random() * 255) +
                ');';
            index = Math.floor(Math.random() * this.text.length);
            // alert(this.$el);
            x = event.clientX - 10;
            y = event.clientY - 17;
            // alert(event.clientX);

            let style = {
                count: 'img' + ++this.count, //
                top: 'top:' + y + 'px;',
                left: 'left:' + x + 'px;',
                opacity: 'opacity:' + 0.5 + ';',
                childBgc: color,
                textIndex: index,
                scale: 'transform: scale(' + 1 + ');'
                // scale : 'left:' + 1
            };
            this.loves.push(style);
        },
        removeSmallHeart() {
            (this.loves[this.count - 1].opacity = 'opacity:' + 0 + ';'),
                (this.loves[this.count - 1].scale = 'transform:translateY(' + -80 + 'px) scale(' + 0.5 + ');');
        }
    }
};
</script>

<style>
/* biubiubiu小心心 start */
.img {
    width: 20px;
    height: 20px;
    opacity: 1;
    position: absolute;
    z-index: 1000;
    transition: 2.5s;
    float: left;
}
.left,
.right {
    width: 10px;
    height: 10px;
    border-radius: 100%;
    position: absolute;
}
.right {
    right: 0;
}
.under {
    width: 10px;
    height: 10px;
    position: absolute;
    top: 5px;
    left: 5px;
    transform: rotate(45deg);
}
.text {
    width: 50px;
    font-size: 10px;
    line-height: 1;
    position: absolute;
    top: -1em;
    left: -15px;
    text-align: center;
}

/* biubiubiu小心心 stop */
</style>
