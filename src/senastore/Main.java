package senastore;

import senastore.model.Cliente;
import senastore.model.Estoque;
import senastore.model.Item;
import senastore.model.MetodoPagamento;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Estoque estoque = new Estoque();
        estoque.inicializarEstoque();
        System.out.println("Estoque disponível:");
        estoque.exibirEstoque();

        Cliente cliente = new Cliente("João");

        System.out.println("Digite o nome do item que deseja comprar (em seguida, será pedido a quantidade). Digite 'sair' para finalizar:");
        while (true) {
            System.out.print("Item: ");
            String itemName = scanner.nextLine().trim();

            if ("sair".equalsIgnoreCase(itemName)) {
                break;
            }

            itemName = itemName.toLowerCase(); // Convertendo a entrada para minúsculas para ser case insensitive
            try {
                Item item = Item.valueOf(itemName.toUpperCase()); // Convertendo a entrada para maiúsculas para corresponder aos enums
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                cliente.adicionarItemAoCarrinho(item, quantidade, estoque); // Passando o objeto estoque como terceiro argumento
            } catch (IllegalArgumentException e) {
                System.out.println("Item não encontrado. Certifique-se de digitar o nome do item corretamente.");
            }
        }

        // Calcular o valor total da compra
        double totalCompra = cliente.calcularTotal();
        System.out.println("Valor total da compra: R$" + totalCompra);

        // Perguntar ao cliente sobre o método de pagamento
        System.out.println("Selecione o método de pagamento (CREDITO, DEBITO, AVISTA):");
        String metodoPagamentoStr = scanner.nextLine().toUpperCase();
        MetodoPagamento metodoPagamento;
        try {
            metodoPagamento = MetodoPagamento.valueOf(metodoPagamentoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Método de pagamento inválido. Usando padrão: AVISTA.");
            metodoPagamento = MetodoPagamento.AVISTA;
        }

        // Continuar com o processo de checkout
        cliente.realizarCompra(estoque, metodoPagamento);

        // Exibir o estoque restante após a compra
        System.out.println("Estoque restante após a compra:");
        estoque.exibirEstoque();
    }
}
