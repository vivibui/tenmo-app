BEGIN TRANSACTION; 
-- 3 users in total
INSERT INTO tenmo_user(username, password_hash)
VALUES ('user1', '$2a$10$I1mZ4KfFvOPfQGfdic/SW.5.FG08kvZd0Jw2CPgi4W4urSLrAV49q') RETURNING user_id; 
INSERT INTO tenmo_user(username, password_hash)
VALUES ('user2', '$2a$10$MPv.hxtlxCSiJ3MdYGgqh.Dzdp0XkpBZND9d.Cv5ZroK77ynTwl1e') RETURNING user_id; 
INSERT INTO tenmo_user(username, password_hash)
VALUES ('user3', '$2a$10$MHhutG8HCDTYw19VHWdbF.XFQJPv7.DO5mA61OYVQJy6BWuTCOy1S') RETURNING user_id;
INSERT INTO tenmo_user(username, password_hash)
VALUES ('user4', '$2a$10$aWb60sU8Teepdr07UxaLG.HfAtvcOtjwAULUFqKPdseJt0HT2gtnS') RETURNING user_id;

-- User 1 has 3 accounts, user 2 has 2 accounts, user 3 has 1 account
INSERT INTO account (user_id, balance, created_at)
VALUES (1001,10000,'2023-01-09 15:00') RETURNING account_id; 
INSERT INTO account (user_id, balance, created_at)
VALUES (1001,25000,'2023-02-15 19:34') RETURNING account_id; 
INSERT INTO account (user_id, balance, created_at)
VALUES (1001,100000,'2023-03-20 05:12') RETURNING account_id; 
INSERT INTO account (user_id, balance, created_at)
VALUES (1002,2500,'2023-05-11 07:42') RETURNING account_id; 
INSERT INTO account (user_id, balance, created_at)
VALUES (1002,8500,'2023-06-17 12:16') RETURNING account_id; 
INSERT INTO account (user_id, balance, created_at)
VALUES (1003,90000,'2022-09-09 16:43') RETURNING account_id; 
INSERT INTO account (user_id, balance, created_at)
VALUES (1004,80000,'2023-03-09 15:49') RETURNING user_id;

-- Pending Request from user 2(2004) to user 1(2001) with amount 500  
INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_at)
VALUES(1,1,2004,2001,500,'2023-07-27 10:10') RETURNING transfer_id; 
-- Pending Send from user 1(2002) to user 3(2006) with amount 1000
INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_at)
VALUES(2,1,2002,2006,1000,'2023-07-26 21:37') RETURNING transfer_id; 
-- Approved Request from user 1(2003) to user 3(2006) with amount 200
INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_at)
VALUES(1,2,2003,2006,200,'2023-05-12 23:49') RETURNING transfer_id; 
-- Approved Send from user 3(2006) to user 2(2005) with amount 15000
INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_at)
VALUES(2,2,2006,2005,15000,'2023-04-14 14:14') RETURNING transfer_id; 
-- Rejected Request from user 3(2006) to user 2(2004) with amount 10000
INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_at)
VALUES(1,3,2006,2004,10000,'2023-03-18 11:18') RETURNING transfer_id; 
-- Rejected Send from user 2(2005) to user 3(2006) with amount 1500
INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount, created_at)
VALUES(2,3,2005,2006,1500,'2023-06-03 19:22') RETURNING transfer_id; 

COMMIT; 


