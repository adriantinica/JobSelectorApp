package student.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        
         String[] recruitingWebsiteURLs = {
            "https://www.delucru.md/",
            "https://www.rabota.md/ro/"
            
        };
    
        String searchTerm = "Java Junior Jobs";
    
        for (String url : recruitingWebsiteURLs) {
            System.out.println("Scraping jobs from " + url);
            scrapeJobs(url, searchTerm);
        }


    }
        
    private static void scrapeJobs(String recruitingWebsiteURL, String searchTerm){

        try {
            Document document = Jsoup.connect(recruitingWebsiteURL).get();
            Elements jobListings = document.select("col-lg-4"); //  HTML element 

            for (Element jobListing : jobListings) {
                //System.out.println(jobListing);//  verification
                String jobTitle = jobListing.select(".job-body").text(); //  HTML element !!!!!!!!!!!!!!!!!!
                String jobDescription = jobListing.select(".job-item").text(); //  HTML element !!!!!!!!!!!!!!!!!!

                if (jobTitle.toLowerCase().contains(searchTerm) || jobDescription.toLowerCase().contains(searchTerm) ) {
                    System.out.println("Position: " + jobTitle);
                    System.out.println("Description: " + jobDescription);
                    System.out.println("------------------------------");
                }else {
                    System.out.println("No jobs were found !!!");
                }
                
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
