create database wechat
use wechat

create table admin(
adminname varchar(10),
adminpwd varchar(50)
)
insert admin(adminname,adminpwd)  values('a','a')
delete  from userinfo
drop table userinfo
alter table userinfo  modify column uid varchar(100);
0329
 oHfPg0f0S8LTNwOIgCkrPzBKHCuQ 22220175012105001.jpg ���ϳϳϳϳϳϳ�~ Ů   ��������¡��  1502007890     �ѹ�ע                                          

userinfo
select * from userinfo
 create table userinfo(
     uid varchar(100),
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
<p>Դ��΢�ſƼ����޹�˾��һ�Ҷ�λ�ڴ�ѧ���������,ʵս��ѵ��һϵ�����������ۺ��������˾��Ϊ�����ѧ��������ҵ��Ϊ��ҵ�ṩ�������ΪĿ�ꡣ��˾���������������ҵ�����������Ϊ���������ҵIT������ѯ���������������ҵԱ��������ѵ��<img alt="" src="../tuwen/201708181419056.PNG" style="height:60px; width:46px" /></p>

select  * from followpush
update followpush set isfollowpush='��' where fid =1
create table followpush(
	fid int primary key auto_increment,
	ftitle varchar(200),
	fpic  varchar(200),
	fcontent varchar(2000),
	lastmodify varchar(50),
	lastmodifytime varchar(200),
	isfollowpush varchar(10)
)

select * from followpush;

drop table followpush;

create table  button(
	bid int primary key auto_increment,
	menutype  varchar(100),
	name varchar(100),
	menukey  varchar(100),
	url   varchar(1024)		
)
alter table sub_button add 	grade varchar(10)
select a.name ,b.name from (select button.bid ,button.name as name from button ) a join
(select sub_button.sb_bid as ssbid,sub_button.sbid ,sub_button.name as name from sub_button) b  on a.bid=b.ssbid


join sub_button

select a.sbid,a.sb_bid,a.menutype,a.name,a.menukey,a.url , b.name  as sub_name from sub_button a join button b on  b.bid=a.sb_bid where 1=1


select name,grade from sub_button where sb_bid=1




select 
drop table button
drop table sub_button
delete from followpush
create table sub_button(
	sbid int primary key auto_increment,
	sb_bid int,
	menutype  varchar(100),
	subname varchar(100),
	menukey  varchar(100),
	url   varchar(2000),
	grade  int
)
<<<<<<< HEAD
alter table sub_button  modify column grade int ;	

select b.name ,s.name,s.grade from button  b
inner join sub_button s
on b.bid=s.sb_bid  order by s.grade desc

=======

create table materia(
	mid int primary key auto_increment,
	murl varchar(300),
	media_id varchar(200),
)
>>>>>>> refs/remotes/origin/yhy

select * from sub_button
update sub_button set sb_bid=1 where sbid=1
update sub_button set grade='fifthname' where sbid=4

select sb.subname as sub_name,b.name as name,sb.menutype as menutype,sb.menukey as menukey,sb.url as url,sb.grade as grade from sub_button  sb join button b on sb.sb_bid=b.bid

insert into button(bid,menutype,name,menukey,url)  values(1,'click','Դ����Ϣ','V1001_TODAY_THINK','www.sina.com');
insert into button(bid,menutype,name,menukey,url)  values(2,'click','ѧԱ���','V1001_TODAY_THINK','www.aliyun.com');
insert into button(bid,menutype,name,menukey,url)  values(3,'click','��������','V1001_TODAY_THINK','www.alibaba.com');

insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(1,1,'click','��˾���','V1001_TODAY_THINK','www.baidu.com',1);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(2,1,'click','ҵ��Χ','V1001_TODAY_THINK','www.baidu.com',2);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(3,1,'click','�γ���ϵ','V1001_TODAY_THINK','www.baidu.com',3);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(4,1,'click','��Ŀչʾ','V1001_TODAY_THINK','www.baidu.com',4);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(5,1,'click','������Ƶ','V1001_TODAY_THINK','www.baidu.com',5);


insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(6,2,'click','�α��ѯ','V1001_TODAY_THINK','www.baidu.com',1);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(7,2,'click','�鼮����','V1001_TODAY_THINK','www.baidu.com',2);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(8,2,'click','��Դ��վ','V1001_TODAY_THINK','www.baidu.com',3);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(9,2,'click','����ƻ�','V1001_TODAY_THINK','www.baidu.com',4);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(10,2,'click','��������','V1001_TODAY_THINK','www.baidu.com',5);

insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(11,3,'click','Դ�����¼�','V1001_TODAY_THINK','www.baidu.com',1);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(12,3,'click','ÿ��֮��','V1001_TODAY_THINK','www.baidu.com',2);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(13,3,'click','���鼦��','V1001_TODAY_THINK','www.baidu.com',3);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(14,3,'click','�������','V1001_TODAY_THINK','www.baidu.com',4);
insert into sub_button(sbid,sb_bid,menutype,subname,menukey,url,grade)  values(15,3,'click','�༶��','V1001_TODAY_THINK','www.baidu.com',5);






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