//pacote em que o arquivo se encontra
package controller;
//importações
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FailedRegisterController {
  //atributos para as contruções das tags do FXML
  @FXML
  private Button btnOkF;

  //ao clicar no botão a função é disparada
  @FXML
  void OnClickButtonOk(ActionEvent event) {
    Stage stage = (Stage) btnOkF.getScene().getWindow(); //Criando Janela
    stage.close(); //Fechando o Stage
  }

}
