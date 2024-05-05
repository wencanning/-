
-- 插入到城市表
insert ignore into city 
values
	(1, "武汉", "W"),
    (2, "荆州", "J");
    
-- 插入到服务表
insert ignore into service 
values 
	(1, "电费", "/static/payitem/电费.png"),
    (2, "水费", "/static/payitem/水.png"),
    (3, "燃气费", "/static/payitem/火.png"),
    (4, "固话费", "/static/payitem/电话.png"),
    (5, "宽带费", "/static/payitem/宽带.png"),
    (6, "有线电视", "/static/payitem/电视.png"),
    (7, "交通违章", "/static/payitem/汽车.png"),
    (8, "供暖费", "/static/payitem/供暖.png");
    
-- 插入到cs表
insert ignore into cs 
values 
	(1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
	(1, 6),
    (1, 7),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 6),
    (2, 7),
    (2, 8);
-- 插入到用户表
insert ignore into user(phonenumber, username, img_url)
values 
	("15272304780", "wcn", "/static/icon/头像_男孩.png");

-- 插入到公司表
insert ignore into company
values
	(1, "武汉燃气有限公司", 3,1);

-- 插入到账单表
insert ignore into bill
values 
	(1, 1, 1, '2024-4-24 19:59', 25);