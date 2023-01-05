
```sql
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

insert  into `user`(`id`,`user_name`,`password`,`name`,`age`,`address`)
 values (1,'ruiwen','123','瑞文',12,'山东'),                                                                         
        (2,'gailun','1332','盖伦',13,'平顶山'),
        (3,'timu','123','提姆',22,'蘑菇石'),
        (4,'daji','1222','妲己',221,'狐山');
```