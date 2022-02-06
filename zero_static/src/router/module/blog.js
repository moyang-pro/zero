const blogRouter = [
    {
        path: '/blog',
        component: () => import('@/views/blog/index'),
        name: 'Blog',
        redirect: '/blog/profiles',
        children: [
            {
                path: 'profiles',
                name: 'Articles',
                component: () => import('@/views/blog/articles')
            },
            {
                path: 'write',
                name: 'WriteBlog',
                component: () => import('@/views/blog/write')
            },
            {
                path: 'edit/:id.html',
                name: 'EditBlog',
                component: () => import('@/views/blog/edit')
            },
            {
                path: 'read/:id.html',
                name: 'ReadBlog',
                component: () => import('@/views/blog/read')
            }
        ]
    }
];
export default blogRouter;
