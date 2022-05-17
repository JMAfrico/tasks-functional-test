package br.com.JMAfrico.tasks.functional;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	@Test
	public void testAmbiente() {
		//não foi necessário dizer o local do chrome drive, pois ele foi adicionado 
		//nas variáveis de ambiente. Lá em variáveis de ambiente, setar uma pasta onde ele está. Na execução ele é encontrado
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.google.com.br");
	}
}
