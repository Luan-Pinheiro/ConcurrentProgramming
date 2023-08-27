//pacote da classe
package controller;
//Importacao de bibliotecas
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.*;

public class ControllerThread implements Initializable {
  //Barra de textos e exibicao de idades
  @FXML
  private Label labelBisneto;
  @FXML
  private Label labelPai;
  @FXML
  private Label labelPrimFilho;
  @FXML
  private Label labelPrimNeto;
  @FXML
  private Label labelSegFilho;
  @FXML
  private Label labelSegNeto;
  @FXML
  private Label labelTercFilho;
  @FXML
  private Text txtBisneto;
  @FXML
  private Text txtPai;
  @FXML
  private Text txtPrimFilho;
  @FXML
  private Text txtPrimNeto;
  @FXML
  private Text txtSegFilho;
  @FXML
  private Text txtSegNeto;
  @FXML
  private Text txtTercFilho;
  //Estrutura principal da tela
  @FXML
  private AnchorPane mainAP;
  @FXML
  private ImageView arvGenImg;
  @FXML
  private Button btnStart;
  //Imagens referentes aos entes da familia
  @FXML
  private ImageView paiImg;
  @FXML
  private ImageView primBisnetoImg;
  @FXML
  private ImageView primFilhoImg;
  @FXML
  private ImageView primNetoImg;
  @FXML
  private ImageView segFilhoImg;
  @FXML
  private ImageView segNetoImg;
  @FXML
  private ImageView terFilhoImg;

  //Instanciando as classes correspondentes aos entes familiares
  ThreadPai pai = new ThreadPai();
  ThreadPrimeiroFilho primeiroFilho = new ThreadPrimeiroFilho();
  ThreadSegundoFilho segundoFilho = new ThreadSegundoFilho();
  ThreadTerceiroFilho terceiroFilho = new ThreadTerceiroFilho();
  ThreadPrimeiroNeto primeiroNeto = new ThreadPrimeiroNeto();
  ThreadSegundoNeto segundoNeto = new ThreadSegundoNeto();
  ThreadBisneto bisneto = new ThreadBisneto();

  //Inicia o funcionamento da aplicacao ao clicar no botao
  @FXML
  public void OnClickBtnStart(ActionEvent event) {
    //inicia o processo pai
    pai.start();
    //Retira o botao
    btnStart.setVisible(false);
  }

  //Metodos para inicializar e trocar as imagens dos familiares
  //incializar imagens
  public void comecaPai(){
    this.paiImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelPai.setVisible(true);
  }
  public void comecaPrimeiroFilho(){
    this.primeiroFilho.start();
    this.primFilhoImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelPrimFilho.setVisible(true);
  }
  public void comecaSegundoFilho(){
    this.segundoFilho.start();
    this.segFilhoImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelSegFilho.setVisible(true);
  }
  public void comecaTerceiroFilho(){
    this.terceiroFilho.start();
    this.terFilhoImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelTercFilho.setVisible(true);
  }
  public void comecaPrimeiroNeto(){
    this.primeiroNeto.start();
    this.primNetoImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelPrimNeto.setVisible(true);
  }
  public void comecaSegundoNeto(){
    this.segundoNeto.start();
    this.segNetoImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelSegNeto.setVisible(true);
  }
  public void comecaPrimeiroBisneto(){
    this.bisneto.start();
    this.primBisnetoImg.setImage(new Image("./view/bebe.jpeg"));;
    this.labelBisneto.setVisible(true);
  }
  //atualiza idades
  public void exibeIdadePai(){
    this.txtPai.setText(String.valueOf(pai.getIdade()));
  }
  public void exibeIdadePrimeiroFilho(){
    this.txtPrimFilho.setText(String.valueOf(primeiroFilho.getIdade()));
  }
  public void exibeIdadeSegundoFilho(){
    this.txtSegFilho.setText(String.valueOf(segundoFilho.getIdade()));
  }
  public void exibeIdadeTerceiroFilho(){
    this.txtTercFilho.setText(String.valueOf(terceiroFilho.getIdade()));
  }
  public void exibeIdadePrimeiroNeto(){
    this.txtPrimNeto.setText(String.valueOf(primeiroNeto.getIdade()));
  }
  public void exibeIdadeSegundoNeto(){
    this.txtSegNeto.setText(String.valueOf(segundoNeto.getIdade()));
  }
  public void exibeIdadeBisneto(){
    this.txtBisneto.setText(String.valueOf(bisneto.getIdade()));
  }
  //Metodos para realizar a troca das imagens nos "Image Views" de cada ente familiar
  public void trocaFazeVidaPai(String diretorio){
    this.paiImg.setImage(new Image(diretorio));
  }
  public void trocaFazeVidaPFilho(String diretorio){
    this.primFilhoImg.setImage(new Image(diretorio));
  }
  public void trocaFazeVidaSFilho(String diretorio){
    this.segFilhoImg.setImage(new Image(diretorio));
  }
  public void trocaFazeVidaTFilho(String diretorio){
    this.terFilhoImg.setImage(new Image(diretorio));
  }
  public void trocaFazeVidaPNeto(String diretorio){
    this.primNetoImg.setImage(new Image(diretorio));
  }
  public void trocaFazeVidaSNeto(String diretorio){
    this.segNetoImg.setImage(new Image(diretorio));
  }
  public void trocaFazeVidaPBisneto(String diretorio){
    this.primBisnetoImg.setImage(new Image(diretorio));
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Tornando o campo das idades dos entes temporariamente invisiveis até os familiares respectivos serem inicializados
    labelBisneto.setVisible(false);
    labelPai.setVisible(false);
    labelPrimFilho.setVisible(false);
    labelPrimNeto.setVisible(false);
    labelSegFilho.setVisible(false);
    labelSegNeto.setVisible(false);
    labelTercFilho.setVisible(false);
    //Definindo o controlador que ira manipular cada thread, no caso, esse. (Processo necessario para evitar estouro de memória 'StackOverFlow')
    pai.linkaControle(this);
    primeiroFilho.linkaControlador(this);
    segundoFilho.linkaControlador(this);
    terceiroFilho.linkaControlador(this);
    primeiroNeto.linkaControlador(this);
    segundoNeto.linkaControlador(this);
    bisneto.linkaControlador(this);
  }
}