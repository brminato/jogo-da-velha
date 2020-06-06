package jogo.da.velha.usecase;

import java.util.Arrays;

import javax.inject.Inject;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class JogoDaVelhaUseCaseTests {

    @Inject
    private JogoDaVelhaUseCase jogoDaVelhaUseCase;

    @Test
    public void convertRequestArrayToIntArrayTest_01 () {
        final String[] jogo = new String[] { "XXO", "XOX", "OOX" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        final String resultado = Arrays.toString(jogoArrayInt).replaceAll("\\[|\\]|,|\\s", "");

        Assertions.assertEquals("110101001", resultado);
    }

    @Test
    public void convertRequestArrayToIntArrayTest_02 () {
        final String[] jogo = new String[] { "XXX", "OOX", "OXO" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        final String resultado = Arrays.toString(jogoArrayInt).replaceAll("\\[|\\]|,|\\s", "");

        Assertions.assertEquals("111001010", resultado);
    }

    @Test
    public void isVelhaColuna1TrueTest () {
        final String[] jogo = new String[] { "XOO", "XXO", "XOX" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaColuna2TrueTest () {
        final String[] jogo = new String[] { "XOX", "XOO", "OOX" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaColuna3TrueTest () {
        final String[] jogo = new String[] { "XXO", "OXO", "XOO" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaLinha1TrueTest () {
        final String[] jogo = new String[] { "XXX", "XOX", "OXO" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaLinha2TrueTest () {
        final String[] jogo = new String[] { "XOX", "OOO", "XXO" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaLinha3TrueTest () {
        final String[] jogo = new String[] { "XOX", "XXO", "OOO" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaDiagonalEsquerdoTrueTest () {
        final String[] jogo = new String[] { "XOO", "XXO", "OOX" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaDiagonalDireitoTrueTest () {
        final String[] jogo = new String[] { "XXO", "XOO", "OOX" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertTrue(isVelha);
    }

    @Test
    public void isVelhaFalseTest_01 () {
        final String[] jogo = new String[] { "XXO", "OOX", "XOX" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertFalse(isVelha);
    }

    @Test
    public void isVelhaFalseTest_02 () {
        final String[] jogo = new String[] { "XOX", "OXO", "OXO" };
        final int[] jogoArrayInt = jogoDaVelhaUseCase.convertRequestArrayToIntArray(jogo);

        boolean isVelha = jogoDaVelhaUseCase.isVelha(jogoArrayInt);

        Assertions.assertFalse(isVelha);
    }

}
