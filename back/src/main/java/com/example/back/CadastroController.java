// Pasta com.example.back (projeto desktop, JavaFX - é um projeto SEPARADO do back Spring Boot,
// mesmo tendo o mesmo nome de pacote). Esta classe é o "controller" da tela de cadastro de ADM:
// liga os campos do FXML (cadastro-view.fxml) com o código Java e decide o que acontece nos cliques.
//
// FLUXO GERAL DESTA TELA (clique em Salvar):
//  1) usuário preenche nome/email/senha/cpf e clica em "Salvar"
//  2) o FXML chama salvarUsuario(event) (ligação feita pelo onAction="#salvarUsuario" no botão)
//  3) monta um JSON manualmente com os dados digitados + a secretKey fixa
//  4) executaMetodoAPI faz a requisição HTTP de verdade (POST) pro back, em /usuarios/admin
//  5) se o back responder 200, mostra alerta de sucesso e volta pro menu; senão, mostra alerta de erro
package com.example.back;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroController {

    // @FXML: liga este campo Java ao componente do FXML que tem o mesmo fx:id (ex.: fx:id="txtNome").
    // É o JavaFX quem preenche essas variáveis sozinho quando a tela carrega.
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtCpf;

    // Metodo chamado pelo clique no botão "Salvar" (onAction="#salvarUsuario" no FXML).
    // ActionEvent event: representa o clique em si; é usado lá embaixo em voltar(event) pra descobrir
    // em qual janela (Stage) trocar de tela.
    @FXML
    protected void salvarUsuario(ActionEvent event) throws Exception {

        // Monta o corpo da requisição na mão, como texto JSON (sem usar nenhuma lib tipo Jackson).
        // .getText() pega o que foi digitado em cada campo. "secretKey" vem fixa no código (hardcoded),
        // a mesma chave usada como "spring.secret" no application.properties do back - é o jeito desta
        // tela provar pro back que quem está cadastrando tem permissão de criar um ADM.
        String jsonRequest = String.format("{\n" +
                        "  \"nome\": \"%s\",\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"senha\": \"%s\",\n" +
                        "  \"cpf\": \"%s\",\n" +
                        "  \"secretKey\": \"%s\"\n" +
                        "}", txtNome.getText(), txtEmail.getText(),
                txtSenha.getText(),txtCpf.getText(),"auvblavnb");

        // Chama o back. "respostaApi" é o código HTTP que voltou (200 = deu certo, outro = erro).
        int respostaApi = executaMetodoAPI("http://localhost:8080/usuarios/admin",jsonRequest, "POST");

        if(respostaApi ==200){
            showMessage(Alert.AlertType.INFORMATION,"Usuario salvo com sucesso!");
            // Depois de salvar com sucesso, volta pro menu.
            voltar(event);

        }else {
            showMessage(Alert.AlertType.ERROR,"Erro ao salvar usuário!");
        }

    }


    // Troca a tela atual pela tela de menu (menu-view.fxml). Chamado tanto pelo botão "Voltar"
    // quanto automaticamente depois de salvar com sucesso.
    @FXML
    protected void voltar(ActionEvent event) throws IOException {
        // Carrega o outro arquivo FXML (a tela de menu) e cria uma nova "Scene" (cena) com ele.
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/example/back/menu-view.fxml"));

        Scene scene = new Scene(loader.load());
        // Pega a janela (Stage) atual a partir de quem disparou o evento (o botão clicado) e troca
        // a cena dela pela nova. É assim que a navegação entre telas funciona no JavaFX (sem rota/URL
        // como no Next.js - aqui é "troca a Scene dentro da mesma Stage").
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);}


    // Faz a chamada HTTP de verdade pro back. Aqui é o "Axios" desse projeto desktop, só que feito
    // na mão com a classe HttpURLConnection do próprio Java (sem nenhuma biblioteca externa).
    private int executaMetodoAPI(String url, String json, String protocoloHttp) throws Exception {

        URL urlAPI = new URL(url);


        // Abre a conexão com a URL informada e configura o verbo HTTP (POST, GET, etc.).
        HttpURLConnection connection = (HttpURLConnection) urlAPI.openConnection();
        connection.setRequestMethod(protocoloHttp);
        // Diz que esta conexão vai ENVIAR dados no corpo da requisição (sem isso não dá pra escrever o JSON).
        connection.setDoOutput(true);

        if (!json.isEmpty()) {
            // Avisa o back que o corpo enviado é um JSON.
            connection.setRequestProperty("Content-Type", "application/json");

            // Escreve o JSON no corpo da requisição e fecha o stream automaticamente (try-with-resources).
            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes());
            }
        }

        // Dispara a requisição de fato e devolve o código HTTP da resposta (200, 404, 500...).
        return connection.getResponseCode();
    }

    // Mostra uma janela de alerta simples (sucesso ou erro) pro usuário.
    private void showMessage(Alert.AlertType type, String msg){
        Alert alerta =new Alert(type);
        alerta.setTitle("Mensagem do Sistema!");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}