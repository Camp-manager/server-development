ALTER TABLE public.atividade DROP CONSTRAINT fk_atividade_cronograma;

ALTER TABLE atividade
    ADD CONSTRAINT fk_atividade_cronograma_equipe FOREIGN KEY (id_cronograma) REFERENCES cronograma_equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE;