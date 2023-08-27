//pacote da classe
package controller;
//importacoes
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Consumidor;
import model.Produtor;
import model.Semaforo;

public class ControllerPC implements Initializable{
  @FXML
  private Button btnEncerrar;
  @FXML
  private Button btnIniciar;
  @FXML
  private Button btnPausarConsu;
  @FXML
  private Button btnPausarProd;
  @FXML
  private Button btnReiniciar;
  @FXML
  private ImageView imgBox1D;
  @FXML
  private ImageView imgBox2D;
  @FXML
  private ImageView imgBox3D;
  @FXML
  private ImageView imgCam1;
  @FXML
  private ImageView imgCam2;
  @FXML
  private Slider sliderConsu;
  @FXML
  private Slider sliderProd;
  
  //instanciando threads no controle
  Produtor produtor;
  Consumidor consumidor;
  //atributos auxiliares de velocidade do produtor e consumidor 
  private int velocidadeSliderProd = 1;
  private int velocidadeSliderConsu;
  
  //Getters e Setters
  public ImageView getImgCam1() {
    return imgCam1;
  }
  public void setImgCam1(ImageView imgCam1) {
    this.imgCam1 = imgCam1;
  }
  public ImageView getImgCam2() {
    return imgCam2;
  }
  public void setImgCam2(ImageView imgCam2) {
    this.imgCam2 = imgCam2;
  }
  
  public ImageView getImgBox1D() {
    return imgBox1D;
  }
  public void setImgBox1D(ImageView imgBox1D) {
    this.imgBox1D = imgBox1D;
  }
  public ImageView getImgBox2D() {
    return imgBox2D;
  }
  public void setImgBox2D(ImageView imgBox2D) {
    this.imgBox2D = imgBox2D;
  }
  public ImageView getImgBox3D() {
    return imgBox3D;
  }
  public void setImgBox3D(ImageView imgBox3D) {
    this.imgBox3D = imgBox3D;
  }
  public int getVelocidadeSliderProd() {
    return velocidadeSliderProd;
  }
  public int getVelocidadeSliderConsu() {
    return velocidadeSliderConsu;
  }
  
  @FXML
  void OnClickBtnIniciar(ActionEvent event){//quando o botao de iniciar eh clicado
    //as threads sao iniciadas
    produtor.start();
    consumidor.start();
  }

  @FXML
  void OnClickBtnPausarConsu(ActionEvent event) {//quando ha o click no botao de pausar do consumidor 
    //caso a thread esteja pausada
    if (btnPausarConsu.getText().equals("Seguir")){
      //a thread volta a  atividade
      consumidor.resume();
      //o botao de pausar eh redefinido para pausar
      setEstiloPausar(btnPausarConsu);
      btnPausarConsu.setText("Pausar");
    } 
    //caso a thread nao esteja pausada
    else{
      //o botao de pausar eh redefinido para seguir
      btnPausarConsu.setText("Seguir");
      setEstiloSeguir(btnPausarConsu);
      //a thread eh pausada
      consumidor.suspend();
    }
  }

  @FXML
  void OnClickBtnPausarProd(ActionEvent event) {//quando ha o click no botao de pausar do produtor 
    //caso a thread esteja pausada
    if (btnPausarProd.getText().equals("Seguir")){
      //a thread volta a  atividade
      produtor.resume();
      //o botao de pausar eh redefinido para pausar
      setEstiloPausar(btnPausarProd);
      btnPausarProd.setText("Pausar");
    } 
    //caso a thread nao esteja pausada
    else{
      //o botao de pausar eh redefinido para seguir
      btnPausarProd.setText("Seguir");
      setEstiloSeguir(btnPausarProd);
      //a thread eh pausada
      produtor.suspend();
    }
  }
  
  @FXML
  void OnClickBtnReiniciar(ActionEvent event){
    //encerrando as threads
    produtor.stop();
    consumidor.stop();
    //instanciando novas threads
    produtor = new Produtor(this);
    consumidor = new Consumidor(this);
    //restaurando os botoes de pausar
    resetPausar(btnPausarConsu);
    btnPausarConsu.setText("Pausar");
    resetPausar(btnPausarProd);
    btnPausarProd.setText("Pausar");
    //tornando imagens dos produtos invisiveis
    imgBox1D.setVisible(false);
    imgBox2D.setVisible(false);
    imgBox3D.setVisible(false);
    //revertendo as imagens dos caminhoes para o padrao original
    imgCam1.setScaleX(1);
    imgCam2.setScaleX(1);
    //setando marcador inicial do sliders
    sliderProd.setValue(1);
    sliderConsu.setValue(0);
    //instanciando um novo semaforo
    Semaforo.setMutex(1);
    Semaforo.setVazio();
    Semaforo.setCheio(0);
    //iniciando as threads
    produtor.start();
    consumidor.start();
  }
  
  @FXML
  void OnClickBtnEncerrar(ActionEvent event){
    //encerra execucao
    System.exit(0);
  }
  //metodos de mudanca de estilizacao
  public void setEstiloSeguir(Button btn){
    //metodo de identificacao quando o cursor esta sobre o botao, semelhante ao hover do css
    btn.setOnMouseEntered(event -> {
      //setando estilo
      btn.setStyle("-fx-border-color: rgb(39, 73, 39);-fx-background-color:  rgba(61, 228, 83, 0.464);;-fx-background-radius:  13px;-fx-border-radius:  10px; -fx-border-width: 2px; -fx-text-fill: white; -fx-font-weight: bold;");
    });
    //quando o cursor sair do botao
    btn.setOnMouseExited(event -> {
      //o estilo retorna ao anterior
      btn.setStyle("-fx-background-color: #09BA4F;-fx-background-radius:  13px;-fx-border-color:  white;-fx-border-radius:  10px; -fx-border-width: 2px; -fx-text-fill: white; -fx-font-weight: bold;");
    });
  }
  public void setEstiloPausar(Button btn){
    //metodo de identificacao quando o cursor esta sobre o botao, semelhante ao hover do css
    btn.setOnMouseEntered(event -> {
      //setando estilo
      btn.setStyle("-fx-border-color: rgb(39, 73, 39);-fx-background-color:  rgba(61, 228, 83, 0.464);;-fx-background-radius:  13px;-fx-border-radius:  10px; -fx-border-width: 2px; -fx-text-fill: white; -fx-font-weight: bold;");
    });
    //quando o cursor sair do botao
    btn.setOnMouseExited(event -> {
      //o estilo retorna ao anterior
      btn.setStyle("-fx-background-color: #BA0909;-fx-background-radius:  13px;-fx-border-color:  white;-fx-border-radius:  10px; -fx-border-width: 2px; -fx-text-fill: white; -fx-font-weight: bold;");
    });
  }
  //metodo que retorna o estilo do botao de pausar para o original
  public void resetPausar(Button btn){
    btn.setStyle("-fx-background-color: #BA0909;-fx-background-radius:  13px;-fx-border-color:  white;-fx-border-radius:  10px; -fx-border-width: 2px; -fx-text-fill: white; -fx-font-weight: bold;");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /*Subdividindo as barras de velocidades em tres partes, 
    setando as bolinhas de selecao para ocupar as tres partes da divisao.*/
    sliderProd.setMin(0);
    sliderProd.setMax(2);
    sliderProd.setValue(1);
    //definindo unidade de marcacao principal do slider
    sliderProd.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderProd.setMinorTickCount(0);
    sliderProd.setSnapToTicks(true);

    sliderConsu.setMin(0);
    sliderConsu.setMax(2);
    sliderConsu.setValue(0);
    //definindo unidade de marcacao principal do slider
    sliderConsu.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderConsu.setMinorTickCount(0);
    sliderConsu.setSnapToTicks(true);
    //Passando a referencia desse controlle para as threads que o chamam.
    produtor = new Produtor(this);
    consumidor = new Consumidor(this);
    //Deixando os Image Views inicialmente invisiveis.
    imgCam1.setVisible(false);
    imgCam2.setVisible(false);
    imgBox1D.setVisible(false);
    imgBox2D.setVisible(false);
    imgBox3D.setVisible(false);

    //definindo listener para mudanca de velocidade do caminhao A em tempo real e atribuindo o novo valor ao atributo de velocidade do produtor
    sliderProd.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade do produtor
        velocidadeSliderProd = newValue.intValue();
      }
    });
    //definindo listener para mudanca de velocidade do caminhao B em tempo real e atribuindo o novo valor ao atributo de velocidade do consumidor
    sliderConsu.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade do consumidor
        velocidadeSliderConsu = newValue.intValue();
      }
    });
  }

}