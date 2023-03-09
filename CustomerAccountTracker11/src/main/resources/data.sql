SELECT * FROM CUSTOMER;

insert into account (id, account_number, account_type, balance) values (101, 'HDFC10101023','SAVING' , 1000.50);
insert into account (id, account_number, account_type, balance) values (102, 'HDFC10134424','CURRENT' , 14.50);
insert into account (id, account_number, account_type, balance) values (103, 'HDFC10341025','SAVING' , 9100.50);
insert into account (id, account_number, account_type, balance) values (104, 'HDFC10341006','SAVING' , 9200.50);
insert into account (id, account_number, account_type, balance) values (105, 'HDFC10341007','SAVING' , 9300.50);
insert into account (id, account_number, account_type, balance) values (106, 'HDFC10341008','SAVING' , 9400.50);




insert into customer (id, adhar_number, gender, mobile_number, name) values (201, '2345-6543-5667', 'MALE', '34598593284', 'MR.XUY');
insert into customer (id, adhar_number, gender, mobile_number, name) values (202, '43545-6543-523452', 'FEMALE', '43455593284', 'Ms.A');
insert into customer (id, adhar_number, gender, mobile_number, name) values (204, '43545-6543-5767', 'MALE', '455932453284', 'Ms.B');
insert into customer (id, adhar_number, gender, mobile_number, name) values (205, '43545-6543-3453', 'FEMALE', '45675593284', 'Ms.X');


insert into customer_account (customer_id, account_id) values (201, 101);
insert into customer_account (customer_id, account_id) values (201, 102);
insert into customer_account (customer_id, account_id) values (202, 103);
insert into customer_account (customer_id, account_id) values (202, 104);
insert into customer_account (customer_id, account_id) values (204, 105);
insert into customer_account (customer_id, account_id) values (205, 106);