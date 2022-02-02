const homeRouter = [
    {
        path: '/home',
        component: () => import('@/views/home'),
        name: 'Home',
        meta: {
            nav: '首页'
        }
    }
];
export default homeRouter;
