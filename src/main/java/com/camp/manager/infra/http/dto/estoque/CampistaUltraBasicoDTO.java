package com.camp.manager.infra.http.dto.estoque;

import com.camp.manager.domain.entity.CampistaEntityDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampistaUltraBasicoDTO{
        private Long id;
        private String nome;
        private String tamanhoCamisetaComprado;

        public CampistaUltraBasicoDTO(CampistaEntityDomain campista) {
            this.id = campista.id();
            this.nome = campista.pessoa().nome();
            this.tamanhoCamisetaComprado = campista.camiseta().tamanhoCamiseta();
        }

        public static List<CampistaUltraBasicoDTO> converter(List<CampistaEntityDomain> listCampistas) {
            return listCampistas.stream()
                    .map(CampistaUltraBasicoDTO::new)
                    .toList();
        }

}
