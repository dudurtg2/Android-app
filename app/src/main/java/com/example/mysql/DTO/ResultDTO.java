package com.example.mysql.DTO;

public class ResultDTO {
    private String nome;
    private String preso;
    private String estoque;

    public ResultDTO(String nome, String preso, String estoque) {
        this.nome = nome;
        this.preso = preso;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreso() {
        return preso;
    }

    public void setPreso(String preso) {
        this.preso = preso;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
}
