package br.com.JMAfrico.tasks.functional;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	@Test
	public void testAmbiente() {
		//n�o foi necess�rio dizer o local do chrome drive, pois ele foi adicionado 
		//nas vari�veis de ambiente. L� em vari�veis de ambiente, setar uma pasta onde ele est�. Na execu��o ele � encontrado
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.google.com.br");
	}
}
