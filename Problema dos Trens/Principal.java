import controller.ControllerTrains;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application{
  private static Scene startTrainScene;
  ControllerTrains cT = new ControllerTrains();
//classe principal 
  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception{
    try {
      //definicao de icone e localizando no diretorio
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./view/assets/Icon.png")));
      //setando o nome da aplicacao
      primaryStage.setTitle("Problema do Trem");
      //impedindo a mudanca na resolucao das janelas, travando redimencionamento
      primaryStage.resizableProperty().setValue(false);

      //carregando os arquivos da tela e gerando novas cenas
      Parent trainsScene = FXMLLoader.load(getClass().getResource("./view/ProblemaTrem.fxml"));
      startTrainScene = new Scene(trainsScene);

      //atribuindo o aquivo xml da cena inicial e exibindo a cena na janela
      primaryStage.setScene(startTrainScene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();// mensagem padrão do java em caso de erro 
    }
  }
}
