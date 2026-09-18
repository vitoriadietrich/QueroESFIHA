/**
 * Representa um combo ou uma bebida do cardapio do QueroESFIHA.
 * Guarda nome, descricao, preco e o estoque disponivel (igual ao
 * exe50.java original, so que aqui cada produto cuida do proprio estoque).
 */
public class Produto {

    private final String nome;
    private final String descricao;
    private final double preco;
    private int estoque;

    public Produto(String nome, String descricao, double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public boolean temEstoque() {
        return estoque > 0;
    }

    public void baixarEstoque(int quantidade) {
        estoque -= quantidade;
        if (estoque < 0) {
            estoque = 0;
        }
    }

    public void devolverEstoque(int quantidade) {
        estoque += quantidade;
    }

    @Override
    public String toString() {
        String status = temEstoque() ? "" : "  (esgotado)";
        return String.format("%s - R$ %.2f%s", nome, preco, status);
    }
}
