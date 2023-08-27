//Pacote da classe
package controller;
//Importacoes
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.TrainA;
import model.TrainB;

public class ControllerTrains implements Initializable{
  //Atributos do fxml
  @FXML
  private CheckBox boxEstritAlternancia;
  @FXML
  private ChoiceBox<String> boxOrientacao;
  @FXML
  private CheckBox boxTravamento;
  @FXML
  private Button btnInit;
  @FXML
  private Button btnReturn;
  @FXML
  private ImageView imgTremA;
  @FXML
  private ImageView imgTremArev;
  @FXML
  private ImageView imgTremB;
  @FXML
  private ImageView imgTremBrev;
  @FXML
  private Slider sliderA;
  @FXML
  private Slider sliderB;
  @FXML
  private Label txtOrientac;
  //Atributos auxiliares quem passarao informacoes para as threads realizarem a selecao
  private int posiOrientacao;
  private int typeOfMutualExclusion = 0;
  //Variaveis de travamento de cada regiao critica
  private int VT1 = 0;
  private int VT2 = 0;
  //Variaveis de estrita alternancia de cada regiao critica
  private int VEZ1 = 0;
  private int VEZ2 = 0;
  //instancia da classe de alerta que sera utilizada em caso da nao selecao de algum campo
  Alert alerta = new  Alert(AlertType.ERROR);
  //Instanciando as Threads de cada trem
  TrainA trainA = new TrainA();
  TrainB trainB = new TrainB();
  //Atributos auxiliares de velocidade de cada trem
  private int speedA;
  private int speedB;
  
  //Metodo para quando o botao iniciar for pressionado
  @FXML
  void OnClickInit(ActionEvent event) {
    //Selecao para caso a ChoiceBox e as CheckBox de solucoes nao forem selecionadas
    if(boxOrientacao.getValue() != null && getTypeOfMutualExclusion() != 0){
      //caso estiverem
      //botao iniciar fica invisivel
      btnInit.setVisible(false);
      //inicia as threads
      trainA.start();
      trainB.start();
      //deixa os campos de selecao de orientacao e solucao invisiveis
      boxOrientacao.setVisible(false);
      boxEstritAlternancia.setVisible(false);
      boxTravamento.setVisible(false);
      txtOrientac.setVisible(false);
    }else{
      //caso nao
      //dispara o alarme
      alerta.setTitle("Erro na selecao de orientacao");
      alerta.setHeaderText("Opcao vazia ou inexistente");
      alerta.setContentText("Selecione uma opcao valida!");
      alerta.showAndWait();
    }
  }
  //Metodo para quando o botao fechar for pressionado
  @FXML
  void OnClickReturn(ActionEvent event){
    //encerra a execucao
    System.exit(0);
  }
  //Metodo que verifica se a hCeckBox da variavel de travamento esta selecionada
  @FXML
  void onSelectedBoxAlternancia(ActionEvent event) {
    if(boxTravamento.isSelected()){
      //caso sim
      //desseleciona a hCeckBox da variavel de travamento 
      boxTravamento.setSelected(false);
    }
  }
  //Metodo que verifica se a hCeckBox da variavel de travamento esta selecionada
  @FXML
  void onSelectedBoxTravamento(ActionEvent event) {
    if(boxEstritAlternancia.isSelected()){
      //caso sim
      //desseleciona a hCeckBox da variavel de travamento 
      boxEstritAlternancia.setSelected(false);
    }
  } 
  //Metodo que identifica e retorna a posicao/opcao selecionada no ChoiceBox 
  public int getPosiOrientacao() {
    switch(boxOrientacao.getSelectionModel().getSelectedIndex()){
      case 0:
        posiOrientacao = 0;
        break;
      case 1:
        posiOrientacao = 1;
        break;
      case 2:
        posiOrientacao = 2;
        break;            
      case 3:
        posiOrientacao = 3;
        break;
      default:
        System.out.println("Erro na selecao de orientacao");
        break;
      }
    return posiOrientacao;
  }
  //Metodo que identifica qual CheckBox esta selecionada e atribui um valor ao atributo typeOfMutualExclusion que sera utilizado nas Threads
  public int getTypeOfMutualExclusion(){
    if(boxEstritAlternancia.isSelected())
      typeOfMutualExclusion = 1;
    else if(boxTravamento.isSelected())
      typeOfMutualExclusion = 2;
    else
      typeOfMutualExclusion = 0;
      
    return typeOfMutualExclusion;
  }
  //Getters e Setters
  public int getVEZ1() {
    return VEZ1;
  }
  public void setVEZ1(int vEZ1) {
    VEZ1 = vEZ1;
  }
  public int getVEZ2() {
    return VEZ2;
  }
  public void setVEZ2(int vEZ2) {
    VEZ2 = vEZ2;
  }
  public int getSpeedA() {
    return speedA;
  }
  public int getSpeedB() {
    return speedB;
  }
  public int getVT1() {
    return VT1;
  }
  public void setVT1(int vT1) {
    VT1 = vT1;
  }
  public int getVT2() {
    return VT2;
  }
  public void setVT2(int vT2) {
    VT2 = vT2;
  }
  public ImageView getImgTremA() {
    return imgTremA;
  }
  public void setImgTremA(ImageView imgTremA) {
    this.imgTremA = imgTremA;
  }
  public ImageView getImgTremArev() {
    return imgTremArev;
  }
  public void setImgTremArev(ImageView imgTremArev) {
    this.imgTremArev = imgTremArev;
  }
  public ImageView getImgTremB() {
    return imgTremB;
  }
  public void setImgTremB(ImageView imgTremB) {
    this.imgTremB = imgTremB;
  }
  public ImageView getImgTremBrev() {
    return imgTremBrev;
  }
  public void setImgTremBrev(ImageView imgTremBrev) {
    this.imgTremBrev = imgTremBrev;
  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /* Subdividindo as barras de velocidades em tres partes, 
    setando as bolinhas de selecao para ocupar as tres partes da divisao. */
    sliderA.setMin(0);
    sliderA.setMax(2);
    sliderA.setValue(0);
    //definindo unidade de marcacao principal do slider
    sliderA.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderA.setMinorTickCount(0);
    sliderA.setSnapToTicks(true);
    sliderB.setMin(0);
    sliderB.setMax(2);
    sliderB.setValue(0);
    //definindo unidade de marcacao principal do slider
    sliderB.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderB.setMinorTickCount(0);
    sliderB.setSnapToTicks(true);

    //Passando a referencia desse controlle para as threads que o chamam.
    trainA.linkController(this);
    trainB.linkController(this);

    //Demarcando o conteudo presente no Choice Box para ser selecionado.
     boxOrientacao.getItems().addAll("Mesma orientacao esquerda", "Mesma orientacao direita", "Trem A comeca na esquerda e B na direita", "Trem B comeca na esquerda e A na direita");
  
    //Deixando os Image Views inicialmente invisiveis.
    imgTremA.setVisible(false);
    imgTremB.setVisible(false);
    imgTremArev.setVisible(false);
    imgTremBrev.setVisible(false);

    //definindo listener para mudanca de velocidade do trem A em tempo real e atribuindo o novo valor ao atributo speedA
    sliderA.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        speedA = newValue.intValue();
      }
    });
    //definindo listener para mudanca de velocidade do trem B em tempo real e atribuindo o novo valor ao atributo speedB
    sliderB.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        speedB = newValue.intValue();
      }
    });
  }
}

