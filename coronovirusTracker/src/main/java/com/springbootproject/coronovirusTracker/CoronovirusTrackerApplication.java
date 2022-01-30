package com.springbootproject.coronovirusTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
//@EnableScheduling-tells the spring to run @Scheduled(cron = "* * 1 * * *") present in service folder

public class CoronovirusTrackerApplication {
	

	//goal:when spring application lpoads.its needs make get request to fetch the csv data from the given url
	//https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv
	//renders the data in good html format
	public static void main(String[] args) {
		SpringApplication.run(CoronovirusTrackerApplication.class, args);
	}

}
