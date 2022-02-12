const blogRouter = [
    {
        path: '/blog',
        component: () => import('@/views/blog/index'),
        name: 'Blog',
        redirect: '/blog/home',
        children: [
            {
                path: 'profile/:emy',
                name: 'UserProfile',
                component: () => import('@/views/blog/profile/index')
            },
            {
                path: 'write',
                name: 'WriteBlog',
                component: () => import('@/views/blog/write/index')
            },
            {
                path: 'edit/:id.html',
                name: 'EditBlog',
                component: () => import('@/views/blog/edit/index')
            },
            {
                path: 'read/:id.html',
                name: 'ReadBlog',
                component: () => import('@/views/blog/read/index')
            }
        ]
    }
];
export default blogRouter;
