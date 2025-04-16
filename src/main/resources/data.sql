/*test*/

INSERT INTO tb_role (name)
values ('ROLE_ADMIN') ON CONFLICT(id) DO NOTHING;

INSERT INTO tb_role (name)
values ('ROLE_USER') ON CONFLICT(id) DO NOTHING;