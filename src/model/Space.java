package model;


public class Space {

    private Integer atual;
    private final int experado;
    private final boolean fixed;
    
    public Space(final int experado, final boolean fixed){
        this.experado = experado;
        this.fixed = fixed;
        if(fixed){
            atual = experado;
        }
    }

    public Integer getAtual() {
        return atual;
    }

    public void setAtual(final Integer atual) {
        if(fixed) return;
        this.atual = atual;
    }    

    public void clearSpace(){
        setAtual(null);
    }

    public int getExperado() {
        return experado;
    }

    public boolean isFixed() {
        return fixed;
    }


    
}
