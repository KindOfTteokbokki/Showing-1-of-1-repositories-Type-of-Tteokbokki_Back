/*
*
* 떡볶이 추천 시스템
*
*/
SHOW DATABASES;
USE utteok;

/*
*
* 01_MO. 메뉴
*
* 공통 코드 테이블
*
* 공통코드유형 테이블
* 코드유형ID
* 코드유형명
* 코드유형영문명
*
* table name : public_code_category
* column	  : code_category_id(pk), name_ko, name_en
*
* 공통코드테이블
* 코드
* 코드명
* 코드유형ID(FK)
* 코드유형영문명
* 사용여부
*
* table name : public_code
* column	  : code(pk), name_ko, code_category_id(fk), code_category_en , use_yn
*
* column (페이지, 코드, 코드 값)
* QS001	쌀떡파 VS 밀떡파
* QS002	오호~ 다른 거 어떤 걸 원해?
* QS003	식감은 어땠으면 좋겠어?
* QS004 	어떤 맛이 좋아?
* QS005	매운거 VS 달달?
* QS006 	얼마나 매운 거 좋아해?
* QS007 	토핑 있는 건 어때?
* ...
* CH101	쌀떡
* CH102	밀떡
* CH201	감자떡
* CH202 	분모자
* CH203	통가래떡
* CH204 	우동같은 밀떡
* ...
*
*/
SHOW TABLES
;
drop table public_code_category;
drop table public_code;
drop table question_for_select;
drop table result_seq;
drop table test;

create table public_code_category(
code_category_id 	int(5)		not null
, name_ko 			varchar(50)	not null
, name_en 			varchar(50)	not null
, PRIMARY KEY (code_category_id)
)
;	-- 질문, 가게 이름, 칭호
create table public_code(
code 					varchar(10) not null
, name_ko 				varchar(50)	not null
, code_category_id		int(5)		not null
, code_category_name_en	varchar(50)	not null
, use_yn				char(1)		not null
, PRIMARY KEY (code)
, FOREIGN KEY (code_category_id) REFERENCES public_code_category(code_category_id)
)
;
select * from public_code_category pcc ;
select * from public_code pc where 1=1 and code_category_id = 102;
insert into public_code_category values(101, '떡볶이 선택 질문', 'Tteokbokki selection question');
insert into public_code_category values(102, '떡볶이 선택 값', 'Tteokbokki selection value');
insert into public_code_category values(103, '가게 이름', 'store name');
insert into public_code_category values(104, '칭호', 'title');
-- 떡볶이 선택 질문
insert into public_code values('QS001', '쌀떡파 VS 밀떡파', 101, 'TKSQ', 'Y');
insert into public_code values('QS002', '오호~ 다른 거 어떤 걸 원해?', 101, 'TKSQ', 'Y');
insert into public_code values('QS003', '식감은 어땠으면 좋겠어?', 101, 'TKSQ', 'Y');
insert into public_code values('QS004', '어떤 맛이 좋아?', 101, 'TKSQ', 'Y');
insert into public_code values('QS005', '매운거 VS 달달?', 101, 'TKSQ', 'Y');
insert into public_code values('QS006', '얼마나 매운 거 좋아해?', 101, 'TKSQ', 'Y');
insert into public_code values('QS007', '토핑 있는 건 어때?', 101, 'TKSQ', 'Y');
-- 떡볶이 선택 값
insert into public_code values('CH100', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH101', '쌀떡', 102, 'TKSV', 'Y');
insert into public_code values('CH102', '밀떡', 102, 'TKSV', 'Y');
insert into public_code values('CH103', '다른거', 102, 'TKSV', 'Y');
insert into public_code values('CH200', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH201', '감자떡', 102, 'TKSV', 'Y');
insert into public_code values('CH202', '분모자', 102, 'TKSV', 'Y');
insert into public_code values('CH203', '통가래떡', 102, 'TKSV', 'Y');
insert into public_code values('CH204', '우동같은 밀떡', 102, 'TKSV', 'Y');
insert into public_code values('CH300', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH301', '쫄깃쫄깃', 102, 'TKSV', 'Y');
insert into public_code values('CH302', '말랑말랑', 102, 'TKSV', 'Y');
insert into public_code values('CH400', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH401', '빨간', 102, 'TKSV', 'Y');
insert into public_code values('CH402', '간장', 102, 'TKSV', 'Y');
insert into public_code values('CH403', '로제', 102, 'TKSV', 'Y');
insert into public_code values('CH404', '마라', 102, 'TKSV', 'Y');
insert into public_code values('CH405', '마라로제', 102, 'TKSV', 'Y');
insert into public_code values('CH406', '바질크림', 102, 'TKSV', 'Y');
insert into public_code values('CH500', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH501', '매운거', 102, 'TKSV', 'Y');
insert into public_code values('CH502', '달달한거', 102, 'TKSV', 'Y');
insert into public_code values('CH600', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH601', '습(기본맛)', 102, 'TKSV', 'Y');
insert into public_code values('CH602', '스으읍(매운맛)', 102, 'TKSV', 'Y');
insert into public_code values('CH603', '스으으읍하(아주 매운맛)', 102, 'TKSV', 'Y');
insert into public_code values('CH700', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH701', '없음', 102, 'TKSV', 'Y');
insert into public_code values('CH702', '차돌', 102, 'TKSV', 'Y');
insert into public_code values('CH703', '오징어', 102, 'TKSV', 'Y');
insert into public_code values('CH704', '치즈많이', 102, 'TKSV', 'Y');
insert into public_code values('CH705', '페퍼로니', 102, 'TKSV', 'Y');
-- 가게 이름
insert into public_code values('ST001', '할매 떡볶이', 103, 'STNM', 'Y');
insert into public_code values('ST002', '빨간 부산오뎅', 103, 'STNM', 'Y');
insert into public_code values('ST003', '오빠네 옛날 떡볶이', 103, 'STNM', 'Y');
insert into public_code values('ST004', '옥이떡볶이', 103, 'STNM', 'Y');
insert into public_code values('ST005', '6.25 떡볶이', 103, 'STNM', 'Y');
-- 칭호
insert into public_code values('TT001', '역시 근본', 104, 'TITL', 'Y');
insert into public_code values('TT002', '페퍼로니♥ 파인애플 피자 극혐', 104, 'TITL', 'Y');
insert into public_code values('TT003', '맛도리', 104, 'TITL', 'Y');
insert into public_code values('TT004', '이 구역 맵찔이', 104, 'TITL', 'Y');
insert into public_code values('TT005', '돌고 돌아 다시 엽떡', 104, 'TITL', 'Y');

/*
*
* 01_MO. 메뉴
*
* table name : question_for_select
* column	  : question_seq(pk), category, code, question1, question2, question3, question4, question5, question6, question7
*
* public_code QS00* == question 없는 데이터는 nothing으로 대체
*
*/
drop table question_for_select;

create table question_for_select (
question_seq	int not null auto_increment
, category				varchar(100)
, code					varchar(100)
, question1				varchar(200)
, question2				varchar(200)
, question3				varchar(200)
, question4				varchar(200)
, question5				varchar(200)
, question6				varchar(200)
, question7				varchar(200)
, PRIMARY KEY (question_seq)
, FOREIGN KEY (question1) REFERENCES public_code(code)
, FOREIGN KEY (question2) REFERENCES public_code(code)
, FOREIGN KEY (question3) REFERENCES public_code(code)
, FOREIGN KEY (question4) REFERENCES public_code(code)
, FOREIGN KEY (question5) REFERENCES public_code(code)
, FOREIGN KEY (question6) REFERENCES public_code(code)
, FOREIGN KEY (question7) REFERENCES public_code(code)
)
;
create sequence question_seq start with 1 increment by 1 maxvalue 999999;

select * from question_for_select qfs ;

insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('STORE', 'ST001', 'CH102', 'CH200', 'CH301', 'CH401', 'CH501', 'CH601', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('STORE', 'ST002', 'CH101', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('STORE', 'ST003', 'CH102', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('STORE', 'ST004', 'CH102', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('STORE', 'ST005', 'CH102', 'CH200', 'CH301', 'CH401', 'CH501', 'CH601', 'CH700');

insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('TITLE', 'TT001', 'CH102', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('TITLE', 'TT002', 'CH102', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('TITLE', 'TT003', 'CH102', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('TITLE', 'TT004', 'CH102', 'CH200', 'CH301', 'CH401', 'CH502', 'CH600', 'CH700');
insert into question_for_select (category, code, question1, question2, question3, question4, question5, question6, question7)
values('TITLE', 'TT005', 'CH102', 'CH200', 'CH301', 'CH401', 'CH501', 'CH601', 'CH700');

/*
*
* 03_MO. 가게 정보
*
* table name : store_info
* column	  : store_seq(pk), question_seq(fk), store_name, menu_name, review, img_path, count
*
* 질문지에 중복이나 값이 없을 경우 무작위로 하나 뽑아 가져온다.
* 가게와 메뉴 이름이 중복이 있을 수 있기에 공통 코드로 가게 이름을 담아둔다.
*
*/
drop table store_info;
create table store_info(
store_seq				int				not null auto_increment
, question_seq 			int				not null
, store_code			varchar(100)
, store_name			varchar(100)
, store_address			varchar(200)
, menu_name				varchar(100)
, review				varchar(500)
, file_path				varchar(500)
, file_original_name	varchar(500)
, file_masking_name		varchar(500)
, count_store			int
, franchise_yn			char(1)
, PRIMARY KEY (store_seq)
, FOREIGN KEY (question_seq) REFERENCES question_for_select(question_seq)
, FOREIGN KEY (store_code) REFERENCES public_code(code)
)
;
create sequence store_seq start with 1 increment by 1 maxvalue 999999;

select * from store_info;
select * from store_seq;

insert into store_info (question_seq, store_code, store_name, store_address, menu_name, review, img_path, count_store, franchise_yn)
values(4, 'ST001', '할매 떡볶이', '서울 성북구 월곡동', '떡볶이', '주문 즉시 새로 조리해서 판떡볶이인데도 갓 끓인 떡볶이를 맛볼 수 있어', '이미지 아직 없음', 0, 'N');
insert into store_info (question_seq, store_code, store_name, store_address, menu_name, review, img_path, count_store, franchise_yn)
values(5, 'ST002', '빨간 부산오뎅', '서울 송파구 방이시장', '떡볶이', '시그니처 메뉴인 매운어묵도 무조건 같이 먹어!', '이미지 아직 없음', 0, 'N');
insert into store_info (question_seq, store_code, store_name, store_address, menu_name, review, img_path, count_store, franchise_yn)
values(6, 'ST003', '오빠네 옛날 떡볶이', '경기 일산 웨스턴돔', '떡볶이', '떡이 하얘보여도 한입 먹어보면 반전 매력이 있는 국물떡볶이야! 달달하고 매콤하고 다해', '이미지 아직 없음', 0, 'N');
insert into store_info (question_seq, store_code, store_name, store_address, menu_name, review, img_path, count_store, franchise_yn)
values(7, 'ST004', '옥이떡볶이', '경기 화성 동탄2신도시', '떡볶이', '수제 오징어튀김을 양념에 찍어 먹으면 더 맛있어', '이미지 아직 없음', 0, 'N');
insert into store_info (question_seq, store_code, store_name, store_address, menu_name, review, img_path, count_store, franchise_yn)
values(8, 'ST005', '6.25 떡볶이', '6.25 떡볶이 경남 마산 부림시장마산 부림시장', '떡볶이', '어묵이 80%라서 떡 좋아하면 주문할 때 미리 말해', '이미지 아직 없음', 0, 'N');

/*
*
* 05_MO. 칭호 정보
*
* table name : title_info
* column	  : title_seq(pk), question_seq(fk), title_code, title_name, img_path
*
*/
drop table title_info ;
create table title_info(
title_seq		int	not null auto_increment
, question_seq 	int	not null
, title_code	varchar(100)
, title_name	varchar(100)
, file_path				varchar(500)
, file_original_name	varchar(500)
, file_masking_name		varchar(500)
, PRIMARY KEY (title_seq)
, FOREIGN KEY (question_seq) REFERENCES question_for_select(question_seq)
, FOREIGN KEY (title_code) REFERENCES public_code(code)
)
;

create sequence title_seq start with 1 increment by 1 maxvalue 999999;

select * from title_info;
select * from title_seq;

insert into title_info (question_seq, title_code, title_name, img_path)
values(9, 'TT001', '역시 근본', '이미지 아직 없음');
insert into title_info (question_seq, title_code, title_name, img_path)
values(10, 'TT002', '페퍼로니♥ 파인애플 피자 극혐', '이미지 아직 없음');
insert into title_info (question_seq, title_code, title_name, img_path)
values(11, 'TT003', '맛도리', '이미지 아직 없음');
insert into title_info (question_seq, title_code, title_name, img_path)
values(12, 'TT004', '이 구역 맵찔이', '이미지 아직 없음');
insert into title_info (question_seq, title_code, title_name, img_path)
values(13, 'TT005', '돌고 돌아 다시 엽떡', '이미지 아직 없음');

/*
*
* 02_MO. 로딩 페이지
*
* table name : loading_for_analyze
* column	  : loading_seq(pk), phrases, img_path
*
* result is random(5)
*
* 추후 여러가지 데이터가 쌓일 경우 사용과 미사용으로 구분하여 랜덤으로 데이터를 보내준다.
*
*/
drop table loading_for_analyze;
create table loading_for_analyze(
loading_seq	int				not null auto_increment
, PHRASES		VARCHAR(500)
, file_path				varchar(500)
, file_original_name	varchar(500)
, file_masking_name		varchar(500)
, use_yn		char(1)
, PRIMARY KEY (loading_seq)
)
;
create sequence loading_seq start with 1 increment by 1 maxvalue 999999;

select * from loading_for_analyze ;

insert into loading_for_analyze (phrases, img_path, use_yn)
values('오징어 올려진 떡볶이도 맛있다더라 1', '아직 이미지 없음 1', 'Y');
insert into loading_for_analyze (phrases, img_path, use_yn)
values('오징어 올려진 떡볶이도 맛있다더라 2', '아직 이미지 없음 2', 'Y');
insert into loading_for_analyze (phrases, img_path, use_yn)
values('오징어 올려진 떡볶이도 맛있다더라 3', '아직 이미지 없음 3', 'Y');

/*
*
* 06_MO. 꿀조합
*
* table name : utteok_combination
* column	  : combination_seq(pk), img_path
*
* result is random(5)
*
*/
create table utteok_combination(
combination_seq	int				not null auto_increment
, file_path				varchar(500)	NOT NULL
, file_original_name	varchar(500)	NOT null
, file_masking_name		varchar(500)	NOT null
, PRIMARY KEY (combination_seq)
)
;
create sequence combination_seq start with 1 increment by 1 maxvalue 999999;

select * from utteok_combination;

insert into utteok_combination (img_path)
values('conbination_img_path 1');
insert into utteok_combination (img_path)
values('conbination_img_path 2');
insert into utteok_combination (img_path)
values('conbination_img_path 3');

/*
*
* 07_MO. 후기
*
* table name : review_for_store
* column	  : seq(pk), img_path, content
*
*/
create table review_for_store(
review_seq	int				not null auto_increment
, content		varchar(1000)
, file_path				varchar(500)
, file_original_name	varchar(500)
, file_masking_name		varchar(500)
, create_date	date
, PRIMARY KEY (review_seq)
)
;
create sequence review_seq start with 1 increment by 1 maxvalue 999999;

SELECT
CONTENT
, file_path			
, file_original_name
, file_masking_name
, CREATE_DATE
FROM
REVIEW_FOR_STORE
ORDER BY CREATE_DATE DESC
;
-- 			, file_path
-- 			, file_original_name
-- 			, file_masking_name

ALTER TABLE REVIEW_FOR_STORE ADD COLUMN file_path varchar(1000) NOT NULL;
ALTER TABLE REVIEW_FOR_STORE ADD COLUMN file_original_name varchar(1000) NOT NULL;
ALTER TABLE REVIEW_FOR_STORE ADD COLUMN file_masking_name varchar(1000) NOT NULL;
;

insert into review_for_store (content, img_path, create_date)
values('재료들의 맛조화가 절묘하게 좋았다', 'review_img_path 1', SYSDATE());
insert into review_for_store (content, img_path, create_date)
values('맛있어요', 'review_img_path 1', SYSDATE());
insert into review_for_store (content, img_path, create_date)
values('시금치 베이글 처음 먹어봤는데, 생각보다 맛있고 제 스타일이었어요 !!', 'review_img_path 2', SYSDATE());

