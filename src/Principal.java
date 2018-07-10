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

public class Principal {
	public static int countQnt4iguais = 0; // Conta a quantidade de mãos com 4 numeros iguais presentes no arquivo
	public static int countQntSequencias = 0; // Conta a quantidade de mãos com seguencias presentes no arquivo
	public static int countQntNumeroDiferencas = 0; // Conta a quanditade de mãos com numeros diferentes presentes no arquivo
	public static  int countQntNadas = 0; // Conta a quantidade de mãos com nada presentes no arquivo
	
	public static void main(String[] args) throws FileNotFoundException {
	
		long inicio = System.currentTimeMillis();
		Mao mao = null;
		BufferedReader br=null;
		InputStream input=null;
		OutputStream output= new FileOutputStream("src/Arquivos/saida.txt");;
		try{
			input = new BufferedInputStream(new FileInputStream("src/Arquivos/Poker2M.txt"));
			br = new BufferedReader(new InputStreamReader(input));
			String linhaAtual;
			while(br.ready()){
				linhaAtual = br.readLine();
				if(!linhaAtual.isEmpty()){
					
					mao = new Mao(File.linhaToDouble(linhaAtual.split(" ")));
					File.escreverDados(mao); 
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
