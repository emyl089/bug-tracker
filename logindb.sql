SELECT * FROM user_account;
SELECT * FROM user_info;

SELECT * FROM user_account WHERE username = 'angrygun25' AND password = 'babayaga';

INSERT INTO user_account (username, password, email) VALUES ('angrygun25','babayaga','john_wick@gmail.com');
INSERT INTO user_info (firstname, lastname, phone_number, gender) VALUES ('John','Wick','+120-4230-2222','Male');

DELETE FROM user_account WHERE account_id > 2;
DELETE FROM user_info WHERE iduser > 2;

SET @count = 0;
UPDATE user_account SET user_account.account_id = @count:= @count + 1 WHERE username = 'angrygun25';
ALTER TABLE user_account auto_increment = 1;