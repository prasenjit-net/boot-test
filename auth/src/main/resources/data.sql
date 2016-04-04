INSERT INTO USERS(USERNAME, NAME, PASSWORD, EXPIRED, LOCKED, ENABLED)
    VALUES ('admin', 'Admin', '$2a$10$DZ0hQV2hDo7l74tcbsJcBOuVL8wWDHZWjRorWOVW8JZa/azyXWeXG', 'Y', 'Y', 'Y');

INSERT INTO SCOPES(USERNAME, AUTHORITY)
    VALUES ('admin', 'admin');

INSERT INTO CLIENTS(CLIENT_ID, CLIENT_SECRET, USERNAME, SECRET_REQUIRED, GRANT_TYPES, REDIRECT_URI)
    VALUES('client', 'client', 'admin', 'Y', 'authorization_code,password,refresh_code', 'http://localhost:8080/')