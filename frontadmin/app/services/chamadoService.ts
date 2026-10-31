import { Chamado } from "../types/chamado";
import api from "./api";

export async function buscarListaChamados(): Promise<Chamado[]> {

    const dados = await api.get<Chamado[]>("http://localhost:8080/chamado");

    if (dados.status == 200) {
        return dados.data;
    }

    return [];
}

export async function deletarChamado(id: number): Promise<number> {

    var dados = await api.delete('http://localhost:8080/chamado/' + id + '/excluir');
    return dados.status;
}

// O botão da tabela só passa o chamado; aqui decidimos o PRÓXIMO status.
// Ciclo: ABERTO -> EM_ANDAMENTO -> FECHADO -> ABERTO.
// RESOLVIDO fica de fora de propósito: só se chega nele pela regra do resolver (com solução).
// Se quiser forçar um status específico, passe o segundo parâmetro.
export async function atualizarStatusChamado(chamado: Chamado, novoStatus?: string): Promise<void> {

    var proximoStatus = novoStatus;

    if (!proximoStatus) {
        if (chamado.status === "ABERTO") {
            proximoStatus = "EM_ANDAMENTO";
        } else if (chamado.status === "EM_ANDAMENTO") {
            proximoStatus = "FECHADO";
        } else {
            proximoStatus = "ABERTO";
        }
    }

    var dadosRetorno = await
        api.patch('http://localhost:8080/chamado/' + chamado.id + '/status', { status: proximoStatus });

    if (dadosRetorno.status == 200) {
        alert("Status atualizado com sucesso!");
    } else {
        alert(dadosRetorno.data);
        return;
    }
}

// Regra de negócio: só resolve se informar a solução.
export async function resolverChamado(id: number, solucao: string): Promise<void> {

    var dadosRetorno = await
        api.patch('http://localhost:8080/chamado/' + id + '/resolver', { solucao: solucao });

    if (dadosRetorno.status == 200) {
        alert("Chamado resolvido com sucesso!");
    } else {
        alert(dadosRetorno.data);
        return;
    }
}

export async function consultaChamadoPorId(id: Number): Promise<Chamado> {

    const valorChamadoBack = await api.get<Chamado>('http://localhost:8080/chamado/' + id);
    return valorChamadoBack.data;
}

export async function atualizarChamado(chamado: Chamado): Promise<number> {
    var dadosRetorno = await api.put<number>('http://localhost:8080/chamado/' + chamado.id, chamado);
    return dadosRetorno.data;
}

export async function salvarChamado(chamado: Chamado): Promise<number> {
    var dadosRetorno = await api.post<number>('http://localhost:8080/chamado', chamado);
    return dadosRetorno.data;
}