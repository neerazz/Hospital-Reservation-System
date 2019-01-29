create table resource
(
   resourceid IDENTITY not null,
   resourcename varchar(255) not null,
   primary key(resourceid)
);

create table booking
(
   bookingid IDENTITY primary key,
   bookingdate date not null ,
   bookingslot varchar(255),
   resourceid integer not null
);