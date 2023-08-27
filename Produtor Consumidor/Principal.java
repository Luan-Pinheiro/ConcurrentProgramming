//importacao
import controller.ControllerPC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application{
  private static Scene startTelaConsumo;
  //instancia do controller
  ControllerPC ctPC = new ControllerPC();
  //classe principal 
  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception{
    try {
      //definicao de icone e localizando no diretorio
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./view/assets/produto.png")));
      //setando o nome da aplicacao
      primaryStage.setTitle("Produtor Consumidor");
      //impedindo a mudanca na resolucao das janelas, travando redimencionamento
      primaryStage.resizableProperty().setValue(false);

      //carregando os arquivos da tela e gerando novas cenas
      Parent telaConsumo = FXMLLoader.load(getClass().getResource("./view/ProdutorConsumidor.fxml"));
      startTelaConsumo = new Scene(telaConsumo);

      //atribuindo o aquivo xml da cena inicial e exibindo a cena na janela
      primaryStage.setScene(startTelaConsumo);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();// mensagem padrão do java em caso de erro 
    }
  }
}
