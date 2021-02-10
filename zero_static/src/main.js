import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 使用element ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'
Vue.use(ViewUI)

Vue.config.productionTip = false

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import '@/icons' // icon
// import '@/permission' // permission control
import '@/styles/index.scss' // global css

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
