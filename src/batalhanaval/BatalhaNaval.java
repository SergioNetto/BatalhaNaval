/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batalhanaval;

import java.util.Random;
import java.util.Scanner;

/**
 * Jogo Batalha Naval - Desafio - POO - ADS noturno - FATEC Mogi Mirim
 * @author Sergio Netto de Araujo Junior
 * 
 * O jogador deve escolher a posição de 5 "navios" e depois tentar adivinhar as 
 * que máquina escolheu antes dela adivinhar as suas.
 * Os navios tem apenas uma posição de tamanho
 * 
 */

public class BatalhaNaval {
    //tamanho do mapa - deve ser mudado também na classe Jogador - não consgui criar
    // constante que valesse nas duas classes para fácil alteração do tamanho do mapa...
    final int TAM=5;
    
    Jogador humano = new Jogador();
    Jogador maquina = new Jogador();
    
    public static void main(String[] args) {
        
        
        System.out.println("***Batalha Naval***");
        BatalhaNaval bn = new BatalhaNaval();
        System.out.println("Digite a posição dos seus 5 navios");
        System.out.println("Obs: a posição deve ser entre 1 e "+bn.TAM);
        // jogador escolhe a posição de seus navios
        bn.preencheMapaHumano();
        
        //maquina escolhe randomicamente a posição dos seus navios
        bn.preencheMapaMaquina();
        System.out.println("Jogadores preparados!");    
        bn.linha();
        //inicio do jogo
        bn.jogo();
        
        
    }
    
    private void jogo(){
        Scanner sc = new Scanner(System.in);
        Random pos = new Random();
        int x,y;
        boolean acertou;
        while (true){
                 placar();
                 linha();
                 mostraMapas();
                 while(true){
                     // le o seu tiro para o mapa das máquinas
                    System.out.print("Digite a posição linha do tiro : "); 
                    x=Integer.parseInt(sc.nextLine());
                    System.out.print("Digite a posição coluna do tiro: ");
                    y=Integer.parseInt(sc.nextLine());
                    // verifica se já atirou neste local antes
                    if((maquina.mapa[x-1][y-1]==0)||(maquina.mapa[x-1][y-1]==1))break;
                    else System.out.println("Tiro repetido! tente de novo.");
                 }
                 linha();          
                 System.out.print("Humano: ");
                 // confere o tiro
                 acertou = maquina.confereTiro(maquina.mapa,x,y);             
                 if (acertou == true) humano.acertos++;
                 if (humano.acertos==5){
                     System.out.println("");
                     System.out.println("Parabéns, humano! Você ganhou!!");
                     System.out.println("");
                     break;
                 }
                 while(true){
                    x=pos.nextInt(TAM)+1;
                    y=pos.nextInt(TAM)+1;
                    // verifica se foi gereda uma posição já utilizada
                    if((humano.mapa[x-1][y-1]==0)||(humano.mapa[x-1][y-1]==1)) break;
                 }
                 System.out.print("Máquina: ");
                 //confere o tiro
                 acertou = humano.confereTiro(humano.mapa,x,y);
                 if (acertou == true) maquina.acertos++;
                 if (maquina.acertos==5){
                     System.out.println("");
                     System.out.println("Melhor sorte da próxima vez, humano... Você perdeu!!");
                     System.out.println("");
                     break;
                 }
                 linha();         
        }
        mostraMapas();
        // termino do jogo
        System.out.println("Resultado Final:");
        placar();
        linha();         
    }
    
    private void preencheMapaHumano(){
        Scanner sc = new Scanner(System.in);
        int cont = 1;
        int x,y;
        boolean gravou;
        while (cont<=5){
           System.out.print("Digite a posição linha do navio "+cont+" : "); 
           x=Integer.parseInt(sc.nextLine());
           System.out.print("Digite a posição coluna do navio "+cont+": ");
           y=Integer.parseInt(sc.nextLine());
           gravou=humano.preencheMapa(humano.mapa,x,y);
           if (gravou==false){
               System.out.println("Já existe um navio neste local. Tente de novo!");
               cont--;
           }
           cont++;
        }
    }

    private void preencheMapaMaquina() {
        Random pos = new Random();
        int cont = 1;
        int x,y;
        boolean gravou;
        while (cont<=5){
            x=pos.nextInt(TAM)+1;
            y=pos.nextInt(TAM)+1;
            gravou=maquina.preencheMapa(maquina.mapa,x,y);
            if (gravou==false) cont--;
            cont++;
        }
    }

    private void mostraMapas(){
        System.out.println("Mapa Humano");
        humano.mostraMapa(humano.mapa,1);
        System.out.println("");
        // o segundo parametro serve para diferenciar o mapa da máquina da do jogador
        // e não mostrar a posição dos navios na tela
        System.out.println("Mapa Máquina");
        maquina.mostraMapa(maquina.mapa,2);
    }
    
    void linha(){
        System.out.println("==================================");  
    }
    
    void placar(){
        System.out.println("Placar:");
        System.out.println("Humano: "+humano.acertos);
        System.out.println("Máquina: "+maquina.acertos);
    }
      
}
