import { Solicitante } from "../types/solicitante";
import api from "./api";

export async function buscarListaSolicitantes(): Promise<Solicitante[]> {

    const dados = await api.get<Solicitante[]>("http://localhost:8080/solicitantes");

    if (dados.status == 200) {
        return dados.data;
    }

    return [];
}

export async function deletarSolicitante(id: number): Promise<number> {

    var dados = await api.delete('http://localhost:8080/solicitantes/' + id + '/excluir');
    return dados.status;
}

export async function atualizarStatusSolicitante(solicitante: Solicitante): Promise<void> {

    var novoStatus = {};
    if (solicitante.status === "ATIVO") {
        novoStatus = { status: "BLOQUEADO" }
    } else {
        novoStatus = { status: "ATIVO" }
    }

    var dadosRetorno = await
        api.patch('http://localhost:8080/solicitantes/' + solicitante.id + '/status', novoStatus);

    if (dadosRetorno.status == 200) {
        alert("Status atualizado com sucesso!");
    } else {
        alert(dadosRetorno.data);
        return;
    }
}

export async function consultaSolicitantePorId(id: Number): Promise<Solicitante> {

    const valorSolicitanteBack = await api.get<Solicitante>('http://localhost:8080/solicitantes/' + id);
    return valorSolicitanteBack.data;
}

export async function atualizarSolicitante(solicitante: Solicitante): Promise<number> {
    var dadosRetorno = await api.put<number>('http://localhost:8080/solicitantes/' + solicitante.id, solicitante);
    return dadosRetorno.data;
}

export async function salvarSolicitante(solicitante: Solicitante): Promise<number> {
    var dadosRetorno = await api.post<number>('http://localhost:8080/solicitantes/' + solicitante.id, solicitante);
    return dadosRetorno.data;
}