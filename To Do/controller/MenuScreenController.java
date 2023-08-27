//pacote em que o arquivo se encontra
package controller;
//importações
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.User;
import view.ChangeScreen;

public class MenuScreenController implements Initializable {
//atributos para as contruções das tags do FXML
    @FXML
    private Button btnOk;

    @FXML
    private Button btnOkF;

    @FXML
    private ImageView imgCapa;

    @FXML
    private Label lblUser;

    @FXML
    private TextField txtBartoDo;

    @FXML
    private TextField txtBarDate;

    @FXML
    private TableColumn<User, String> tbvDia;

    @FXML
    private TableColumn<User, String> tbvTarefa;

    @FXML
    private TableView<User> tbvToDo;

  //atributos da classe de controle  
  private String day;
  private String task; 
  //criando lista observável para ser exibida no table view
  ObservableList<User> listDayTask = FXCollections.observableArrayList(
    new User(ChangeScreen.currentUser.getUsername(), task, day)
  );
  @FXML
  void OnClickButtonInserir(ActionEvent event) {
    //capturando texto do textfield
    day = txtBarDate.getText();
    task = txtBartoDo.getText();
    //instanciando usuário que será inserído no table view
    User user = new User(ChangeScreen.currentUser.getUsername(),task, day);
    //inserindo usuário na lista que será exbida no table view
    listDayTask.add(user);
    //inserindo dados dados das células das colunas do table view, para cada tipo de atributo da classe user
    tbvDia.setCellValueFactory(new PropertyValueFactory<User, String>("day"));
    tbvTarefa.setCellValueFactory(new PropertyValueFactory<User, String>("task"));
    //exibindo itens no tableview
    tbvToDo.setItems(listDayTask); 
  }
  @FXML
  void OnClickButtonRemover(ActionEvent event) {
    //verificando se a lista está vazia para evitar o erro de tentar remover conteudo do ponteiro que não aponta pra lugar algum
    if(!(listDayTask.isEmpty()))
    //em caso de lista não nula remove o seu ultimo elemento adicionado;
    listDayTask.remove(listDayTask.get(listDayTask.size()-1));
  }
  //executado ao inicializar a tela
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //acionando e adicionandi listener
    ChangeScreen.addOnChangeScreenEventListener(new ChangeScreen.onChangeScreen() {
      @Override
      public void onChangedScreen(String newScreen) {
        //exibindo no campo, o nome do usuário logado
        lblUser.setText(ChangeScreen.currentUser.getUsername());
      }
    }); 
  }
}
