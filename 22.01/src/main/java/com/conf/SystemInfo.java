package com.conf;

public class SystemInfo {
    private int numberOfCore; //liczba rdzeni procesora

    public SystemInfo(){
        numberOfCore = Runtime.getRuntime().availableProcessors();
    }

    /**
     *
     * @return liczba rdzeni procesora
     */
    public int getNumberOfCore(){
        return this.numberOfCore;
    }


}
