
package batalhanaval;

/**
 *
 * 
 */
public class Jogador {
        // Tamanho do mapa - não consegui criar uma contante que valesse nas duas classes...
        final int TAM=5;
        int mapa[][] = new int[TAM][TAM];
        int acertos = 0;
        
        
        void inicializaMapa(int[][]mapa){
            for(int i = 0; i<TAM; i++){
                for(int j = 0; j<TAM;j++){
                    mapa[i][j]=0;
                }
            }
        }
      
        Boolean preencheMapa(int [][]mapa,int x, int y){
            if(mapa[x-1][y-1]!=0){
                return false;
            } else{
                mapa[x-1][y-1]=1;
                return true;
            }
        }
        
        void mostraMapa(int [][]mapa,int p){
        System.out.println("   1 2 3 4 5");
        
        for(int linha=0 ; linha < TAM ; linha++ ){
            System.out.print(linha+1);
            if (linha<9) System.out.print(" ");
            for(int coluna=0 ; coluna < TAM ; coluna++ ){
                if(mapa[linha][coluna]==0){
                    System.out.print(" ~");
                }else if((mapa[linha][coluna]==1)&&(p==1)){
                    System.out.print(" N");
                }else if((mapa[linha][coluna]==1)&&(p==2)){
                    System.out.print(" ~");    
                }else if(mapa[linha][coluna]==2){
                    System.out.print(" X");
                } else if(mapa[linha][coluna]==3){
                    System.out.print(" *");
                }   
            }
            System.out.println();
        }

    }

    boolean confereTiro(int[][] mapa, int x, int y) {
        if(mapa[x-1][y-1]==0){
                System.out.println("Agua...");
                mapa[x-1][y-1]=3;
                return false;
            } else {
                  mapa[x-1][y-1]=2;
                  System.out.println("Acertou!");
                  return true;
            } 
    }
        
        
    
}
