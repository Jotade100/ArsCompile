package edu.arscompile.modelos;

public class CeldaParser {
    String action = "error";
    int number;

    public CeldaParser(){}

    public CeldaParser(String action, int number) {
        this.action = action;
        this.number = number;

    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAction(){
        return action;
    }

    public int getNumber(){
        return number;
    }

}