package jogo.da.velha.usecase;

import java.util.Arrays;

import javax.inject.Singleton;

@Singleton
public class JogoDaVelhaUseCase {

    private static Integer[] vitoriasBinarias = {
            0b100100100, //Posicoes da Coluna 1
            0b010010010, //Posicoes da Coluna 2
            0b001001001, //Posicoes da Coluna 3

            0b111000000, //Posicoes da Linha 1
            0b000111000, //Posicoes da Linha 2
            0b000000111, //Posicoes da Linha 3

            0b100010001, //Diagonal Esquerdo
            0b001010100  //Diagonal Direito
    };

    public int[] convertRequestArrayToIntArray (String[] jogo) {
        String resultadoJogo = "";

        for (String linhaJogo : jogo) {
            resultadoJogo += linhaJogo;
        }

        resultadoJogo = resultadoJogo.replaceAll("x", "1").replaceAll("X", "1").replaceAll("o", "0")
                .replaceAll("O", "0");

        int[] jogoArrayInt = new int[9];
        char[] jogoChar = resultadoJogo.toCharArray();

        for (int i = 0; i < 9; i++) {
            char posicao = jogoChar[i];
            jogoArrayInt[i] = Character.getNumericValue(posicao);
        }

        return jogoArrayInt;
    }

    public boolean isVelha (int[] jogo) {
        //      Converte jogo em resultado binario do Jogador 1
        final String resultadoBinarioJogador1 = Arrays.toString(jogo).replaceAll("\\[|\\]|,|\\s", "");

        //      Valida jogo do Jogador 1
        boolean isVelha = validaJogoJogador(resultadoBinarioJogador1);

        //      Caso nao seja velha, valida jogo do Jogador 2
        if (!isVelha) {
            //          Converte jogo em resultado binario do Jogador 2
            final String resultadoBinarioJogador2 = resultadoBinarioJogador1.replaceAll("1", "X").replaceAll("0", "1")
                    .replace("X", "0");

            //      Valida jogo do Jogador 2
            isVelha = validaJogoJogador(resultadoBinarioJogador2);
        }
        //      Retorna resultado
        return isVelha;
    }

    private boolean validaJogoJogador (String jogoJogador) {
        //      Converte o resultado em Decimal
        int resultadoDecimal = Integer.parseInt(jogoJogador, 2);
        boolean isVelha = false;

        for (int vitoria : vitoriasBinarias) {
            //      Faz uma operacao AND entre resultado e vitoria
            int resultado = resultadoDecimal & vitoria;
            //      Compara resultado e vitoria
            if (resultado == vitoria) {
                isVelha = true;
                break;
            }
        }

        return isVelha;
    }

}
