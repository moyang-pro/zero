import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// 使用element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import 'github-markdown-css';

// main中引入语法高亮配置
import Highlight from './utils/blog/highlight';
Vue.use(Highlight); // 导入代码高亮文件

import 'katex';
import 'normalize.css/normalize.css'; // A modern alternative to CSS resets
import '@/icons'; // icon
import '@/permission'; // permission control
import '@/styles/index.scss'; // global css
Vue.use(ElementUI);

// use
Vue.use(mavonEditor);

// 引入echarts 新的引入方式 eChart5
import * as echarts from 'echarts';
Vue.prototype.$echarts = echarts;

//按需引入 ant-design-vue 组件
import { Timeline, Statistic } from 'ant-design-vue';
Vue.use(Timeline).use(Statistic);

import 'wowjs/css/libs/animate.css';

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
