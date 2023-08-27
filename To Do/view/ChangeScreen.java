//pacote em que o arquivo se encontra
package view;

//Importando bibliotecas
import java.io.IOException;
import java.util.ArrayList;
import controller.CadScreenController;
import controller.FailedLoginController;
import controller.FailedRegisterController;
import controller.LoginScreenController;
import controller.MenuScreenController;
import controller.StartScreenController;
import controller.SucessCadController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

public class ChangeScreen extends Application{
  //criando atributos da classe
  private static Scene startScene;
  private static Scene loginScreen;
  private static Scene registerScreen;
  private static Scene menuScreen;
  private static Stage stage;
  //instâncias das classes de controle dos arquivos FXML para estarem disponíveis na raiz do projeto
  CadScreenController cSC = new CadScreenController();
  FailedLoginController fLD = new FailedLoginController();
  FailedRegisterController fRC = new FailedRegisterController();
  LoginScreenController lSC = new LoginScreenController();
  MenuScreenController mSC = new MenuScreenController();
  StartScreenController sSC = new StartScreenController();
  SucessCadController sCC = new SucessCadController();

  @Override
  public void start(Stage primaryStage) throws IOException{
      stage = primaryStage;
      try{
        //definindo icone da janela
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("imgs/icon.png")));

        //configurando dados da tela de título 
        primaryStage.setTitle("ToJavaDO");
        //configurando padrão da tela para não ser redimencionada
        primaryStage.resizableProperty().setValue(false);

        //carregando arquivos da tela e criando cenas
        Parent startFXML = FXMLLoader.load(getClass().getResource("/view/start.fxml"));
        startScene = new Scene(startFXML);
        Parent loginFXML = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        loginScreen = new Scene (loginFXML);
        Parent registerFXML = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
        registerScreen= new Scene (registerFXML);
        Parent menuFXML = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
        menuScreen = new Scene (menuFXML);

        //atribuindo o arquivo xml para uma cena e atribuindo a janela (stage)
        primaryStage.setScene(startScene);
        primaryStage.show();
      }catch(Exception e){
        e.printStackTrace();//print padrão do sistema em caso de erro
      }
  }
  //Definidos casos e tipos para cada mudança de cena de acordo com o corresponde da interação com a tela
  public static void changeScene(String scr) {
    switch (scr) {
      case "start":
        stage.setScene(startScene);
        notifyScreenChangeEvent("start");
        break;
      case "login":
        stage.setScene(loginScreen);
        notifyScreenChangeEvent("login");
        break;
      case "register":
        stage.setScene(registerScreen);
        notifyScreenChangeEvent("register");
        break;
      case "menu":
        stage.setScene(menuScreen);
        notifyScreenChangeEvent("menu");
        break;
      }
  }

  /*Cria uma lista de screen listeners. Cada screen listener (um screen listener é 
  uma função que recebe como parametro um string que representa o nome da tela carregada)vai ser executado 
  assim que uma tela mudar, e cada screen listeners receverá como parâmetro o nome da nova tela*/
  private static ArrayList<onChangeScreen> listeners = new ArrayList<>();

  //contrato de interface
  public static interface onChangeScreen {
    //essa é o método que foi mencionada no método do array de listeners
    void onChangedScreen(String newScreen);
  }

  /*Criando classe com atributo estático para compartilhar informação 
  do  usuário entre duas telas (login/menu)*/
  public static User currentUser = new User("Default",""); 

  /*
  Método que insere o listener na lista de listeners 
   */
  public static void addOnChangeScreenEventListener(onChangeScreen newListener) {
    listeners.add(newListener);
  }
  /*
  Método que executa todos os listeners com um valor específico, que foi passado como parametro
   */
  public static void notifyScreenChangeEvent(String newScreen) {
    for (onChangeScreen listener : listeners)
      listener.onChangedScreen(newScreen);
  }

}
