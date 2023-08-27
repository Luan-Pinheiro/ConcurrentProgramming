//pacote em que o arquivo se encontra
package model;
//importações
import java.io.Serializable;

public class User implements Serializable{
  //atributos da classe
  private String username;
  private String senha;
  private String task;
  private String day;

  //Contrutores
  public User (String username, String senha){
    this.username = username;
    this.senha = senha;
  }
  public User(String username, String task, String day){
    this.task = task;
    this.day = day;
  }

  //Getters 
  public String getDay() {
    return day;
  }
  public String getTask() {
    return task;
  }
  public String getUsername() {
    return username;
  }
  public String getSenha() {
    return senha;
  }
}
