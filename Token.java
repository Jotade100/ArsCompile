package edu.arscompile.modelos;


public class Token {

    int type;
    int left;
    int right;
    Object value;

    public Token(){}

    public Token(int type, Object value){
        this.type = type;
        this.value = value;
    }

    public Token(Object value, int left, int right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    

    public Token(int type, Object value, int left, int right){
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getType(){
        return type;
    }

    public Object getValue(){
        return value;
    }

    public int getLeft(){
        return left;
    }

    public int getRight(){
        return right;
    }


    public void imprimirTokenBonitoCorto(){
        System.out.print("<Type: "+ type + ", Value: "+ value+">");
    }

    public void imprimirTokenBonitoLargo(){
        System.out.print("<Type: "+ type + ", Value: "+ value+ ", L\u00ednea: "+ left+", Posici\u00f3n: "+ right+ ">");
    }

    public String retornarTokenBonitoCorto(){
        return "<Type: "+ type + ", Value: "+ value+">";
    }

    public String retornarTokenBonitoLargo(){
        return "<Type: "+ type + ", Value: "+ value+ ", L\u00ednea: "+ left+", Posici\u00f3n: "+ right+ ">";
    }

}