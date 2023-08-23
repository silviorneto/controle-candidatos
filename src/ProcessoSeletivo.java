import enums.AcaoAposAnaliseCandidato;
import model.Candidato;
import service.CandidatoService;

import java.util.ArrayList;

public class ProcessoSeletivo {
    private static ArrayList<Candidato> candidatosAprovados = new ArrayList<>();
    private static ArrayList<Candidato> candidatosInscritos = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("### PROCESSO SELETIVO ###");

        String [] candidatos = {"FELIPE","MÁRCIA","JULIA","PAULO","AUGUSTO","MÔNICA","FABRÍCIO","MIRELA","DANIELA","JORGE"};

        primeiraChamada(candidatos);

        if (candidatosAprovados.size() < 5) {
            ArrayList<Candidato> novaLista = filtrarCandidatos();
            segundaChamada(novaLista);
        }

        imprimirAprovados();

    }

    public static void primeiraChamada(String[] candidatos) {
        System.out.println();
        System.out.println("### ANALISE DE CANDIDATOS - 1 CHAMADA ###");

        for (String nomeCandidato : candidatos) {
            if (candidatosAprovados.size() > 4) {
                break;
            }

            Candidato candidato = new Candidato(nomeCandidato);
            candidatosInscritos.add(candidato);

            AcaoAposAnaliseCandidato acao = CandidatoService.analisarCandidato(candidato);

            if (acao == AcaoAposAnaliseCandidato.LIGAR) {
                CandidatoService.realizarContato(candidato);

                if (candidato.isContatoComSucesso()) {
                    candidatosAprovados.add(candidato);
                }
            } else if (acao == AcaoAposAnaliseCandidato.LIGAR_COM_CONTRAPROPOSTA) {
                if (candidato.isContatoComSucesso()) {
                    if (CandidatoService.realizarContraProposta(candidato)) {
                        candidatosAprovados.add(candidato);
                    }
                }
            }
        }
    }

    private static ArrayList<Candidato> segundaChamada(ArrayList<Candidato> listaFiltrada) {
        System.out.println();
        System.out.println("### ANALISE DE CANDIDATOS - 2 CHAMADA ###");
        for (Candidato candidato : listaFiltrada) {
            if (candidatosAprovados.size() > 4) {
                break;
            }
            AcaoAposAnaliseCandidato acao = CandidatoService.analisarCandidato(candidato);

            if (acao == AcaoAposAnaliseCandidato.LIGAR) {
                CandidatoService.realizarContato(candidato);
                if (candidato.isContatoComSucesso()) {
                    candidatosAprovados.add(candidato);
                }
            } else if (acao == AcaoAposAnaliseCandidato.LIGAR_COM_CONTRAPROPOSTA || acao == AcaoAposAnaliseCandidato.AGUARDAR) {
                if (candidato.isContatoComSucesso()) {
                    if (CandidatoService.realizarContraProposta(candidato)) {
                        candidatosAprovados.add(candidato);
                    }
                }
            }

        }

        return candidatosAprovados;
    }

    private static ArrayList<Candidato> filtrarCandidatos () {
        ArrayList<Candidato> listaFiltrada = new ArrayList<>();

        for (Candidato candidato: candidatosInscritos)
            if (candidatosAprovados.stream().filter(c -> c.getNome().toLowerCase() == candidato.getNome().toLowerCase()).toArray().length < 1) {
                listaFiltrada.add(candidato);
            }

        return listaFiltrada;
    }

    private static void imprimirAprovados () {
        System.out.println("\n### Lista de Aprovados ###");
        for (Candidato candidato : candidatosAprovados) {
            System.out.println(candidato);
        }
    }

}
