import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Stream;

public class File {
	public static ArrayList<Double[]> lerArquivo(){
		ArrayList<Double[]> maos = new ArrayList<Double[]>();
		BufferedReader br=null;
		FileReader fr=null;
		
		try{
			fr = new FileReader("src/Arquivos/poker2K.txt");
			br = new BufferedReader(fr);
			String linhaAtual;
			Double count = new Double((double) 1);
			while(br.ready()){
				linhaAtual = br.readLine();
				if(!linhaAtual.isEmpty()){
					Double[] index = new Double[1];
					index[0] = count;
					maos.add(Stream.concat(Arrays.stream(index),
						Arrays.stream(linhaToDouble(linhaAtual.split(" ")))
						).toArray(Double[]::new));
					count = count + 1;
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				fr.close();
				br.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return maos;
	}

	 public static int [] escreverDados(Maos maos){
	        int countQnt4iguais = 0; // Conta a quantidade de mãos com 4 numeros iguais presentes no arquivo
	        int count4Iguais = 0; // Contador auxiliar para verificar se existe 4 numeros iguais na mão 
	        Boolean has4Iguais = false; // Booleano auxiliar que é utilizado para confirmação de 4 numeros iguais numa mão
	        int countQntSequencias = 0; // Conta a quantidade de mãos com seguencias presentes no arquivo
	        Boolean hasSequencia = false; // Booleano auxiliar que é utilizado para confirmação de uma sequencia numa mão
	        int countNumerosDiferentes = 0; // Contador auxiliar para verificar os numeros diferentes numa mão 
	        int countQntNumeroDiferencas = 0; // Conta a quanditade de mãos com numeros diferentes presentes no arquivo
	        Boolean hasDiferenca = false; // Booleano auxixliar que é utilizado para confirmação de uma sequencia numa mão
	        int countQntNadas = 0; // Conta a quantidade de mãos com nada presentes no arquivo
	        Boolean hasNada = false; // Booleano auxiliar que é utilizado para confirmação de um nada numa mão
	                for(Double[] mao : maos.getListaMaos()){
	                    has4Iguais = false;
	                    hasSequencia = false;
	                    hasDiferenca = false;
	                    hasNada = false;
	                    for(int i = 1; i<mao.length; i++){
	                        for(int j = i; j<mao.length; j++){
	                            if(mao[i].equals(mao[j])){
	                                count4Iguais++;
	                            }
	                        }
	                        if(count4Iguais == 4){
	                            countQnt4iguais++;
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
	                        continue;
	                    }else {
	                    Double [] maoOrdenada = new Double [5];
	                    for(int i = 0; i<mao.length-1; i++){
	                        maoOrdenada[i] = mao[i+1];
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
	                        countQntSequencias++;
	                        hasSequencia = false;
	                        continue;
	                    } else {
	                        for (int i=1; i < mao.length; i++){
	                            //countNumerosDiferentes = 0;
	                            for(int j = i+1; j < mao.length; j++){
	                                if(!mao[i].equals(mao[j])){
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
	                            countQntNumeroDiferencas ++;
	                            hasDiferenca = false;
	                            continue;
	                        }else{
	                            countQntNadas++;
	                            hasNada = true;
	                            continue;
	                        }
	                    }
	                }
	            }
	        int saidas [] = {countQnt4iguais, countQntNumeroDiferencas, countQntSequencias};
	        return saidas; 
	            
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