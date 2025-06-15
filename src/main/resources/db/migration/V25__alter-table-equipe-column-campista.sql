ALTER TABLE equipe DROP COLUMN IF EXISTS id_campista;
ALTER TABLE equipe ADD COLUMN id_funcionario_lider BIGINT NOT NULL;