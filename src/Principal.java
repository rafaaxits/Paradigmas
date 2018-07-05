import java.io.FileWriter;
import java.util.Calendar;

public class Principal {

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		Maos maos = new Maos (File.lerArquivo());
		int saidas [] = File.escreverDados(maos);
		long fim = System.currentTimeMillis();
		try{
			FileWriter writer = new FileWriter("/Users/mac/Documents/Documentos 18.1/Paradigmas/ProjetoParadigmas2kv1/src/Files/pokerTestSaida.txt");
			writer.write(Math.abs(inicio - fim) + " | " + 
                    saidas[0] + " | " + saidas[1] + " | " + saidas[2] + " | ");
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
