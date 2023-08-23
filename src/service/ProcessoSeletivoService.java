package service;

import enums.AcaoAposAnaliseCandidato;
import model.Candidato;

import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivoService {
    public static final double SALARIO_BASE = 2_000.0;

    public static AcaoAposAnaliseCandidato analisarCandidato(Candidato candidato) {
        if (SALARIO_BASE > candidato.getSalarioPretendido()) {
            System.out.printf("%s: LIGAR PARA O CANDIDATO%n", candidato.getNome());
            return AcaoAposAnaliseCandidato.LIGAR;
        } else if (SALARIO_BASE == candidato.getSalarioPretendido()) {
            System.out.printf("%s: LIGAR PARA O CANDIDATO, COM CONTRA PROPOSTA%n", candidato.getNome());
            return AcaoAposAnaliseCandidato.LIGAR_COM_CONTRAPROPOSTA;
        } else {
            System.out.printf("%s: AGUARDANDO RESULTADO DOS DEMAIS CANDIDATOS%n", candidato.getNome());
            return AcaoAposAnaliseCandidato.AGUARDAR;
        }
    }

    private static int tentativasDeContato() {
        return ThreadLocalRandom.current().nextInt(1, 5);
    }

    public static void contatoComSucesso(Candidato candidato) {
        if (tentativasDeContato() <= 3) {
            candidato.setContatoComSucesso();
        }
    }
}
