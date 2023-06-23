package com.controle.combustivel;

public class SetaValores {

    private static double distanciaPercorrer;
    private static double kmPorLitros;
    private static double totalLitros;
    private static double precoCombustivel;
    private static double valorPagamneto;

    public static double getDistanciaPercorrer() {
        return distanciaPercorrer;
    }

    public static void setDistanciaPercorrer(double distanciaPercorrer) {
        SetaValores.distanciaPercorrer = distanciaPercorrer;
    }

    public static double getKmPorLitros() {
        return kmPorLitros;
    }

    public static void setKmPorLitros(double kmPorLitros) {
        SetaValores.kmPorLitros = kmPorLitros;
    }

    public static double getTotalLitros() {
        return totalLitros;
    }

    public static void setTotalLitros(double totalLitros) {
        SetaValores.totalLitros = totalLitros;
    }

    public static double getPrecoCombustivel() {
        return precoCombustivel;
    }

    public static void setPrecoCombustivel(double precoCombustivel) {
        SetaValores.precoCombustivel = precoCombustivel;
    }

    public static double getValorPagamneto() {
        return valorPagamneto;
    }

    public static void setValorPagamneto(double valorPagamneto) {
        SetaValores.valorPagamneto = valorPagamneto;
    }

    public static double getKmAtual() {
        return kmAtual;
    }

    public static void setKmAtual(double kmAtual) {
        SetaValores.kmAtual = kmAtual;
    }

    private static double  kmAtual;


}
