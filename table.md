
create table public_code_category(
code_category_id int(5) not null
, name_ko varchar(50) not null
, name_en varchar(50) not null
, primary key (code_category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table public_code(
code varchar(10) not null
, name_ko varchar(50) not null
, code_category_id int(5) not null
, code_category_name_en varchar(50) not null
, use_yn char(1) not null
, primary key (code)
, foreign key (code_category_id) references public_code_category(code_category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table question_for_select (
question_seq int not null auto_increment
, category varchar(100)
, code varchar(100)
, question1 varchar(200)
, question2 varchar(200)
, question3 varchar(200)
, question4 varchar(200)
, question5 varchar(200)
, question6 varchar(200)
, question7 varchar(200)
, primary key (question_seq)
, foreign key (question1) references public_code(code)
, foreign key (question2) references public_code(code)
, foreign key (question3) references public_code(code)
, foreign key (question4) references public_code(code)
, foreign key (question5) references public_code(code)
, foreign key (question6) references public_code(code)
, foreign key (question7) references public_code(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence question_seq start with 1 increment by 1 maxvalue 999999;

create table store_info(
store_seq int not null auto_increment
, question_seq int not null
, store_code varchar(100)
, store_name varchar(100)
, store_address varchar(200)
, menu_name varchar(100)
, review varchar(500)
, franchise_yn char(1)
, primary key (store_seq)
, foreign key (question_seq) references question_for_select(question_seq)
, foreign key (store_code) references public_code(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence store_seq start with 1 increment by 1 maxvalue 999999;

create table title_info(
title_seq int not null auto_increment
, question_seq int not null
, title_code varchar(100)
, primary key (title_seq)
, foreign key (question_seq) references question_for_select(question_seq)
, foreign key (title_code) references public_code(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence title_seq start with 1 increment by 1 maxvalue 999999;

create table loading_for_analyze(
loading_seq int not null auto_increment
, phrases varchar(500)
, use_yn char(1)
, primary key (loading_seq)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence loading_seq start with 1 increment by 1 maxvalue 999999;

create table utteok_combination(
combination_seq int not null auto_increment
, content varchar(1000)
, file_path varchar(500) not null
, file_original_name varchar(500) not null
, file_masking_name varchar(500) not null
, primary key (combination_seq)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence combination_seq start with 1 increment by 1 maxvalue 999999;

create table review_for_store(
review_seq int not null AUTO_INCREMENT
, user_id varchar(30)
, content varchar(1000)
, file_path varchar(1000)
, file_original_name varchar(1000)
, file_masking_name varchar(1000)
, create_date date
, primary key (review_seq)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence review_seq start with 1 increment by 1 maxvalue 999999;

create table menu_img(
menu_img_seq int not null auto_increment
, question_seq int not null
, file_path varchar(500)
, file_original_name varchar(500)
, file_masking_name varchar(500)
, primary key (menu_img_seq)
, foreign key (menu_img_seq) references question_for_select(question_seq)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence menu_img_seq start with 1 increment by 1 maxvalue 999999;

create table title_img (
title_img_seq int not null auto_increment
, title_code varchar(10) not null
, icorn_file_path varchar(500)
, icorn_file_original_name varchar(500)
, icorn_file_masking_name varchar(500)
, img_file_path varchar(500)
, img_file_original_name varchar(500)
, img_file_masking_name varchar(500)
, primary key (title_img_seq)
, foreign key (title_code) references public_code(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence title_img_seq start with 1 increment by 1 maxvalue 999999;

create table oauth_login(
id int not null auto_increment
, email varchar(100)
, nickname varchar(100) not null
, utteok_nickname varchar(100)
, oAuthProvider varchar(100) NOT NULL
, create_date date
, recent_date date
, primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence oauth_login_seq start with 1 increment by 1 maxvalue 999999;

create table mytaste_count(
mytaste_count_seq int not null auto_increment
, user_id varchar(30) not null
, store_seq int
, menu_count int
, primary key (mytaste_count_seq)
, foreign key (store_seq) references store_info(store_seq)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence mytaste_count_seq start with 1 increment by 1 maxvalue 999999;

create table have_title(
have_title_seq int not null auto_increment
, user_id varchar(30) not null
, title_seq int
, get_title boolean
, primary key (have_title_seq)
, foreign key (title_seq) references title_info(title_seq)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create sequence have_title_seq start with 1 increment by 1 maxvalue 999999;
alter table have_title modify get_title boolean comment 'TRUE == 1, FLASE == 0';