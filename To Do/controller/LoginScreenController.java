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

public class LoginScreenController{
  //atributos da classe
  UserData userD = new UserData();
  //atributos para as contruções das tags do FXML
  @FXML
  private Button btnCad;

  @FXML
  private Button btnLogin;

  @FXML
  private ImageView imgStart;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private TextField txtUser;


  @FXML
  void OnClickBtnCad(ActionEvent event) {
    ChangeScreen.changeScene("register");//mudando para a tela de registro 
  }

  @FXML
  void OnClickBtnLogin(ActionEvent event) throws IOException {
    //capturando dados inseridos no textfiled e armazenando nas variáveis
    String username = txtUser.getText();
    String password = txtPassword.getText();
    //verificando se os campos estão vazios
    if(username.isEmpty() || password.isEmpty()){
     //carregando arquivos da tela e criando cenas
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/failedLogin.fxml"));
      Parent root = loader.load();
      //criando janela
      Stage stage = new Stage();
      // impossibilitando redimencionamento da tela
      stage.resizableProperty().setValue(false);
      //atribuindo o arquivo xml para uma cena e atribuindo a janela (stage)
      stage.setScene(new Scene(root));
      stage.show();
      //atribuindo icone a janela
      stage.getIcons().add(new Image(getClass().getResourceAsStream("../view/imgs/icon.png")));
      return;
   }
    //instanciando usuário
    User user = new User(username, password);
    ChangeScreen.currentUser = user; //Passando novo valor para variável estática
    boolean isValidUser = checkUserCredentials(user);//validação de dados de login do usuário
        
    if(isValidUser){
      ChangeScreen.changeScene("menu");//em caso da validação ser positiva, mudança para tela de menu
    }else{
      //em validação negativa uma nota tela é carregada e iniciada para a falha, com impossibilidade de redimencionamento
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/failedLogin.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.resizableProperty().set(false);
      stage.show();
      //icone da nova tela
      stage.getIcons().add(new Image(getClass().getResourceAsStream("../view/imgs/icon.png")));
    }
  }
  //sobreescrevendo métodos da classe UserData
  public boolean checkUserCredentials(User user){
    return userD.checkUserCredentials(user);
  }
  public ArrayList<User> getUserList(){
    return userD.getUserList();
  }
}