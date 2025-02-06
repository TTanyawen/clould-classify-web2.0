- 新做一个html页面
- 功能
	- 输入：要展示第几页，每页多少结果
	- 输出：PageResult
### 导入依赖
```xml
<dependency>  
    <groupId>com.github.jsqlparser</groupId>  
    <artifactId>jsqlparser</artifactId>  
    <version>4.5</version> <!-- 使用最新稳定版本 -->  
</dependency>
```


### PageResult封装结果

```java
@Data  
@AllArgsConstructor  
@NoArgsConstructor  
public class PageResult implements Serializable {  
  
    private long total; //总记录数  
  
    private List records; //当前页数据集合  
  
}
```
### controller层

- 由于是GET，所以用RequestParam形式传参数
- 接收参数
	- page(当前查询的页号)
	- pageSize(每一页的大小)
- 返回PageResult封装的结果
	- 这样前端可以通过data.data.records拿到List数组
```java
@GetMapping("/getUsers")  
public Result<PageResult> getUsers(@RequestParam int page, @RequestParam int pageSize) {  
    PageResult pageResult = userService.getUsersPage_pageHelper(page, pageSize);  
    System.out.println("本页总共数据条数："+pageResult.getTotal());  
    return Result.success(pageResult);  
}
```

### service层

- 使用github上的PageHelper工具，在Mapper执行前加入startPage
	- 会自动在之后的sql内加上limit语句
- 调用mapper层的方法
- 返回一个`Page<User>`结果
- 将结果封装到PageResult返回
```java
public PageResult getUsersPage_pageHelper(int page, int pageSize) {  
    PageHelper.startPage(page, pageSize);  
    Page<User> pageInfo =  userMapper.selectMyPage();  
    PageResult pageResult=new PageResult(pageInfo.getTotal(),pageInfo.getResult());  
    return pageResult;  
}
```

### mapper.java
- 虽然不知道发生了什么，反正能把查询结果封装给`Page<User>`就是了

```java
Page<User> selectMyPage();
```
### mapper.xml
- 这里结尾不要加分号，等下后面还会自动添加limit语句

```xml
<select id="selectMyPage" resultMap="UserResultMap">  
    select * from tb_user
</select>
```



### 接口测试
- 测试后端接口，是没问题的，可以开始写前端
	- ![[Pasted image 20250118190445.png]]
### 前端表单发请求，接受结果，显示数据
```html
<!DOCTYPE html>  
<html lang="en">  
<head>  
  <meta charset="UTF-8">  
  <meta name="viewport" content="width=device-width, initial-scale=1.0">  
  <title>用户列表</title>  
  <style>  
    .pagination {  
      display: flex;  
      gap: 5px;  
    }  
    .pagination button {  
      padding: 5px 10px;  
    }  
  </style>  
</head>  
<body>  
  
<h1>用户列表</h1>  
  
<!-- 用户列表 -->  
<table id="userTable" border="1">  
  <thead>  
  <tr>  
    <th>用户ID</th>  
    <th>用户名</th>  
  </tr>  
  </thead>  
  <tbody class="userInfoContainer">  
  <!-- 使用Thymeleaf渲染用户列表 -->  
  <tr th:if="${users != null}" th:each="user : ${users}">  
    <td th:text="${user.userId}"></td>  
    <td th:text="${user.userName}"></td>  
  </tr>  
  </tbody>  
</table>  
  
<div class="pageChoose">  
  <!-- 表单提交 -->  
  <form id="pageSearchForm">  
    <label for="page">要展示第几页：</label>  
    <input type="number" id="page" name="page" min="1" >  
    <input type="number" id="pageSize" name="pageSize" min="3" max="10">  
    <button type="submit">提交</button>  
  </form>  
</div>  
  
<script>  
  
  document.getElementById('pageSearchForm').addEventListener('submit', async function(event) {  
    event.preventDefault(); // 阻止默认表单提交  
    console.log("点击提交");  
  
    const page = document.getElementById('page').value;  
    const pageSize = document.getElementById('pageSize').value;  
  
    try {  
      // 构建带参数的 URL      
      const queryString = new URLSearchParams({ page, pageSize }).toString();  
  
      const url = `/getUsers?${queryString}`;  
      console.log(url);  
      // 发送 Fetch 请求  
      const response = await fetch(url, {  
        method: 'GET',  
        headers: {  
          'Content-Type': 'application/json', // 指定请求为 JSON 格式  
        },  
      });  
  
      const data = await response.json();  
  
      if (data.code === 1 && data.data) {  
        const userInfoContainer = document.querySelector('.userInfoContainer');  
        userInfoContainer.innerHTML = ''; // 清空旧数据  
  
        data.data.records.forEach(user => {  
          const userItem = document.createElement('tr');  
          userItem.classList.add('userItem');  
          userItem.innerHTML = `  
                    <td>${user.userId}</td>  
                    <td>${user.userName}</td>  
                `;  
          userInfoContainer.appendChild(userItem);  
        });  
      } else {  
        alert("获取用户数据失败或无数据");  
      }  
    } catch (error) {  
      console.error("请求用户数据失败", error);  
      alert("请求用户数据失败");  
    }  
  });  
  
</script>  
</body>  
</html>
```