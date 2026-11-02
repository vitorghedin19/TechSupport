import { Solicitante } from "../types/solicitante";
import api from "./api";

export async function buscarListaSolicitantes(): Promise<Solicitante[]> {
    const dados = await api.get<Solicitante[]>("/solicitante");
    if (dados.status == 200) {
        return dados.data;
    }
    return [];
}

export async function deletarSolicitante(id: number): Promise<number> {
    var dados = await api.delete('/solicitante/' + id + '/excluir');
    return dados.status;
}

export async function atualizarStatusSolicitante(solicitante: Solicitante): Promise<void> {
    var novoStatus = {};
    if (solicitante.status === "ATIVO") {
        novoStatus = { status: "BLOQUEADO" }
    } else {
        novoStatus = { status: "ATIVO" }
    }

    var dadosRetorno = await api.patch('/solicitante/' + solicitante.id + '/status', novoStatus);

    if (dadosRetorno.status == 200) {
        alert("Status atualizado com sucesso!");
    } else {
        alert(dadosRetorno.data);
        return;
    }
}

export async function consultaSolicitantePorId(id: Number): Promise<Solicitante> {
    const valorSolicitanteBack = await api.get<Solicitante>('/solicitante/' + id);
    return valorSolicitanteBack.data;
}

export async function atualizarSolicitante(solicitante: Solicitante): Promise<number> {
    var dadosRetorno = await api.put<number>('/solicitante/' + solicitante.id, solicitante);
    return dadosRetorno.data;
}

export async function salvarSolicitante(solicitante: Solicitante): Promise<number> {
    var dadosRetorno = await api.post<number>('/solicitante', solicitante);
    return dadosRetorno.data;
}

export async function buscarSolicitanteLogado() : Promise<Solicitante>{
    return (await api.get<Solicitante>('/solicitante/buscarsolicitantelogado')).data
}