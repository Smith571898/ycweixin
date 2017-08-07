create database wechat
use wechat
create table  userinfo(
	uid   int ,
	head varchar(200),
	nikname varchar(200),
 	sex     varchar(10),
 	address  varchar(200),
	followtime  varchar(200),
	isfollow   varchar(50),
	name     varchar(50),
	telephone  varchar(20),
	schoolname  varchar(100),	
	nowclass   varchar(200),
	ycclass   varchar(200)
)

create table followpush(
	fid int,
	ftitle varchar(200),
	fpic  varchar(200),
	fcontent varchar(2000),
	lastmodify varchar(50),
	lastmodifytime varchar(200),
	isfollowpush varchar(10)

)


insert into userinfo(uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass) values (1,'1.jpg','聂小小诚','男','湖南衡阳','2017-08-06','已关注','聂诚','18373471143','湖南工学院','软件1401班','源辰33班')
insert into userinfo(uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass) values (2,'2.jpg','汤汤汤格','女','湖南衡阳','2017-08-07','已关注','汤格','18473434532','湖南工学院','软件1401班','源辰27班');
insert into userinfo(uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass) values (3,'3.jpg','清晨','男','湖南衡阳','2017-08-07','已关注','杨鸿宇','15386014960','湖南工学院','软件1401班','源辰33班');
insert into userinfo(uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass) values (4,'4.jpg','梦回孤零','男','湖南衡阳','2017-08-07','已关注','胡伟豪','17674707473','湖南工学院','软件1401班','源辰27班');
insert into userinfo(uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass) values (5,'5.jpg','大白菜','女','湖南衡阳','2017-08-07','已关注','曹慧','18374747343','湖南工学院','软件1401班','源辰27班');
insert into followpush(fid,ftitle,fpic,fcontent,lastmodify,lastmodifytime,isfollowpush) values (1,'欢迎关注源辰微信','6.jpg','源辰微信科技有限公司是一家定位于大学生软件开发,实战培训等一系列软件服务的综合性软件公司，为解决大学生高起点就业和为企业提供解决方案为目标。公司是以软件教育及行业软件开发工作为主，兼顾企业IT技术咨询服务及软件开发，企业员工技术培训。','admin','2017-08-07','是');

select  uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass from userinfo where 1=1       limit 0,0


update userinfo set sex='男'  where  uid=1
select * from userinfo
