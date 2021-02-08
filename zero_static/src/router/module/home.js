export default [
    {
        path: '/home',
        component: resolve => require(['@/views/home'], resolve),
        name: 'Home',
        meta: {
            nav: '首页'
        }
    }
]