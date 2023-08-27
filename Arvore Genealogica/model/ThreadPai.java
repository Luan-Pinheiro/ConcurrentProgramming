//pacote da classe
package model;
//Importacao de bibliotecas
import controller.ControllerThread;
import javafx.application.Platform;

public class ThreadPai extends Thread{
  ControllerThread cT;
  ThreadPrimeiroFilho primeiroFilho = new ThreadPrimeiroFilho();
  ThreadSegundoFilho segundoFilho = new ThreadSegundoFilho();
  ThreadTerceiroFilho terceiroFilho = new ThreadTerceiroFilho();
  private int idade = 0;

  /*Metodo para conectar o Controle a esta Thread, evitando um estouro de memoria.
  Fator decorrente de uma 'recursao' sem caso base, originada em uma 
  chamada reciproca entre a Thread e o ControllerThread
  */
  public void linkaControle(ControllerThread cT){
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
    for(idade = 0; idade <=90; idade++){
      /*Atualizando a idade em tempo real na tela pelo 'Platform.runlater' 
      sobre a funcao de exibicao da idade do ente familiar*/
      Platform.runLater(()-> cT.exibeIdadePai());
      if(idade < 5){
        if(idade == 0){
          cT.comecaPai();//Estabelecendo imagem e idade do pai na janela
          System.out.println("O pai nasceu!\n");
        }
      }
      if(idade >=4 && idade<=15){
        cT.trocaFazeVidaPai("./view/crianca.png");//Alteracao da imagem do ente no ImageView
      }
      if(idade>=16 && idade <=55){
        cT.trocaFazeVidaPai("./view/homem.png");//Alteracao da imagem do ente no ImageView
        if(idade == 22){
          System.out.println("O primeiro filho nasceu!\n");
          cT.comecaPrimeiroFilho();//Inicio da Thread do primeiro filho
        }
        if(idade == 25){
          System.out.println("O segundo filho nasceu!\n");
          cT.comecaSegundoFilho();//Inicio da Thread do segundo filho
        } 
        if(idade == 32){
          System.out.println("O terceiro filho nasceu!\n");
          cT.comecaTerceiroFilho();//Inicio da Thread do terceiro filho
        } 
      }
      if(idade >= 56 && idade <= 89){
        cT.trocaFazeVidaPai("./view/idoso.png");//Alteracao da imagem do ente no ImageView
      }
      if(idade == 90){
        cT.trocaFazeVidaPai("./view/lapide.png");//Alteracao da imagem do ente no ImageView
        System.out.println("O pai morreu!" + " Aos " + idade + " anos\n");
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
