create schema if not exists scout_pro_development;

drop table if exists scout_pro_development.pesdbinfo;
drop table if exists scout_pro_development.corestats;
drop table if exists scout_pro_development.game;
drop table if exists scout_pro_development.positionplayedstats;
drop table if exists scout_pro_development.characteristic;
drop table if exists scout_pro_development.whoscoredinfo;
drop table if exists scout_pro_development.marketvalue;
drop table if exists scout_pro_development.transfer;
drop table if exists scout_pro_development.transfermarktinfo;
drop table if exists scout_pro_development.psmlInfo;
drop table if exists scout_pro_development.player;



create table if not exists scout_pro_development.player (
	id int auto_increment not null,
    transfermarktUrl varchar(256) null,
    whoScoredUrl varchar(256) null,
    pesDbUrl varchar(256) null,
    psmlUrl varchar(256) null,
    primary key(id))
    engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.transfermarktInfo(
	id int auto_increment not null,
    playerName varchar(256) not null,
    dateOfBirth varchar(15) not null,
    age int not null,
    nationality varchar(50) not null,
    nationalTeam varchar(50) not null,
    clubTeam varchar(50) not null,
    contractUntil varchar(12) not null,
    position varchar(15) not null,
    lastChange timestamp not null,
    playerId int not null,
    primary key(id),
    index indtmtoplayer(playerId ASC),
    constraint tm_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.marketValue(
	id int auto_increment not null,
    value decimal(15,2) not null,
    datePoint datetime not null,
    transfermarktInfoId int not null,
    primary key(id),
    index indmvtotm(transfermarktInfoId ASC),
    constraint mv_tm foreign key(transfermarktInfoId) references scout_pro_development.transfermarktinfo(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.transfer(
	id int auto_increment not null,
    fromTeam varchar(50) not null,
    toTeam varchar(50) not null,
    dateOfTransfer datetime not null,
    marketValue decimal(15,2) not null,
    transferFee decimal(15,2) not null,
    transfermarktInfoId int not null,
    primary key(id),
    index indtrtotm(transfermarktInfoId ASC),
    constraint tr_tm foreign key(transfermarktInfoId) references scout_pro_development.transfermarktinfo(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.whoScoredInfo(
	id int auto_increment not null,
    season varchar(10) not null,
    lastChange timestamp not null,
    playerId int not null,
    primary key(id),
    index indwstoplayer(playerId ASC),
    constraint ws_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.coreStats(
	id int auto_increment not null,
    competition varchar(100) not null,
    startedApps varchar(3) not null,
    subApps varchar(3) not null,
    mins varchar(4) not null,
    goals varchar(3) not null,
    assists varchar(3) not null,
    yellowCards varchar(2) not null,
    redCards varchar(2) not null,
    shotsPerGame varchar(4) not null,
    passSuccess varchar(5) not null,
    aerialsWon varchar(4) not null,
    manOfTheMatch varchar(3) not null,
    rating varchar(5) not null,
    whoScoredInfoId int not null,
    primary key(id),
    index indcstows(whoScoredInfoId ASC),
    constraint cs_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.positionPlayedStats(
	id int auto_increment not null,
    position varchar(3) not null,
    apps int not null,
    goals int not null,
    assists int not null,
    rating decimal(5,2) not null,
	whoScoredInfoId int not null,
	primary key(id),
    index indppstows(whoScoredInfoId ASC),
    constraint pps_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.characteristic(
	id int not null,
    strengths text,
    weaknesses text,
    styleOfPlay text,
	primary key(id),
    constraint ch_ws foreign key(id) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.game(
	id int auto_increment not null,
    competition varchar(5) not null,
    dateOfGame datetime not null,
    team1 varchar(50) not null,
    team2 varchar(50) not null,
    result varchar(5) not null,
    manOfTheMatch boolean not null,
    goals varchar(3) not null,
    assists varchar(3) not null,
    yellowCard boolean not null,
    redCard boolean not null,
    minutesPlayed varchar(3) not null,
    rating varchar(3) not null,
	whoScoredInfoId int not null,
    primary key(id),
    index indgtows(whoScoredInfoId ASC),
    constraint g_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.pesDbInfo(
	id int auto_increment not null,
    season varchar(5) not null,
    teamName varchar(50) not null,
    foot varchar(5) not null,
    weekCondition char(1) not null,
    primaryPosition varchar(3) not null,
    otherPositions varchar(40) null,
    playingStyle text null,
    playerSkills text null,
    comPlayingStyles text null,
    lastChange timestamp not null,
    playerId int not null,
    primary key(id),
    index indpdbtoplayer(playerId ASC),
    constraint pdb_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action)
	engine = InnoDB
    collate = utf8_unicode_ci;
    
create table if not exists scout_pro_development.psmlInfo(
	id int auto_increment not null,
    teamName varchar(50) not null default 'Free Agent',
    teamValue decimal(15,2) null default 00.00,
    lastChange timestamp not null,
    playerId int not null,
    primary key(id),
    index indpstoplayer(playerId),
    constraint ps_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action)
    engine = InnoDB
    collate = utf8_unicode_ci;
 