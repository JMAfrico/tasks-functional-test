package br.com.JMAfrico.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksTest {

	WebDriverWait wait;
	
//	public WebDriver acessarAplicacao() {
//		WebDriver driver = new ChromeDriver();
//		driver.navigate().to("http://localhost:8001/tasks");
//		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		return driver;
//	}
	
	public WebDriver acessarAplicacaoComSeleniumGridDockerizado() throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"),capabilities);
		driver.navigate().to("http://192.168.1.5:8001/tasks");//ip minha maquina(ipconfig-Adaptador ethernet WSL ou wifi)
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
//	@Test
//	public void testAmbiente() {
//		//não foi necessário dizer o local do chrome drive, pois ele foi adicionado 
//		//nas variáveis de ambiente. Lá em variáveis de ambiente, setar uma pasta onde ele está. Na execução ele é encontrado
//		WebDriver driver = new ChromeDriver();
//		driver.navigate().to("http://localhost:8001/tasks/");
//		driver.quit();
//	}
	
	@Test
	public void deveIncluirNovaTarefa() throws MalformedURLException {
		WebDriver driver = acessarAplicacaoComSeleniumGridDockerizado();
		
		driver.findElement(By.xpath("//a[@id='addTodo']")).click();
		driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste atualizado 2");
		driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("05/06/2022");
		driver.findElement(By.xpath("//input[@id='saveButton']")).click();
		//boolean existe = driver.findElement(By.xpath("//p[@text,'Success']")).isDisplayed();
		String text = driver.findElement(By.xpath("//p[@id='message']")).getText();
		//Assert.assertTrue(existe);
		Assert.assertEquals("Success!", text);
		driver.quit();
	}
	
	@Test
	public void naoDeveIncluirNovaTarefaDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacaoComSeleniumGridDockerizado();
		
		driver.findElement(By.xpath("//a[@id='addTodo']")).click();
		driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste data invalida");
		driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("04/04/2022");
		driver.findElement(By.xpath("//input[@id='saveButton']")).click();
		String text = driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals("Due date must not be in past", text);
		driver.quit();
	}
	
	@Test
	public void naoDeveIncluirNovaTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacaoComSeleniumGridDockerizado();
		
		driver.findElement(By.xpath("//a[@id='addTodo']")).click();
		driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("20/08/2022");
		driver.findElement(By.xpath("//input[@id='saveButton']")).click();
		String text = driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals("Fill the task description", text);
		driver.quit();
	}
	
	@Test
	public void naoDeveIncluirNovaTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacaoComSeleniumGridDockerizado();
		
		driver.findElement(By.xpath("//a[@id='addTodo']")).click();
		driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste sem data");
		driver.findElement(By.xpath("//input[@id='saveButton']")).click();
		String text = driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals("Fill the due date", text);
		driver.quit();
	}
}
