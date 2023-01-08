package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {

        this.dao = new MemoryAuctionDao();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> list() {
        return dao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        return dao.get(id);
    }

    /*   @RequestMapping(value = "/auctions", method = RequestMethod.POST)
       public Auction create(@RequestBody Auction auction) {
           if (auction != null)
               dao.create(auction);
           return auction;

       }*/
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Auction add(@RequestBody Auction auction) {
        return dao.create(auction);
    }

    }


   /* @RequestMapping(path = "/auctions?title_like", method = RequestMethod.GET)
    public List<Auction> auction(@RequestParam(value = "title_like", defaultValue = "") String title) {
        return dao.list();
    }

    @RequestMapping(value = "/auctions", method = RequestMethod.POST)
    public Auction add(@RequestBody Auction auction) {
        if (auction != null) {
            auction(auction.getTitle()).add(auction);
            return auction;
        }
        return null;

    } */




