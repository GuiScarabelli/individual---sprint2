package Entidades;

public class Arena {
    private Integer idArena;
    private String nomeArena;
    private Integer fkEmpresa;

    public Integer getIdArena() {
        return idArena;
    }

    public void setIdArena(Integer idArena) {
        this.idArena = idArena;
    }

    public String getNomeArena() {
        return nomeArena;
    }

    public void setNomeArena(String nomeArena) {
        this.nomeArena = nomeArena;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
