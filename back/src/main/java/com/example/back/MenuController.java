// Tela de menu (a "tela inicial" depois do login). É daqui que o usuário escolhe pra onde ir:
// cadastro de ADM, cadastro de comunicação, ou sair do programa.
package com.example.back;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    // Clique no botão de cadastro de ADM: troca a tela atual pela cadastro-view.fxml (que usa o
    // CadastroController).
    @FXML
    public void abrirCadastro(ActionEvent event) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/example/back/cadastro-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    // Clique no botão de comunicação: mesma ideia, mas abre a comunicacao-view.fxml.
    @FXML
    public void abrirComunicacao(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/back/comunicacao-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    // Encerra o programa inteiro (fecha todas as janelas e finaliza o processo Java).
    @FXML
    public void sair(){
        System.exit(0);
    }
}