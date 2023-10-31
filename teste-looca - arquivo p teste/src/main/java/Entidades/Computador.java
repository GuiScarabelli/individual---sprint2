package Entidades;

import com.github.britooo.looca.api.util.Conversor;

public class Computador {
        private Integer id;
        private String SO;
        private Long memoriaTot;
        private String processador;
        private Long discoTotal;
        private Integer qtdDiscos;

        public void gerarTextoInicio(){
            System.out.println("""
                Iniciando captura...
                """);
        }

        public int getId() {
            return this.id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getSO() {
            return SO;
        }

        public void setSO(String So) {
            this.SO = So;
        }

        public Long getMemoriaTot() {
            return memoriaTot;
        }

        public void setMemoriaTot(Long memoria) {
            this.memoriaTot = memoria;
        }

        public String getProcessador() {
        return processador;
    }

        public void setProcessador(String processador) {
            this.processador = processador;
        }

        public Long getDiscoTotal() {
            return discoTotal;
        }

        public void setDiscoTotal(Long discoTotal) {
            this.discoTotal = discoTotal;
        }

        public Integer getQtdDiscos() {
            return qtdDiscos;
        }

        public void setQtdDiscos(Integer qtdDiscos) {
            this.qtdDiscos = qtdDiscos;
        }
}
