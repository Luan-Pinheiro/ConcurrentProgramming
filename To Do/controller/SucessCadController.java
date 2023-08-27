//pacote em que o arquivo se encontra
package controller;
//importações
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.ChangeScreen;  
public class SucessCadController {
  //atributos para as contruções das tags do FXML

  @FXML
  private Button btnOk;

  @FXML
  private ImageView imgPaper;

  @FXML
  private ImageView imgText;

  //ao clicar no botão da tela executa esta função
  @FXML
  void btnOk(ActionEvent event) {
    Stage stage = (Stage) btnOk.getScene().getWindow(); //Criando Janela
    stage.close(); //Fechando o Stage
    ChangeScreen.changeScene("login");//mudando para a tela de login
  }

}