# 区块链快讯

使用Dockerfile

mvn clean package -P prod dockerfile:build

mvn dockerfile:build
请检查Expose daemon on tcp://localhost:2375 without TLS

## 如果出现错误
2017-12-13 05:42:34.428 ERROR 1 --- [nio-8080-exec-4] o.h.e.j.s.SqlExceptionHelper             : Expression #2 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'coinnewsblog.article0_.cate_name' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by

## 解决
去掉only_full_group_by
set global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

## API信息

// 获取分类列表
https://news.block18.io/public/listCategories
// 页头信息
https://news.block18.io/public/toppic
// 获取当天的快讯文章
https://news.block18.io/public/newsList/page
// 获取文章
https://news.block18.io/public/articleList/cateid/page
// 文章详情
https://news.block18.io/public/detail/id

// 获取百科
https://news.block18.io/public/encyclopediaList/8/page
// 获取研究报告
https://news.block18.io/public/reportList/9/page
// 获取视频
https://news.block18.io/public/videoList/7/page

## 版本信息
1.7.0 版本情况
主要添加百科、研究报告和视频等相关功能