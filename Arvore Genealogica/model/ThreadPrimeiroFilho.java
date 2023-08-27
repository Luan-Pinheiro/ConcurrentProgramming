//pacote da classe
package model;
//Importacao de bibliotecas
import controller.ControllerThread;
import javafx.application.Platform;

public class ThreadPrimeiroFilho extends Thread{
  ControllerThread cT;
  ThreadPrimeiroNeto primeiroNeto = new ThreadPrimeiroNeto();
  private int idade = 0;

  /*Metodo para conectar o Controle a esta Thread, evitando um estouro de memoria.
  Fator decorrente de uma 'recursao' sem caso base, originada em uma 
  chamada reciproca entre a Thread e o ControllerThread
  */
  public void linkaControlador(ControllerThread cT){
    this.cT = cT;
  }
  //Obtencao do valor de um atributo encapsulado
  public int getIdade() {
    return idade;
  }
  //metodo run da Thread
  @Override
  public void run(){
    //Ciclo do ente familiar
    for(idade = 0; idade <=61; idade++){
      /*Atualizando a idade em tempo real na tela pelo 'Platform.runlater' 
      sobre a funcao de exibicao da idade do ente familiar*/
      Platform.runLater(() -> cT.exibeIdadePrimeiroFilho());
      if(idade >=4 && idade<=15){
        cT.trocaFazeVidaPFilho("./view/crianca.png");//Alteracao da imagem do ente no ImageView
      }
      if(idade>=16 && idade <=55){
        cT.trocaFazeVidaPFilho("./view/homem.png");//Alteracao da imagem do ente no ImageView
        if(idade == 16){
          System.out.println("O primeiro neto nasceu!(Primeiro Neto)\n");
          cT.comecaPrimeiroNeto();//Incio da Thread do primeiro neto
        }
      }
      if(idade >= 56 && idade <= 89){
        cT.trocaFazeVidaPFilho("./view/idoso.png");//Alteracao da imagem do ente no ImageView
      }
      if(idade == 61){
        System.out.println("O primeiro filho morreu!" + " Aos " + idade + " anos\n");
        cT.trocaFazeVidaPFilho("./view/lapide.png");//Alteracao da imagem do ente no ImageView
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
