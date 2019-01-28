create table resource
(
   resourceId IDENTITY not null,
   name varchar(255) not null,
   available_count integer,
   primary key(resourceId)
);

create table Booking
(
   bookingId IDENTITY primary key,
   bookingDate date not null ,
   bookingSlot varchar(255),
   resourceid integer not null
);