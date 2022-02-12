import Vue from 'vue';
import VueRouter from 'vue-router';
/* Layout */
// import Layout from '@/layout'
import homeRoute from '@/router/module/home';
import blogRoute from '@/router/module/blog';

Vue.use(VueRouter);
/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */
const constantRoutes = [
    {
        path: '/',
        name: 'Main',
        redirect: '/home'
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/login')
    },
    {
        path: '/help',
        name: 'Help',
        component: () => import('@/views/help')
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('@/views/register/index'),
        redirect: '/register/emy-account',
        children: [
            {
                path: 'wx',
                name: 'WxRegister',
                component: () => import('@/views/register/wx')
            },
            {
                path: 'email',
                name: 'EmailRegister',
                component: () => import('@/views/register/email')
            },
            {
                path: 'emy-account',
                name: 'AccountRegister',
                component: () => import('@/views/register/account')
            }
        ]
    },
    ...homeRoute,
    ...blogRoute,
    {
        path: '/404.html',
        component: () => import('@/views/error-page/404'),
        hidden: true
    },
    // 404 page must be placed at the end !!!
    { path: '*', redirect: '/404.html', hidden: true }
];

/**
 * 解决报错问题：报错显示是路由重复
 * Error: Avoided redundant navigation to current location
 */
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err);
};

const createRouter = () =>
    new VueRouter({
        // mode: 'history', // require service support
        scrollBehavior: () => ({ y: 0 }),
        routes: constantRoutes
    });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter();
    router.matcher = newRouter.matcher; // reset router
}

export default router;
