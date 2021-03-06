package com.practice;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// Go to aa.com/homePage.do
// select depart date
// select return date
// select destination
// click on search
// quit the browser

public class handlingCalendar {
	public static String url = "https://www.aa.com/homePage.do";

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement depCal = driver.findElement(By.id("aa-leavingOn"));
		depCal.click();

		WebElement Month = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])"));
		String actualMonth = Month.getText();
		String expectedMonth = "December";
		while (!actualMonth.equals(expectedMonth)) {
			WebElement next = driver.findElement(By.xpath("//a[@title='Next']"));
			next.click();
			Month = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])"));
			actualMonth = Month.getText();
		}
		List<WebElement> dates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
		String expectedDate = "25";
		for (WebElement date : dates) {
			if (date.getText().equals(expectedDate)) {
				date.click();
				break;
			}
		}
		WebElement returnCal = driver.findElement(By.id("aa-returningFrom"));
		returnCal.click();
		expectedMonth = "March";
		while (!actualMonth.equals(expectedMonth)) {
			WebElement next = driver.findElement(By.xpath("//a[@title='Next']"));
			next.click();
			Month = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])"));
			actualMonth = Month.getText();
		}
		expectedDate = "12";
		dates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
		for (WebElement date : dates) {
			if (date.getText().equals(expectedDate)) {
				date.click();
				break;
			}
		}

		
		WebElement from = driver.findElement(By.id("reservationFlightSearchForm.originAirport"));
		from.clear();
		from.sendKeys("New York");
		WebElement destination = driver.findElement(By.id("reservationFlightSearchForm.destinationAirport"));
		destination.sendKeys("Bishkek");
		WebElement search = driver.findElement(By.id("flightSearchForm.button.reSubmit"));
		search.click();
		driver.quit();

	}
}
