# clould-classify-web2.0
云彩社区云彩识别项目web部分，2.0版本，[进度：80%]
### 介绍
- 云彩识别项目一开始只是我在学深度学习的时候想为模型连一个前端，尝试部署到云服务器上，所以写了一个简陋的页面和接口
- 后来在学习springboot之后，想要把各种学到的技术点融入到这个小小的项目里
- 2023年先写了Web端的粗糙版本
- 之后，由于在网上学习的项目一般都是手把手教着做的，知识点是学会了，但是感觉没有经过太多的思考，所以想自己从构想到编码来做一个网页
- 现在，对项目进行了大幅的增删改，微信小程序端还在开发中，Web端的功能也逐渐增多
- 总之，这是一个用来学习，提高应用能力的项目
### 当前实现的功能
1. 注册登录
2. index页面和know页面
3. 论坛页面的帖子展示, 点赞，评论
4. me页面的展示
5. 云彩识别功能的文件上传，python后端模型识别，返回结果
### 新的改变
1. 加入了swagger
2. 加入动态sql的处理
3. 定义切面类，加入了JWT令牌校验，某些请求需要对用户登录状态进行校验。限定部分页面需要登录才能拿到数据
4. 自定义注解实现自动填充公共字段，create_time,update_time,create_user,update_user (仅在tb_comment表实现了)
5. 使用ThreadLocal保存用户id
6. 加入Redis缓存
7. 密码加入md5加密，加密后才存入数据库
8. 解决了点赞后数据更新不及时的问题
9. 加入登录和注册的提示信息

### 待办事项
1. 使用Redis的命令setnx实现点赞一人只能点一次
2. 加入发帖功能
3. 加入论坛页面的搜索功能
4. 加入用户me页面的


### 页面展示

![image](https://github.com/user-attachments/assets/9e83c83e-0e25-4a05-9cd1-3be6206e9e91)

![image](https://github.com/user-attachments/assets/4361fa0e-36f6-4407-8f78-dcc8b72368e6)

![image](https://github.com/user-attachments/assets/14b3be69-f1da-4c7d-a787-7649a5f3f019)


![image](https://github.com/user-attachments/assets/e4b3af90-efad-4f0d-8d75-450e9088da43)

![image](https://github.com/user-attachments/assets/f44ae434-2e3e-4672-97cd-8d0f40776055)

![image](https://github.com/user-attachments/assets/74e530f7-3fbf-4951-b737-8209ba387d28)

![image](https://github.com/user-attachments/assets/460003a9-d95d-4042-b3ef-76dccd587288)





