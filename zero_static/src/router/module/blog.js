const blogRouter = [
    {
        path: '/blog',
        component: () => import('@/views/blog/index'),
        name: 'Blog',
        redirect: '/blog/articles',
        children: [
            {
                path: 'articles',
                name: 'Articles',
                component: () => import('@/views/blog/articles')
            },
            {
                path: 'write',
                name: 'WriteBlog',
                component: () => import('@/views/blog/write')
            },
            {
                path: 'read',
                name: 'ReadBlog',
                component: () => import('@/views/blog/read')
            }
        ]
    }
];
export default blogRouter;
