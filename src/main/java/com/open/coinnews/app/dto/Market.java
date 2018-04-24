package com.open.coinnews.app.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ld on 2018/2/6.
 * 实时交易数据
 */

public class Market {
   private int tid;

    private String Id;

    private String name;

    private String symbol;

    private String rank;

    private String price_usd;

    private String max_supply;


    private String price_btc;

    private String market_cap_usd;


    private String available_supply;

    private String total_supply ;

    private String  volumeUsd_24h;


    private String percentChange_1h ;


    private String percentChange_24h ;


    private String percentChange_7d ;


    private String price_cny;


    private String volumeCny_24h;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getPrice_cny() {
        return price_cny;
    }

    public void setPrice_cny(String price_cny) {
        this.price_cny = price_cny;
    }

    public String getVolumeCny_24h() {
        return volumeCny_24h;
    }

    public void setVolumeCny_24h(String volumeCny_24h) {
        this.volumeCny_24h = volumeCny_24h;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMarketCapCny() {
        return marketCapCny;
    }

    public void setMarketCapCny(String marketCapCny) {
        this.marketCapCny = marketCapCny;
    }

    private String  marketCapCny;


    private Date  create_date;

    private String  last_updated;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getPercentChange_1h() {
        return percentChange_1h;
    }

    public void setPercentChange_1h(String percentChange_1h) {
        this.percentChange_1h = percentChange_1h;
    }

    public String getPercentChange_24h() {
        return percentChange_24h;
    }

    public void setPercentChange_24h(String percentChange_24h) {
        this.percentChange_24h = percentChange_24h;
    }

    public String getPercentChange_7d() {
        return percentChange_7d;
    }

    public void setPercentChange_7d(String percentChange_7d) {
        this.percentChange_7d = percentChange_7d;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date =create_date;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getVolumeUsd_24h() {
        return volumeUsd_24h;
    }

    public void setVolumeUsd_24h(String volumeUsd_24h) {
        this.volumeUsd_24h = volumeUsd_24h;
    }
    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }


    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }


}

