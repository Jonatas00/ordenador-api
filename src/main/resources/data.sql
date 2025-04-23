/*test*/

INSERT INTO tb_role (id, name)
values (1, 'ROLE_ADMIN') ON CONFLICT(id) DO NOTHING;

INSERT INTO tb_role (id, name)
values (2, 'ROLE_USER') ON CONFLICT(id) DO NOTHING;