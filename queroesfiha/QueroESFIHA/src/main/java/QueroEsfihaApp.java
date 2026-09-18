import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * QueroESFIHA - versao JavaFX do sistema de pedidos que antes era em
 * modo texto (exe50.java). Mesma regra de negocio: combos, bebidas,
 * controle de estoque, desconto VIP de 10% para total >= R$ 40 e
 * escolha da forma de pagamento.
 *
 * Para rodar:  mvn clean javafx:run
 */
public class QueroEsfihaApp extends Application {

    private static final double LIMITE_DESCONTO_VIP = 40.0;

    // ---- combos (mesmos precos e composicao do exe50.java) ----
    private final ObservableList<Produto> combos = FXCollections.observableArrayList(
            new Produto("Combo 1", "5 esfihas - 3x carne, 2x queijo", 10.00, 2),
            new Produto("Combo 2", "10 esfihas - 5x carne, 5x queijo", 15.00, 1),
            new Produto("Combo 3", "15 esfihas - 5x carne, 5x queijo, 5x calabresa", 20.00, 4),
            new Produto("Combo 4", "20 esfihas - carne, queijo, calabresa, frango c/ catupiry", 27.90, 3),
            new Produto("Combo 5", "25 esfihas - + atum", 35.00, 5),
            new Produto("Combo 6", "30 esfihas - + palmito", 40.00, 3)
    );

    // ---- bebidas (mesmos precos do exe50.java) ----
    private final ObservableList<Produto> bebidas = FXCollections.observableArrayList(
            new Produto("Coca-Cola 350ml", "", 6.00, 3),
            new Produto("Guaraná 350ml", "", 6.00, 3),
            new Produto("Fanta Laranja 350ml", "", 6.00, 3),
            new Produto("Coca-Cola 1L", "", 9.00, 3),
            new Produto("Guaraná 1L", "", 9.00, 3),
            new Produto("Água 500ml", "", 4.00, 3)
    );

    private final List<ItemCarrinho> carrinho = new ArrayList<>();
    private final ObservableList<String> linhasCarrinho = FXCollections.observableArrayList();

    private final CheckBox chkVip = new CheckBox("Cliente VIP (10% de desconto acima de R$ 40)");
    private final Label lblTotal = new Label("Total: R$ 0,00");

    @Override
    public void start(Stage palco) {

        chkVip.setSelected(true); // igual ao "static boolean VIP = true;" do exe50.java

        // ---------- cabecalho ----------
        Label titulo = new Label("QueroESFIHA");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        Label subtitulo = new Label("Escolha seu combo ou bebida e monte seu pedido");
        subtitulo.setFont(Font.font("Arial", 13));

        VBox cabecalho = new VBox(2, titulo, subtitulo);
        cabecalho.setAlignment(Pos.CENTER);

        // ---------- aba de combos ----------
        ComboBox<Produto> cbCombo = new ComboBox<>(combos);
        cbCombo.setPromptText("Escolha um combo");
        cbCombo.setPrefWidth(300);

        Label lblDescCombo = new Label(" ");
        lblDescCombo.setFont(Font.font("Arial", 12));
        lblDescCombo.setWrapText(true);
        cbCombo.valueProperty().addListener((obs, antigo, novo) ->
                lblDescCombo.setText(novo == null ? " " : novo.getDescricao()
                        + "   |   estoque: " + novo.getEstoque()));

        Spinner<Integer> spQtdCombo = new Spinner<>(1, 20, 1);
        spQtdCombo.setPrefWidth(80);

        Button btAddCombo = new Button("Adicionar combo");
        btAddCombo.setOnAction(e -> {
            Produto p = cbCombo.getValue();
            if (p == null) {
                aviso("Escolha um combo antes de adicionar.");
                return;
            }
            adicionarAoCarrinho(p, spQtdCombo.getValue());
            atualizarComboSelecionado(cbCombo);
        });

        VBox painelCombos = new VBox(10,
                new Label("Combos:"),
                new HBox(10, cbCombo, spQtdCombo, btAddCombo),
                lblDescCombo
        );
        painelCombos.setPadding(new Insets(12));

        Tab tabCombos = new Tab("Combos", painelCombos);
        tabCombos.setClosable(false);

        // ---------- aba de bebidas ----------
        ComboBox<Produto> cbBebida = new ComboBox<>(bebidas);
        cbBebida.setPromptText("Escolha uma bebida");
        cbBebida.setPrefWidth(300);

        Label lblEstoqueBebida = new Label(" ");
        lblEstoqueBebida.setFont(Font.font("Arial", 12));
        cbBebida.valueProperty().addListener((obs, antigo, novo) ->
                lblEstoqueBebida.setText(novo == null ? " " : "estoque: " + novo.getEstoque()));

        Spinner<Integer> spQtdBebida = new Spinner<>(1, 20, 1);
        spQtdBebida.setPrefWidth(80);

        Button btAddBebida = new Button("Adicionar bebida");
        btAddBebida.setOnAction(e -> {
            Produto p = cbBebida.getValue();
            if (p == null) {
                aviso("Escolha uma bebida antes de adicionar.");
                return;
            }
            adicionarAoCarrinho(p, spQtdBebida.getValue());
            atualizarComboSelecionado(cbBebida);
        });

        VBox painelBebidas = new VBox(10,
                new Label("Bebidas:"),
                new HBox(10, cbBebida, spQtdBebida, btAddBebida),
                lblEstoqueBebida
        );
        painelBebidas.setPadding(new Insets(12));

        Tab tabBebidas = new Tab("Bebidas", painelBebidas);
        tabBebidas.setClosable(false);

        TabPane abas = new TabPane(tabCombos, tabBebidas);

        // ---------- carrinho ----------
        ListView<String> listaCarrinho = new ListView<>(linhasCarrinho);
        listaCarrinho.setPrefHeight(160);
        VBox.setVgrow(listaCarrinho, Priority.ALWAYS);

        lblTotal.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Button btRemover = new Button("Remover selecionado");
        btRemover.setOnAction(e -> {
            int i = listaCarrinho.getSelectionModel().getSelectedIndex();
            if (i < 0) {
                aviso("Selecione um item do carrinho para remover.");
                return;
            }
            ItemCarrinho item = carrinho.remove(i);
            item.getProduto().devolverEstoque(item.getQuantidade());
            atualizarTela();
        });

        Button btLimpar = new Button("Limpar carrinho");
        btLimpar.setOnAction(e -> {
            for (ItemCarrinho item : carrinho) {
                item.getProduto().devolverEstoque(item.getQuantidade());
            }
            carrinho.clear();
            atualizarTela();
        });

        HBox botoesCarrinho = new HBox(10, btRemover, btLimpar);

        // ---------- pagamento ----------
        ComboBox<String> cbPagamento = new ComboBox<>(FXCollections.observableArrayList(
                "Débito", "Crédito", "Vale Refeição ou Alimentação", "PIX"
        ));
        cbPagamento.setPromptText("Forma de pagamento");
        cbPagamento.setPrefWidth(240);

        Button btFinalizar = new Button("Finalizar pedido");
        btFinalizar.setDefaultButton(true);
        btFinalizar.setOnAction(e -> {
            if (carrinho.isEmpty()) {
                aviso("Seu carrinho esta vazio.");
                return;
            }
            String pagamento = cbPagamento.getValue();
            if (pagamento == null) {
                aviso("Escolha a forma de pagamento.");
                return;
            }
            finalizarPedido(pagamento);
        });

        HBox linhaPagamento = new HBox(10, cbPagamento, btFinalizar);
        linhaPagamento.setAlignment(Pos.CENTER_LEFT);

        VBox painelCarrinho = new VBox(10,
                new Label("Carrinho:"),
                listaCarrinho,
                botoesCarrinho,
                chkVip,
                lblTotal,
                linhaPagamento
        );
        painelCarrinho.setPadding(new Insets(12, 0, 0, 0));

        // ---------- layout final ----------
        VBox raiz = new VBox(14, cabecalho, abas, painelCarrinho);
        raiz.setPadding(new Insets(20));

        palco.setTitle("QueroESFIHA");
        palco.setScene(new Scene(raiz, 560, 620));
        palco.show();
    }

    private void atualizarComboSelecionado(ComboBox<Produto> cb) {
        // forca o ComboBox a re-renderizar o item (estoque mudou)
        Produto atual = cb.getValue();
        cb.setValue(null);
        cb.setValue(atual);
    }

    private void adicionarAoCarrinho(Produto produto, int quantidadeDesejada) {
        if (produto.getEstoque() < quantidadeDesejada) {
            aviso("Estoque insuficiente para " + produto.getNome()
                    + ". Disponivel: " + produto.getEstoque());
            return;
        }

        produto.baixarEstoque(quantidadeDesejada);

        for (ItemCarrinho item : carrinho) {
            if (item.getProduto() == produto) {
                item.somarQuantidade(quantidadeDesejada);
                atualizarTela();
                return;
            }
        }
        carrinho.add(new ItemCarrinho(produto, quantidadeDesejada));
        atualizarTela();
    }

    private double totalCarrinho() {
        double soma = 0;
        for (ItemCarrinho item : carrinho) {
            soma += item.subtotal();
        }
        return soma;
    }

    private void atualizarTela() {
        linhasCarrinho.clear();
        for (ItemCarrinho item : carrinho) {
            linhasCarrinho.add(item.toString());
        }
        lblTotal.setText(String.format("Total: R$ %.2f", totalCarrinho()));
    }

    private void finalizarPedido(String formaPagamento) {
        double total = totalCarrinho();
        boolean aplicaDesconto = chkVip.isSelected() && total >= LIMITE_DESCONTO_VIP;
        double totalFinal = aplicaDesconto ? total - (total * 0.10) : total;

        StringBuilder sb = new StringBuilder();
        for (ItemCarrinho item : carrinho) {
            sb.append(item).append("\n");
        }
        sb.append("\nSubtotal: R$ ").append(String.format("%.2f", total));
        if (aplicaDesconto) {
            sb.append("\nDesconto VIP de 10% aplicado!");
        }
        sb.append("\nForma de pagamento: ").append(formaPagamento);
        sb.append("\nValor final: R$ ").append(String.format("%.2f", totalFinal));

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Pagamento realizado");
        alerta.setHeaderText("Pagamento no " + formaPagamento + " realizado com sucesso!");
        alerta.setContentText(sb.toString());
        alerta.showAndWait();

        carrinho.clear();
        atualizarTela();
    }

    private void aviso(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Atenção");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
