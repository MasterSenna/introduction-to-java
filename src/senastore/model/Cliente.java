package senastore.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Item> carrinho;

    public Cliente(String nome) {
        this.nome = nome;
        this.carrinho = new ArrayList<>();
    }

    public void adicionarItemAoCarrinho(Item item, int quantidade, Estoque estoque) {
        int quantidadeDisponivel = estoque.getQuantidadeDisponivel(item);
        if (quantidadeDisponivel >= quantidade) {
            for (int i = 0; i < quantidade; i++) {
                carrinho.add(item);
            }
            System.out.println(quantidade + "x " + item.getNome() + " adicionado ao carrinho.");
        } else {
            System.out.println("Desculpe, a quantidade do item " + item.getNome() + " disponível em estoque é insuficiente.");
        }
    }

    public double calcularTotal() {
        return carrinho.stream().mapToDouble(Item::getPreco).sum();
    }

    public void realizarCompra(Estoque estoque, MetodoPagamento metodoPagamento) {
        double total = calcularTotal();
        int totalItens = carrinho.size();
        carrinho.forEach(item -> estoque.atualizarEstoque(item, 1)); // Atualiza o estoque para cada item no carrinho
        carrinho.clear(); // Limpa o carrinho após a compra
        System.out.println("Compra realizada com sucesso por " + nome + ". Método de Pagamento: " + metodoPagamento + ". Total: R$" + total);
        System.out.println("Total de itens comprados: " + totalItens);
    }
}
