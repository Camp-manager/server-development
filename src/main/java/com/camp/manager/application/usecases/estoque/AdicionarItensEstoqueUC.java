package com.camp.manager.application.usecases.estoque;

import com.camp.manager.application.gateway.EstoqueGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.EstoqueEntityDomain;
import com.camp.manager.domain.entity.ItemEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.LimitOverflowException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.estoque.AdicionarItensRequest;
import com.camp.manager.infra.http.request.estoque.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdicionarItensEstoqueUC implements UseCase<AdicionarItensRequest, MethodResponse<Void>> {

    private final EstoqueGateway estoqueGateway;

    @Autowired
    public AdicionarItensEstoqueUC(EstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Override
    public MethodResponse<Void> execute(AdicionarItensRequest input) {
        boolean estoqueExists = this.estoqueGateway.estoqueEhExistente(input.getIdEstoque());
        if(!estoqueExists) throw new NotFoundException("Estoque com is " + input.getIdEstoque() + " não encontrado!");

        EstoqueEntityDomain estoqueEntityDomain = this.estoqueGateway.buscarEstoquePorId(input.getIdEstoque());
        List<ItemEntityDomain> items = this.converterItens(input.getItens());

        Long quantidadeTotalParaAdicionar = items.stream()
                .mapToLong(ItemEntityDomain::quantidade)
                .sum();

        if((estoqueEntityDomain.quantidade() + quantidadeTotalParaAdicionar) > estoqueEntityDomain.limite()) {
            throw new LimitOverflowException("A quantidade total de itens excede o limite do estoque.");
        }

        EstoqueEntityDomain estoqueAtualizado = new EstoqueEntityDomain(
                estoqueEntityDomain.id(),
                estoqueEntityDomain.localEstoque(),
                estoqueEntityDomain.quantidade() + quantidadeTotalParaAdicionar,
                estoqueEntityDomain.limite(),
                estoqueEntityDomain.itens() != null ? estoqueEntityDomain.itens() : List.of()
        );

        estoqueAtualizado.itens().addAll(items);

        this.estoqueGateway.salvarEstoque(estoqueAtualizado);

        return new MethodResponse<>(200, "Itens Adicionados com Sucessor", null);
    }

    private List<ItemEntityDomain> converterItens(List<ItemRequest> itens) {
        return itens
                .stream()
                .map(item -> new ItemEntityDomain(
                        null,
                        item.getDescricao(),
                        item.getQuantidade(),
                        item.getTipoItem(),
                        item.getValidade(),
                        item.getValor()
                )).collect(Collectors.toList());
    }
}
