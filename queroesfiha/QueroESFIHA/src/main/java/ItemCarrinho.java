/**
 * Um item dentro do carrinho de compras: um produto e a quantidade pedida.
 */
public class ItemCarrinho {

    private final Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void somarQuantidade(int extra) {
        this.quantidade += extra;
    }

    public double subtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return String.format("%dx %s  -  R$ %.2f", quantidade, produto.getNome(), subtotal());
    }
}
