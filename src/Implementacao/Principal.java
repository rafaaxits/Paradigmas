package Implementacao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Principal {
	public static int countQnt4iguais = 0; // Conta a quantidade de mãos com 4 numeros iguais presentes no arquivo
	public static int countQntSequencias = 0; // Conta a quantidade de mãos com seguencias presentes no arquivo
	public static int countQntNumeroDiferencas = 0; // Conta a quanditade de mãos com numeros diferentes presentes no arquivo
	public static  int countQntNadas = 0; // Conta a quantidade de mãos com nada presentes no arquivo
	
	public static double[] linhaToDouble(String[] linha) {
		 double [] resultado = new double[linha.length];
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
	
	public static void main(String[] args) throws FileNotFoundException {
	
		long inicio = System.currentTimeMillis();
		double[] mao = null;
		BufferedReader br=null;
		InputStream input=null;
		OutputStream output= new FileOutputStream("src/Arquivos/saida.txt");;
		try{
			input = new BufferedInputStream(new FileInputStream("src/Arquivos/Poker2K.txt"));
			br = new BufferedReader(new InputStreamReader(input));
			String linhaAtual;
			while(br.ready()){
				linhaAtual = br.readLine();
				if(!linhaAtual.isEmpty()){
					
					mao = linhaToDouble(linhaAtual.split(" "));
					int count4Iguais = 0; // Contador auxiliar para verificar se existe 4 numeros iguais na mão 
			        Boolean has4Iguais = false; // Booleano auxiliar que é utilizado para confirmação de 4 numeros iguais numa mão
			        
			        Boolean hasSequencia = false; // Booleano auxiliar que é utilizado para confirmação de uma sequencia numa mão
			        int countNumerosDiferentes = 0; // Contador auxiliar para verificar os numeros diferentes numa mão 
			        
			        Boolean hasDiferenca = false; // Booleano auxixliar que é utilizado para confirmação de uma sequencia numa mão
			        
			        Boolean hasNada = false; // Booleano auxiliar que é utilizado para confirmação de um nada numa mão
			                    has4Iguais = false;
			                    hasSequencia = false;
			                    hasDiferenca = false;
			                    hasNada = false;
			                    for(byte i = 0; i<mao.length; i++){
			                        for(byte j = i; j<mao.length; j++){
			                            if(mao[i] == (mao[j])){
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
			                    double [] maoOrdenada = new double [5];
			                    for(byte i = 0; i<mao.length; i++){
			                        maoOrdenada[i] = mao[i];
			                    }
			                    Arrays.sort(maoOrdenada);
			                    for(byte i=0; i < maoOrdenada.length; i++){
			                        int j = i + 1;
			                        if(j == maoOrdenada.length){
			                            continue;
			                        }
			                        if(maoOrdenada[i]==(maoOrdenada[j] - 1)){
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
			                        for (byte i=0; i < mao.length; i++){
			                            //countNumerosDiferentes = 0;
			                            for(byte j = (byte) (i+1); j < mao.length; j++){
			                                if(mao[i] != (mao[j])){
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
					String saida = countQnt4iguais + " | " + countQntNumeroDiferencas + " | " + countQntSequencias;
					output.write(saida.getBytes(Charset.forName("UTF-8")));
					/*mao.add(Stream.concat(Arrays.stream(index),
						Arrays.stream(linhaToDouble(linhaAtual.split(" ")))
						).toArray(Double[]::new));*/
					/*count = count + 1;*/
					
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				input.close();
				br.close();
				output.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		long fim = System.currentTimeMillis();
		try{
			FileWriter writer = new FileWriter("src/Arquivos/saida.txt");
			writer.write(Math.abs(inicio - fim) + " | " + 
					countQnt4iguais + " | " + countQntNumeroDiferencas + " | " + countQntSequencias);
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}