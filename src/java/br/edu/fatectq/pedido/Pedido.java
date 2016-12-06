package br.edu.fatectq.pedido;

public class Pedido {
    
    private int codigo;
    private String hora_ent;
    private String status_ped;
    private int cod_mesa;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getHora_ent() {
        return hora_ent;
    }

    public void setHora_ent(String hora_ent) {
        this.hora_ent = hora_ent;
    }

    public String getStatus_ped() {
        return status_ped;
    }

    public void setStatus_ped(String status_ped) {
        this.status_ped = status_ped;
    }

    public int getCod_mesa() {
        return cod_mesa;
    }

    public void setCod_mesa(int cod_mesa) {
        this.cod_mesa = cod_mesa;
    }
}
