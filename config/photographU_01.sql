drop database if exists db_photograph_u;
create database db_photograph_u;
use db_photograph_u;

create table user (
  id         int                 not null auto_increment,
  nickname   char(15)            not null default 'abc',
  head_image varchar(100)                 default 'default.jpg',
  sex        char(1)                      default '*',
  birthday   date                         default '2018-06-01',
  phone      char(11)            not null,
  password   char(33)            not null,
  school     char(20)            not null default 'WSU',
  is_deleted tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'User';
#insert into user (nickname, head_image, sex, birthday, phone, password)values ('ddd', 'default.jpg', 'Male', '1996-12-28', '18483638749', '982352');

create table photographer (
  id             int                 not null auto_increment,
  user_id        int                 not null,
  card_no        char(15)            not null,
  card_image     varchar(100)        not null,
  price          decimal(9, 2)       not null default 0.00,
  server_content varchar(100)        not null default 'NONE',
  introduce      varchar(100),
  star_value     int                 not null default 0,
  is_deleted     tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'Photographer';

create table note (
  id           int                 not null auto_increment,
  user_id      int                 not null,
  style_id     int                 not null,
  content      varchar(100)        not null,
  release_time datetime            not null,
  is_deleted   tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'Note';

create table admire (
  id         int                 not null auto_increment,
  user_id    int                 not null,
  note_id    int                 not null,
  is_deleted tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'Likes';

create table comment (
  id         int                 not null auto_increment,
  user_id    int                 not null,
  note_id    int                 not null,
  content    varchar(100)        not null,
  father_id  int                 not null,
  is_deleted tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'Comments';

create table orderinfo (
  id              int                 not null auto_increment,
  user_id         int                 not null,
  photographer_id int                 not null,
  order_time      datetime            not null,
  address         varchar(50)         not null,
  other           varchar(50)         not null,
  state           int unsigned          default 0,
  is_deleted      tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'order infor';

create table review (
  id              int                 not null auto_increment,
  user_id         int                 not null,
  photographer_id int                 not null,
  content         varchar(100)        not null,
  is_deleted      tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'review';

create table follow (
  id              int                 not null auto_increment,
  user_id         int                 not null,
  photographer_id int                 not null,
  is_deleted      tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'follow';

create table style (
  id         int                 not null auto_increment,
  name       char(8)             not null,
  image      varchar(100)        not null,
  is_deleted tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'Style';
insert into style (name, image) values ('graduate', 'default.jpg');
insert into style (name, image) values ('ancient', 'default.jpg');
insert into style (name, image) values ('personal', 'default.jpg');
insert into style (name, image) values ('CosPlay', 'default.jpg');
insert into style (name, image) values ('meeting', 'default.jpg');
insert into style (name, image) values ('personal', 'default.jpg');
insert into style (name, image) values ('business', 'default.jpg');

create table admin (
  id         int                 not null auto_increment,
  account    char(20)            not null,
  password   char(33)            not null,
  is_deleted tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'administor';
  insert into admin(account,password) values('photograph_u','123456');

create table styleinfo (
  id              int                 not null auto_increment,
  photographer_id int                 not null,
  style_id        int                 not null,
  is_deleted      tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'style setting infor';

create table image (
  id         int                 not null auto_increment,
  note_id    int                 not null,
  name       varchar(100)        not null,
  is_deleted tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'note image';

create table photo (
  id              int                 not null auto_increment,
  photographer_id int                 not null,
  name            varchar(100)        not null,
  is_deleted      tinyint(1) unsigned not null default 0,
  primary key (id)
)
  comment 'photo';
create table verify (
  id         int                 not null   auto_increment,
  user_id    int                 not null,
  admin_id   int                 not null default 1,
  card_no    char(15)            not null,
  card_image varchar(100),
  submit_time datetime           not null,
  verify_time datetime,
  state      int unsigned            default 0,
  is_deleted tinyint(1) unsigned not null   default 0,
  primary key (id)
)
  comment 'verify information';



