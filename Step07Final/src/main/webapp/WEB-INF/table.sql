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

-- 글 목록을 저장할 테이블
CREATE TABLE posts(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);

CREATE SEQUENCE posts_seq;

-- 어떤 세션에서 몇번글을 읽었는지 정보를 저장할 테이블
CREATE TABLE readed_data(
	num NUMBER REFERENCES posts(num),
	session_id VARCHAR2(50)
);

create table comments(
	num number primary key,	--댓글의 글 번호
	writer varchar2(100) not null, -- 작성자
	content varchar2(200)not null, -- 내용
	targetWriter varchar2(100) not null, --댓글 대상자의 id
	postNum number not null, -- 원글의 글 번호
	parentNum number not null, --댓글의 그룹번호
	delete char(3) default 'no',
	createdAt date
);

create sequence comments_seq;