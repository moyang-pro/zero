#!/usr/bin/env bash

echo '=====开始搭建ZERO环境====='

echo '=====开始运行mysql====='
docker-compose -f /zero/docker-compose/project/mysql/mysql.yml up -d
echo '=====mysql正在进行初始化====='
echo "=====mysql已经准备就绪====="

echo '=====开始运行redis====='
docker-compose -f /zero/docker-compose/project/redis/redis.yml up -d

echo '=====开始运行nginx====='
docker-compose -f /zero/docker-compose/project/nginx/nginx.yml up -d

echo '=====开始运行zero-blog====='
docker-compose -f /zero/docker-compose/project/zero-blog/zero_blog.yml up -d


echo '================================================================='
echo '=====【ZERO 项目 启动成功】====='
echo '================================================================='
