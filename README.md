# snow
# 评论加图限制4张
# 数据库设计
# 数据库地址：43.143.241.133/snowschema  
# 用户：bikini   密码：bikini

## 数据表

列名使用小驼峰命名法

所有id和status都是int，其他都是varchar，time是Data，value是float


#### user

userID (nn), userName, (nn) user Status (nn), password（密文）(nn),  userImg\*, userMessage

#### canteen

canteenID(nn), canteenName(nn), canteenStatus(nn), canteenMessage\*, canteenImg\*

#### window

windowID(nn), windowName(nn), windowStatus(nn), windowMessage\*, windowFloor(nn), canteenID(nn),/*加个windowImg*/

#### comment

commentID(nn), commentName, commentStatus(nn),  commentTime(nn), commentText(nn), commentRate, userID, /*增加windowID*/

#### dish

dishID(nn), dishName(nn), dishStatus(nn), windowID(nn), dishValue(nn)

#### 推荐窗口信息

？/*不存储窗口推荐信息，通过计算实现*/

#### 推荐饭搭信息

？/*实时计算*/

#### 窗口流量与预测消息

？windowData

# 前后端数据接口

## 一、说明

- 不需要登录权限的url前缀是`/api`，需要登录权限的url前缀是`/user`

所有数据都打包成该格式
```js
ResponseData={
    status,//状态码，默认正常是0，未登录错误是1，默认错误是2
    data,//数据
    message,//消息，比如请求成功，请求失败，参数非法，密码错误等提示信息
}
```

Mapper方法命名规范：（行为）（目标(s)）By（特征）（And特征）*
如： getCommentsByOrderAndPage

service方法命名规范：url小驼峰



## 二、API列表

### 1、无需权限的API

#### 1）账号

##### 注册

URL: `api/register`

```js
params={
    userName,
    ...
}
data={}
```

##### 登录

URL: `api/login`

```js
params={
    userID,
    password
}
data={}
```

##### 获取登录状态

URL: `api/get_login_info`

```js
```



#### 2）窗口

##### 获取所有窗口[]

URL:  `api/windows_list`

```js
params={}
data={
    windowsList:[
        {   
            windowID,
            windowName,
            canteenName,
            windowFloor,
            windowStatus,
        }
    ]
}
```

##### 获取食堂-窗口对象[]

URL: api/objects_list

```js
params={}
data={
    list:[
        {
            canteenID,
            canteenName,
            children:[
                {
                    windowfloor,
                    children:[
                        {
                            windowID,
                            windowName,
                        }
                    ]
                }
            ]
        }
    ]
}
```

##### 获取窗口详细信息[]

URL:  `api/window_detail`

```js
params={
    windowID,//NotNULL
}
data={
    windowID,
    windowName,
    canteenName,
    windowFloor,
    windowStatus,
    peopleTimeData:[
        {
        time,
        number,
        }
    ]
}
```

##### 获取推荐窗口[]

URL:  `api/windows`

```js
params={}
data={
    windowsList:[
        {   
            windowID,
            windowName,
            canteenName,
            windowFloor,
            windowStatus,
        }
    ]
}
```

#### 3）评论

##### 获取评论[]

URL:  `api/comments`

```js
params={
    order,//默认是'comment_time'
    desc,//默认是true
    page,//默认是1
}
data={
    commentID,
    commentTime,
    commentText,
    commentRate,
    userID,
    userName,
    userImg,
}
```

##### 获取指定窗口的评论[]

URL:  `api/window_comments`

```js
params={
    windowsID,
    order,//默认是'comment_time'
    desc,//默认是true
    page,//默认是1
}
data={


    data={
    commentID,
    commentTime,
    commentText,
    commentRate,
    userID,
    userName,
    userImg,
}
}
```



### 2、需要权限的API


个人信息

个性化推荐

找饭搭子

发表评论
{

}

