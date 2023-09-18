drop database if exists Clinic;
create database Clinic;
use Clinic;



drop table if exists Patients; 
create table Patients(
Pname varchar(32),
Pgender varchar(16),
Pemail varchar(64) not null,
P_age int,
Pmedicalstate varchar(64),
primary key (Pemail));

insert into Patients values('Mohammad Nael','male','mohammad12@gmail.com',23,'Diabetes');
insert into Patients values('Adel Saleh','male','salehadel@gmail.com',18,'Pressure');
insert into Patients values('Yasmine Hadi','female','yasmeen45@gmail.com',33,null);
insert into Patients values('Tala Jad','female','tala2001@gmail.com',20,'Pregnant');
insert into Patients values('Taher Hussien','male','tahertaher@gmail.com',52,null);
insert into Patients values('Naya Ali','female','nayaali89@gmail.com',33,null);
insert into Patients values('Mahmoud Almasri','male','mohamoudmasri@gmail.com',23,'Insulin sensitivity');
insert into Patients values('Zahia Zaher','female','zaherzahia12@gmail.com',29,null);
insert into Patients values('Mohammad Ramzi','male','mohammad120@gmail.com',23,'Diabetes');
insert into Patients values('Laila Barakat','female','laila1999@gmail.com',22,null);
insert into Patients values('Mohammad Alahmad','male','mohammadmohammad00@gmail.com',12,null);
insert into Patients values('Dana Alhaj','female','danaalhaj@gmail.com',35,'Pregnant');
insert into Patients values('Sara Alkhatteb','female','alkhateebsara001@gmail.com',12,'Insulin sensitivity');
insert into Patients values('Saad Barakat','male','saad0000@gmail.com',35,'Diabetes');
insert into Patients values('Donia Almajed','female','doniaalmajedd@gmail.com',55,'Pressure');
insert into Patients values('Mays Husni','female','mayshusnihusni@gmail.com',19,null);
insert into Patients values('Bana Raed','female','banabana10@gmail.com',10,null);

select * from Patients;
select AVG(P_age) from Patients;
select  p.Pname,p.P_age from Patients p where p.P_age > (select AVG(P_age) from Patients);

drop table if exists Diagnosis; 
create table Diagnosis(
Did int,
Dtype varchar(32),
primary key(Did));

insert into Diagnosis values(1,'Impacted Teeth');
insert into Diagnosis values(2,'Nerves');
insert into Diagnosis values(3,'Anodontia'); 
insert into Diagnosis values(4,'Supernumerary Teeth');
insert into Diagnosis values(5,'Motteled Teeth');
insert into Diagnosis values(6,'Embedded Teeth');
insert into Diagnosis values(7,'Dental Root Caries');
insert into Diagnosis values(8,'Radicular Cyst');
insert into Diagnosis values(9,'Periodontosis');
insert into Diagnosis values(10,'Partial Loss Of Teeth');
insert into Diagnosis values(11,'Retained Dental Root');
insert into Diagnosis values(12,'Pulpitis');
insert into Diagnosis values(13,'Cracked Tooth');
insert into Diagnosis values(14,'Ankylosis of Tooth');


select * from Diagnosis;

drop table if exists Treatment; 
create table Treatment(
Treatment_id int,
Did int,
Ttype varchar(32),
Treatment_Cost_Paid_Amount double,
Treatment_Cost_Remaining_Amount double,
primary key(Treatment_id),
foreign key (Did) references Diagnosis(Did) on delete cascade);


insert into Treatment values(100,1,'Metal Crown',500,100);
insert into Treatment values(200,2,'Zironia',350,0);
insert into Treatment values(300,2,'Pulptomy',400,250);
insert into Treatment values(400,4,'All Ceramic',300,120);
insert into Treatment values(500,5,'Heavy Calculus',100,300);
insert into Treatment values(600,6,'Endodontic TRT',450,200);
insert into Treatment values(700,7,'Non vital Pulptomy',300,200);
insert into Treatment values(800,8,'Minor Calculus',600,0);
insert into Treatment values(900,9,'Post and core',50,250);
insert into Treatment values(1000,10,'Scaling',300,300);
insert into Treatment values(1100,11,'Loose',200,200);
insert into Treatment values(1200,12,'Abscess',150,300);
insert into Treatment values(1300,13,'Pocket cleaning',700,300);
insert into Treatment values(1400,14,'Cyst',250,250);
insert into Treatment values(1500,1,'Granuloma',200,0);
insert into Treatment values(1600,2,'Ulcered Gingiva',150,0);
insert into Treatment values(1700,3,'Swajed Crown',400,300);

select * from Treatment;

drop table if exists Treatment_To_Patient; 
create table Treatment_To_Patient(
Pemail varchar(64),
Did int,
Teeth_Number int,
Treatment_id int,
primary key(Treatment_id,Pemail,Did),
foreign key (Pemail) references Patients(Pemail),
foreign key (Did) references Diagnosis(Did),
foreign key (Treatment_id) references Treatment(Treatment_id));

insert into Treatment_To_Patient values('mohammad12@gmail.com',2,1,1600);
insert into Treatment_To_Patient values('salehadel@gmail.com',3,14,300);
insert into Treatment_To_Patient values('yasmeen45@gmail.com',3,2,1700);
insert into Treatment_To_Patient values('tala2001@gmail.com',5,1,500);
insert into Treatment_To_Patient values('tahertaher@gmail.com',7,9,700);
insert into Treatment_To_Patient values('nayaali89@gmail.com',2,4,200);
insert into Treatment_To_Patient values('mohamoudmasri@gmail.com',11,6,1100);
insert into Treatment_To_Patient values('zaherzahia12@gmail.com',7,8,700);
insert into Treatment_To_Patient values('mohammad120@gmail.com',3,1,300);
insert into Treatment_To_Patient values('laila1999@gmail.com',9,14,900);
insert into Treatment_To_Patient values('mohammadmohammad00@gmail.com',3,3,300);
insert into Treatment_To_Patient values('danaalhaj@gmail.com',14,2,1400);
insert into Treatment_To_Patient values('alkhateebsara001@gmail.com',4,2,400);
insert into Treatment_To_Patient values('saad0000@gmail.com',3,10,1700);
insert into Treatment_To_Patient values('doniaalmajedd@gmail.com',6,7,600);
insert into Treatment_To_Patient values('mayshusnihusni@gmail.com',5,3,500);
insert into Treatment_To_Patient values('banabana10@gmail.com',2,13,200);

select * from Treatment_To_Patient;

drop table if exists Appointments; 
create table Appointments(
Appointment_id int,
Pemail varchar(64),
A_Date_Of_Last_visit date,
A_Date_Of_Next_visit date,
Appointment_time varchar(64),
A_Number_Of_Patient_On_waiting_List int,
primary key(Appointment_id),
foreign key (Pemail) references Patients(Pemail) on delete cascade);


insert into Appointments values(123,'mohammad12@gmail.com','2022-5-2','2022-6-2','10:00:00',1);
insert into Appointments values(234,'salehadel@gmail.com','2022-5-2','2022-6-1','11:30:00',1);
insert into Appointments values(456,'yasmeen45@gmail.com','2022-4-28','2022-6-2','1:45:00',1);
insert into Appointments values(678,'tala2001@gmail.com','2022-4-28','2022-6-6','12:20:00',5);
insert into Appointments values(789,'tahertaher@gmail.com','2022-6-1','2022-6-15','2:10:00',1);
insert into Appointments values(923,'nayaali89@gmail.com','2022-4-25','2022-6-5','3:00:00',2);
insert into Appointments values(544,'mohamoudmasri@gmail.com','2022-6-6','2022-6-12','3:45:00',4);
insert into Appointments values(211,'zaherzahia12@gmail.com','2022-6-1','2022-6-12','8:30:00',1);
insert into Appointments values(555,'mohammad120@gmail.com','2022-6-5','2022-6-18','4:35:00',4);
insert into Appointments values(233,'laila1999@gmail.com','2022-5-29','2022-6-11','10:15:00',6);
insert into Appointments values(111,'mohammadmohammad00@gmail.com','2022-6-3','2022-6-14','1:55:00',2);
insert into Appointments values(467,'danaalhaj@gmail.com','2022-6-7','2022-6-8','5:30:00',1);
insert into Appointments values(898,'alkhateebsara001@gmail.com','2022-6-15','2022-6-25','6:00:00',4);
insert into Appointments values(999,'saad0000@gmail.com','2022-6-15','2022-6-25','6:15:00',2);
insert into Appointments values(459,'doniaalmajedd@gmail.com','2022-6-6','2022-6-14','7:00:00',1);
insert into Appointments values(546,'mayshusnihusni@gmail.com','2022-6-17','2022-6-18','9:20:00',0);
insert into Appointments values(223,'banabana10@gmail.com','2022-6-15','2022-6-17','7:20:00',0);

select * from Appointments; 

drop table if exists Perscription_Medication; 
create table Perscription_Medication(
Perscription_Medication_id int,
Pemail varchar(64),
Patient_age int,
Medicine_name varchar(16),
Patient_name varchar(32),
Date_Of_Visit date,
Treatment_id int,
Tnotes varchar(64),
primary key(Perscription_Medication_id),
foreign key (Treatment_id) references Treatment(Treatment_id) on delete cascade,
foreign key (Pemail) references Patients(Pemail)on delete cascade);


insert into Perscription_Medication values(513,'mohammad12@gmail.com',23,'Amoxicillin','Mohammad Nael','2022-5-2',100,'Take medicine three times a day  after eating');
insert into Perscription_Medication values(211,'salehadel@gmail.com',18,'Amitriptyline','Adel Saleh','2022-5-2',300,'Take medicine two times a day  before eating');
insert into Perscription_Medication values(312,'yasmeen45@gmail.com',33,'Azithromycin','Yasmine Hadi','2022-4-28',400,'Take medicine three times a day  after eating');
insert into Perscription_Medication values(455,'tala2001@gmail.com',20,'Amoxicillin','Tala Jad','2022-4-28',600,'Take medicine two times a day  after eating');
insert into Perscription_Medication values(677,'tahertaher@gmail.com',52,'Valacyclovir','Taher Hussien','2022-6-1',500,'Take medicine two times a day  after eating');
insert into Perscription_Medication values(988,'nayaali89@gmail.com',33,'Amoxicillin','Naya Ali','2022-4-25',1200,'Take medicine  after breakfast');
insert into Perscription_Medication values(567,'mohamoudmasri@gmail.com',21,'Cephalexin','Mahmoud Almasri','2022-6-6',600,'Take medicine three times a day  after eating');
insert into Perscription_Medication values(123,'zaherzahia12@gmail.com',29,'Triazolam','Zahia Zaher','2022-6-1',1700,'Take medicine before eating');
insert into Perscription_Medication values(444,'mohammad120@gmail.com',23,'Ciprofloxacin','Mohammad Ramzi','2022-6-5',800,'Take medicine three times a day  before eating');
insert into Perscription_Medication values(344,'laila1999@gmail.com',22,'Diazepam','Laila Barakat','2022-5-29',1200,'Take medicine three times a day  after eating');
insert into Perscription_Medication values(666,'mohammadmohammad00@gmail.com',44,'Triazolam','Mohammad Alahmad','2022-6-3',500,'Take medicine two times a day  before eating');
insert into Perscription_Medication values(111,'danaalhaj@gmail.com',35,'Midazolam','Dana Alhaj','2022-6-8',400,'Take medicine  after eating');
insert into Perscription_Medication values(333,'alkhateebsara001@gmail.com',12,'Cevimeline','Sara Alkhateeb','2022-6-15',1100,'Take medicine two times a day  after eating');
insert into Perscription_Medication values(998,'saad0000@gmail.com',35,'Pilocarpine','Saad Barakat','2022-6-15',700,'Take medicine three times a day  after eating');
insert into Perscription_Medication values(766,'doniaalmajedd@gmail.com',55,'Ciprofloxacin','Donia Almajed','2022-6-6',300,'Take medicine two times a day  after eating');
insert into Perscription_Medication values(412,'mayshusnihusni@gmail.com',19,'Metronidazole','Mays Husni','2022-6-17',200,'Take medicine two times a day  after eating');
insert into Perscription_Medication values(889,'banabana10@gmail.com',10,'Clarithromycin','Bana Raed','2022-6-15',1000,'Take medicine three times a day  after eating');

select * from Perscription_Medication;
