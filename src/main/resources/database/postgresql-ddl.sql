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
	id serial primary key,
    myPlayer boolean not null,
    transfermarktUrl varchar(256) null unique,
    whoScoredUrl varchar(256) null,
    pesDbUrl varchar(256) null,
    psmlUrl varchar(256) null,
    lastMeasured timestamp not null);
    
create table if not exists scout_pro_development.transfermarktInfo(
	id serial primary key,
    playerName varchar(256) not null,
    dateOfBirth varchar(15) not null,
    age int not null,
    nationality varchar(50) not null,
    nationalTeam varchar(50) not null,
    currentValue numeric(15,2) not null,
    lastChangedCurrentValue date not null,
    clubTeam varchar(50) not null,
    contractUntil varchar(12) not null,
    position varchar(40) not null,
    lastMeasured timestamp not null,
    constraint tm_player foreign key(id) references scout_pro_development.player(id) on delete no action on update no action);

create table if not exists scout_pro_development.marketValue(
	id serial primary key,
    worth numeric(15,2) not null,
    datePoint date not null,
    clubTeam varchar(50) not null,
    transfermarktInfoId int not null,
    constraint mv_tm foreign key(transfermarktInfoId) references scout_pro_development.transfermarktinfo(id) on delete no action on update no action);

	create index indmvtotm on scout_pro_development.marketValue(transfermarktInfoId);

create table if not exists scout_pro_development.transfer(
	id serial primary key,
    fromTeam varchar(50) not null,
    toTeam varchar(50) not null,
    dateOfTransfer date not null,
    marketValue varchar(25) not null,
    transferFee varchar(25) not null,
    transfermarktInfoId int not null,
    constraint tr_tm foreign key(transfermarktInfoId) references scout_pro_development.transfermarktinfo(id) on delete no action on update no action);

	create index indtrtotm on scout_pro_development.transfer(transfermarktInfoId);

create table if not exists scout_pro_development.whoScoredInfo(
	id serial primary key,
    season varchar(10) not null,
    lastMeasured timestamp not null,
    playerId int not null,
    constraint ws_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action);

	create index indwstoplayer on scout_pro_development.whoScoredInfo(playerId);

create table if not exists scout_pro_development.coreStats(
	id serial primary key,
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
    constraint cs_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

	create index indcstows on scout_pro_development.coreStats(whoScoredInfoId);

create table if not exists scout_pro_development.positionPlayedStats(
	id serial primary key,
    position varchar(3) not null,
    apps int not null,
    goals int not null,
    assists int not null,
    rating numeric(5,2) not null,
	whoScoredInfoId int not null,
    constraint pps_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

	create index indppstows on scout_pro_development.positionPlayedStats(whoScoredInfoId);

create table if not exists scout_pro_development.characteristic(
	id serial primary key,
    strengths text,
    weaknesses text,
    styleOfPlay text,
    constraint ch_ws foreign key(id) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

create table if not exists scout_pro_development.game(
	id serial primary key,
    competition varchar(50) not null,
    dateOfGame date not null,
    team1 varchar(50) not null,
    team2 varchar(50) not null,
    result varchar(5) not null,
    manOfTheMatch boolean not null,
    goals varchar(5) not null,
    assists varchar(5) not null,
    yellowCard boolean not null,
    redCard boolean not null,
    minutesPlayed varchar(5) not null,
    rating varchar(5) not null,
	whoScoredInfoId int not null,
    constraint g_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

	create index indgtows on scout_pro_development.game(whoScoredInfoId);

create table if not exists scout_pro_development.pesDbInfo(
	id serial primary key,
    season varchar(5) not null,
    pesDbName varchar(256) not null,
    teamName varchar(50) not null,
    foot varchar(5) not null,
    weekCondition varchar(1) not null,
    primaryPosition varchar(3) not null,
    positionNumberValue int not null,
    otherStrongPositions varchar(50) null,
    otherWeakPositions varchar(50) null,
    attackingProwess int not null,
    ballControl int not null,
    dribbling int not null,
    lowPass int not null,
    loftedPass int not null,
    finishing int not null,
    placeKicking int not null,
    swerve int not null,
    header int not null,
    defensiveProwess int not null,
    ballWinning int not null,
    kickingPower int not null,
    speed int not null,
    explosivePower int not null,
    bodyControl int not null,
    physicalContact int not null,
    jump int not null,
    stamina int not null,
    goalkeeping int not null,
    catching int not null,
    clearing int not null,
    reflexes int not null,
    coverage int not null,
    form int not null,
    injuryResistance int not null,
    weakFootUsage int not null,
    weakFootAccuracy int not null,
    overallRating int not null,
    playingStyle varchar(20) not null,
    playerSkills text not null,
    comPlayingStyles text not null,
    lastMeasured timestamp not null,
    playerId int not null,
    constraint pdb_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action);

	create index indpdbtoplayer on scout_pro_development.pesDbInfo(playerId);

create table if not exists scout_pro_development.psmlInfo(
	id serial primary key,
    teamName varchar(50) not null default 'Free Agent',
    teamValue numeric(15,2) null default 00.00,
    lastMeasured timestamp not null,
    playerId int not null,
    constraint ps_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action);

 	create index indpstoplayer on scout_pro_development.psmlInfo(playerId);create schema if not exists scout_pro_development;

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
	id serial primary key,
    myPlayer boolean not null,
    transfermarktUrl varchar(256) null unique,
    whoScoredUrl varchar(256) null,
    pesDbUrl varchar(256) null,
    psmlUrl varchar(256) null,
    lastMeasured timestamp not null);

create table if not exists scout_pro_development.transfermarktInfo(
	id serial primary key,
    playerName varchar(256) not null,
    dateOfBirth varchar(15) not null,
    age int not null,
    nationality varchar(50) not null,
    nationalTeam varchar(50) not null,
    currentValue numeric(15,2) not null,
    lastChangedCurrentValue date not null,
    clubTeam varchar(50) not null,
    contractUntil varchar(12) not null,
    position varchar(40) not null,
    lastMeasured timestamp not null,
    constraint tm_player foreign key(id) references scout_pro_development.player(id) on delete no action on update no action);

create table if not exists scout_pro_development.marketValue(
	id serial primary key,
    worth numeric(15,2) not null,
    datePoint date not null,
    clubTeam varchar(50) not null,
    transfermarktInfoId int not null,
    constraint mv_tm foreign key(transfermarktInfoId) references scout_pro_development.transfermarktinfo(id) on delete no action on update no action);

	create index indmvtotm on scout_pro_development.marketValue(transfermarktInfoId);

create table if not exists scout_pro_development.transfer(
	id serial primary key,
    fromTeam varchar(50) not null,
    toTeam varchar(50) not null,
    dateOfTransfer date not null,
    marketValue varchar(25) not null,
    transferFee varchar(25) not null,
    transfermarktInfoId int not null,
    constraint tr_tm foreign key(transfermarktInfoId) references scout_pro_development.transfermarktinfo(id) on delete no action on update no action);

	create index indtrtotm on scout_pro_development.transfer(transfermarktInfoId);

create table if not exists scout_pro_development.whoScoredInfo(
	id serial primary key,
    season varchar(10) not null,
    lastMeasured timestamp not null,
    playerId int not null,
    constraint ws_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action);

	create index indwstoplayer on scout_pro_development.whoScoredInfo(playerId);

create table if not exists scout_pro_development.coreStats(
	id serial primary key,
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
    constraint cs_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

	create index indcstows on scout_pro_development.coreStats(whoScoredInfoId);

create table if not exists scout_pro_development.positionPlayedStats(
	id serial primary key,
    position varchar(3) not null,
    apps int not null,
    goals int not null,
    assists int not null,
    rating numeric(5,2) not null,
	whoScoredInfoId int not null,
    constraint pps_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

	create index indppstows on scout_pro_development.positionPlayedStats(whoScoredInfoId);

create table if not exists scout_pro_development.characteristic(
	id serial primary key,
    strengths text,
    weaknesses text,
    styleOfPlay text,
    constraint ch_ws foreign key(id) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

create table if not exists scout_pro_development.game(
	id serial primary key,
    competition varchar(50) not null,
    dateOfGame date not null,
    team1 varchar(50) not null,
    team2 varchar(50) not null,
    result varchar(5) not null,
    manOfTheMatch boolean not null,
    goals varchar(5) not null,
    assists varchar(5) not null,
    yellowCard boolean not null,
    redCard boolean not null,
    minutesPlayed varchar(5) not null,
    rating varchar(5) not null,
	whoScoredInfoId int not null,
    constraint g_ws foreign key(whoScoredInfoId) references scout_pro_development.whoscoredinfo(id) on delete no action on update no action);

	create index indgtows on scout_pro_development.game(whoScoredInfoId);

create table if not exists scout_pro_development.pesDbInfo(
	id serial primary key,
    season varchar(5) not null,
    pesDbName varchar(256) not null,
    teamName varchar(50) not null,
    foot varchar(5) not null,
    weekCondition varchar(1) not null,
    primaryPosition varchar(3) not null,
    positionNumberValue int not null,
    otherStrongPositions varchar(50) null,
    otherWeakPositions varchar(50) null,
    attackingProwess int not null,
    ballControl int not null,
    dribbling int not null,
    lowPass int not null,
    loftedPass int not null,
    finishing int not null,
    placeKicking int not null,
    swerve int not null,
    header int not null,
    defensiveProwess int not null,
    ballWinning int not null,
    kickingPower int not null,
    speed int not null,
    explosivePower int not null,
    bodyControl int not null,
    physicalContact int not null,
    jump int not null,
    stamina int not null,
    goalkeeping int not null,
    catching int not null,
    clearing int not null,
    reflexes int not null,
    coverage int not null,
    form int not null,
    injuryResistance int not null,
    weakFootUsage int not null,
    weakFootAccuracy int not null,
    overallRating int not null,
    playingStyle varchar(20) not null,
    playerSkills text not null,
    comPlayingStyles text not null,
    lastMeasured timestamp not null,
    playerId int not null,
    constraint pdb_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action);

	create index indpdbtoplayer on scout_pro_development.pesDbInfo(playerId);

create table if not exists scout_pro_development.psmlInfo(
	id serial primary key,
    teamName varchar(50) not null default 'Free Agent',
    teamValue numeric(15,2) null default 00.00,
    lastMeasured timestamp not null,
    playerId int not null,
    constraint ps_player foreign key(playerId) references scout_pro_development.player(id) on delete no action on update no action);

 	create index indpstoplayer on scout_pro_development.psmlInfo(playerId);