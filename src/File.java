import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Stream;

public class File {
	public static ArrayList<double[]> lerArquivo(String arq){
		ArrayList<double[]> maos = new ArrayList<double[]>();
		BufferedReader br=null;
		FileReader fr=null;

		try{
			fr = new FileReader(arq);
			br = new BufferedReader(fr);
			String linhaAtual;
			double count = 1;
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
		return maos;
	}

	 public static int [] escreverDados(Maos maos){
	        int countQnt4iguais = 0;
	        int count4Iguais = 0;
	        boolean has4Iguais = false;
	        int countQntSequencias = 0;
	        boolean hasSequencia = false;
	        int countNumerosDiferentes = 0;
	        int countQntNumeroDiferencas = 0;
	        boolean hasDiferenca = false;
	        int countQntNadas = 0;
	        boolean hasNada = false;
	                for(double[] mao : maos.getListaMaos()){
	                    has4Iguais = false;
	                    hasSequencia = false;
	                    hasDiferenca = false;
	                    hasNada = false;
	                    //Ordenando a mão antes de qualquer verificação
	                    Arrays.sort(mao);
	                    for(int i = 0; i<mao.length; i++){
	                        for(int j = i; j<mao.length; j++){
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
	                    /*double[] maoOrdenada = new double[5];
	                    for(int i = 0; i<mao.length; i++){
	                        maoOrdenada[i] = mao[i];
	                    }
	                    Arrays.sort(maoOrdenada);*/
	                    for(int i=0; i < mao.length; i++){
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
	                        for (int i=0; i < mao.length; i++){
	                            //countNumerosDiferentes = 0;
	                            for(int j = i+1; j < mao.length; j++){
	                                if(mao[i]!=mao[j]){
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
	        /*writer.write(Math.abs(Calendar.getInstance().get(Calendar.MILLISECOND) - tempoExec.get(Calendar.MILLISECOND)) + " | " +
	                        countQnt4iguais + " | " + countQntNumeroDiferencas + " | " + countQntSequencias + " | ");
	        writer.close();*/
	        int saidas [] = {countQnt4iguais, countQntNumeroDiferencas, countQntSequencias};
	        return saidas;

	    }


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
}