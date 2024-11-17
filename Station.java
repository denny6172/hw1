/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Station {

    /**
     * Default constructor
     */
    public Station() {
    }

    public Station(int number, String location) {
        this.number = number;
        this.location = location;
    }

    /**
     * 
     */
    private int number;

    /**
     * 
     */
    private String location;

    /**
     * @return
     */
    public int getNumber() {
        // TODO implement here
        return this.number;
    }

    /**
     * @param number
     */
    public void setNumber(int number) {
        // TODO implement here
        this.number = number;
    }

    /**
     * @return
     */
    public String getLocation() {
        // TODO implement here
        return this.location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        // TODO implement here
        this.location = location;
    }

}