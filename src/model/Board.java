package model;

import java.util.Collection;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static model.Games_Status_Enum.COMPLETO;
import static model.Games_Status_Enum.INCOMPLETO;
import static model.Games_Status_Enum.NAO_INICIADO;

public class Board {

    private final List<List<Space>> spaces;

    

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }



    public List<List<Space>> getSpaces() {
        return spaces;
    }
    public Games_Status_Enum getStatusEnum(){
        if(spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getAtual()))){
            return NAO_INICIADO;

        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getAtual())) ? INCOMPLETO : COMPLETO;
    }

    public boolean hasErros(){
        if(getStatusEnum()==NAO_INICIADO){
            return false;
        }
        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> nonNull(s.getAtual()) && !s.getAtual().equals(s.getExperado()));
    }

    public boolean changeValue(final int coluna, final int linha, final int valor){
        var space = spaces.get(coluna).get(linha);
        if(space.isFixed()){
            return false;
        }

        space.setAtual(valor);
        return true;
    }

    public boolean clearValor(final int coluna, final int linha){
        var space = spaces.get(coluna).get(linha);
        if(space.isFixed()){
            return false;
        }
        space.clearSpace();
        return true;
    }

    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinalizado(){
        return !hasErros() && getStatusEnum() == COMPLETO;
    }
}
