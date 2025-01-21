-- 가입된 회원정보를 저장할 테이블
create table users(
	num number primary key,
	userName varChar2(100) unique,
	password varchar2(100) not null,
	email varchar2(100) unique,
	profileImage varchar2(100), 	--아무거나 못 들어감 잘못된 데이터가 들어가는걸 방지
	role varchar2(10) default 'USER' check(role in('USER','STAFF','ADMIN')),
	createdAt date default sysdate,
	updatedAt date default sysdate
);

create sequence users_seq;
