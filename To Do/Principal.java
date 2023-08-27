//importações necessárias na classe
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ChangeScreen;

public class Principal extends Application{
  ChangeScreen cS = new ChangeScreen();//Instanciando a classe de mundança de telas

  @Override
  public void start(Stage primaryStage) throws IOException{
    try{
      cS.start(primaryStage);//Utilizando a instancia da classe para iniciar o programa na primeira tela
    }catch(Exception e){
      e.printStackTrace();//printando em caso de erro
    }
  }
  /*Método principal main*/
  public static void main(String[] args) {
    launch(args);
  }
}