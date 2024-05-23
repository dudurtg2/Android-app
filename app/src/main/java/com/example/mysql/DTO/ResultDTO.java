package com.example.mysql.DTO;

import com.example.mysql.DAO.ResultDAO;

public  class ResultDTO {
    private static String nome = "null";
    private static String preso = "null";
    private static String estoque = "null";
    private static String idd = "null";

    public static String getIdd() {
        return idd;
    }

    public static void setIdd(String idd) {
        ResultDTO.idd = idd;
    }

    public ResultDTO(String nome, String preso, String estoque,String idd) {
        ResultDTO.nome = nome;
        ResultDTO.preso = preso;
        ResultDTO.estoque = estoque;
        ResultDTO.idd = idd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        ResultDTO.nome = nome;
    }

    public String getPreso() {
        return preso;
    }

    public void setPreso(String preso) {
        ResultDTO.preso = preso;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        ResultDTO.estoque = estoque;
    }
}
