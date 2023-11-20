package Entidades;

public class StatusPc {
    private Integer idCaptura;
    private Double processadorEmUso;
    private Long memoriaUso;
    private Long discoDisponivel;
    private Double tempProcessador;
    private String dtHoraCaptura;

    public Integer getIdCaptura() {
        return idCaptura;
    }

    public void setIdCaptura(Integer idCaptura) {
        this.idCaptura = idCaptura;
    }

    public Double getProcessadorEmUso() {
        return processadorEmUso;
    }

    public void setProcessadorEmUso(Double processadorEmUso) {
        this.processadorEmUso = processadorEmUso;
    }

    public Long getMemoriaUso() {
        return memoriaUso;
    }

    public void setMemoriaUso(Long memoriaUso) {
        this.memoriaUso = memoriaUso;
    }

    public Long getDiscoDisponivel() {
        return discoDisponivel;
    }

    public void setDiscoDisponivel(Long discoDisponivel) {
        this.discoDisponivel = discoDisponivel;
    }

    public String getDtHoraCaptura() {
        return dtHoraCaptura;
    }

    public void setDtHoraCaptura(String dtHoraCaptura) {
        this.dtHoraCaptura = dtHoraCaptura;
    }

    public Double getTempProcessador() {
        return tempProcessador;
    }

    public void setTempProcessador(Double tempProcessador) {
        this.tempProcessador = tempProcessador;
    }
}
