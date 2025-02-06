- 向tb_comment表加入两个字段
	- private Long createUser;  ->create_user
	- private Long updateUser; ->update_user
- 修改mapper里的sql语句与映射
- 自动注入的切面类处加入有关id的注入


```sql
DROP TABLE IF EXISTS tb_comment;
CREATE TABLE tb_comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    user_id  BIGINT NOT NULL COMMENT '外键',
    post_id BIGINT NOT NULL COMMENT '外键',
    comment_text VARCHAR(1024),
    comment_like BIGINT,
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    create_user BIGINT DEFAULT NULL COMMENT '创建用户',
    update_user BIGINT DEFAULT NULL COMMENT '更新用户',
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    FOREIGN KEY (post_id) REFERENCES tb_post(post_id)
);

INSERT INTO tb_comment (user_id, post_id, comment_text, comment_like, create_time, update_time, create_user, update_user) VALUES
(1, 1, 'Great post! Really enjoyed it.', 45, '2024-01-01 10:00:00', '2024-01-01 12:00:00', 1, 1),
(2, 1, 'Thanks for sharing!', 30, '2024-01-01 11:00:00', '2024-01-01 12:30:00', 2, 2),
(3, 1, 'Amazing picture!', 60, '2024-01-01 11:15:00', '2024-01-01 13:00:00', 3, 3),
(4, 1, 'Well said!', 25, '2024-01-01 11:30:00', '2024-01-01 13:30:00', 4, 4),
(5, 1, 'This is so inspiring!', 70, '2024-01-01 12:00:00', '2024-01-01 14:00:00', 5, 5),
(2, 1, 'Love the vibes!', 35, '2024-01-01 12:15:00', '2024-01-01 14:30:00', 2, 2),
(3, 1, 'Beautiful scenery!', 50, '2024-01-01 12:30:00', '2024-01-01 15:00:00', 3, 3),
(8, 4, 'Such a great shot!', 40, '2024-01-01 13:00:00', '2024-01-01 15:30:00', 8, 8),

(1, 2, 'Looks like fun!', 55, '2024-01-02 10:00:00', '2024-01-02 12:00:00', 1, 1),
(2, 2, 'Wish I was there!', 40, '2024-01-02 11:00:00', '2024-01-02 12:30:00', 2, 2),
(3, 2, 'Perfect beach day!', 65, '2024-01-02 11:15:00', '2024-01-02 13:00:00', 3, 3),
(4, 2, 'So relaxing!', 45, '2024-01-02 11:30:00', '2024-01-02 13:30:00', 4, 4),
(5, 2, 'The ocean looks amazing!', 80, '2024-01-02 12:00:00', '2024-01-02 14:00:00', 5, 5),
(1, 2, 'Great memories!', 35, '2024-01-02 12:15:00', '2024-01-02 14:30:00', 1, 1),
(2, 2, 'Thanks for the share!', 25, '2024-01-02 12:30:00', '2024-01-02 15:00:00', 2, 2),
(3, 2, 'Can’t wait to go there!', 60, '2024-01-02 13:00:00', '2024-01-02 15:30:00', 3, 3),

(1, 3, 'Adventure awaits!', 70, '2024-01-03 10:00:00', '2024-01-03 12:00:00', 1, 1),
(2, 3, 'Exciting times ahead!', 55, '2024-01-03 11:00:00', '2024-01-03 12:30:00', 2, 2),
(3, 3, 'Safe travels!', 45, '2024-01-03 11:15:00', '2024-01-03 13:00:00', 3, 3),
(4, 3, 'Have fun!', 50, '2024-01-03 11:30:00', '2024-01-03 13:30:00', 4, 4),
(5, 3, 'Looking forward to the stories!', 65, '2024-01-03 12:00:00', '2024-01-03 14:00:00', 5, 5),
(1, 3, 'New beginnings!', 35, '2024-01-03 12:15:00', '2024-01-03 14:30:00', 1, 1),
(2, 3, 'This is so cool!', 40, '2024-01-03 12:30:00', '2024-01-03 15:00:00', 2, 2),
(3, 3, 'Best of luck!', 55, '2024-01-03 13:00:00', '2024-01-03 15:30:00', 3, 3),

(1, 4, 'Looks amazing!', 60, '2024-01-04 10:00:00', '2024-01-04 12:00:00', 1, 1),
(2, 4, 'Can’t wait to visit!', 45, '2024-01-04 11:00:00', '2024-01-04 12:30:00', 2, 2),
(3, 4, 'So beautiful!', 75, '2024-01-04 11:15:00', '2024-01-04 13:00:00', 3, 3),
(4, 4, 'You’re in paradise!', 65, '2024-01-04 11:30:00', '2024-01-04 13:30:00', 4, 4),
(5, 4, 'Living the dream!', 85, '2024-01-04 12:00:00', '2024-01-04 14:00:00', 5, 5),
(1, 4, 'Take me there!', 55, '2024-01-04 12:15:00', '2024-01-04 14:30:00', 1, 1),
(2, 4, 'Absolutely stunning!', 50, '2024-01-04 12:30:00', '2024-01-04 15:00:00', 2, 2),
(3, 4, 'I’m so jealous!', 40, '2024-01-04 13:00:00', '2024-01-04 15:30:00', 3, 3),

(1, 5, 'What’s the book?', 70, '2024-01-05 10:00:00', '2024-01-05 12:00:00', 1, 1),
(2, 5, 'Sounds interesting!', 60, '2024-01-05 11:00:00', '2024-01-05 12:30:00', 2, 2),
(3, 5, 'I need to read it too!', 55, '2024-01-05 11:15:00', '2024-01-05 13:00:00', 3, 3),
(4, 5, 'Would love to hear more!', 45, '2024-01-05 11:30:00', '2024-01-05 13:30:00', 4, 4),
(5, 5, 'Books are the best!', 75, '2024-01-05 12:00:00', '2024-01-05 14:00:00', 5, 5),
(1, 5, 'Can you recommend it?', 65, '2024-01-05 12:15:00', '2024-01-05 14:30:00', 1, 1),
(2, 5, 'Looks like a good read!', 50, '2024-01-05 12:30:00', '2024-01-05 15:00:00', 2, 2),
(3, 5, 'Adding it to my list!', 60, '2024-01-05 13:00:00', '2024-01-05 15:30:00', 3, 3);

```


![[Pasted image 20250118142455.png]]
![[Pasted image 20250118142628.png]]
![[Pasted image 20250118142831.png]]

- BaseContext

```java
package com.example.java7_4.context;  
  
public class BaseContext {  
  
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();  
  
    public static void setCurrentId(Long id) {  
        threadLocal.set(id);  
    }  
  
    public static Long getCurrentId() {  
        return threadLocal.get();  
    }  
  
    public static void removeCurrentId() {  
        threadLocal.remove();  
    }  
  
}
```


![[Pasted image 20250118143157.png]]

- BaseContext在拦截器的preHandle方法处设置id，这就会要求在拦截器内能获取到ld，所以生成jwt令牌的时候要使用到userId才行
	- ![[Pasted image 20250118145814.png]]
	- 从token中使用密钥获得claims
	- 从claims中获取userId

- 效果展示
	- ![[Pasted image 20250118145934.png]]