CREATE USER 'cs532_user'@'localhost:3307' IDENTIFIED BY 'pswdForBankDb';

GRANT ALL PRIVILEGES ON bankdb.* TO 'cs532_user'@'localhos:3307t' WITH GRANT OPTION;

SHOW GRANTS FOR 'cs532_user'@'localhost:3307';