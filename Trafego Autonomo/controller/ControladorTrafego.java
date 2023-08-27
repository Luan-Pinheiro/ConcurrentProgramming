package controller;

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
import model.Carro;

public class ControladorTrafego implements Initializable{
  @FXML private Button btnCarro1;
  @FXML private Button btnCarro2;
  @FXML private Button btnCarro3;
  @FXML private Button btnCarro4;
  @FXML private Button btnCarro5;
  @FXML private Button btnCarro6;
  @FXML private Button btnCarro7;
  @FXML private Button btnCarro8;
  @FXML private Button btnEncerrar;
  @FXML private Button btnIniciar;
  @FXML private Button btnReiniciar;

  @FXML private ImageView imgCarro1;
  @FXML private ImageView imgCarro2;
  @FXML private ImageView imgCarro3;
  @FXML private ImageView imgCarro4;
  @FXML private ImageView imgCarro5;
  @FXML private ImageView imgCarro6;
  @FXML private ImageView imgCarro7;
  @FXML private ImageView imgCarro8;
  
  @FXML private ImageView imgPlay2;
  @FXML private ImageView imgPlay3;
  @FXML private ImageView imgPlay4;
  @FXML private ImageView imgPlay5;
  @FXML private ImageView imgPlay1;
  @FXML private ImageView imgPlay6;
  @FXML private ImageView imgPlay7;
  @FXML private ImageView imgPlay8;
  @FXML private ImageView imgStop1;
  @FXML private ImageView imgStop2;
  @FXML private ImageView imgStop3;
  @FXML private ImageView imgStop4;
  @FXML private ImageView imgStop5;
  @FXML private ImageView imgStop6;
  @FXML private ImageView imgStop7;
  @FXML private ImageView imgStop8;
  
  @FXML private Slider sldCarro1;
  @FXML private Slider sldCarro2;
  @FXML private Slider sldCarro3;
  @FXML private Slider sldCarro4;
  @FXML private Slider sldCarro5;
  @FXML private Slider sldCarro6;
  @FXML private Slider sldCarro7;
  @FXML private Slider sldCarro8;

  //Declarando as Threads 
  Carro carro1;
  Carro carro2;
  Carro carro3;
  Carro carro4;
  Carro carro5;
  Carro carro6;
  Carro carro7;
  Carro carro8;
  
  private int veloCarro1;
  private int veloCarro2;
  private int veloCarro3;
  private int veloCarro4;
  private int veloCarro5;
  private int veloCarro6;
  private int veloCarro7;
  private int veloCarro8;
  
  //Getters e Setters
  public int getVeloCarro(int indice) {
    switch(indice){
      case 0: 
        return veloCarro1;
      case 1: 
        return veloCarro2;
      case 2: 
        return veloCarro3;
      case 3: 
        return veloCarro4;
      case 4: 
        return veloCarro5;
      case 5: 
        return veloCarro6;
      case 6: 
        return veloCarro7;
      case 7: 
        return veloCarro8;
    }
    return 0;
  }
  public ImageView getImgCarro1() {
    return imgCarro1;
  }
  public void setImgCarro1(ImageView imgCarro1) {
    this.imgCarro1 = imgCarro1;
  }
  public ImageView getImgCarro2() {
    return imgCarro2;
  }
  public void setImgCarro2(ImageView imgCarro2) {
    this.imgCarro2 = imgCarro2;
  }
  public ImageView getImgCarro3() {
    return imgCarro3;
  }
  public void setImgCarro3(ImageView imgCarro3) {
    this.imgCarro3 = imgCarro3;
  }
  public ImageView getImgCarro4() {
    return imgCarro4;
  }
  public void setImgCarro4(ImageView imgCarro4) {
    this.imgCarro4 = imgCarro4;
  }
  public ImageView getImgCarro5() {
    return imgCarro5;
  }
  public void setImgCarro5(ImageView imgCarro5) {
    this.imgCarro5 = imgCarro5;
  }
  public ImageView getImgCarro6() {
    return imgCarro6;
  }
  public void setImgCarro6(ImageView imgCarro6) {
    this.imgCarro6 = imgCarro6;
  }
  public ImageView getImgCarro7() {
    return imgCarro7;
  }
  public void setImgCarro7(ImageView imgCarro7) {
    this.imgCarro7 = imgCarro7;
  }
  public ImageView getImgCarro8() {
    return imgCarro8;
  }
  public void setImgCarro8(ImageView imgCarro8) {
    this.imgCarro8 = imgCarro8;
  }
  
  public void turnImgVisibleAndConfigureSliderOnStart(){
    /*Definindo a bolinha do slider para iniciar no centro*/
    //Carro1
    sldCarro1.setValue(0);
    //Carro2
    sldCarro2.setValue(0);
    //Carro3
    sldCarro3.setValue(0);
    //Carro4
    sldCarro4.setValue(0);
    //Carro5
    sldCarro5.setValue(0);
    //Carro6
    sldCarro6.setValue(0);
    //Carro7
    sldCarro7.setValue(0);
    //Carro8
    sldCarro8.setValue(0);

    //Deixando alguns imageViews invisiveis
    imgCarro1.setVisible(false);
    imgCarro2.setVisible(false);
    imgCarro3.setVisible(false);
    imgCarro4.setVisible(false);
    imgCarro5.setVisible(false);
    imgCarro6.setVisible(false);
    imgCarro7.setVisible(false);
    imgCarro8.setVisible(false);
  }

  @FXML
  void OnClickEncerrar(ActionEvent event) {
    //Encerrando execucao
    System.exit(0);
  }
  @FXML
  void OnClickIniciar(ActionEvent event) {
    btnIniciar.setVisible(false);
    //Tornando os carros e botoes visiveis
    imgStop1.setVisible(true);
    imgStop2.setVisible(true);
    imgStop3.setVisible(true);
    imgStop4.setVisible(true);
    imgStop5.setVisible(true);
    imgStop6.setVisible(true);
    imgStop7.setVisible(true);
    imgStop8.setVisible(true);
    btnCarro1.setVisible(true);
    btnCarro2.setVisible(true);
    btnCarro3.setVisible(true);
    btnCarro4.setVisible(true);
    btnCarro5.setVisible(true);
    btnCarro6.setVisible(true);
    btnCarro7.setVisible(true);
    btnCarro8.setVisible(true);
    imgCarro1.setVisible(true);
    imgCarro2.setVisible(true);
    imgCarro3.setVisible(true);
    imgCarro4.setVisible(true);
    imgCarro5.setVisible(true);
    imgCarro6.setVisible(true);
    imgCarro7.setVisible(true);
    imgCarro8.setVisible(true);
    //Iniciando Threads
    carro1.start();
    carro2.start();
    carro3.start();
    carro4.start();
    carro5.start();
    carro6.start();
    carro7.start();
    carro8.start();
  }
  @FXML
  void OnClickPauseCarro1(ActionEvent event) {
    if(imgPlay1.isVisible()){
      imgPlay1.setVisible(false);
      imgStop1.setVisible(true);
      carro1.resume();
    }else{
      imgPlay1.setVisible(true);
      imgStop1.setVisible(false);
      carro1.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro2(ActionEvent event) {
    if(imgPlay2.isVisible()){
      imgPlay2.setVisible(false);
      imgStop2.setVisible(true);
      carro2.resume();
    }else{
      imgPlay2.setVisible(true);
      imgStop2.setVisible(false);
      carro2.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro3(ActionEvent event) {
    if(imgPlay3.isVisible()){
      imgPlay3.setVisible(false);
      imgStop3.setVisible(true);
      carro3.resume();
    }else{
      imgPlay3.setVisible(true);
      imgStop3.setVisible(false);
      carro3.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro4(ActionEvent event) {
    if(imgPlay4.isVisible()){
      imgPlay4.setVisible(false);
      imgStop4.setVisible(true);
      carro4.resume();
    }else{
      imgPlay4.setVisible(true);
      imgStop4.setVisible(false);
      carro4.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro5(ActionEvent event) {
    if(imgPlay5.isVisible()){
      imgPlay5.setVisible(false);
      imgStop5.setVisible(true);
      carro5.resume();
    }else{
      imgPlay5.setVisible(true);
      imgStop5.setVisible(false);
      carro5.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro6(ActionEvent event) {
    if(imgPlay6.isVisible()){
      imgPlay6.setVisible(false);
      imgStop6.setVisible(true);
      carro6.resume();
    }else{
      imgPlay6.setVisible(true);
      imgStop6.setVisible(false);
      carro6.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro7(ActionEvent event) {
    if(imgPlay7.isVisible()){
      imgPlay7.setVisible(false);
      imgStop7.setVisible(true);
      carro7.resume();
    }else{
      imgPlay7.setVisible(true);
      imgStop7.setVisible(false);
      carro7.suspend();
    }
  }
  @FXML
  void OnClickPauseCarro8(ActionEvent event) {
    if(imgPlay8.isVisible()){
      imgPlay8.setVisible(false);
      imgStop8.setVisible(true);
      carro8.resume();
    }else{
      imgPlay8.setVisible(true);
      imgStop8.setVisible(false);
      carro8.suspend();
    }
  }
  @FXML
  void OnClickReiniciar(ActionEvent event) {
    //Terminando as Threads
    carro1.stop();
    carro2.stop();
    carro3.stop();
    carro4.stop();
    carro5.stop();
    carro6.stop();
    carro7.stop();
    carro8.stop();
    //Instanciando as Threads
    carro1 = new Carro(this, "01");
    carro2 = new Carro(this, "06");
    carro3 = new Carro(this, "07");
    carro4 = new Carro(this, "08");
    carro5 = new Carro(this, "16");
    carro6 = new Carro(this, "17");
    carro7 = new Carro(this, "20");
    carro8 = new Carro(this, "21");
    ///Reiniciando semaforos
    Carro.setSemaforo(1);
    /*Definindo a bolinha do slider para iniciar no centro*/
    //Carro1
    sldCarro1.setValue(1);
    //Carro2
    sldCarro2.setValue(1);
    //Carro3
    sldCarro3.setValue(1);
    //Carro4
    sldCarro4.setValue(1);
    //Carro5
    sldCarro5.setValue(1);
    //Carro6
    sldCarro6.setValue(1);
    //Carro7
    sldCarro7.setValue(1);
    //Carro8
    sldCarro8.setValue(1);
    //Tornando os carros visiveis e botao de play invisivel
    imgPlay1.setVisible(false);
    imgPlay2.setVisible(false);
    imgPlay3.setVisible(false);
    imgPlay4.setVisible(false);
    imgPlay5.setVisible(false);
    imgPlay6.setVisible(false);
    imgPlay7.setVisible(false);
    imgPlay8.setVisible(false);
    imgStop1.setVisible(true);
    imgStop2.setVisible(true);
    imgStop3.setVisible(true);
    imgStop4.setVisible(true);
    imgStop5.setVisible(true);
    imgStop6.setVisible(true);
    imgStop6.setVisible(true);
    imgStop7.setVisible(true);
    imgStop8.setVisible(true);
    imgCarro1.setVisible(true);
    imgCarro2.setVisible(true);
    imgCarro3.setVisible(true);
    imgCarro4.setVisible(true);
    imgCarro5.setVisible(true);
    imgCarro6.setVisible(true);
    imgCarro7.setVisible(true);
    imgCarro8.setVisible(true);
    //Iniciando Threads
    carro1.start();
    carro2.start();
    carro3.start();
    carro4.start();
    carro5.start();
    carro6.start();
    carro7.start();
    carro8.start();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Tornando botoes de pause invisiveis
    imgPlay1.setVisible(false);
    imgPlay2.setVisible(false);
    imgPlay3.setVisible(false);
    imgPlay4.setVisible(false);
    imgPlay5.setVisible(false);
    imgPlay6.setVisible(false);
    imgPlay7.setVisible(false);
    imgPlay8.setVisible(false);
    imgStop1.setVisible(false);
    imgStop2.setVisible(false);
    imgStop3.setVisible(false);
    imgStop4.setVisible(false);
    imgStop5.setVisible(false);
    imgPlay6.setVisible(false);
    imgStop6.setVisible(false);
    imgStop6.setVisible(false);
    imgStop7.setVisible(false);
    imgStop8.setVisible(false);
    btnCarro1.setVisible(false);
    btnCarro2.setVisible(false);
    btnCarro3.setVisible(false);
    btnCarro4.setVisible(false);
    btnCarro5.setVisible(false);
    btnCarro6.setVisible(false);
    btnCarro7.setVisible(false);
    btnCarro8.setVisible(false);

    /*Subdividindo as barras de velocidades em tres partes, 
    setando as bolinhas de selecao para ocupar as tres partes da divisao.*/
    //Carro1
    sldCarro1.setMin(0);
    sldCarro1.setMax(2);
    //Carro2
    sldCarro2.setMin(0);
    sldCarro2.setMax(2);
    //Carro3
    sldCarro3.setMin(0);
    sldCarro3.setMax(2);
    //Carro4
    sldCarro4.setMin(0);
    sldCarro4.setMax(2);
    //Carro5
    sldCarro5.setMin(0);
    sldCarro5.setMax(2);
    //Carro6
    sldCarro6.setMin(0);
    sldCarro6.setMax(2);
    //Carro7
    sldCarro7.setMin(0);
    sldCarro7.setMax(2);
    //Carro8
    sldCarro8.setMin(0);
    sldCarro8.setMax(2);

    turnImgVisibleAndConfigureSliderOnStart();

    //definindo unidade de marcacao principal do slider
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    //carro1
    sldCarro1.setMajorTickUnit(1);
    sldCarro1.setMinorTickCount(0);
    sldCarro1.setSnapToTicks(true);
    //carro2
    sldCarro2.setMajorTickUnit(1);
    sldCarro2.setMinorTickCount(0);
    sldCarro2.setSnapToTicks(true);
    //carro3
    sldCarro3.setMajorTickUnit(1);
    sldCarro3.setMinorTickCount(0);
    sldCarro3.setSnapToTicks(true);
    //carro4
    sldCarro4.setMajorTickUnit(1);
    sldCarro4.setMinorTickCount(0);
    sldCarro4.setSnapToTicks(true);
    //carro5
    sldCarro5.setMajorTickUnit(1);
    sldCarro5.setMinorTickCount(0);
    sldCarro5.setSnapToTicks(true);
    //carro6
    sldCarro6.setMajorTickUnit(1);
    sldCarro6.setMinorTickCount(0);
    sldCarro6.setSnapToTicks(true);
    //carro7
    sldCarro7.setMajorTickUnit(1);
    sldCarro7.setMinorTickCount(0);
    sldCarro7.setSnapToTicks(true);
    //carro8
    sldCarro8.setMajorTickUnit(1);
    sldCarro8.setMinorTickCount(0);
    sldCarro8.setSnapToTicks(true);

    //Instanciando as Threads
    carro1 = new Carro(this, "01");
    carro2 = new Carro(this, "06");
    carro3 = new Carro(this, "07");
    carro4 = new Carro(this, "08");
    carro5 = new Carro(this, "16");
    carro6 = new Carro(this, "17");
    carro7 = new Carro(this, "20");
    carro8 = new Carro(this, "21");

    //definindo listeners para mudanca de velocidade dos carros em tempo real
    sldCarro1.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro1 = newValue.intValue();
      }
    });
    sldCarro2.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro2 = newValue.intValue();
      }
    });
    sldCarro3.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro3 = newValue.intValue();
      }
    });
    sldCarro4.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro4 = newValue.intValue();
      }
    });
    sldCarro5.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro5 = newValue.intValue();
      }
    });
    sldCarro6.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro6 = newValue.intValue();
      }
    });
    sldCarro7.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro7 = newValue.intValue();
      }
    });
    sldCarro8.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade
        veloCarro8 = newValue.intValue();
      }
    });
  }

}
