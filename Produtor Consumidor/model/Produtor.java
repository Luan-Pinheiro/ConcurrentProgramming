//pacote da classe
package model;
//importacoes
import controller.ControllerPC;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Produtor extends Thread{
  //atributos 
  private int posX;
  private int speed;
  private int pontoParada = 196;
  ControllerPC cT;
  //construtor do produtor
  public Produtor (ControllerPC cT){
    this.cT = cT;
  }

  //Metodo que, baseado no atributo capturado em tempo real do controle, atribui a velocidade ao caminhao do produtor
  public int velocidadeProd() {
    speed = cT.getVelocidadeSliderProd();
    switch(speed) {
        case 0: 
            speed = 8;
            break;
        case 1: 
            speed = 5;
            break;
        case 2: 
            speed = 2;
            break;
        default:
            System.out.println("Não foi possivel atribuir");
            break;
    }
    return speed;
  }


  //funcoes que movem o caminhao horizontalmente na tela ate a posicao de parada, no eixo x
  // caminhao indo para esquerda
  public void moveEsq(int stopPoint1, ImageView img){
    while(posX >= stopPoint1){
      try {
        //tempo de espera varia para cada velocidade setada no slider de cada caminhao
        sleep(velocidadeProd());
        //fazendo movimentacao do caminhao na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setX(posX));
        posX--;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  // caminhao indo para direita
  public void moveDir(int stopPoint2,ImageView img){
    while(posX <= stopPoint2){
      try {
        //tempo de espera varia para cada velocidade setada no slider de cada caminhao
        sleep(velocidadeProd());
        //fazendo movimentacao do caminhao na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setX(posX));
        posX++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //metodo de producao
  public void produzir(){
    try {
      //tempo de producao
      sleep(velocidadeProd());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  //metodo de insercao na regiao critica
  public void insereItem(){//insere no deposito a caixa 1 depois a 2 depois a 3
    try {
      sleep(velocidadeProd());
      if(!cT.getImgBox1D().isVisible())
        cT.getImgBox1D().setVisible(true);
      else if(!cT.getImgBox2D().isVisible())
        cT.getImgBox2D().setVisible(true);
      else if(!cT.getImgBox3D().isVisible())
        cT.getImgBox3D().setVisible(true);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //funcoes de ida e volta do caminhao
  public void deliveryIda(int stopPoint){
    //deixa imagem visivel
    Platform.runLater(()->cT.getImgCam1().setVisible(true));
    //dirige para direita
    moveDir(stopPoint,cT.getImgCam1());
  }
  public void deliveryVolta(int stopPoint) {
    //reverte o caminhao
    Platform.runLater(()->cT.getImgCam1().setScaleX(-1));
    //dirige para esquerda
    moveEsq(stopPoint,cT.getImgCam1());
    //reverte novamente
    Platform.runLater(()->cT.getImgCam1().setScaleX(1));
    //deixa imagem invisivel
    Platform.runLater(()->cT.getImgCam1().setVisible(false));
  }

  public void run(){
    //pegando a posicao inicial da imagem
    int auxX = (int)cT.getImgCam1().getX();
    auxX = posX;
  
    while(true){
      try {
        //producao
        produzir();
        //caminhao vai entregrar
        deliveryIda(pontoParada);
        //////////////////////////////////////
        //REGIAO CRITICA
        Semaforo.vazio.acquire();
        Semaforo.mutex.acquire();
        insereItem();
        Semaforo.mutex.release();
        Semaforo.cheio.release();
        //////////////////////////////////////
        //caminhao volta para fabrica
        deliveryVolta(0);
        //colocando a posicao como inicial
        posX = auxX;
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}
