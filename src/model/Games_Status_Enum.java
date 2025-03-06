package model;

public enum Games_Status_Enum {

    NAO_INICIADO("Não Iniciado"),
    INCOMPLETO("Incompleto"),
    COMPLETO("Completo");

    private String label;

    Games_Status_Enum (final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
}
