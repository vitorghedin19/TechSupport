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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroEmpresaController {

    private static final String SECRET_KEY = "auvblavnb";

    @FXML
    private TextField txtRazaoSocial;

    @FXML
    private TextField txtNomeFantasia;

    @FXML
    private TextField txtCnpj;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    protected void buscarCnpj(ActionEvent event) throws Exception {

        String cnpj = txtCnpj.getText().replaceAll("[^0-9]", "");

        URL urlAPI = new URL("http://localhost:8080/empresa/consulta-cnpj/" + cnpj);

        HttpURLConnection connection = (HttpURLConnection) urlAPI.openConnection();
        connection.setRequestMethod("GET");

        int respostaApi = connection.getResponseCode();

        if (respostaApi == 200) {
            String corpo = lerCorpo(connection.getInputStream());
            txtRazaoSocial.setText(extrairCampo(corpo, "razaoSocial"));
            txtNomeFantasia.setText(extrairCampo(corpo, "nomeFantasia"));
        } else {
            String erro = lerCorpo(connection.getErrorStream());
            showMessage(Alert.AlertType.ERROR, erro.isEmpty() ? "Erro ao consultar CNPJ! Código HTTP: " + respostaApi : erro);
        }

    }

    @FXML
    protected void salvar(ActionEvent event) throws Exception {

        String jsonRequest = String.format("{\n" +
                        "  \"razaoSocial\": \"%s\",\n" +
                        "  \"nomeFantasia\": \"%s\",\n" +
                        "  \"cnpj\": \"%s\",\n" +
                        "  \"nome\": \"%s\",\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"senha\": \"%s\",\n" +
                        "  \"cpf\": \"%s\",\n" +
                        "  \"secretKey\": \"%s\"\n" +
                        "}", txtRazaoSocial.getText(), txtNomeFantasia.getText(), txtCnpj.getText(),
                txtNome.getText(), txtEmail.getText(), txtSenha.getText(),
                txtCpf.getText(), SECRET_KEY);

        int respostaApi = executaMetodoAPI("http://localhost:8080/usuarios/tecnico-empresa", jsonRequest, "POST");

        if (respostaApi == 200) {
            showMessage(Alert.AlertType.INFORMATION, "Técnico e empresa salvos com sucesso!");
            voltar(event);
        } else {
            showMessage(Alert.AlertType.ERROR, "Erro ao salvar! Código HTTP: " + respostaApi);
        }

    }

    @FXML
    protected void voltar(ActionEvent event) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/example/back/menu-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private int executaMetodoAPI(String url, String json, String protocoloHttp) throws Exception {

        URL urlAPI = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlAPI.openConnection();
        connection.setRequestMethod(protocoloHttp);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = connection.getOutputStream()) {
            os.write(json.getBytes());
        }

        return connection.getResponseCode();
    }

    private String lerCorpo(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        try (stream) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String extrairCampo(String json, String campo) {
        Matcher matcher = Pattern.compile("\"" + campo + "\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"").matcher(json);
        if (matcher.find()) {
            return matcher.group(1).replace("\\\"", "\"").replace("\\\\", "\\");
        }
        return "";
    }

    private void showMessage(Alert.AlertType type, String msg){
        Alert alerta = new Alert(type);
        alerta.setTitle("Mensagem do Sistema!");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}