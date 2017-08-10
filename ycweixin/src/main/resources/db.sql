create database wechat
use wechat

drop table userinfo

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


insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (1,'1.jpg','��СС��','��','���Ϻ���','2017-08-06','�ѹ�ע','����','18373471143','���Ϲ�ѧԺ','���1401��','Դ��33��')
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (2,'2.jpg','��������','Ů','���Ϻ���','2017-08-07','�ѹ�ע','����','18473434532','���Ϲ�ѧԺ','���1401��','Դ��27��');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (3,'3.jpg','�峿','��','���Ϻ���','2017-08-07','�ѹ�ע','�����','15386014960','���Ϲ�ѧԺ','���1401��','Դ��33��');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (4,'4.jpg','�λع���','��','���Ϻ���','2017-08-07','�ѹ�ע','��ΰ��','17674707473','���Ϲ�ѧԺ','���1401��','Դ��27��');
insert into userinfo(uid,headimgurl,nickname,sex,address,subscribe_time,subscribe,name,telephone,schoolname,nowclass,ycclass) values (5,'5.jpg','��ײ�','Ů','���Ϻ���','2017-08-07','�ѹ�ע','�ܻ�','18374747343','���Ϲ�ѧԺ','���1401��','Դ��27��');
insert into followpush(fid,ftitle,fpic,fcontent,lastmodify,lastmodifytime,isfollowpush) values (1,'��ӭ��עԴ��΢��','6.jpg','Դ��΢�ſƼ����޹�˾��һ�Ҷ�λ�ڴ�ѧ���������,ʵս��ѵ��һϵ�����������ۺ��������˾��Ϊ�����ѧ��������ҵ��Ϊ��ҵ�ṩ�������ΪĿ�ꡣ��˾���������������ҵ�����������Ϊ���������ҵIT������ѯ���������������ҵԱ��������ѵ��','admin','2017-08-07','��');
delete  from followpush
select  uid,head,nikname,sex,address,followtime,isfollow,name,telephone,schoolname,nowclass,ycclass from userinfo where 1=1       limit 0,0
select * from  followpush 

update userinfo set sex='��'  where  uid=1
select * from userinfo
