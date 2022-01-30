package com.springbootproject.coronovirusTracker.service;



import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springbootproject.coronovirusTracker.models.LocationStats;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class cornoVirusDataServices {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";

    //LocationStats-model class
   private List<LocationStats> allStats = new ArrayList<>();

   //to return the values in model class
    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    //@PostConstruct-whenever the application starts this particular method gets executed
    @Scheduled(cron = "* * 1 * * *")
    // @Scheduled(cron = "* * 1 * * *")-to run the method on regular basis-cron (sec min hr day mon year)
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        		
        
        for (CSVRecord record : records) {
        	//To save the data in memory create data object
           /* String state = record.get("Province_State");
            System.out.println(state);*/
        	
        	LocationStats locationStat = new LocationStats();
        	locationStat.setState(record.get("Province_State"));
            locationStat.setCountry(record.get("Country_Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
          
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            //System.out.println(locationStat);
            newStats.add(locationStat);
            
            
            
        }
       
        this.allStats = newStats;
        //to render the stats data in html page-create controller
    }

}
