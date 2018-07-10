package Implementacao;
import java.util.Arrays;

public class File {

	 public static void escreverDados(Mao mao){
	        
	        int count4Iguais = 0; // Contador auxiliar para verificar se existe 4 numeros iguais na mão 
	        Boolean has4Iguais = false; // Booleano auxiliar que é utilizado para confirmação de 4 numeros iguais numa mão
	        
	        Boolean hasSequencia = false; // Booleano auxiliar que é utilizado para confirmação de uma sequencia numa mão
	        int countNumerosDiferentes = 0; // Contador auxiliar para verificar os numeros diferentes numa mão 
	        
	        Boolean hasDiferenca = false; // Booleano auxixliar que é utilizado para confirmação de uma sequencia numa mão
	        
	        Boolean hasNada = false; // Booleano auxiliar que é utilizado para confirmação de um nada numa mão
	        Double[] maoAux = mao.getMao(); 
	                    has4Iguais = false;
	                    hasSequencia = false;
	                    hasDiferenca = false;
	                    hasNada = false;
	                    for(int i = 0; i<maoAux.length; i++){
	                        for(int j = i; j<maoAux.length; j++){
	                            if(maoAux[i].equals(maoAux[j])){
	                                count4Iguais++;
	                            }
	                        }
	                        if(count4Iguais == 4){
	                            Principal.countQnt4iguais++;
	                            count4Iguais = 0;
	                            has4Iguais = true;
	                            break;
	                        } else if(count4Iguais > 1 || i >= 3){
	                            count4Iguais = 0;
	                            has4Iguais = false;
	                            break;
	                        }
	                        else{
	                            count4Iguais = 0;
	                            has4Iguais = false;
	                        }
	                    }
	                    if(has4Iguais){
	                        has4Iguais = false;
	                    }else {
	                    Double [] maoOrdenada = new Double [5];
	                    for(int i = 0; i<maoAux.length; i++){
	                        maoOrdenada[i] = maoAux[i];
	                    }
	                    Arrays.sort(maoOrdenada);
	                    for(int i=0; i < maoOrdenada.length; i++){
	                        int j = i + 1;
	                        if(j == maoOrdenada.length){
	                            continue;
	                        }
	                        if(maoOrdenada[i].equals(maoOrdenada[j] - 1)){
	                            hasSequencia = true;
	                        } else{
	                            hasSequencia = false;
	                            break;
	                        }
	                    }
	                    if(hasSequencia == true){
	                        Principal.countQntSequencias++;
	                        hasSequencia = false;
	                    } else {
	                        for (int i=0; i < maoAux.length; i++){
	                            //countNumerosDiferentes = 0;
	                            for(int j = i+1; j < maoAux.length; j++){
	                                if(!maoAux[i].equals(maoAux[j])){
	                                    //countNumerosDiferentes++;
	                                    hasDiferenca = true;
	                                }else{
	                                    hasDiferenca = false;
	                                    break;
	                                }
	                            }
	                            if(!hasDiferenca){
	                                countNumerosDiferentes = 0;
	                                break;
	                            }
	                        }
	                        if(hasDiferenca == true){
	                            Principal.countQntNumeroDiferencas ++;
	                            hasDiferenca = false;
	                        }else{
	                        	Principal.countQntNadas++;
	                            hasNada = true;
	                        }
	                    }
	                } 
	            
	    }
	 
	public static Double[] linhaToDouble(String[] linha) {
		 Double [] resultado = new Double[linha.length];
		 for(short i = 0; i<linha.length;i++){
			 switch (linha[i]){
			 	case "T":
			 		linha[i] = "10";
			 		resultado[i] = new Double(linha[i]);
			 	break;
			 	case "J":
			 		linha[i] = "11";
			 		resultado[i] = new Double(linha[i]);
			 	break;
			 	case "Q":
			 		linha[i] = "12";
			 		resultado[i] = new Double(linha[i]);
			 	break;
			 	case "K":
			 		linha[i] = "13";
			 		resultado[i] = new Double(linha[i]);
			 	break;
			 	case "A":
			 		linha[i] = "14";
			 		resultado[i] = new Double(linha[i]);
			 	break;
			 	default: 
			 		resultado[i] = new Double(linha[i]);
			 }
		 }
		 return resultado;
	}
}