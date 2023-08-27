//pacote da classe
package model;
//Importacao de bibliotecas
import controller.ControllerThread;
import javafx.application.Platform;

public class ThreadPrimeiroNeto extends Thread{
  ControllerThread cT;
  ThreadBisneto primeiroBisneto = new ThreadBisneto();
  private int idade = 0;

  /*Metodo para conectar o Controle a esta Thread, evitando um estouro de memoria.
  Fator decorrente de uma 'recursao' sem caso base, originada em uma 
  chamada reciproca entre a Thread e o ControllerThread
  */
  public void linkaControlador(ControllerThread cT){
    this.cT = cT;
  }
  //Obtencao do valor de um atributo encapsulado
  public int getIdade(){
    return idade;
  }
  //metodo run da Thread
  @Override
  public void run(){
    //Ciclo do ente familiar
    for(idade = 0; idade<=35; idade++){
      /*Atualizando a idade em tempo real na tela pelo 'Platform.runlater' 
      sobre a funcao de exibicao da idade do ente familiar*/
      Platform.runLater(() -> cT.exibeIdadePrimeiroNeto());
      if(idade >=4 && idade<=15){
        cT.trocaFazeVidaPNeto("./view/crianca.png");//Alteracao da imagem do ente no ImageView
      }
      if(idade>=16 && idade <=55){
        cT.trocaFazeVidaPNeto("./view/homem.png");//Alteracao da imagem do ente no ImageView
        if(idade == 30){
          System.out.println("O primeiro bisneto nasceu (Primeiro filho)!\n");
          cT.comecaPrimeiroBisneto();//Incio da Thread do primeiro bisneto
        }
        if(idade == 35){
          cT.trocaFazeVidaPNeto("./view/lapide.png");//Alteracao da imagem do ente no ImageView
          System.out.println("O primeiro neto morreu!" + " Aos " + idade + " anos\n");
        } 
      } 
      try {
        //estabelecendo tempo de 1000 milisegundos ou 1 segundo por iteacao no for
        sleep(1000);
      } catch (InterruptedException e) {  
        e.printStackTrace();//print padrao em caso de erro
      }

    }
  }
}
