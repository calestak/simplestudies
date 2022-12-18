package com.techelevator.auctions.services;

import org.springframework.web.client.RestTemplate;

import com.techelevator.auctions.model.Auction;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions/";
    public static String API_TITLE_URL = "http://localhost:3000/auctions?title_like=";
    public static String API_PRICE_URL = "http://localhost:3000/auctions?currentBid_lte=";

    private RestTemplate restTemplate = new RestTemplate();


    public Auction[] getAllAuctions() {
        // call api here
        return restTemplate.getForObject(API_BASE_URL, Auction[].class);
    }

    public Auction getAuction(int id) {
        // call api here
        return restTemplate.getForObject(API_BASE_URL + id, Auction.class);
    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        // call api here
        return restTemplate.getForObject(API_TITLE_URL + title, Auction[].class);
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        // call api here
        return restTemplate.getForObject(API_PRICE_URL + price, Auction[].class);
    }

}
