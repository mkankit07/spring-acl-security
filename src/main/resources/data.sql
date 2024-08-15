INSERT INTO public.users (user_id, created_at, updated_at, status,username, password,created_by,updated_by)
VALUES (1, now(), now(), 'ACTIVE', 'bsl.admin@yopmail.com','$2a$10$Ca5U16aLZYt38D/L32sgwexTjZBGzYtnAuN0lUBAFKcnYmrUouaXq',1,1);

INSERT INTO public.roles (role_id, name, created_at, updated_at, status,created_by,updated_by)
VALUES (1, 'SUPER_SITE_ADMIN' ,now(), now(), 'ACTIVE',1,1);

INSERT INTO public.roles (role_id, name, created_at, updated_at, status,created_by,updated_by)
VALUES (2, 'SITE_ADMIN' ,now(), now(), 'ACTIVE',1,1);

INSERT INTO public.roles (role_id, name, created_at, updated_at, status,created_by,updated_by)
VALUES (3, 'USER' ,now(), now(), 'ACTIVE',1,1);


INSERT INTO public.users_roles(role_id,user_id) VALUES (1, 1);

INSERT INTO public.users (user_id, created_at, updated_at, status,username, password,created_by,updated_by)
VALUES (2 , now(), now(), 'ACTIVE', 'admin@yopmail.com','$2a$10$Ca5U16aLZYt38D/L32sgwexTjZBGzYtnAuN0lUBAFKcnYmrUouaXq',1,1);

INSERT INTO public.users_roles(role_id,user_id) VALUES (2, 2);


INSERT INTO public.users (user_id, created_at, updated_at, status,username, password, created_by,updated_by)
VALUES (3 , now(), now(), 'ACTIVE', 'user@yopmail.com','$2a$10$Ca5U16aLZYt38D/L32sgwexTjZBGzYtnAuN0lUBAFKcnYmrUouaXq',1,1);

INSERT INTO public.users_roles(role_id,user_id) VALUES (3, 3);

INSERT INTO acl_sid (id, principal, sid) VALUES
(1,false, 'SUPER_SITE_ADMIN'),
(2,false, 'SITE_ADMIN'),
(3,false, 'user@yopmail.com');

INSERT INTO acl_class (id, class) VALUES (1, 'com.example.springaclsecurity.model.entities.Site');

INSERT INTO acl_class (id, class) VALUES (2, 'com.example.springaclsecurity.model.entities.ShortLink');

INSERT INTO public.acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (1, 1, '1', NULL, 1, 1);
INSERT INTO public.acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 1, 0, 1, 1, 1, false, false);


INSERT INTO public.sites (site_id, created_at, status, updated_at, description, hero_image_url, location_id, title, created_by, updated_by) VALUES (1, now(), 'ACTIVE', now(), 'root site', 'https://baps.org/', NULL, 'root',1,1);