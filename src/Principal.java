
public class Principal {

	public static void main(String[] args) {
		Maos maos = new Maos (File.lerArquivo());
		File.escreverDados(maos);
	}

}
