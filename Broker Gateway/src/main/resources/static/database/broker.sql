/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/2 14:34:55                            */
/*==============================================================*/


drop table if exists Completion;

drop table if exists Initiator;

drop table if exists OrderBlotter;

drop table if exists Product;

drop table if exists user;

/*==============================================================*/
/* Table: Completion                                            */
/*==============================================================*/
create table Completion
(
   CompletionId         int not null,
   TraderId             int,
   Side                 varchar(4),
   primary key (CompletionId)
);

/*==============================================================*/
/* Table: Initiator                                             */
/*==============================================================*/
create table Initiator
(
   InitiatorId          int not null,
   TraderId             int,
   Side                 varchar(4),
   primary key (InitiatorId)
);

/*==============================================================*/
/* Table: OrderBlotter                                          */
/*==============================================================*/
create table OrderBlotter
(
   TradeId              int not null,
   Broker               varchar(10),
   ProductId            int,
   Period               date,
   Price                int,
   Quantity             int,
   Ini_Trader           varchar(20),
   Ini_Side             varchar(4),
   Cpl_Trader           varchar(20),
   Cpl_Side             varchar(4),
   primary key (TradeId)
);

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product
(
   ProductId            int,
   Name                 varchar(20),
   Period               date,
   Introduction         varchar(100)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userId               int,
   username             varchar(20),
   password             varchar(80),
   role                 varchar(10)
);

