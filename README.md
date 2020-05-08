# 版本更新

使用Dockerfile

mvn clean package -P prod dockerfile:build

mvn dockerfile:build
请检查Expose daemon on tcp://localhost:2375 without TLS

## 如果出现错误
2017-12-13 05:42:34.428 ERROR 1 --- [nio-8080-exec-4] o.h.e.j.s.SqlExceptionHelper             : Expression #2 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'coinnewsblog.article0_.cate_name' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by

## 解决
去掉only_full_group_by
set global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
