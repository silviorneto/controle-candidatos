package model;

import service.ProcessoSeletivoService;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class Candidato {
    private String nome;
    private double salarioPretendido;
    private boolean contatoComSucesso;

    public Candidato(String nome) {
        this.nome = nome;
        this.salarioPretendido = calculaSalarioPretendido();
        this.contatoComSucesso = false;
    }

    public String getNome() {
        return nome;
    }
    public double getSalarioPretendido() {
        return salarioPretendido;
    }

    public boolean isContatoComSucesso() {
        return contatoComSucesso;
    }

    public void setContatoComSucesso() {
        this.contatoComSucesso = true;
    }

    private double calculaSalarioPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

    public boolean aceitouContraProposta() {
        Random random = new Random();
        boolean aceitou = random.nextBoolean();

        if (aceitou) {
            this.salarioPretendido = ProcessoSeletivoService.SALARIO_BASE;
        }
        return aceitou;
    }

    @Override
    public String toString() {
        return "Candidato[" +
                "nome='" + nome + '\'' +
                ", salarioPretendido=" + salarioPretendido +
                ", contatoComSucesso=" + contatoComSucesso +
                ']';
    }
}
