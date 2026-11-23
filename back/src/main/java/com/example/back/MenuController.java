// Menu do desktop (depois do login): cadastro de ADM, cadastro de técnico+empresa, comunicação ou sair.
package com.example.back;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    // Cada botão troca a Scene pela tela correspondente.
    @FXML
    public void abrirCadastro(ActionEvent event) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/example/back/cadastro-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    @FXML
    public void abrirCadastroEmpresa(ActionEvent event) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/example/back/cadastro-empresa-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    @FXML
    public void abrirComunicacao(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/back/comunicacao-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void sair(){
        System.exit(0);
    }
}