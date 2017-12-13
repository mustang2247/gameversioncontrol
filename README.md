# gameversioncontrol
This is game version control server.

# 游戏版本控制
采用Spring boot + jdbc开发

## 目前已经完成
1、http请求配置文件；
2、采用http修改和添加配置文件；
3、实现redis读取和写入；
4、增加热部署功能。

## future（计划）
1、整合apache shiro实现权限管理；
2、完善后台管理；
3、根据需求添加。

## build
mvn clean package -P qa


## 增加更新提示
data["productId"] = sdkConfig.channelInfo.productId;
data["id"] = sdkConfig.channelInfo.id;
data["packageName"] = sdkConfig.channelInfo.packageName;
data["channel"] = sdkConfig.channelInfo.channel;
data["version"] = version;
string requsetValue = data.ToJson();

HTTPRequest requset = new HTTPRequest(new Uri(serverUrl + "?" + "value=" + requsetValue));

