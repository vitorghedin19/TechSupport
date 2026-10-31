import { Equipamento } from "../types/equipamento";
import api from "./api";

export async function buscarListaEquipamentos(): Promise<Equipamento[]> {

    const dados = await api.get<Equipamento[]>("http://localhost:8080/equipamentos");

    if (dados.status == 200) {
        return dados.data;
    }

    return [];
}

export async function deletarEquipamento(id: number): Promise<number> {

    var dados = await api.delete('http://localhost:8080/equipamentos/' + id + '/excluir');
    return dados.status;
}

export async function atualizarStatusEquipamento(equipamento: Equipamento): Promise<void> {

    var novoStatus = {};
    if (equipamento.status === "ATIVO") {
        novoStatus = { status: "BLOQUEADO" }
    } else {
        novoStatus = { status: "ATIVO" }
    }

    var dadosRetorno = await
        api.patch('http://localhost:8080/equipamentos/' + equipamento.id + '/status', novoStatus);

    if (dadosRetorno.status == 200) {
        alert("Status atualizado com sucesso!");
    } else {
        alert(dadosRetorno.data);
        return;
    }
}

export async function consultaEquipamentoPorId(id: Number): Promise<Equipamento> {

    const valorEquipamentoBack = await api.get<Equipamento>('http://localhost:8080/equipamentos/' + id);
    return valorEquipamentoBack.data;
}

export async function atualizarEquipamento(equipamento: Equipamento): Promise<number> {
    var dadosRetorno = await api.put<number>('http://localhost:8080/equipamentos/' + equipamento.id, equipamento);
    return dadosRetorno.data;
}

export async function salvarEquipamento(equipamento: Equipamento): Promise<number> {
    var dadosRetorno = await api.post<number>('http://localhost:8080/equipamentos/' + equipamento.id, equipamento);
    return dadosRetorno.data;
}