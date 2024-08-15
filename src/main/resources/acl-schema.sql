
CREATE TABLE IF NOT EXISTS acl_sid (
  id serial,
  principal BOOLEAN NOT NULL,
  sid varchar(100) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_1 UNIQUE (sid,principal)
);

CREATE TABLE IF NOT EXISTS acl_class (
  id serial,
  class varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_2 UNIQUE (class)
);

CREATE TABLE IF NOT EXISTS acl_entry (
  id serial,
  acl_object_identity int NOT NULL,
  ace_order int NOT NULL,
  sid int NOT NULL,
  mask int NOT NULL,
  granting BOOLEAN NOT NULL,
  audit_success BOOLEAN NOT NULL,
  audit_failure BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_4 UNIQUE (acl_object_identity,ace_order)
);

CREATE TABLE IF NOT EXISTS acl_object_identity (
  id serial,
  object_id_class int NOT NULL,
  object_id_identity varchar NOT NULL,
  parent_object int DEFAULT NULL,
  owner_sid int DEFAULT NULL,
  entries_inheriting BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_3 UNIQUE (object_id_class,object_id_identity)
);

ALTER TABLE acl_entry
ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id);

ALTER TABLE acl_entry
ADD FOREIGN KEY (sid) REFERENCES acl_sid(id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);