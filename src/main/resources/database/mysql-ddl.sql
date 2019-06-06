-- cleaning --
drop schema if exists scout_pro_development;
drop user if exists 'scout_pro_dev'@'localhost';

-- creating --
create schema if not exists scout_pro_development;

create table if not exists scout_pro_development.player (
	id int auto_increment not null,
	player_name varchar(256) not null,
    transfermarkt_url varchar(256) null unique,
    who_scored_url varchar(256) null,
    pes_db_url varchar(256) null,
    psml_url varchar(256) null,
    transfermarkt_last_measured timestamp not null,
    who_scored_last_measured timestamp not null,
    pes_db_last_measured timestamp not null,
    psml_last_measured timestamp not null,
    primary key(id))
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.transfermarkt_info(
	id int auto_increment not null,
    date_of_birth varchar(15) not null,
    age tinyint not null,
    nationality varchar(50) not null,
    national_team varchar(50),
    club_team varchar(50) not null,
    contract_until varchar(12) not null,
    position varchar(40) not null,
    player_id int not null,
    primary key(id),
    index ix_transfermarkt_info_player_id(player_id ASC),
    constraint fk_tm_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.market_value(
	id int auto_increment not null,
    worth decimal(15,2) not null,
    date_point datetime not null,
    club_team varchar(50) not null,
    player_id int not null,
    primary key(id),
    index ix_market_value_player_id(player_id ASC),
    constraint fk_mv_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.transfer(
	id int auto_increment not null,
    from_team varchar(50) not null,
    to_team varchar(50) not null,
    date_of_transfer datetime not null,
    market_value varchar(25) not null,
    transfer_fee varchar(25) not null,
    player_id int not null,
    primary key(id),
    index ix_transfer_player_id(player_id ASC),
    constraint fk_tr_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.who_scored_info(
	id int auto_increment not null,
    strengths varchar(512) not null,
    weaknesses varchar(512) not null,
    style_of_play varchar(512) not null,
    player_id int not null,
    primary key(id),
    index ix_who_scored_info_player_id (player_id ASC),
    constraint fk_who_scored_info_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.statistics_by_competition(
	id int auto_increment not null,
    competition varchar(100) not null,
    started_apps tinyint not null,
    sub_apps tinyint not null,
    mins smallint not null,
    goals tinyint not null,
    assists tinyint not null,
    yellow_cards tinyint not null,
    red_cards tinyint not null,
    shots_per_game decimal(5,2) not null,
    pass_success decimal(5,2) not null,
    aerials_won decimal(5,2) not null,
    man_of_the_match tinyint not null,
    rating decimal(5,2) not null,
    who_scored_info_id int not null,
    primary key(id),
    index ix_stat_by_comp_who_scored_info_id(who_scored_info_id ASC),
    constraint fk_stcomp_who_scored_info_id foreign key(who_scored_info_id) references scout_pro_development.who_scored_info(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.statistics_by_position(
	id int auto_increment not null,
    position varchar(3) not null,
    apps tinyint not null,
    goals tinyint not null,
    assists tinyint not null,
    rating decimal(5,2) not null,
	who_scored_info_id int not null,
	primary key(id),
    index ix_stat_by_pos_who_scored_info_id(who_scored_info_id ASC),
    constraint fk_stpos_who_scored_info_id foreign key(who_scored_info_id) references scout_pro_development.who_scored_info(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.who_scored_game(
	id int auto_increment not null,
    competition varchar(50) not null,
    date_of_game datetime not null,
    team1 varchar(50) not null,
    team2 varchar(50) not null,
    result varchar(5) not null,
    man_of_the_match boolean not null,
    goals tinyint not null,
    assists tinyint not null,
    yellow_card boolean not null,
    red_card boolean not null,
    minutes_played tinyint not null,
    rating decimal(5,2) not null,
	who_scored_info_id int not null,
    primary key(id),
    index ix_game_who_scored_info_id(who_scored_info_id ASC),
    constraint fk_gm_who_scored_info_id foreign key(who_scored_info_id) references scout_pro_development.who_scored_info(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.pes_db_info(
	id int auto_increment not null,
	player_name varchar(256) not null,
    team_name varchar(50) not null,
    foot varchar(5) not null,
    week_condition char(1) not null,
    primary_position varchar(3) not null,
    other_strong_positions varchar(100) null,
    other_weak_positions varchar(100) null,
    attacking_prowess tinyint not null,
    ball_control tinyint not null,
    dribbling tinyint not null,
    low_pass tinyint not null,
    lofted_pass tinyint not null,
    finishing tinyint not null,
    place_kicking tinyint not null,
    swerve tinyint not null,
    header tinyint not null,
    defensive_prowess tinyint not null,
    ball_winning tinyint not null,
    kicking_power tinyint not null,
    speed tinyint not null,
    explosive_power tinyint not null,
    body_control tinyint not null,
    physical_contact tinyint not null,
    jump tinyint not null,
    stamina tinyint not null,
    goalkeeping tinyint not null,
    catching tinyint not null,
    clearing tinyint not null,
    reflexes tinyint not null,
    coverage tinyint not null,
    form tinyint not null,
    injury_resistance tinyint not null,
    weak_foot_usage tinyint not null,
    weak_foot_accuracy tinyint not null,
    overall_rating tinyint not null,
    playing_style varchar(30) not null,
    player_skills varchar(512) not null,
    com_playing_styles varchar(512) not null,
    player_id int not null,
    primary key(id),
    index ix_pes_db_info_player_id(player_id ASC),
    constraint fk_pdb_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.psml_info(
	id int auto_increment not null,
    psml_team varchar(50) not null default 'Free Agent',
    psml_value decimal(15,2) null default 00.00,
    player_id int not null,
    primary key(id),
    index ix_psml_info_player_id(player_id),
    constraint fk_psml_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.psml_transfer(
    id int auto_increment not null,
    from_team varchar(50) not null,
    to_team varchar(50) not null,
    date_of_transfer datetime not null,
    psml_info_id int not null,
    primary key(id),
    index ix_psml_transfer_psml_info_id(psml_info_id ASC),
    constraint fk_psmltr_psml_info_id foreign key(psml_info_id) references scout_pro_development.psml_info(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.app_user(
    id int auto_increment not null,
    username varchar(50) not null unique,
    pass varchar(50) not null,
    primary key(id),
    index ix_app_user_username(username ASC))
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.user_player(
    user_id int not null,
    player_id int not null,
    my_player boolean not null,
    primary key(user_id, player_id),
    constraint fk_up_app_user_id foreign key(user_id) references scout_pro_development.app_user(id) on delete no action on update no action,
    constraint fk_up_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.app_role(
    id int auto_increment not null,
    role_name varchar(10) not null,
    primary key(id))
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.user_role(
    user_id int not null,
    role_id int not null,
    primary key(user_id, role_id),
    constraint fk_ur_app_user_id foreign key(user_id) references scout_pro_development.app_user(id) on delete no action on update no action,
    constraint fk_ur_app_role_id foreign key(role_id) references scout_pro_development.app_role(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.news(
    id int auto_increment not null,
    content text,
    inserted_date timestamp,
    player_id int not null,
    primary key(id),
    index ix_news_player_id (player_id ASC),
    constraint fk_news_player_id foreign key(player_id) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;

create table if not exists scout_pro_development.scrape_reg_expression(
    id int auto_increment not null,
    field_name varchar(50) not null unique,
    regex varchar(128) not null,
    primary key(id),
    index ix_scrape_reg_expression_field_name (field_name ASC))
    engine = InnoDB
    collate = utf8_unicode_ci;

create user 'scout_pro_dev'@'localhost' identified by 'scout_pro_dev_user';
grant select, insert, update, delete on scout_pro_development.* to 'scout_pro_dev'@'localhost';