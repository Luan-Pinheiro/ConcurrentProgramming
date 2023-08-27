//pacote em que o arquivo se encontra
package data;
//importações
import model.User;
import java.io.*;
import java.util.*;

public class UserData implements Serializable{
  //Lista que irá armazenar os usuários
  private ArrayList<User> usuario = new ArrayList<>();

  public ArrayList<User> userInsert(User user) {
    //Instanciando objetos para realizar a serialização e escrição/leitura do arquivo
    FileOutputStream fluxo = null;
    ObjectOutputStream obs = null;
    try {
      //definindo nome do arquivo gerado e permitindo indexação ao fim do arquivo sem apaga-lo
      fluxo = new FileOutputStream("Users.ser", true);
      obs = new ObjectOutputStream(fluxo);
      //adicionando o usuário na lista de usuários 
      usuario.add(user);
      //escrevendo usuário no arquivo
      obs.writeObject(user);
      //mensagens em caso de erros localizados
    } catch (FileNotFoundException e) {
      System.out.println("Arquivo não localizado");
    } catch (IOException x) {
      System.out.println(x.getMessage());
    }catch (NullPointerException r) {
      System.out.println(r.getMessage());
    }finally{
      if(obs != null)
        try{
          //fechando o arquivo
          obs.close();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
    }
    return usuario;
 }
  public ArrayList<User> getUserList(){
    //criação de lista de usuarios para serem lidos 
    ArrayList<User> userRead = null;
    //Instanciando objetos para realizar a serialização e escrição/leitura do arquivo
    FileInputStream fluxo = null;
    ObjectInputStream obs = null;
    try {
      userRead = new ArrayList<>();
      //localizando arquivo para leitura
      fluxo = new FileInputStream("Users.ser");

      while (fluxo.available() > 0) {
        obs = new ObjectInputStream(fluxo);
        //casting e armazenando objeto lido no objeto do tipo User
        User user = (User) obs.readObject();
        //adicionando o objeto na lista
        userRead.add(user);
        //percorrendo a lista para remoção de espaços vazios e/ou repetidos
        for (int i = 0; i < userRead.size(); i++) {
          for(int j = i+1; j < userRead.size(); j++){
              if(userRead.get(i) == userRead.get(j) || userRead.get(j) == null)
                userRead.remove(userRead.get(j));
              }
          }
      }
    //mensagens para erros localizados
    }catch (ClassNotFoundException x) {
      System.out.println(x.getMessage());
    }catch (FileNotFoundException e) {
      System.out.println("Arquivo não localizado");
    }catch (IOException x) {
      System.out.println(x.getMessage());
    }catch (NullPointerException r) {
      System.out.println(r.getMessage());
    }finally{
      if(obs != null)
        try{
          //fechamento do arquivo
          obs.close();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
    }
    return userRead;
  }
  //função que verifica compatibilidade entre senha inserida e o do usuário existente no arquivo
  public boolean checkUserCredentials(User user){
    ArrayList<User> users  = getUserList();
    for(int index = 0; index < users.size(); index++){
      if(user.getUsername().equals(users.get(index).getUsername())){
        if(user.getSenha().equals(users.get(index).getSenha())){
          return true;
        }
      }         
    }
    return false;
 }
}