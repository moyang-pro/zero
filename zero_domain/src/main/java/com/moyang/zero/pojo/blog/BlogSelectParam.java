package com.moyang.zero.pojo.blog;

import lombok.Data;

/**
 * @Author: moyang
 * @ClassName: BlogSelectParam
 * @Date: 2022/2/27 23:34
 * @Description: 博客查询参数
 * @Version: V1.0
 **/
@Data
public class BlogSelectParam {

     private String author;

     private String sortColumn;

     private boolean isDesc;
}
