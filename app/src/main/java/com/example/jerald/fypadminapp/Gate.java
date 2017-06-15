package com.example.jerald.fypadminapp;

/**
 * Created by 15017292 on 4/6/2017.
 */

public class Gate {

    private String gateName;
    private String id;
    private String terminalName;

    public Gate(){

    }

    public Gate(String gateName,String id, String terminalName) {
        this.gateName = gateName;
        this.id = id;
        this.terminalName = terminalName;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
