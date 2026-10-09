package com.example.back;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML
    String nome;

    @FXML
    String cpf;

    @FXML
    String email;

    @FXML
    String senha;

    @FXML
    protected void salvarUsuario(ActionEvent event) throws IOException {

    }


    @FXML
    protected void voltar(ActionEvent event) throws IOException {
    FXMLLoader loader =
            new FXMLLoader(getClass().getResource("/com/example/back/menu-view.fxml"));

    Scene scene = new Scene(loader.load());
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);}

}
