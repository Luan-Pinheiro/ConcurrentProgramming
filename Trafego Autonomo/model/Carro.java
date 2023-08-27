package model;

import java.util.concurrent.Semaphore;
import controller.ControladorTrafego;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro extends Thread{
  private int posicaoInicialX;
  private int posicaoInicialY;
  private String percurso = "";
  ControladorTrafego ct;
  private int[] speedCarro = new int[8];
  private static Semaphore[] semaforo = new Semaphore[50];
  
  // Metodo para reinicializar semaforo
  public static void setSemaforo(int valor) {
    for(int i = 0; i < 50; i++){
      semaforo[i] = new Semaphore(valor);
    }
  }
  //contrutor
  public Carro(ControladorTrafego ct, String perc){
    this.ct = ct;
    this.percurso = perc;
    this.posicaoInicialX = 0;
    this.posicaoInicialY = 0;

    for(int i = 0; i < 50; i++){
      semaforo[i] = new Semaphore(1);
    }
  }
  
  // metodo de captura de velocidade em tempo real
  public int velocidadeCarro(int indice) {
    speedCarro[indice] = ct.getVeloCarro(indice);   
    switch(speedCarro[indice]){
        case 0: 
        speedCarro[indice] = 11;
          break;
        case 1: 
        speedCarro[indice] = 7;
            break;
        case 2: 
        speedCarro[indice] = 4;
          break;
        default:
          System.out.println("Não foi possivel atribuir");
          break;
    }
    return speedCarro[indice];
  }
  
  //funcoes que movem os carros horizontalmente e verticalmente na tela
  // carro indo para esquerda
  public void moveLeft(int stopPoint,ImageView img,int indice){
    while(this.posicaoInicialX >= stopPoint){
      try {
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(indice));
        //fazendo movimentacao do carro na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setX(this.posicaoInicialX));
        this.posicaoInicialX--;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
   // carro indo para cima
   public void moveUp(int stopPoint,ImageView img,int indice){
    while(this.posicaoInicialY >= stopPoint){
      try {
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(indice));
        //fazendo movimentacao do carro na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setY(this.posicaoInicialY ));
        this.posicaoInicialY --;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  // camcarroinhao indo para direita
  public void moveRight(int stopPoint,ImageView img,int indice){
    while(this.posicaoInicialX <= stopPoint){
      try {
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(indice));
        //fazendo movimentacao do ccarroaminhao na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setX(this.posicaoInicialX));
        this.posicaoInicialX++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  // camcarroinhao indo para baixo
  public void moveDown(int stopPoint,ImageView img,int indice){
    while(this.posicaoInicialY  <= stopPoint){
      try {
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(indice));
        //fazendo movimentacao do ccarroaminhao na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setY(this.posicaoInicialY ));
        this.posicaoInicialY ++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //PERCURSOS
  public void percurso01SH() throws InterruptedException{
    ImageView imagemCarro = ct.getImgCarro1();
    Platform.runLater(()->imagemCarro.setRotate(0));
    Platform.runLater(()->imagemCarro.setScaleX(1));
    while(true){
      moveRight(50,imagemCarro,0);
      //CRUZAMENTO 
      moveRight(150,imagemCarro,0);
      //CRUZAMENTO 
      moveRight(180,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(90));
      moveDown(65,imagemCarro,0);
      //CRUZAMENTO 
      moveDown(85, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(0));
      moveRight(260,imagemCarro,0);
      //CRUZAMENTO 
      moveRight(290,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(270));
      moveUp(25, imagemCarro,0);
      //CRUZAMENTO 
      moveUp(0, imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(0));
      moveRight(370,imagemCarro,0);
      //CRUZAMENTO 
      moveRight(470,imagemCarro,0);
      //CRUZAMENTO 
      moveRight(500,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(90));
      moveDown(65,imagemCarro,0);
      //CRUZAMENTO 
      moveDown(85,imagemCarro,0);
      moveDown(145,imagemCarro,0);
      //CRUZAMENTO 
      moveDown(165,imagemCarro,0);
      moveDown(230,imagemCarro,0);
      //CRUZAMENTO 
      moveDown(250,imagemCarro,0);
      moveDown(315,imagemCarro,0);
      //CRUZAMENTO 
      moveDown(335,imagemCarro,0);
      moveDown(400,imagemCarro,0);
      //CRUZAMENTO 
      moveDown(420,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(0));
      Platform.runLater(()->imagemCarro.setScaleX(-1));
      moveLeft(420, imagemCarro, 0);
      //CRUZAMENTO 
      moveLeft(400, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(270));
      Platform.runLater(()->imagemCarro.setScaleX(1));
      moveUp(355,imagemCarro,0);
      //CRUZAMENTO 
      moveUp(335,imagemCarro,0);
      moveUp(275,imagemCarro,0);
      //CRUZAMENTO 
      moveUp(250,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setScaleX(-1));
      Platform.runLater(()->imagemCarro.setRotate(0));
      moveLeft(320, imagemCarro, 0);
      //CRUZAMENTO 
      moveLeft(300, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(90));
      moveUp(195,imagemCarro,0);
      //CRUZAMENTO 
      moveUp(170,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(0));
      moveLeft(215, imagemCarro, 0);
      //CRUZAMENTO 
      moveLeft(190,imagemCarro,0);
      Platform.runLater(()->imagemCarro.setRotate(270));
      moveDown(225, imagemCarro, 0);
      //CRUZAMENTO 
      moveDown(245, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(0));
      moveLeft(105, imagemCarro, 0);
      //CRUZAMENTO 
      moveLeft(85, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(270));
      moveDown(310, imagemCarro, 0);
      //CRUZAMENTO 
      moveDown(330, imagemCarro, 0);
      moveDown(390, imagemCarro, 0);
      //CRUZAMENTO 
      moveDown(410, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(0));
      moveLeft(0, imagemCarro, 0);
      //CRUZAMENTO 
      moveLeft(-25, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setRotate(90));
      moveUp(365, imagemCarro, 0);
      //CRUZAMENTO 
      moveUp(345, imagemCarro, 0);
      moveUp(280, imagemCarro, 0);
      //CRUZAMENTO 
      moveUp(260, imagemCarro, 0);
      moveUp(195, imagemCarro, 0);
      //CRUZAMENTO 
      moveUp(175, imagemCarro, 0);
      moveUp(110, imagemCarro, 0);
      //CRUZAMENTO 
      moveUp(90, imagemCarro, 0);
      moveUp(30, imagemCarro, 0);
      //CRUZAMENTO 
      moveUp(0, imagemCarro, 0);
      Platform.runLater(()->imagemCarro.setScaleX(1));
      Platform.runLater(()->imagemCarro.setRotate(0));
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  public void percurso06SH() {
    ImageView imagemCarro2 = ct.getImgCarro2();
    Platform.runLater(()->imagemCarro2.setRotate(0));
    Platform.runLater(()->imagemCarro2.setScaleX(-1));
    while (true) {
        Platform.runLater(() -> imagemCarro2.setScaleX(-1));
        moveLeft(-45, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-75, imagemCarro2, 1);
        moveLeft(-155, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-180, imagemCarro2, 1);
        moveLeft(-260, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-290, imagemCarro2, 1);
        moveLeft(-370, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-395, imagemCarro2, 1);
        moveLeft(-475, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-500, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setRotate(90));
        moveUp(-60, imagemCarro2, 1);
        //CRUZAMENTO 
        moveUp(-80, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(1));
        Platform.runLater(() -> imagemCarro2.setRotate(0));
        moveRight(-425, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-395, imagemCarro2, 1);
        moveRight(-320, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-290, imagemCarro2, 1);
        moveRight(-215, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-185, imagemCarro2, 1);
        moveRight(-105, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-75, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(-1));
        Platform.runLater(() -> imagemCarro2.setRotate(90));
        moveUp(-140, imagemCarro2, 1);
        //CRUZAMENTO 
        moveUp(-165, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setRotate(0));
        moveLeft(-155, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-180, imagemCarro2, 1);
        moveLeft(-260, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-290, imagemCarro2, 1);
        moveLeft(-370, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-395, imagemCarro2, 1);
        moveLeft(-475, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-500, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(-1));
        Platform.runLater(() -> imagemCarro2.setRotate(90));
        moveUp(-225, imagemCarro2, 1);
        //CRUZAMENTO 
        moveUp(-245, imagemCarro2, 1);
        moveUp(-305, imagemCarro2, 1);
        //CRUZAMENTO 
        moveUp(-330, imagemCarro2, 1);
        moveUp(-390, imagemCarro2, 1);
        //CRUZAMENTO 
        moveUp(-415, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(1));
        Platform.runLater(() -> imagemCarro2.setRotate(0));
        moveRight(-425, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-395, imagemCarro2, 1);
        moveRight(-320, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-290, imagemCarro2, 1);
        moveRight(-215, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-185, imagemCarro2, 1);
        moveRight(-105, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-75, imagemCarro2, 1);
        moveRight(25, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setRotate(90));
        moveDown(-360, imagemCarro2, 1);
        //CRUZAMENTO 
        moveDown(-340, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(-1));
        Platform.runLater(() -> imagemCarro2.setRotate(0));
        moveLeft(-45, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-75, imagemCarro2, 1);
        moveLeft(-155, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-180, imagemCarro2, 1);
        moveLeft(-260, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-290, imagemCarro2, 1);
        moveLeft(-370, imagemCarro2, 1);
        //CRUZAMENTO 
        moveLeft(-395, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(1));
        Platform.runLater(() -> imagemCarro2.setRotate(90));
        moveDown(-280, imagemCarro2, 1);
        //CRUZAMENTO 
        moveDown(-260, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setScaleX(1));
        Platform.runLater(() -> imagemCarro2.setRotate(0));
        moveRight(-425, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-395, imagemCarro2, 1);
        moveRight(-320, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-290, imagemCarro2, 1);
        moveRight(-215, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-185, imagemCarro2, 1);
        moveRight(-105, imagemCarro2, 1);
        //CRUZAMENTO 
        moveRight(-75, imagemCarro2, 1);
        moveRight(25, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setRotate(90));
        moveDown(-190, imagemCarro2, 1);
        //CRUZAMENTO 
        moveDown(-170, imagemCarro2, 1);
        moveDown(-110, imagemCarro2, 1);
        //CRUZAMENTO 
        moveDown(-90, imagemCarro2, 1);
        moveDown(-30, imagemCarro2, 1);
        //CRUZAMENTO 
        moveDown(0, imagemCarro2, 1);
        Platform.runLater(() -> imagemCarro2.setRotate(0));
        this.posicaoInicialX = 0;
        this.posicaoInicialY = 0;
    }
  }
  public void percurso07SA() throws InterruptedException{
    ImageView imagemCarro3 = ct.getImgCarro3();
    Platform.runLater(()->imagemCarro3.setRotate(0));
    Platform.runLater(()->imagemCarro3.setScaleX(1));
    semaforo[10].acquire(); // Semaforo do cruzamento 16, carro 3 com 6
    semaforo[17].acquire(); // Semaforo do cruzamento 14, carro 3 com 7
    semaforo[31].acquire(); // Semaforo cruzamento 13, carro 3 com 8
    while(true){
      moveRight(30, imagemCarro3, 2);
      //CRUZAMENTO 14
      moveRight(70, imagemCarro3, 2);
      semaforo[31].release(); // Semaforo cruzamento 13, carro 3 com 8
      semaforo[17].release(); // Semaforo do cruzamento 14, carro 3 com 7
      moveRight(  155, imagemCarro3, 2);
      //CRUZAMENTO 15
      semaforo[8].acquire(); // Semaforo do cruzamento 15, carro 3 com 5
      moveRight(  180, imagemCarro3, 2);
      semaforo[8].release(); // Semaforo do cruzamento 15, carro 3 com 5
      moveRight(  260, imagemCarro3, 2);
      //CRUZAMENTO 16
      moveRight(  290, imagemCarro3, 2);
      semaforo[10].release(); // Semaforo do cruzamento 16, carro 3 com 6
      moveRight(  370, imagemCarro3, 2);
      //CRUZAMENTO 17
      semaforo[32].acquire(); // Semaforo cruzamento 17, carro 3 com 8
      semaforo[15].acquire(); // Semaforo do cruzamento 17, carro 3 com 7
      moveRight(  395, imagemCarro3, 2);
      moveRight(  475, imagemCarro3, 2);
      //CRUZAMENTO 18
      semaforo[9].acquire(); // Semaforo do cruzamento 18, carro 3 com 5
      semaforo[6].acquire(); // Semaforo do cruzamento 18, carro 3 com 4
      moveRight(  495, imagemCarro3, 2);
      semaforo[32].release(); // Semaforo cruzamento 17, carro 3 com 8
      Platform.runLater(() -> imagemCarro3.setRotate(270));
      semaforo[15].release(); // Semaforo do cruzamento 17, carro 3 com 7
      moveUp(-45, imagemCarro3, 2);
      //CRUZAMENTO 12
      moveUp(-65, imagemCarro3, 2);
      semaforo[6].release(); // Semaforo do cruzamento 18, carro 3 com 4
      moveUp(-125, imagemCarro3, 2);
      //CRUZAMENTO 6
      moveUp(-165, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setScaleX(-1));
      Platform.runLater(() -> imagemCarro3.setRotate(0));
      moveLeft(420, imagemCarro3, 2);
      //CRUZAMENTO 5
      semaforo[24].acquire(); // Semaforo cruzamento 5, carro 5 com 7
      moveLeft(400, imagemCarro3, 2);
      moveLeft(325, imagemCarro3, 2);
      //CRUZAMENTO 4
      semaforo[16].acquire(); // Semaforo cruzamento 4, carro 3 com 7
      moveLeft(295, imagemCarro3, 2);
      moveLeft(215, imagemCarro3, 2);
      //CRUZAMENTO 3
      moveLeft(190, imagemCarro3, 2);
      semaforo[16].release(); // Semaforo cruzamento 4, carro 3 com 7
      semaforo[9].release(); // Semaforo do cruzamento 18, carro 3 com 5
      moveLeft(105, imagemCarro3, 2);
      //CRUZAMENTO
      moveLeft(80, imagemCarro3, 2);
      moveLeft(0, imagemCarro3, 2);
      //CRUZAMENTO 2
      moveLeft(-35, imagemCarro3, 2);
      semaforo[24].release(); // Semaforo cruzamento 5, carro 5 com 7
      Platform.runLater(() -> imagemCarro3.setRotate(270));
      moveDown(-100, imagemCarro3, 2);
      //CRUZAMENTO 7
      semaforo[7].acquire(); //Semaforo cruzamento 7, carro 3 com 4
      moveDown(-80, imagemCarro3, 2);
      moveDown(-30, imagemCarro3, 2);
      //CRUZAMENTO 13
      semaforo[31].acquire(); // Semaforo cruzamento 13, carro 3 com 8
      semaforo[17].acquire(); // Semaforo do cruzamento 14, carro 3 com 7
      semaforo[10].acquire(); // Semaforo do cruzamento 16, carro 3 com 6
      moveDown(0, imagemCarro3, 2);
      semaforo[7].release(); //Semaforo cruzamento 7, carro 3 com 4
      Platform.runLater(() -> imagemCarro3.setScaleX(1));
      Platform.runLater(() -> imagemCarro3.setRotate(0));
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  public void percurso08SA() throws InterruptedException{
    ImageView imagemCarro4 = ct.getImgCarro4();
    Platform.runLater(()->imagemCarro4.setRotate(0));
    Platform.runLater(()->imagemCarro4.setScaleX(1));
    semaforo[14].acquire(); // Semaforo cruzamento 13, carro 4 com 7
    semaforo[22].acquire(); // Semaforo cruzamento 20, carro 4 com 8
    while(true){
      moveRight(45, imagemCarro4, 3);
      //CRUZAMENTO 20
      moveRight(70, imagemCarro4, 3);
      semaforo[22].release(); // Semaforo cruzamento 13, carro 4 com 8
      semaforo[14].release(); // Semaforo cruzamento 13, carro 4 com 7
      moveRight(  155, imagemCarro4, 3);
      //CRUZAMENTO 21
      semaforo[2].acquire();// Semaforo do cruzamento 21, carro 4 com 5
      moveRight(  180, imagemCarro4, 3);
      moveRight(  255, imagemCarro4, 3);
      //CRUZAMENTO 22
      semaforo[3].acquire();// Semaforo do cruzamento 22, carro 4 com 6
      moveRight(  295, imagemCarro4, 3);
      semaforo[3].release();// Semaforo do cruzamento 22, carro 4 com 6
      moveRight(  370, imagemCarro4, 3);
      //CRUZAMENTO 23
      semaforo[26].acquire(); // Semaforo cruzamento 23, carro 4 com 8
      semaforo[11].acquire(); // Semaforo cruzamento 23, carro 4 com 7
      moveRight(  395, imagemCarro4, 3);
      moveRight(  475, imagemCarro4, 3);
      //CRUZAMENTO 24
      moveRight(  495, imagemCarro4, 3);
      Platform.runLater(() -> imagemCarro4.setRotate(270));
      moveUp(-45, imagemCarro4, 3);
      //CRUZAMENTO 18
      semaforo[6].acquire(); // Semaforo do cruzamento 18, carro 4 com 3
      moveUp(-75, imagemCarro4, 3);
      semaforo[26].release(); // Semaforo cruzamento 23, carro 4 com 8
      semaforo[11].release(); // Semaforo cruzamento 13, carro 4 com 7
      moveUp(-125, imagemCarro4, 3);
      //CRUZAMENTO
      moveUp(-165, imagemCarro4, 3);
      semaforo[6].release(); // Semaforo do cruzamento 18, carro 4 com 3
      Platform.runLater(() -> imagemCarro4.setScaleX(-1));
      Platform.runLater(() -> imagemCarro4.setRotate(0));
      semaforo[2].release();// Semaforo do cruzamento 21, carro 4 com 5
      moveLeft(420, imagemCarro4, 3);
      //CRUZAMENTO 11 
      semaforo[25].acquire(); // Semaforo cruzamento 11, carro 4 com 8
      semaforo[12].acquire();// Semaforo do cruzamento 11, carro 4 com 7
      moveLeft(400, imagemCarro4, 3);
      semaforo[25].release(); // Semaforo cruzamento 11, carro 4 com 8
      moveLeft(325, imagemCarro4, 3);
      //CRUZAMENTO
      moveLeft(295, imagemCarro4, 3);
      semaforo[12].release();// Semaforo do cruzamento 11, carro 4 com 7
      moveLeft(215, imagemCarro4, 3);
      //CRUZAMENTO 9
      semaforo[13].acquire();// Semaforo do cruzamento 9, carro 4 com 7
      semaforo[4].acquire();// Semaforo do cruzamento 9, carro 4 com 5
      moveLeft(180, imagemCarro4, 3);
      semaforo[4].release();// Semaforo do cruzamento 9, carro 4 com 5
      moveLeft(105, imagemCarro4, 3);
      //CRUZAMENTO 8
      semaforo[23].acquire(); // Semaforo cruzamento 8, carro 4 com 8
      moveLeft(80, imagemCarro4, 3);
      semaforo[23].release(); // Semaforo cruzamento 8, carro 4 com 8
      semaforo[13].release();// Semaforo do cruzamento 11, carro 4 com 7
      moveLeft(0, imagemCarro4, 3);
      //CRUZAMENTO 7
      semaforo[7].acquire(); //Semaforo cruzamento 7, carro 4 com 3
      moveLeft(-35, imagemCarro4, 3);
      Platform.runLater(() -> imagemCarro4.setRotate(270));
      moveDown(-110, imagemCarro4, 3);
      //CRUZAMENTO 13
    semaforo[22].acquire(); // Semaforo cruzamento 20, carro 4 com 8
      semaforo[14].acquire();// Semaforo do cruzamento 11, carro 4 com 7
      semaforo[5].acquire();// Semaforo do cruzamento 13, carro 4 com 6
      moveDown(-80, imagemCarro4, 3);
      semaforo[7].release(); //Semaforo cruzamento 7, carro 4 com 3
      moveDown(-20, imagemCarro4, 3);
      //CRUZAMENTO
      moveDown(0, imagemCarro4, 3);
      semaforo[5].release();// Semaforo do cruzamento 13, carro 4 com 6
      Platform.runLater(() -> imagemCarro4.setScaleX(1));
      Platform.runLater(() -> imagemCarro4.setRotate(0));
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  public void percurso16SA() throws InterruptedException{
    ImageView imagemCarro5 = ct.getImgCarro5();
    Platform.runLater(()->imagemCarro5.setRotate(270));
    Platform.runLater(()->imagemCarro5.setScaleX(1));
    semaforo[2].acquire();//Semaforo do cruzamento 21, carro 5 com 4
    semaforo[18].acquire(); // Semaforo cruzamento 23, carro 5 com 7
    semaforo[35].acquire(); // Semaforo cruzamento 18, carro 5 com 7
    while(true){
      moveUp(-35, imagemCarro5, 4);
      //CRUZAMENTO 18
      semaforo[9].acquire(); // Semaforo do cruzamento 18, carro 5 com 3
      moveUp(-55, imagemCarro5, 4);
      semaforo[35].release(); // Semaforo cruzamento 18, carro 5 com 7
      semaforo[18].release(); // Semaforo cruzamento 23, carro 5 com 7
      moveUp(-110, imagemCarro5, 4);
      //CRUZAMENTO 12
      moveUp(-135, imagemCarro5, 4);
      semaforo[2].release();//Semaforo do cruzamento 21, carro 5 com 4
      moveUp(-195, imagemCarro5, 4);
      //CRUZAMENTO 6
      moveUp(-225, imagemCarro5, 4);
      Platform.runLater(() -> imagemCarro5.setScaleX(-1));
      Platform.runLater(() -> imagemCarro5.setRotate(0));
      moveLeft(-65, imagemCarro5, 4);
      //CRUZAMENTO 5
      semaforo[36].acquire(); // Semaforo cruzamento 5, carro 5 com 7
      moveLeft(-95, imagemCarro5, 4);
      moveLeft(-175, imagemCarro5, 4);
      //CRUZAMENTO 4
      semaforo[19].acquire(); // Semaforo cruzamento 4, carro 5 com 7
      moveLeft(-200, imagemCarro5, 4);
      moveLeft(-280, imagemCarro5, 4);
      //CRUZAMENTO 3
      moveLeft(-310, imagemCarro5, 4);
      semaforo[36].release(); // Semaforo cruzamento 5, carro 5 com 7
      Platform.runLater(() -> imagemCarro5.setScaleX(1));
      Platform.runLater(() -> imagemCarro5.setRotate(90));
      semaforo[9].release(); // Semaforo do cruzamento 18, carro 5 com 3
      moveDown(-170, imagemCarro5, 4);
      //CRUZAMENTO 9
      semaforo[4].acquire();// Semaforo do cruzamento 9, carro 5 com 4
      moveDown(-135, imagemCarro5, 4);
      semaforo[19].release(); // Semaforo cruzamento 4, carro 5 com 7
      semaforo[4].release();// Semaforo do cruzamento 9, carro 5 com 4
      moveDown(-100, imagemCarro5, 4);
      //CRUZAMENTO 15
      semaforo[1].acquire(); // Semaforo do cruzamento 15, carro 5 com 6
      semaforo[8].acquire(); // Semaforo do cruzamento 15, carro 5 com 3
      moveDown(-55, imagemCarro5, 4);
      semaforo[8].release(); // Semaforo do cruzamento 15, carro 5 com 3
      semaforo[1].release(); // Semaforo do cruzamento 15, carro 5 com 6
      moveDown(-5, imagemCarro5, 4);
      //CRUZAMENTO 21
      semaforo[2].acquire(); // Semaforo do cruzamento 21, carro 5 com 4
      moveDown(15, imagemCarro5, 4);
      Platform.runLater(() -> imagemCarro5.setRotate(0));
      moveRight(-240, imagemCarro5, 4);
      //CRUZAMENTO 22
      semaforo[0].acquire(); // Semaforo do cruzamento 22, carro 5 com 6
      moveRight(-200, imagemCarro5, 4);
      semaforo[0].release(); // Semaforo do cruzamento 22, carro 5 com 6
      moveRight(-130, imagemCarro5, 4);
      //CRUZAMENTO 23
      semaforo[35].acquire(); // Semaforo cruzamento 18, carro 5 com 7
      semaforo[18].acquire(); // Semaforo cruzamento 23, carro 5 com 7
      moveRight(-100, imagemCarro5, 4);
      moveRight(-25, imagemCarro5, 4);
      //CRUZAMENTO
      moveRight(0, imagemCarro5, 4);
      Platform.runLater(() -> imagemCarro5.setRotate(270));
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  public void percurso17SA() throws InterruptedException{
    ImageView imagemCarro6 = ct.getImgCarro6();
    Platform.runLater(()->imagemCarro6.setRotate(270));
    Platform.runLater(()->imagemCarro6.setScaleX(1));
    semaforo[21].acquire(); // Semaforo cruzamento 33, carro 6 com 7
    while(true){
      moveUp(-35, imagemCarro6, 5);
      //CRUZAMENTO 28
      moveUp(-55, imagemCarro6, 5);
      semaforo[21].release(); // Semaforo cruzamento 33, carro 6 com 7
      moveUp(-100, imagemCarro6, 5);
      //CRUZAMENTO 22
      semaforo[0].acquire(); // Semaforo do cruzamento 22, carro 6 com 5
      semaforo[3].acquire(); // Semaforo do cruzamento 22, carro 6 com 4
      moveUp(-150, imagemCarro6, 5);
      semaforo[0].release(); // Semaforo do cruzamento 22, carro 6 com 5
      semaforo[3].release(); // Semaforo do cruzamento 22, carro 6 com 4
      moveUp(-190, imagemCarro6, 5);
      //CRUZAMENTO 16
      semaforo[10].acquire(); // Semaforo do cruzamento 16, carro 6 com 3
      moveUp(-230, imagemCarro6, 5);
      Platform.runLater(() -> imagemCarro6.setScaleX(-1));
      Platform.runLater(() -> imagemCarro6.setRotate(0));
      moveLeft(-55, imagemCarro6, 5);
      //CRUZAMENTO 15
      semaforo[1].acquire(); // Semaforo do cruzamento 15, carro 6 com 5
      moveLeft(-115, imagemCarro6, 5);
      semaforo[1].release(); // Semaforo do cruzamento 15, carro 6 com 5
      moveLeft(-175, imagemCarro6, 5);
      //CRUZAMENTO 14 
      semaforo[33].acquire(); // Semaforo cruzamento 14, carro 6 com 8
      semaforo[20].acquire(); // Semaforo cruzamento 14, carro 6 com 7
      moveLeft(-200, imagemCarro6, 5);
      moveLeft(-280, imagemCarro6, 5);
      //CRUZAMENTO 13 
      semaforo[5].acquire();// Semaforo do cruzamento 13, carro 6 com 4
      moveLeft(-310, imagemCarro6, 5);
      semaforo[10].release(); // Semaforo do cruzamento 16, carro 6 com 3
      Platform.runLater(() -> imagemCarro6.setScaleX(1));
      Platform.runLater(() -> imagemCarro6.setRotate(90));
      moveDown(-170, imagemCarro6, 5);
      //CRUZAMENTO 19
      moveDown(-145, imagemCarro6, 5);
      semaforo[33].release(); // Semaforo cruzamento 14, carro 6 com 8
      semaforo[20].release(); // Semaforo cruzamento 14, carro 6 com 7
      semaforo[5].release();// Semaforo do cruzamento 13, carro 6 com 4
      moveDown(-90, imagemCarro6, 5);
      //CRUZAMENTO 25
      moveDown(-55, imagemCarro6, 5);
      moveDown(-5, imagemCarro6, 5);
      //CRUZAMENTO 31
      moveDown(15, imagemCarro6, 5);
      Platform.runLater(() -> imagemCarro6.setRotate(0));
      moveRight(-235, imagemCarro6, 5);
      //CRUZAMENTO 32
      semaforo[34].acquire(); // Semaforo cruzamento 32, carro 6 com 7 
      moveRight(-205, imagemCarro6, 5);
      moveRight(-130, imagemCarro6, 5);
      //CRUZAMENTO 33
      semaforo[21].acquire(); // Semaforo cruzamento 33, carro 6 com 7
      moveRight(-100, imagemCarro6, 5);
      moveRight(-25, imagemCarro6, 5);
      //CRUZAMENTO 34 
      moveRight(0, imagemCarro6, 5);
      semaforo[34].release(); // Semaforo cruzamento 32, carro 6 com 7 
      Platform.runLater(() -> imagemCarro6.setRotate(270));
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  public void percurso20SA() throws InterruptedException{
    ImageView imagemCarro7 = ct.getImgCarro7();
    Platform.runLater(()->imagemCarro7.setRotate(0));
    Platform.runLater(()->imagemCarro7.setScaleX(1));
    while(true){
      moveRight(65, imagemCarro7, 6);
      //CRUZAMENTO 29
      semaforo[27].acquire(); // Semaforo cruzamento 29, carro 7 com 8
      moveRight(75, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(270));
      moveUp(-50, imagemCarro7, 6);
      //CRUZAMENTO 23
      semaforo[11].acquire(); // Semaforo cruzamento 23, carro 7 com 4
      semaforo[18].acquire(); // Semaforo cruzamento 23, carro 7 com 5
      moveUp(-80,imagemCarro7,6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveRight(150, imagemCarro7, 6);
      //CRUZAMENTO 24
      moveRight(175, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(270));
      moveUp(-130, imagemCarro7, 6);
      //CRUZAMENTO 18
      semaforo[15].acquire(); // Semaforo cruzamento 18, carro 7 com 3
      moveUp(-160,imagemCarro7,6);
      semaforo[18].release(); // Semaforo cruzamento 23, carro 7 com 5
      Platform.runLater(() -> imagemCarro7.setScaleX(-1));
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      semaforo[11].release(); // Semaforo cruzamento 23, carro 7 com 4
      moveLeft(105, imagemCarro7, 6);
      //CRUZAMENTO 17
      moveLeft(80, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(90));
      semaforo[15].release(); // Semaforo cruzamento 18, carro 7 com 3
      moveUp(-210, imagemCarro7, 6);
      //CRUZAMENTO 11
      semaforo[12].acquire(); // Semaforo cruzamento 11, carro 7 com 4
      moveUp(-245,imagemCarro7,6);
      semaforo[27].release(); // Semaforo cruzamento 29, carro 7 com 8
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(0, imagemCarro7, 6);
      //CRUZAMENTO 10
      moveLeft(-30, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(90));
      semaforo[12].release(); // Semaforo cruzamento 11, carro 7 com 4
      moveUp(-300, imagemCarro7, 6);
      //CRUZAMENTO 4
      semaforo[30].acquire(); // Semaforo cruzamento 4, carro 7 com 8
      semaforo[19].acquire(); // Semaforo cruzamento 4, carro 7 com 5
      semaforo[16].acquire(); // Semaforo cruzamento 4, carro 7 com 3
      moveUp(-330,imagemCarro7,6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(-105, imagemCarro7, 6);
      //CRUZAMENTO 3
      moveLeft(-135, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(270));
      semaforo[30].release(); // Semaforo cruzamento 4, carro 7 com 8
      semaforo[16].release(); // Semaforo cruzamento 4, carro 7 com 3
      moveDown(-275, imagemCarro7, 6);
      //CRUZAMENTO 9
      semaforo[13].acquire(); // Semaforo cruzamento 9, carro 7 com 4
      moveDown(-245, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      semaforo[19].release(); // Semaforo cruzamento 4, carro 7 com 5
      moveLeft(-210, imagemCarro7, 6);
      //CRUZAMENTO 8
      semaforo[29].acquire(); // Semaforo cruzamento 8, carro 7 com 8
      moveLeft(-245, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(270));
      semaforo[13].release(); // Semaforo cruzamento 9, carro 7 com 4
      moveDown(-200, imagemCarro7, 6);
      //CRUZAMENTO 14
      semaforo[17].acquire(); // Semaforo cruzamento 14, carro 7 com 3
      semaforo[20].acquire(); // Semaforo cruzamento 14, carro 7 com 6
      moveDown(-160, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(-320, imagemCarro7, 6);
      //CRUZAMENTO 13
      semaforo[14].acquire(); // Semaforo cruzamento 13, carro 7 com 4
      moveLeft(-355, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(270));
      semaforo[17].release(); // Semaforo cruzamento 14, carro 7 com 3
      moveDown(-105, imagemCarro7, 6);
      //CRUZAMENTO 19
      moveDown(-75, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setScaleX(1));
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      semaforo[20].release(); // Semaforo cruzamento 14, carro 7 com 6
      moveRight(-280, imagemCarro7, 6);
      //CRUZAMENTO 20
      moveRight(-245, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(90));
      semaforo[14].release(); // Semaforo cruzamento 13, carro 7 com 4
      moveDown(-20, imagemCarro7, 6);
      //CRUZAMENTO 26
      moveDown(5, imagemCarro7, 6);
      semaforo[29].release(); // Semaforo cruzamento 8, carro 7 com 8
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveRight(-170, imagemCarro7, 6);
      //CRUZAMENTO 27
      moveRight(-135, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(90));
      moveDown(60, imagemCarro7, 6);
      //CRUZAMENTO 33
      semaforo[28].acquire(); // Semaforo cruzamento 33, carro 7 com 8
      semaforo[21].acquire(); // Semaforo cruzamento 33, carro 7 com 6
      moveDown(85, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveRight(-60, imagemCarro7, 6);
      //CRUZAMENTO 34
      moveRight(-30, imagemCarro7, 6);
      semaforo[28].release(); // Semaforo cruzamento 33, carro 7 com 8
      Platform.runLater(() -> imagemCarro7.setRotate(270));
      moveUp(35, imagemCarro7, 6);
      //CRUZAMENTO 28
      moveUp(5, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      semaforo[21].release(); // Semaforo cruzamento 33, carro 7 com 6
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  public void percurso21SH() throws InterruptedException{
    ImageView imagemCarro8 = ct.getImgCarro8();
    Platform.runLater(()->imagemCarro8.setRotate(90));
    Platform.runLater(()->imagemCarro8.setScaleX(1));
    semaforo[29].acquire(); // Semaforo cruzamento 26, carro 8 com 7
    while(true){
      moveUp(-20, imagemCarro8, 7);
      //CRUZAMENTO 20
      semaforo[22].acquire(); // Semaforo cruzamento 20, carro 8 com 4
      moveUp(-65,imagemCarro8,7);
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(-85, imagemCarro8, 7);
      //CRUZAMENTO 19
      semaforo[33].acquire(); // Semaforo cruzamento 19, carro 8 com 6
      moveLeft(-105, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(90));
      moveUp(-125, imagemCarro8, 7);
      //CRUZAMENTO 13
      semaforo[31].acquire(); // Semaforo cruzamento 13, carro 8 com 3
      moveUp(-150,imagemCarro8,7);
      Platform.runLater(() -> imagemCarro8.setScaleX(-1));
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      semaforo[22].release(); // Semaforo cruzamento 20, carro 8 com 4
      moveRight(-40, imagemCarro8, 7);
      //CRUZAMENTO 14
      moveRight(0, imagemCarro8, 7);
      semaforo[33].release(); // Semaforo cruzamento 19, carro 8 com 6
      semaforo[31].release(); // Semaforo cruzamento 13, carro 8 com 3
      Platform.runLater(() -> imagemCarro8.setScaleX(1));
      Platform.runLater(() -> imagemCarro8.setRotate(90));
      moveUp(-205, imagemCarro8, 7);
      //CRUZAMENTO 8
      semaforo[23].acquire(); // Semaforo cruzamento 8, carro 8 com 4
      moveUp(-230,imagemCarro8,7);
      semaforo[29].release(); // Semaforo cruzamento 26, carro 8 com 7
      semaforo[23].release(); // Semaforo cruzamento 8, carro 8 com 4
      moveUp(-285, imagemCarro8, 7);
      //CRUZAMENTO 2
      semaforo[24].acquire(); // Semaforo cruzamento 2, carro 8 com 3
      moveUp(-315,imagemCarro8,7);
      Platform.runLater(() -> imagemCarro8.setScaleX(-1));
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveRight(75, imagemCarro8, 7);
      //CRUZAMENTO 3
      semaforo[36].acquire(); // Semaforo cruzamento 3, carro 8 com 5
      semaforo[30].acquire(); // Semaforo cruzamento 3, carro 8 com 7
      moveRight(100, imagemCarro8, 7);
      moveRight(175, imagemCarro8, 7);
      //CRUZAMENTO 4 
      moveRight(205, imagemCarro8, 7);
      semaforo[30].release(); // Semaforo cruzamento 3, carro 8 com 7
      moveRight(280, imagemCarro8, 7);
      //CRUZAMENTO 5
      moveRight(315, imagemCarro8, 7);
      semaforo[36].release(); // Semaforo cruzamento 3, carro 8 com 5
      Platform.runLater(() -> imagemCarro8.setRotate(90));
      semaforo[24].release(); // Semaforo cruzamento 2, carro 8 com 3
      moveDown(-250, imagemCarro8, 7);
      //CRUZAMENTO 11
      semaforo[27].acquire(); // Semaforo cruzamento 29, carro 8 com 7
      semaforo[25].acquire(); // Semaforo cruzamento 11, carro 8 com 4
      moveDown(-230,imagemCarro8,7);
      semaforo[25].release(); // Semaforo cruzamento 11, carro 8 com 4
      moveDown(-165, imagemCarro8, 7);
      //CRUZAMENTO 17
      semaforo[32].acquire(); // Semaforo cruzamento 17, carro 8 com 3
      moveDown(-145,imagemCarro8,7);
      Platform.runLater(() -> imagemCarro8.setScaleX(-1));
      Platform.runLater(() -> imagemCarro8.setRotate(0)); 
      moveRight(390, imagemCarro8, 7);
      //CRUZAMENTO 18
      semaforo[35].acquire(); // Semaforo cruzamento 18, carro 8 com 5
      semaforo[26].acquire(); // Semaforo cruzamento 18, carro 8 com 4
      moveRight(420, imagemCarro8, 7);
      semaforo[32].release(); // Semaforo cruzamento 17, carro 8 com 3
      Platform.runLater(() -> imagemCarro8.setRotate(90));
      moveDown(-90, imagemCarro8, 7);
      //CRUZAMENTO 24
      moveDown(-70,imagemCarro8,7);
      Platform.runLater(() -> imagemCarro8.setScaleX(1));
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(345, imagemCarro8, 7);
      //CRUZAMENTO 23
      moveLeft(315, imagemCarro8, 7);
      semaforo[35].release(); // Semaforo cruzamento 18, carro 8 com 5
      semaforo[26].release(); // Semaforo cruzamento 18, carro 8 com 4
      Platform.runLater(() -> imagemCarro8.setRotate(270));
      moveDown(-5, imagemCarro8, 7);
      //CRUZAMENTO  29
      moveDown(20,imagemCarro8,7);
      semaforo[27].release(); // Semaforo cruzamento 29, carro 8 com 7
      moveDown(80, imagemCarro8, 7);
      //CRUZAMENTO 35
      moveDown(100,imagemCarro8,7);
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(240, imagemCarro8, 7);
      //CRUZAMENTO 34
      semaforo[34].acquire(); // Semaforo cruzamento 34, carro 8 com 6
      semaforo[28].acquire(); // Semaforo cruzamento 29, carro 8 com 7
      moveLeft(210, imagemCarro8, 7);
      moveLeft(135, imagemCarro8, 7);
      //CRUZAMENTO 33
      moveLeft(100, imagemCarro8, 7);
      semaforo[28].release(); // Semaforo cruzamento 29, carro 8 com 7
      moveLeft(25, imagemCarro8, 7);
      //CRUZAMENTO 32
      moveLeft(0, imagemCarro8, 7);
      semaforo[34].release(); // Semaforo cruzamento 34, carro 8 com 6
      Platform.runLater(() -> imagemCarro8.setRotate(90));
      moveUp(50, imagemCarro8, 7);
      //CRUZAMENTO 26
      semaforo[29].acquire(); // Semaforo cruzamento 26, carro 8 com 7
      moveUp(20,imagemCarro8,7);
      this.posicaoInicialX = 0;
      this.posicaoInicialY = 0;
    }
  }
  //SELECAO DE PERCURSO DA INSTANCIA DA CLASSE
  public void movimentaVeiculo() throws InterruptedException{
    switch(this.percurso){
      case"01":
        percurso01SH();
        break;
      case"06":
        percurso06SH();
        break;
      case"07":
        percurso07SA();
        break;
      case"08":
        percurso08SA();
        break;
      case"16":
        percurso16SA();
        break;
      case"17":
        percurso17SA();
        break;
      case"20":
        percurso20SA();
        break;
      case"21":
        percurso21SH();
        break;
    }
  }
  public void run(){
    try {
      movimentaVeiculo();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
