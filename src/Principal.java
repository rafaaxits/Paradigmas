import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Principal {
	
	public static double[] linhaToDouble(String[] linha) {
		 double [] resultado = new double[linha.length];
		 for(short i = 0; i<linha.length;i++){
			 switch (linha[i]){
			 	case "T":
			 		linha[i] = "10";
			 		resultado[i] = Double.parseDouble(linha[i]);
			 	break;
			 	case "J":
			 		linha[i] = "11";
			 		resultado[i] = Double.parseDouble(linha[i]);
			 	break;
			 	case "Q":
			 		linha[i] = "12";
			 		resultado[i] = Double.parseDouble(linha[i]);
			 	break;
			 	case "K":
			 		linha[i] = "13";
			 		resultado[i] = Double.parseDouble(linha[i]);
			 	break;
			 	case "A":
			 		linha[i] = "14";
			 		resultado[i] = Double.parseDouble(linha[i]);
			 	break;
			 	default:
			 		resultado[i] = Double.parseDouble(linha[i]);
			 }
		 }
		 return resultado;
	}

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		ArrayList<double[]> maos = new ArrayList<double[]>();
		BufferedReader br=null;
		FileReader fr=null;

		try{
			fr = new FileReader("src/Arquivos/poker2M.txt");
			br = new BufferedReader(fr);
			String linhaAtual;
			int count = 1;
			while(br.ready()){
				linhaAtual = br.readLine();
				if(!linhaAtual.isEmpty()){
					maos.add(linhaToDouble(linhaAtual.split(" ")));
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
		
		 	int countQnt4iguais = 0; // Conta a quantidade de mãos com 4 numeros iguais presentes no arquivo
	        int count4Iguais = 0; // Contador auxiliar para verificar se existe 4 numeros iguais na mão 
	        boolean has4Iguais = false; // Booleano auxiliar que é utilizado para confirmação de 4 numeros iguais numa mão
	        int countQntSequencias = 0; // Conta a quantidade de mãos com seguencias presentes no arquivo
	        boolean hasSequencia = false; // Booleano auxiliar que é utilizado para confirmação de uma sequencia numa mão
	        int countQntNumeroDiferencas = 0; // Conta a quanditade de mãos com numeros diferentes presentes no arquivo
	        boolean hasDiferenca = false; // Booleano auxixliar que é utilizado para confirmação de uma sequencia numa mão
	        int countQntNadas = 0; // Conta a quantidade de mãos com nada presentes no arquivo
	        boolean hasNada = false; // Booleano auxiliar que é utilizado para confirmação de um nada numa mão
	                for(double[] mao : maos){
	                    has4Iguais = false;
	                    hasSequencia = false;
	                    hasDiferenca = false;
	                    hasNada = false;
	                    //Ordenando a mão antes de qualquer verificação
	                    Arrays.sort(mao);
	                    for(byte i = 0; i<mao.length; i++){
	                        for(byte j = i; j<mao.length; j++){
	                            if(mao[i]==mao[j]){
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
	                    for(byte i=0; i < mao.length; i++){
	                        int j = i + 1;
	                        if(j == mao.length){
	                            continue;
	                        }
	                        if((mao[i])== (mao[j]-1)){
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
	                        for (byte i=0; i < mao.length; i++){
	                            for(byte j = (byte) (i+1); j < mao.length; j++){
	                                if(mao[i]!=mao[j]){
	                                    hasDiferenca = true;
	                                }else{
	                                    hasDiferenca = false;
	                                    break;
	                                }
	                            }
	                            if(!hasDiferenca){
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
		
		long fim = System.currentTimeMillis();
		try{
			FileWriter writer = new FileWriter("src/Arquivos/pokerTestSaida.txt");
			writer.write(Math.abs(inicio - fim) + " | " + 
                    saidas[0] + " | " + saidas[1] + " | " + saidas[2] + " | ");
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
