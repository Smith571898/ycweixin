create database wechat
use wechat

drop table userinfo
alter table userinfo  modify column uid varchar(100);

 create table userinfo(
     uid int,
     headimgurl varchar(500),
     nickname varchar(200),
     sex varchar(20),
     address varchar(100),
     subscribe_time  varchar(100),
     subscribe  varchar(10),
     name varchar(50),
     telephone varchar(20),
     schoolname varchar(100),
     nowclass varchar(100),
     ycclass varchar(100)
);


create table followpush(
	fid int,
	ftitle varchar(200),
	fpic  varchar(200),
	fcontent varchar(2000),
	lastmodify varchar(50),
	lastmodifytime varchar(200),
	isfollowpush varchar(10),
)

select * from followpush;

drop table followpush;

create table  button(
	bid int primary key,
	menutype  varchar(100),
	name varchar(100),
	menukey  varchar(100),
	url   varchar(1024)	
)
drop table sub_button
create table sub_button(
	sbid int primary key,
	sb_bid int,
	menutype  varchar(100),
	name varchar(100),
	menukey  varchar(100),
	url   varchar(2000)
)

create table materia(
	mid int primary key auto_increment,
	murl varchar(300),
	media_id varchar(200),
)

select * from sub_button
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(1,1,'click','源辰大事件','V1001_TODAY_THINK','www.baidu.com');
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(2,1,'click','每周之星','V1001_TODAY_THINK','www.baidu.com');
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(3,1,'click','心灵鸡汤','V1001_TODAY_THINK','www.baidu.com');
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(4,2,'click','班级绑定','V1001_TODAY_THINK','www.baidu.com');
insert into button(bid,menutype,name,menukey,url)  values(1,'click','源辰信息','V1001_TODAY_THINK','www.sina.com');
insert into button(bid,menutype,name,menukey,url)  values(2,'click','学员天地','V1001_TODAY_THINK','www.aliyun.com');
insert into button(bid,menutype,name,menukey,url)  values(3,'click','娱乐中心','V1001_TODAY_THINK','www.alibaba.com');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (1,'1.jpg','聂小小诚','男','湖南衡阳','2017-08-06','已关注','聂诚','18373471143','湖南工学院','软件1401班','源辰33班')
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (2,'2.jpg','汤汤汤格','女','湖南衡阳','2017-08-07','已关注','汤格','18473434532','湖南工学院','软件1401班','源辰27班');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (3,'3.jpg','清晨','男','湖南衡阳','2017-08-07','已关注','杨鸿宇','15386014960','湖南工学院','软件1401班','源辰33班');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (4,'4.jpg','梦回孤零','男','湖南衡阳','2017-08-07','已关注','胡伟豪','17674707473','湖南工学院','软件1401班','源辰27班');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (5,'5.jpg','大白菜','女','湖南衡阳','2017-08-07','已关注','曹慧','18374747343','湖南工学院','软件1401班','源辰27班');
insert into followpush(fid,ftitle,fpic,fcontent,lastmodify,lastmodifytime,isfollowpush) values (1,'欢迎关注源辰微信','6.jpg','源辰微信科技有限公司是一家定位于大学生软件开发,实战培训等一系列软件服务的综合性软件公司，为解决大学生高起点就业和为企业提供解决方案为目标。公司是以软件教育及行业软件开发工作为主，兼顾企业IT技术咨询服务及软件开发，企业员工技术培训。','admin','2017-08-07','是');
delete  from userinfo
select  uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass from userinfo where 1=1       limit 0,0
select * from  followpush 

update userinfo set sex='女'  where  sex='男'
select * from userinfo


select * from button left join sub_button on bid = sb_bid;