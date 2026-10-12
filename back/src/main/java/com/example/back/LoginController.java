// Tela de login do desktop. Diferente do login do front (que usa email+senha e recebe um JWT do
// back), aqui é só uma "chave única" (key) comparada com um valor fixo no código - não chama a API
// nenhuma vez, é tudo local, na própria aplicação desktop.
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

public class LoginController {

    // Chave fixa no código (hardcoded) - é só com isso que o login compara o que foi digitado.
    private String key = "entrar";

    @FXML
    private TextField txtKey;

    // Chamado pelo clique no botão de login (ligação feita no FXML, igual os outros controllers).
    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {

        // Compara o texto digitado com a chave fixa. .equals() porque String em Java não se compara
        // com "==" (isso compararia a referência do objeto, não o conteúdo do texto).
        if (txtKey.getText().equals(key)){
            showMessage(Alert.AlertType.INFORMATION, "Login efetuado com sucesso!");

            // Mesma lógica de navegação do voltar() dos outros controllers: carrega o FXML do menu,
            // cria uma Scene nova e troca a cena da janela atual por ela.
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/com/example/back/menu-view.fxml"));

            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        }

        else {showMessage(Alert.AlertType.ERROR, "Erro ao efetuar o login!");}

    }

    // Terceira cópia deste mesmo metodo (já tem uma igual no CadastroController). Outro bom
    // candidato pra ir pra aquela classe central que comentamos (junto com voltar/executaMetodoAPI).
    private void showMessage(Alert.AlertType type, String msg){
        Alert alerta = new Alert(type);
        alerta.setTitle("Mensagem do Sistema!");
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();

    }

}