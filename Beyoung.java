package Project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Beyoung {
	
	WebDriver Driver;
	String Baseurl ="https://www.beyoung.in/";
	
	@BeforeTest
	public void setup()
	{
		Driver = new ChromeDriver();
		Driver.get(Baseurl);
		Driver.manage().window().maximize();
	}
	
	@Test
	public void test() throws IOException
	{
		//wait
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//title verification
		String Title = Driver.getTitle();
		System.out.println(Title);
		String ExpectedTitle ="Be Young";
		if(Title.equals(ExpectedTitle))
		{
			System.out.println("Title Matching");
		}
		else
		{
			System.out.println("Title Not matching");
		}	
		//Screenshot
		File screenshot = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File("./Screenshot/Beyoung.png"));
		//link count
		List<WebElement> li=Driver.findElements(By.tagName("a"));
		System.out.println("Total no of links = "+li.size());
		
		/*for(WebElement ele:li)
		{
			String Link = ele.getAttribute("href");
			String linktext = ele.getText();
			
			System.out.println("Link ="+Link + "-----"+linktext);
		}*/
		//Mouse Hoverable
		Actions Act = new Actions(Driver);
		WebElement Women = Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div/ul/li[2]/a"));
		Act.moveToElement(Women).perform();
		//Click kurti
		Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div/ul/li[2]/ul/li/article[1]/a[4]")).click();
		//Click track order
		Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/section/div/div[1]/a/span")).click();
		//select radio button
		WebElement radio = Driver.findElement(By.xpath("//*[@id=\"__next\"]/section/div/div/div/div[1]/div/label[2]/input"));
		radio.click();
		//Enter the values
		Driver.findElement(By.xpath("//*[@id=\"__next\"]/section/div/div/div/div[2]/div/input[1]")).sendKeys("UAH0456987");
		Driver.findElement(By.xpath("//*[@id=\"__next\"]/section/div/div/div/div[2]/div/input[2]")).sendKeys("abc@gmail.com");
		Driver.findElement(By.xpath("//*[@id=\"__next\"]/section/div/div/div/div[2]/div/button")).click();
		//text verification
		WebElement text = Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[5]/div"));
		if(text.getText().equals("Valid"))
		{
			System.out.println("Track Shipment Successfull");
		}
		else
		{
			System.out.println("Track Shipment is not Successfull");
		}
		
		
	}
	
	@AfterTest
	public void quit()
	{
		Driver.quit();
	}

}
