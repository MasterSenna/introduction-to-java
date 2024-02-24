package senastore.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Item, Integer> itensDisponiveis;

    public Estoque() {
        this.itensDisponiveis = new HashMap<>();
    }

    public void inicializarEstoque() {
        itensDisponiveis.put(Item.CALCA, 10);
        itensDisponiveis.put(Item.CAMISETA, 20);
        itensDisponiveis.put(Item.BERMUDA, 15);
        itensDisponiveis.put(Item.BONE, 5);
    }

    public void atualizarEstoque(Item item, int quantidade) {
        if (itensDisponiveis.containsKey(item)) {
            int quantidadeAtual = itensDisponiveis.get(item);
            if (quantidadeAtual >= quantidade) {
                itensDisponiveis.put(item, quantidadeAtual - quantidade);
                System.out.println("Item " + item.getNome() + " comprado. Total: " + quantidade);
            } else {
                System.out.println("Desculpe, a quantidade do item " + item.getNome() + " disponível em estoque é insuficiente.");
            }
        } else {
            System.out.println("Desculpe, o item selecionado não está disponível.");
        }
    }

    public int getQuantidadeDisponivel(Item item) {
        return itensDisponiveis.getOrDefault(item, 0);
    }

    public void exibirEstoque() {
        System.out.println("Estoque disponível:");
        for (Map.Entry<Item, Integer> entry : itensDisponiveis.entrySet()) {
            System.out.println(entry.getKey().getNome() + ": " + entry.getValue());
        }
    }

    public void exibirEstoqueComPrecos() {
        System.out.println("Estoque disponível:");
        for (Map.Entry<Item, Integer> entry : itensDisponiveis.entrySet()) {
            System.out.println(entry.getKey().getNome() + ": " + entry.getValue() + " - Preço: R$" + entry.getKey().getPreco());
        }
    }
}
