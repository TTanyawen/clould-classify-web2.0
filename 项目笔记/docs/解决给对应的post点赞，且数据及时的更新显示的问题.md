### 知识点
- 看数据是否被正确解析，就到网页的检查处
	- ![[Pasted image 20250113172102.png]]
- 两种方法：
	- foreach找(见代码)
	- 一步到位
		- `const postLikeElement = document.querySelector(`.post_like[ddd='${postId}'] .like_num_post`);`
- 没法正确解析的问题
	- 属性的绑定需要加上th:attr
	- th:attr="ddd=${post.postId}“
### html代码
```html
<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <title>CloudForum</title>  
    <link rel="stylesheet" href="/css/style.css">  
    <style>  
        a {  
            text-decoration: none; /* 去除下划线 */        }  
        html, body {  
            height: 100%;  
            margin: 0;  
            padding: 0;  
        }  
  
        /* 创建一个整体的容器，使用flex布局 */        .container { }  
  
        /* 头部区域 */        header {  
            background-color: var(--header-color);  
            height: 60px;  
            width: 100%;  
            color: white;  
            text-align: center;  
            line-height: 60px;  
        }  
  
        header .content {  
            margin: auto;  
            width: 800px;  
            height: 100%;  
        }  
  
        header .logo {  
            float: left;  
            margin-top: 5px;  
        }  
  
        header .logo img {  
            height: 50px;  
        }  
  
        header .block {  
            float: right;  
            margin-left: 20px;  
            width: 100px;  
            background-color: var(--header-block-color);  
        }  
        header .block a{  
            font-size: 20px;  
            color: var(--header-bolck-fcolor);  
            font-weight: bolder;  
        }  
        /*hover*/  
        header .block :hover{  
            float: right;  
            margin-left: 20px;  
            width: 100px;  
            background-color: var(--header-block-hover-color);  
        }  
        /* 激活状态 */        .block.active {  
            background-color: var(--header-block-hover-color);  
            color: white;  
        }  
        /* 中间内容部分，指定固定的长宽并居中 */        main {  
            margin: auto;  
            background-color: #f8f8f8;  
            width: 800px;  
        }  
  
  
        main .mainTitle{  
            height: 100px;  
            width: 800px;  
            margin: auto;  
            display: flex;  
            justify-content: center; /* 水平居中对齐 */            align-items: center;     /* 垂直居中对齐 */            text-align: center;  
            font-size: 50px;  
            font-weight: bolder;  
            background-color: var(--main-title-color);  
            color:var(--main-title-fcolor);  
        }  
        main .post{  
            height: 2500px;  
            width: 800px;  
            margin-top: 20px;  
            background-color: #e7edf0;  
        }  
  
        main .post_item  
       {  
            float: left;  
            height: 500px;  
            width: 350px;  
            margin-top: 30px;  
            margin-left: 30px;  
            border-radius: 10%;  
            overflow-y: scroll;  
            background-color: #617aa5;  
  
        }  
        main .post_item::-webkit-scrollbar  
        {  
            display: none; /* 对于 WebKit 浏览器 */        }  
        main .post_item .meinfo .profile_pic{  
  
            float: left;  
            height: 50px;  
            width: 50px;  
            margin-left: 20px;  
            margin-top: 20px;  
            /*background: #53e4bd url('/images/profiles/alice.jpg') no-repeat center center;*/  
            clip-path: circle(50%);  
        }  
        main .post_item .meinfo .profile_name{  
            float: left;  
            margin-left: 20px;  
            margin-top: 20px;  
            height: 50px;  
            line-height: 50px;  
            width: 150px;  
            font-size: 18px;  
            color: white;  
            /*background-color: #eee9e9;*/  
        }  
        main .post_item .post_pic{  
            float: left;  
            height: 280px;  
            width: 310px;  
            margin-left: 20px;  
            margin-top: 20px;  
            background: #53e4bd url('/images/posts/post1.jpg') no-repeat center center;  
            border-radius: 10%;  
        }  
        main .post_item .post_text{  
            float: left;  
            height: 50px;  
            width: 310px;  
            margin-left: 20px;  
            margin-top: 20px;  
            background-color: rgba(242, 251, 251, 0.56);  
        }  
        main .post_item .post_comment{  
            float: left;  
            height: 20px;  
            width: 200px;  
            margin-left: 20px;  
            margin-top: 20px;  
            margin-bottom: 20px;  
            background-color: rgba(242, 251, 251, 0.56);  
        }  
        main .post_item .post_like{  
            float: right;  
            height: 20px;  
            width: 100px;  
            margin-right: 20px;  
            margin-top: 20px;  
            /*background-color: #cce9d0;*/  
            color: white;  
        }  
  
        main .post_item .post_like .like_num_post{  
            float: right;  
            height: 20px;  
            width: 40px;  
        }  
        main .post_item .post_like .like_btn{  
            float: left;  
            height: 20px;  
            width: 20px;  
            border-radius: 50%;  
            background-color: rgba(109, 200, 227, 0.52);  
            cursor: pointer; /* 添加此属性以更改鼠标样式 */  
        }  
  
        /*评论部分*/  
        main .comment_section .comment-item{  
            float: left;  
            height: 140px;  
            width: 310px;  
            margin-left: 20px;  
            margin-top: 20px;  
            border-radius: 8%;  
            background-color: rgba(242, 251, 251, 0.17);  
        }  
        main .comment_section .userinfo .profile_pic{  
  
            float: left;  
            height: 30px;  
            width: 30px;  
            margin-left: 10px;  
            margin-top: 10px;  
            /*background: #53e4bd url('/images/profiles/alice.jpg') no-repeat center center;*/  
            clip-path: circle(50%);  
        }  
        main .comment_section .userinfo .profile_name{  
            float: left;  
            margin-left: 10px;  
            margin-top: 10px;  
            height: 30px;  
            line-height: 30px;  
            width: 150px;  
            font-size: 14px;  
            /*background-color: #eee9e9;*/  
            color: white;  
        }  
        main .comment_section .comment_text{  
            float: left;  
            height: 50px;  
            width: 280px;  
            margin-left: 10px;  
            margin-top: 10px;  
            font-size: 16px;  
            background-color: rgba(255, 255, 255, 0.31);  
        }  
        main .comment_section .comment_like{  
            float: right;  
            height: 20px;  
            width: 100px;  
            margin-right: 10px;  
            margin-top: 10px;  
            /*background-color: #cce9d0;*/  
            color: white;  
        }  
        main .comment_section .comment_like .like_btn{  
            float: left;  
            height: 20px;  
            width: 20px;  
            border-radius: 50%;  
            background-color: rgba(109, 200, 227, 0.52);  
            cursor: pointer; /* 添加此属性以更改鼠标样式 */        }  
  
        /* 底部区域 */        footer {  
            background-color:  var(--footer-color);  
            height: 300px;  
            width: 100%;  
            text-align: center;  
            line-height: 60px;  
            margin-top: auto;  
            padding-top: 50px;  
        }  
        footer .creatorLogo {  
            width: 100%;  
            height: 70px;  
            /*margin-left:auto;*/  
            /*margin-top: 20px;*/            /*background-color: #fff;*/        }  
  
        footer .creatorLogo img {  
            height: 70px;  
        }  
        footer .creatorInfo{  
            height: 100px;  
            width: 100%;  
            margin-top: 20px;  
            text-align: center;  
            line-height: 20px;  
            font-size: 25px;  
            font-weight: lighter;  
            color: var(--footer-fcolor);  
            /*background-color: rgba(255, 255, 255, 0.51);*/  
        }  
    </style>  
</head>  
<body>  
<div class="container">  
    <header>  
        <div class="content">  
            <div class="logo">  
                <a href="/index">  
                    <img src="/images/logo.png" alt="Cloud Website Logo">  
                </a>  
            </div>  
            <div id="me" class="block">  
                <a href="/me">me</a>  
            </div>  
            <div id="classify" class="block">  
                <a href="/classify">classify</a>  
            </div>  
            <div id="forum" class="block">  
                <a href="/forum">forum</a>  
            </div>  
            <div id="know" class="block">  
                <a href="/know">know</a>  
            </div>  
        </div>  
    </header>  
    <main>  
        <div class="mainTitle">Forum</div>  
        <div class="post">  
            <div class="post_item" th:each="post:${posts}">  
                <div class="meinfo">  
                    <div class="profile_pic" th:style="'background: #53e4bd url(' + '\'' + '/images/profiles/' + ${post.userId} + '.jpg'+'\'' + ') no-repeat center center;'"></div>  
                    <div class="profile_name" th:text="${post.userName}"></div>  
                    <div class="post_pic" th:style="'background: #53e4bd url(' + '\'' + '/images/' + ${post.postImgPath} + '\'' + ') no-repeat center center;'"></div>  
                    <div class="post_text" th:text="${post.postText}"></div>  
                    <div class="post_comment" th:if="${session.userLogin}">  
<!--                        <input type="text" style="border: none; background-color: transparent; outline: none;">-->  
<!--                        <form action="/submitComment" method="POST">-->  
<!--                            <input type="text" name="content" style="border: none; background-color: transparent; outline: none;">-->  
<!--&lt;!&ndash;                            <button type="submitComment">提交</button>&ndash;&gt;-->  
<!--                        </form>-->  
                        <form action="/submitComment" method="POST" >  
                            <input type="hidden" name="userId" th:value="${session.userLogin.getUserId()}">  <!-- 用户ID -->  
                            <input type="hidden" name="postId" th:value="${post.postId}">  <!-- 帖子ID -->  
<!--                            <input type="text" name="commenttext" placeholder="Write your comment">-->  
                            <input type="text" name="commentText" style="border: none; background-color: transparent; outline: none;" placeholder="Write your comment">  
  
                            <button type="submit">提交评论</button>  
                        </form>  
  
  
                    </div>  
  
  
<!--                    <div class="post_like" data-postId="${post.postId}">-->  
                    <div class="post_like" th:attr="ddd=${post.postId}">  
  
<!--                        <div class="like_btn" th:onclick="likePost(${post.postid})"></div>-->  
  
                        <div class="like_btn" th:attr="onclick=|likePost('${post.postId}')|">👍</div>  
                        <div class="like_num_post" th:text="'   '+${post.postLike}"></div>  
                    </div>  
  
                </div>  
  
                <div class="comment_section">  
                    <div class="comment-item" th:each="comment:${comments}" th:if="${comment.postId} == ${post.postId}">  
                        <div class="userinfo">  
                            <div class="profile_pic" th:style="'background: #53e4bd url(' + '\'' + '/images/profiles/' + ${comment.userId} + '.jpg'+'\'' + ') no-repeat center center;'"></div>  
                            <div class="profile_name" th:text="${comment.userName}"></div>  
                            <div class="comment_text" th:text="${comment.commentText}"></div>  
                            <div class="comment_like" th:attr="data-commentId=${comment.commentId}">  
                                <div class="like_btn" th:attr="onclick=|likeComment('${comment.commentId}')|">👍</div>  
                                <div class="like_num_comment" th:text="${comment.commentLike}"></div>  
                            </div>  
                        </div>  
                    </div>  
                </div>  
  
  
            </div>  
        </div>  
    </main>  
    <footer>  
        <div class="creatorLogo">  
            <img src="/images/creatorLogo.jpg">  
        </div>  
        <div class="creatorInfo">  
            <p>CREATOR:TAN</p>  
            <p>EMAIL:tanyawen2640@gmail.com</p>  
            <p>PHONE:13527800350</p>  
        </div>  
    </footer>  
</div>  
<script>  
    // JavaScript 示例  
    window.onload = function() {  
        // 获取当前页面的文件名  
        const currentPage = window.location.pathname.split("/").pop().replace('.html', '');  
  
        // 根据文件名给对应的块添加 active 类  
        const activeBlock = document.getElementById(currentPage);  
        if (activeBlock) {  
            activeBlock.classList.add('active');  
        }  
    };  
  
  
  
  
    //点赞  
    function likePost(postId) {  
        console.log("likePost")  
        fetch('/likePost/' + postId, {  
            method: 'POST',  
            headers: {  
                'Content-Type': 'application/json'  
            }  
        })  
            .then(response => response.json())  
            .then(data => {  
                if (data.success) {  
                    // 更新点赞数显示，假设 class="post_like" 是显示点赞数的元素  
                    // document.querySelector('.like_num_post').textContent =  data.newLikeCount;  
                    const newLikeCount=data.newLikeCount  
                    console.log("newLikeCount"+newLikeCount)  
                    const postLikeElement = document.querySelector(`.post_like[ddd='${postId}'] .like_num_post`);  
                    // const likeNumElements = document.querySelector('main .post_item .post_like .like_num_post');  
                    const likeNumElements = document.querySelectorAll('.post_like');  
                    // likeNumElements.forEach((likeElement) => {  
                    //         const nowPostId = likeElement.getAttribute('ddd');                    //         console.log(nowPostId+","+postId)                    //         // 如果当前的postId与目标postId匹配  
                    //         if (postId === nowPostId) {  
                    //             const likeNumElement = likeElement.querySelector('.like_num_post');                    //                    //             // 修改该postId对应的like_num_post文本  
                    //             if (likeNumElement) {  
                    //                 console.log("更新post点赞数据")  
                    //                 likeNumElement.textContent = newLikeCount;  // 设置新的点赞数  
                    //             }else{  
                    //                 console.log("没有likeNumElement")  
                    //             }                    //         }                    //     });                    if (postLikeElement) {  
                        console.log("更新post点赞数据")  
                        postLikeElement.textContent = newLikeCount;  
                    }else{  
                        console.log("没有postLikeElement")  
                    }  
                } else {  
                    alert('Failed to like the post');  
                }  
            })  
            .catch(error => console.error('Error:', error));  
    }  
  
  
    //点赞  
    function likeComment(commentId) {  
        console.log("likeComment")  
        fetch('/likeComment/' + commentId, {  
            method: 'POST',  
            headers: {  
                'Content-Type': 'application/json'  
            }  
        })  
            .then(response => response.json())  
            .then(data => {  
                if (data.success) {  
                    // 更新点赞数显示，假设 class="post_like" 是显示点赞数的元素  
                    // document.querySelector('.like_num_comment').textContent =  data.newLikeCount;  
                    const commentLikeElement = document.querySelector(`.comment_like[data-commentId='${commentId}'] .like_num_comment`);  
                    if (commentLikeElement) {  
                        commentLikeElement.textContent = data.newLikeCount;  
                    }  
                } else {  
                    alert('Failed to like the post');  
                }  
            })  
            .catch(error => console.error('Error:', error));  
    }  
</script>  
</body>  
</html>
```