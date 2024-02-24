package senastore.model;

public enum Item {
    CALCA("Calça", 100.0),
    CAMISETA("Camiseta", 50.0),
    BERMUDA("Bermuda", 75.0),
    BONE("Boné", 25.0);

    private final String nome;
    private final double preco;

    Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
