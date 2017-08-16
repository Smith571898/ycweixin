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
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(1,1,'click','Դ�����¼�','V1001_TODAY_THINK','www.baidu.com');
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(2,1,'click','ÿ��֮��','V1001_TODAY_THINK','www.baidu.com');
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(3,1,'click','���鼦��','V1001_TODAY_THINK','www.baidu.com');
insert into sub_button(sbid,sb_bid,menutype,name,menukey,url)  values(4,2,'click','�༶��','V1001_TODAY_THINK','www.baidu.com');
insert into button(bid,menutype,name,menukey,url)  values(1,'click','Դ����Ϣ','V1001_TODAY_THINK','www.sina.com');
insert into button(bid,menutype,name,menukey,url)  values(2,'click','ѧԱ���','V1001_TODAY_THINK','www.aliyun.com');
insert into button(bid,menutype,name,menukey,url)  values(3,'click','��������','V1001_TODAY_THINK','www.alibaba.com');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (1,'1.jpg','��СС��','��','���Ϻ���','2017-08-06','�ѹ�ע','����','18373471143','���Ϲ�ѧԺ','���1401��','Դ��33��')
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (2,'2.jpg','��������','Ů','���Ϻ���','2017-08-07','�ѹ�ע','����','18473434532','���Ϲ�ѧԺ','���1401��','Դ��27��');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (3,'3.jpg','�峿','��','���Ϻ���','2017-08-07','�ѹ�ע','�����','15386014960','���Ϲ�ѧԺ','���1401��','Դ��33��');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (4,'4.jpg','�λع���','��','���Ϻ���','2017-08-07','�ѹ�ע','��ΰ��','17674707473','���Ϲ�ѧԺ','���1401��','Դ��27��');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (5,'5.jpg','��ײ�','Ů','���Ϻ���','2017-08-07','�ѹ�ע','�ܻ�','18374747343','���Ϲ�ѧԺ','���1401��','Դ��27��');
insert into followpush(fid,ftitle,fpic,fcontent,lastmodify,lastmodifytime,isfollowpush) values (1,'��ӭ��עԴ��΢��','6.jpg','Դ��΢�ſƼ����޹�˾��һ�Ҷ�λ�ڴ�ѧ���������,ʵս��ѵ��һϵ�����������ۺ��������˾��Ϊ�����ѧ��������ҵ��Ϊ��ҵ�ṩ�������ΪĿ�ꡣ��˾���������������ҵ�����������Ϊ���������ҵIT������ѯ���������������ҵԱ��������ѵ��','admin','2017-08-07','��');
delete  from userinfo
select  uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass from userinfo where 1=1       limit 0,0
select * from  followpush 

update userinfo set sex='Ů'  where  sex='��'
select * from userinfo


select * from button left join sub_button on bid = sb_bid;