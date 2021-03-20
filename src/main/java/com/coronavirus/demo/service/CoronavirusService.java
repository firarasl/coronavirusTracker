package com.coronavirus.demo.service;


import com.coronavirus.demo.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
public class CoronavirusService {

    private static String COVID_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports_us/01-01-2021.csv";
    private List<LocationStats> allStatistics = new ArrayList<>();

    public List<LocationStats> getAllStatistics() {
        return allStatistics;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStatistics = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(COVID_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .parse(csvReader);
        for (CSVRecord record : records){
            LocationStats locationStats = new LocationStats();
            if (record.get("Province_State") !=null && !record.get("Province_State").isEmpty())
                locationStats.setState(record.get("Province_State"));
            if (record.get("Active") !=null && !record.get("Active").isEmpty())
                locationStats.setActive(Double.parseDouble(record.get("Active")));

            if (record.get("Deaths") !=null && !record.get("Deaths").isEmpty())
                locationStats.setDeath(Long.parseLong(record.get("Deaths")));
            if (record.get("Recovered") !=null && !record.get("Recovered").isEmpty())
                locationStats.setRecovered(Double.parseDouble(record.get("Recovered")));
            newStatistics.add(locationStats);

        }
        this.allStatistics = newStatistics;
    }

}
