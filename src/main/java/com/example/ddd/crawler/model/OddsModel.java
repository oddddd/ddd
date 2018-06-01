package com.example.ddd.crawler.model;

import com.example.ddd.ddd.Model;

import java.io.Serializable;

/**
 * OddsModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:27 2018/6/1
 */
public class OddsModel extends Model implements Serializable {
    private String oddsName;
    private String firstOddsWin;
    private String firstOddsDeuce;
    private String firstOddsLose;
    private String nowOddsWin;
    private String nowOddsDeuce;
    private String nowOddsLose;
    private String firstRateWin;
    private String firstRateDeuce;
    private String firstRateLose;
    private String nowRateWin;
    private String nowRateDeuce;
    private String nowRateLose;
    private String firstReturnRate;
    private String nowReturnRate;
    private String firstKellyWin;
    private String firstKellyDeuce;
    private String firstKellyLose;
    private String nowKellyWin;
    private String nowKellyDeuce;
    private String nowKellyLose;

    @Override
    public String toString() {
        return "OddsModel{" +
                "oddsName='" + oddsName + '\'' +
                ", firstOddsWin='" + firstOddsWin + '\'' +
                ", firstOddsDeuce='" + firstOddsDeuce + '\'' +
                ", firstOddsLose='" + firstOddsLose + '\'' +
                ", nowOddsWin='" + nowOddsWin + '\'' +
                ", nowOddsDeuce='" + nowOddsDeuce + '\'' +
                ", nowOddsLose='" + nowOddsLose + '\'' +
                ", firstRateWin='" + firstRateWin + '\'' +
                ", firstRateDeuce='" + firstRateDeuce + '\'' +
                ", firstRateLose='" + firstRateLose + '\'' +
                ", nowRateWin='" + nowRateWin + '\'' +
                ", nowRateDeuce='" + nowRateDeuce + '\'' +
                ", nowRateLose='" + nowRateLose + '\'' +
                ", firstReturnRate='" + firstReturnRate + '\'' +
                ", nowReturnRate='" + nowReturnRate + '\'' +
                ", firstKellyWin='" + firstKellyWin + '\'' +
                ", firstKellyDeuce='" + firstKellyDeuce + '\'' +
                ", firstKellyLose='" + firstKellyLose + '\'' +
                ", nowKellyWin='" + nowKellyWin + '\'' +
                ", nowKellyDeuce='" + nowKellyDeuce + '\'' +
                ", nowKellyLose='" + nowKellyLose + '\'' +
                '}';
    }

    /**
     * Getter for property 'oddsName'.
     *
     * @return Value for property 'oddsName'.
     */
    public String getOddsName() {
        return oddsName;
    }

    /**
     * Setter for property 'oddsName'.
     *
     * @param oddsName Value to set for property 'oddsName'.
     */
    public void setOddsName(String oddsName) {
        this.oddsName = oddsName;
    }

    /**
     * Getter for property 'firstOddsWin'.
     *
     * @return Value for property 'firstOddsWin'.
     */
    public String getFirstOddsWin() {
        return firstOddsWin;
    }

    /**
     * Setter for property 'firstOddsWin'.
     *
     * @param firstOddsWin Value to set for property 'firstOddsWin'.
     */
    public void setFirstOddsWin(String firstOddsWin) {
        this.firstOddsWin = firstOddsWin;
    }

    /**
     * Getter for property 'firstOddsDeuce'.
     *
     * @return Value for property 'firstOddsDeuce'.
     */
    public String getFirstOddsDeuce() {
        return firstOddsDeuce;
    }

    /**
     * Setter for property 'firstOddsDeuce'.
     *
     * @param firstOddsDeuce Value to set for property 'firstOddsDeuce'.
     */
    public void setFirstOddsDeuce(String firstOddsDeuce) {
        this.firstOddsDeuce = firstOddsDeuce;
    }

    /**
     * Getter for property 'firstOddsLose'.
     *
     * @return Value for property 'firstOddsLose'.
     */
    public String getFirstOddsLose() {
        return firstOddsLose;
    }

    /**
     * Setter for property 'firstOddsLose'.
     *
     * @param firstOddsLose Value to set for property 'firstOddsLose'.
     */
    public void setFirstOddsLose(String firstOddsLose) {
        this.firstOddsLose = firstOddsLose;
    }

    /**
     * Getter for property 'nowOddsWin'.
     *
     * @return Value for property 'nowOddsWin'.
     */
    public String getNowOddsWin() {
        return nowOddsWin;
    }

    /**
     * Setter for property 'nowOddsWin'.
     *
     * @param nowOddsWin Value to set for property 'nowOddsWin'.
     */
    public void setNowOddsWin(String nowOddsWin) {
        this.nowOddsWin = nowOddsWin;
    }

    /**
     * Getter for property 'nowOddsDeuce'.
     *
     * @return Value for property 'nowOddsDeuce'.
     */
    public String getNowOddsDeuce() {
        return nowOddsDeuce;
    }

    /**
     * Setter for property 'nowOddsDeuce'.
     *
     * @param nowOddsDeuce Value to set for property 'nowOddsDeuce'.
     */
    public void setNowOddsDeuce(String nowOddsDeuce) {
        this.nowOddsDeuce = nowOddsDeuce;
    }

    /**
     * Getter for property 'nowOddsLose'.
     *
     * @return Value for property 'nowOddsLose'.
     */
    public String getNowOddsLose() {
        return nowOddsLose;
    }

    /**
     * Setter for property 'nowOddsLose'.
     *
     * @param nowOddsLose Value to set for property 'nowOddsLose'.
     */
    public void setNowOddsLose(String nowOddsLose) {
        this.nowOddsLose = nowOddsLose;
    }

    /**
     * Getter for property 'firstRateWin'.
     *
     * @return Value for property 'firstRateWin'.
     */
    public String getFirstRateWin() {
        return firstRateWin;
    }

    /**
     * Setter for property 'firstRateWin'.
     *
     * @param firstRateWin Value to set for property 'firstRateWin'.
     */
    public void setFirstRateWin(String firstRateWin) {
        this.firstRateWin = firstRateWin;
    }

    /**
     * Getter for property 'firstRateDeuce'.
     *
     * @return Value for property 'firstRateDeuce'.
     */
    public String getFirstRateDeuce() {
        return firstRateDeuce;
    }

    /**
     * Setter for property 'firstRateDeuce'.
     *
     * @param firstRateDeuce Value to set for property 'firstRateDeuce'.
     */
    public void setFirstRateDeuce(String firstRateDeuce) {
        this.firstRateDeuce = firstRateDeuce;
    }

    /**
     * Getter for property 'firstRateLose'.
     *
     * @return Value for property 'firstRateLose'.
     */
    public String getFirstRateLose() {
        return firstRateLose;
    }

    /**
     * Setter for property 'firstRateLose'.
     *
     * @param firstRateLose Value to set for property 'firstRateLose'.
     */
    public void setFirstRateLose(String firstRateLose) {
        this.firstRateLose = firstRateLose;
    }

    /**
     * Getter for property 'nowRateWin'.
     *
     * @return Value for property 'nowRateWin'.
     */
    public String getNowRateWin() {
        return nowRateWin;
    }

    /**
     * Setter for property 'nowRateWin'.
     *
     * @param nowRateWin Value to set for property 'nowRateWin'.
     */
    public void setNowRateWin(String nowRateWin) {
        this.nowRateWin = nowRateWin;
    }

    /**
     * Getter for property 'nowRateDeuce'.
     *
     * @return Value for property 'nowRateDeuce'.
     */
    public String getNowRateDeuce() {
        return nowRateDeuce;
    }

    /**
     * Setter for property 'nowRateDeuce'.
     *
     * @param nowRateDeuce Value to set for property 'nowRateDeuce'.
     */
    public void setNowRateDeuce(String nowRateDeuce) {
        this.nowRateDeuce = nowRateDeuce;
    }

    /**
     * Getter for property 'nowRateLose'.
     *
     * @return Value for property 'nowRateLose'.
     */
    public String getNowRateLose() {
        return nowRateLose;
    }

    /**
     * Setter for property 'nowRateLose'.
     *
     * @param nowRateLose Value to set for property 'nowRateLose'.
     */
    public void setNowRateLose(String nowRateLose) {
        this.nowRateLose = nowRateLose;
    }

    /**
     * Getter for property 'firstReturnRate'.
     *
     * @return Value for property 'firstReturnRate'.
     */
    public String getFirstReturnRate() {
        return firstReturnRate;
    }

    /**
     * Setter for property 'firstReturnRate'.
     *
     * @param firstReturnRate Value to set for property 'firstReturnRate'.
     */
    public void setFirstReturnRate(String firstReturnRate) {
        this.firstReturnRate = firstReturnRate;
    }

    /**
     * Getter for property 'nowReturnRate'.
     *
     * @return Value for property 'nowReturnRate'.
     */
    public String getNowReturnRate() {
        return nowReturnRate;
    }

    /**
     * Setter for property 'nowReturnRate'.
     *
     * @param nowReturnRate Value to set for property 'nowReturnRate'.
     */
    public void setNowReturnRate(String nowReturnRate) {
        this.nowReturnRate = nowReturnRate;
    }

    /**
     * Getter for property 'firstKellyWin'.
     *
     * @return Value for property 'firstKellyWin'.
     */
    public String getFirstKellyWin() {
        return firstKellyWin;
    }

    /**
     * Setter for property 'firstKellyWin'.
     *
     * @param firstKellyWin Value to set for property 'firstKellyWin'.
     */
    public void setFirstKellyWin(String firstKellyWin) {
        this.firstKellyWin = firstKellyWin;
    }

    /**
     * Getter for property 'firstKellyDeuce'.
     *
     * @return Value for property 'firstKellyDeuce'.
     */
    public String getFirstKellyDeuce() {
        return firstKellyDeuce;
    }

    /**
     * Setter for property 'firstKellyDeuce'.
     *
     * @param firstKellyDeuce Value to set for property 'firstKellyDeuce'.
     */
    public void setFirstKellyDeuce(String firstKellyDeuce) {
        this.firstKellyDeuce = firstKellyDeuce;
    }

    /**
     * Getter for property 'firstKellyLose'.
     *
     * @return Value for property 'firstKellyLose'.
     */
    public String getFirstKellyLose() {
        return firstKellyLose;
    }

    /**
     * Setter for property 'firstKellyLose'.
     *
     * @param firstKellyLose Value to set for property 'firstKellyLose'.
     */
    public void setFirstKellyLose(String firstKellyLose) {
        this.firstKellyLose = firstKellyLose;
    }

    /**
     * Getter for property 'nowKellyWin'.
     *
     * @return Value for property 'nowKellyWin'.
     */
    public String getNowKellyWin() {
        return nowKellyWin;
    }

    /**
     * Setter for property 'nowKellyWin'.
     *
     * @param nowKellyWin Value to set for property 'nowKellyWin'.
     */
    public void setNowKellyWin(String nowKellyWin) {
        this.nowKellyWin = nowKellyWin;
    }

    /**
     * Getter for property 'nowKellyDeuce'.
     *
     * @return Value for property 'nowKellyDeuce'.
     */
    public String getNowKellyDeuce() {
        return nowKellyDeuce;
    }

    /**
     * Setter for property 'nowKellyDeuce'.
     *
     * @param nowKellyDeuce Value to set for property 'nowKellyDeuce'.
     */
    public void setNowKellyDeuce(String nowKellyDeuce) {
        this.nowKellyDeuce = nowKellyDeuce;
    }

    /**
     * Getter for property 'nowKellyLose'.
     *
     * @return Value for property 'nowKellyLose'.
     */
    public String getNowKellyLose() {
        return nowKellyLose;
    }

    /**
     * Setter for property 'nowKellyLose'.
     *
     * @param nowKellyLose Value to set for property 'nowKellyLose'.
     */
    public void setNowKellyLose(String nowKellyLose) {
        this.nowKellyLose = nowKellyLose;
    }
}
