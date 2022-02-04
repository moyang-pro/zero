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

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
