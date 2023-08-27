//Importacao de bibliotecas necessarias na classe principal
import controller.ControllerThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application{
  private static Scene startTreeScene;
  ControllerThread cT = new ControllerThread();

  //classe principal 
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception {
    try {
      //definicao de icone e localizando no diretorio
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./view/treeIcon.png")));
      //setando o nome da aplicacao
      primaryStage.setTitle("Arvore Genealogica");
      //impedindo a mudanca na resolucao das janelas, travando redimencionamento
      primaryStage.resizableProperty().setValue(false);

      //carregando os arquivos da tela e gerando novas cenas
      Parent treeScene = FXMLLoader.load(getClass().getResource("./view/ArvoreGen.fxml"));
      startTreeScene = new Scene(treeScene);

      //atribuindo o aquivo xml da cena inicial e exibindo a cena na janela
      primaryStage.setScene(startTreeScene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();// mensagem padrão do java em caso de erro 
    }
  }
}

