import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// 使用element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import 'normalize.css/normalize.css'; // A modern alternative to CSS resets
import '@/icons'; // icon
import '@/permission'; // permission control
import '@/styles/index.scss'; // global css
Vue.use(ElementUI);

Vue.use(ViewUI);

// use
Vue.use(mavonEditor);

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
