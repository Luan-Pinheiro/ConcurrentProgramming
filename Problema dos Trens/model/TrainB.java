//Pacote da Classe
package model;
//Importacoes
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import controller.ControllerTrains;

public class TrainB extends Thread {
  //atributos
  private int posiEixoX;
  private int posiEixoY;
  private ControllerTrains cT;
  private int speed;

  //Metodo que, baseado no atributo capturado em tempo real do controle, atribui a velocidade ao trem B
  public int realTimeSpeedTrainB() {
    speed= cT.getSpeedB();
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
  //Define a referencia do controle
  public void linkController(ControllerTrains cT){
    this.cT = cT;
  }
  //funcao que move o trem para a direita na tela ate a posicao de parada, no eixo x
  public void moveToRight(int stopPoint){
    while(posiEixoX <= stopPoint){
      try {
        //fazendo movimentacao do trem na tela atraves do metodo Platform.runLater
        Platform.runLater(()->cT.getImgTremB().setX(posiEixoX));
        posiEixoX++;
        //tempo de espera varia para cada velocidade setada no slider de cada trem
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //funcao que move o trem para baixo na tela ate a posicao de parada, no eixo y
  public void moveDown(ImageView img,int stopPoint){
    while(posiEixoY<=stopPoint){
      try {
        //fazendo movimentacao do trem na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setY(posiEixoY));
        posiEixoY++;
        //tempo de espera varia para cada velocidade setada no slider de cada trem
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //funcao que move o trem para cima na tela ate a posicao de parada, no eixo y
  public void moveUp(ImageView img,int stopPoint){
    while(posiEixoY >= stopPoint){
      try {
        //fazendo movimentacao do trem na tela atraves do metodo Platform.runLater
        Platform.runLater(()->img.setY(posiEixoY));
        posiEixoY--;
        //tempo de espera varia para cada velocidade setada no slider de cada trem
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //funcao que move o trem para esquerda na tela ate a posicao de parada, no eixo x
  public void moveToLeft(int stopPoint){
    while(posiEixoX >= stopPoint){
      try {
        //fazendo movimentacao do trem na tela atraves do metodo Platform.runLater
        Platform.runLater(()->cT.getImgTremBrev().setX(posiEixoX));
        posiEixoX--;
        //tempo de espera varia para cada velocidade setada no slider de cada trem
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //Metodo que executa toda a sequencia do percuso do Trem B comecando na esquerda, para Variavel de Travamento
  public void leftToRight(ImageView img){
    //Fazendo o Trem B aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(cT.getImgTremB().getX());
    posiEixoY = (int)(cT.getImgTremB().getY());
    //armazenando posicoes iniciais da imagem em auxiliares, para faze-la retorna ao inicio no fim do ciclo
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    //inicio do ciclo
    while(true){
      //REGIAO NAO CRITICA 1
      moveToRight(95);
      Platform.runLater(()->img.setRotate(270));
      //Espera se a regiao esta ocupada e troca o valor da variavel quando este trem entra na regiao critica
      while(cT.getVT1()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem B esperando vez para Zona Critica [1]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT1(1);
      //REGIAO CRITICA 1
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(255);
      //libera a passagem
      cT.setVT1(0);
      //REGIAO NAO CRITICA 2
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(395);
      Platform.runLater(()->img.setRotate(270));
      //Espera se a regiao esta ocupada e troca o valor da variavel quando este trem entra na regiao critica
      while(cT.getVT2()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem B esperando vez para Zona Critica [2]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT2(1);
      //REGIAO CRITICA 2
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(555);
      //libera a passagem
      cT.setVT2(0);
      //REGIAO NAO CRITICA 3
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(680);
      //Redefinindo posicoes x e y para as inicias, fazendo o trem retornar a origem ao fim do trajeto
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }
  //Metodo que executa toda a sequencia do percuso do Trem B Bcomecando na direita, para Variavel de Travamento
  public void RightToLeft(ImageView img){
    //Fazendo o Trem B aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    //armazenando posicoes iniciais da imagem em auxiliares, para faze-la retorna ao inicio no fim do ciclo
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    //inicio do ciclo
    while(true){
      //REGIAO NAO CRITICA 3
      moveToLeft(-95);
      Platform.runLater(()->img.setRotate(90));
      //Espera se a regiao esta ocupada e troca o valor da variavel quando este trem entra na regiao critica
      while(cT.getVT2()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem A esperando vez para Zona Critica [1]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT2(1);
      //REGIAO CRITICA 2
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-255);
      //libera a passagem
      cT.setVT2(0);
      //REGIAO NAO CRITICA 2
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-395);
      Platform.runLater(()->img.setRotate(90));
      //Espera se a regiao esta ocupada e troca o valor da variavel quando este trem entra na regiao critica
      while(cT.getVT1()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem A esperando vez para Zona Critica [1]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT1(1);
      //REGIAO CRITICA 1
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-555);
      //libera a passagem
      cT.setVT1(0);
      //REGIAO NAO CRITICA 1
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-680);
      //Redefinindo posicoes x e y para as inicias, fazendo o trem retornar a origem ao fim do trajeto
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }
  //Metodo que executa toda a sequencia do percuso do Trem B comecando na esquerda, para Estrita Alternancia
  public void leftToRightEA(ImageView img){
    //Fazendo o Trem B aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(cT.getImgTremB().getX());
    posiEixoY = (int)(cT.getImgTremB().getY());
    //armazenando posicoes iniciais da imagem em auxiliares, para faze-la retorna ao inicio no fim do ciclo
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    //inicio do ciclo
    while(true){
      //REGIAO NAO CRITICA 1
      moveToRight(95);
      Platform.runLater(()->img.setRotate(270));
      //Espera se a regiao esta ocupada
      while(cT.getVEZ1()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      //REGIAO CRITICA 1
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(255);
      //libera passagem
      cT.setVEZ1(0);
      //REGIAO NAO CRITICA 2
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(395);
      Platform.runLater(()->img.setRotate(270));
      //Espera se a regiao esta ocupada
      while(cT.getVEZ2()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      //REGIAO CRITICA 2
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(555);
      //libera a passagem
      cT.setVEZ2(0);
      //REGIAO NAO CRITICA 3
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(680);
      //Redefinindo posicoes x e y para as inicias, fazendo o trem retornar a origem ao fim do trajeto
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }
  //Metodo que executa toda a sequencia do percuso do Trem B comecando na direita, para Estrita Alternancia
  public void RightToLeftEA(ImageView img){
    //Fazendo o Trem B aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    //armazenando posicoes iniciais da imagem em auxiliares, para faze-la retorna ao inicio no fim do ciclo
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    //inicio do ciclo
    while(true){
      //liberando preferencia da Regiao Critica 2 
      cT.setVEZ2(1);
      //REGIAO NAO CRITICA 3
      moveToLeft(-95);
      Platform.runLater(()->img.setRotate(90));
      //Espera se a regiao esta ocupada
      while(cT.getVEZ2()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      //REGIAO CRITICA 2
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-255);
      //libera a passagem
      cT.setVEZ2(0);
      //REGIAO NAO CRITICA 2
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-395);
      Platform.runLater(()->img.setRotate(90));
      //Espera se a regiao esta ocupada
      while(cT.getVEZ1()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      //REGIAO CRITICA 1
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-555);
      //libera a passagem
      cT.setVEZ1(0);
      //REGIAO NAO CRITICA 1
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-680);
      //Redefinindo posicoes x e y para as inicias, fazendo o trem retornar a origem ao fim do trajeto
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }
  //Metodo que seleciona qual o tipo de solucao foi selecionada (Na CheckBox)
  public void selectedMutualExclusionAndOrientation(){
    switch(cT.getTypeOfMutualExclusion()){
      case 1:
        System.out.println("Estrita Alternancia selecionada como solucao!");
        //Seleciona a orientacao dos trens 
        switch(cT.getPosiOrientacao()){
          case 0:
            leftToRightEA(cT.getImgTremB());
            break;
          case 1:
            RightToLeftEA(cT.getImgTremBrev());
            break;
          case 2:
            RightToLeftEA(cT.getImgTremBrev());
            break;            
          case 3:
            leftToRightEA(cT.getImgTremB());
            break;
          default:
            System.out.println("Erro!");
            break;
        }
       break;
      case 2:
        System.out.println("Variavel de Travamento selecionada como solucao!");
        //Seleciona a orientacao dos trens
        switch(cT.getPosiOrientacao()){
          case 0:
            leftToRight(cT.getImgTremB());
            break;
          case 1:
            RightToLeft(cT.getImgTremBrev());
            break;
          case 2:
            RightToLeft(cT.getImgTremBrev());
            break;            
          case 3:
            leftToRight(cT.getImgTremB());
            break;
          default:
            System.out.println("Erro!");
            break;
        }
       break;
      default:
        System.out.println("Erro! Tipo de Exclusao nao identidicado");
        break;
  }
 }

  public void run(){
    //Verificando orientacao e tipo de exclusao mutua.
    selectedMutualExclusionAndOrientation();
  }
}
