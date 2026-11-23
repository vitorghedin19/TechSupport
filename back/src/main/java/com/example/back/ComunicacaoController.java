package com.example.back;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

// Tela de cadastro de comunicação: POST /comunicacao/criar (rota liberada no back, sem token).
// A secretKey fixa precisa ser igual ao spring.secret do back.
public class ComunicacaoController {

    @FXML
    private TextField txtNome;

    @FXML
    protected void salvarComunicacao(ActionEvent event)throws Exception{

        String jsonRequest = String.format("{\n" +
                "  \"nome\": \"%s\",\n" +
                "  \"secretKey\": \"%s\"\n" +
                "}", txtNome.getText(), "auvblavnb");

        int respostaApi = executaMetodoAPI("http://localhost:8080/comunicacao/criar",jsonRequest, "POST");

        if (respostaApi == 200){
            showMessage(Alert.AlertType.INFORMATION, "Comunicação salva com sucesso!");
            voltar(event);
        }else {
            showMessage(Alert.AlertType.ERROR, "Erro ao salvar! Código HTTP: " + respostaApi);
        }
    } //fecha o "salvarComunicacao"

    @FXML
    protected void voltar(ActionEvent event) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/example/back/menu-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    } //fecha o "voltar"

    private int executaMetodoAPI(String url, String json, String protocoloHttp) throws Exception {

        URL urlAPI = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlAPI.openConnection();
        connection.setRequestMethod(protocoloHttp);
        connection.setDoOutput(true);

        if (!json.isEmpty()) {
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes());
            }
        }
        return connection.getResponseCode();
    } //fecha o "executaMetodoAPI"

    private void showMessage(Alert.AlertType type, String msg){
        Alert alerta =new Alert(type);
        alerta.setTitle("Mensagem do Sistema!");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    } //fecha o "showMessage"
} //fecha tudo