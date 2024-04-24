package br.com.alura.cepfinder;

public class BaseCep {
    String cep;
    String logradouro;
    String complemento;
    String bairro;
    String uf;
    int ddd;

    public BaseCep(BaseSalvar novoCep) {
        this.cep = novoCep.cep();
        this.logradouro = novoCep.logradouro();
        this.complemento = novoCep.complemento();
        this.bairro = novoCep.bairro();
        this.uf = novoCep.uf();
        this.ddd = novoCep.ddd();
    }

    @Override
    public String toString() {
        return "(CEP:" + cep + "," +
                "Logradouro:" + logradouro + "," +
                "Complemento:" + complemento + "," +
                "Bairro:" + bairro + "," +
                "UF:" + uf + "," +
                "DDD:" + ddd + ")";
    }
}


