//pacote em que o arquivo se encontra
package controller;
//importações
import java.io.IOException;
import java.util.ArrayList;
import data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.User;
import view.ChangeScreen;

public class CadScreenController {
  //atributos da classe
  UserData userD = new UserData();
  //atributos para as contruções das tags do FXML
  @FXML
  private Button btnRegister;

  @FXML
  private Button btnReturn;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private TextField txtUsername;

  @FXML
  private ImageView txtImg;

  @FXML
  void OnClickBtnRegister(ActionEvent event) throws IOException {
    //capturando dados dos campos de texto e inserindo nas variáveis
    String username =txtUsername.getText();
    String password =txtPassword.getText();
    //instanciando usuário
    User usuario = new User(username, password);
    //verificação para caso os dados de usuário estajm vazios
    if(!(username.isEmpty() && password.isEmpty())){
      //em caso negativo o usuário é escrito no arquivo atraves do insertUser() 
      userInsert(usuario);
      //validação de dados do usuário
      boolean isValidUser = checkUserCredentials(usuario);
      if(isValidUser){
        //caso a validação seja um sucesso a nova tela/janela é carregada e exibida
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/sucessRegister.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        //icone da tela
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../view/imgs/icon.png")));
        }
    }else{
      /*em caso da validação ser negativa por usuário/senha incorretos ou campos não preenchidos
      um nova tela de erro é carregada e exibida
      */
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/failedRegister.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setResizable(false);
      stage.show();
      //icone da tela
      stage.getIcons().add(new Image(getClass().getResourceAsStream("../view/imgs/icon.png")));
    }
  }
  //ao clicar no botão o método é disparado
  @FXML
  void OnClickBtnReturn(ActionEvent event) {
    ChangeScreen.changeScene("login");//muda para tela de login 
  }
  //sobreescrevendo métodos da classe UserData
  public ArrayList<User> userInsert(User user){
    return userD.userInsert(user);
  }
  public ArrayList<User> searchUser(){
    return userD.getUserList();
  }
  public boolean checkUserCredentials(User user){
    return userD.checkUserCredentials(user);
  }

}

