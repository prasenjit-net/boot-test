CREATE TABLE "USERS"(
  "USERNAME" VARCHAR2(100) PRIMARY KEY,
  "NAME" VARCHAR2(255) NOT NULL,
  "PASSWORD" VARCHAR2(100) NOT NULL,
  "EXPIRED" VARCHAR2(1) NOT NULL,
  "LOCKED" VARCHAR2(1) NOT NULL,
  "ENABLED" VARCHAR2(1) NOT NULL,
  "PASSWORD_CHANGE_DATE" DATETIME
);

CREATE TABLE "SCOPES"(
  "USERNAME" VARCHAR2(100) NOT NULL,
  "AUTHORITY" VARCHAR2(50) NOT NULL
);