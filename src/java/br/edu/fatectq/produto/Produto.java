package br.edu.fatectq.produto;

public class Produto {
    
    private Integer codigo;
    private String nome;
    private String tipo;
    private float preco;
    private float qtd;
    private int categ;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getQtd() {
        return qtd;
    }

    public void setQtd(float qtd) {
        this.qtd = qtd;
    }

    public int getCateg() {
        return categ;
    }

    public void setCateg(int categ) {
        this.categ = categ;
    }
    
}
