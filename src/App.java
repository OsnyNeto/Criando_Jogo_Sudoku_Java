import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Board;
import model.Space;

public class App {

    private final static  Scanner scanner = new Scanner(System.in);
    private static Board board;
    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) throws Exception {
        final var position = Stream.of(args)
        .collect(Collectors.toMap(
            k -> k.split(";")[0], 
            v -> v.split(";")[1]));
        var option = -1;
        while(true){
            System.out.println("Selecione uma das opções: ");
            System.out.println("1 - Iniciar um novo jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");
           
            option = scanner.nextInt();

            switch (option) {
                case 1 : startGame(position);
                case 2 : inputNumber();
                case 3 : removeNumber();
                case 4 : showCurrentGame();
                case 5 : showGameStatus();
                case 6 : clearGame();
                case 7 : finishGame();
                case 8 : System.exit(0);                                    
                default: System.out.println("Opção inválida!");
                                    
            }
            
        }
    }
    
    private static void startGame(Map<String, String> positions) {
        if(nonNull(board)){
            System.out.println("O jogo já foi iniciado");
            return;
        }
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig =positions.get("%s,%s".formatted(i,j));
                var experado = Integer.parseInt(positionConfig.split(",")[0]);
                var fixo = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(experado,fixo);
                spaces.get(i).add(currentSpace);
            }
        }
        board=new Board(spaces);
        System.out.println("O jogo está pronto para começar.");
    }

    private static void inputNumber() {
        if(isNull(board)){
            System.out.println("O jogo não foi iniciado");
        }
        System.out.println("Informe a colunua que o número será inserido.");
        var coluna = runUntilValidNumber(0, 8);
        System.out.println("Informe a linha que o número será inserido.");
        var linha = runUntilValidNumber(0, 8);
        System.out.printf("Iforme o número que vai entrar na posição %s,%s\n",coluna,linha);
        var valor = runUntilValidNumber(1, 9);
        if(!board.changeValue(coluna, linha, valor)){
            System.out.printf("A posição %s,%s tem um valor fixo\n",coluna,linha);
        }

    }
    
    private static void removeNumber() {
        if(isNull(board)){
            System.out.println("O jogo não foi iniciado");
        }
        System.out.println("Informe a colunua que o número será inserido.");
        var coluna = runUntilValidNumber(0, 8);
        System.out.println("Informe a linha que o número será inserido.");
        var linha = runUntilValidNumber(0, 8);
        System.out.printf("Iforme o número que vai entrar na posição %s,%s\n",coluna,linha);
        if(!board.clearValor(coluna, linha)){
            System.out.printf("A posição %s,%s tem um valor fixo\n",coluna,linha);
        }
    }

    private static void showCurrentGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private static void showGameStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private static void clearGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private static void finishGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static int runUntilValidNumber(final int min, final int max){
        var current  = scanner.nextInt();
        while(current<min || current > max){
            System.out.printf("Informe um número entre %s e %s.\n",min,max);
            current = scanner.nextInt();
        }
        return current;
    }



}
