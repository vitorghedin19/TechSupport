'use client'

import { Chamado, ChamadoFormProps } from "@/app/types/chamado";
import { Equipamento } from "@/app/types/equipamento";
import { atualizarChamado, salvarChamado } from "@/app/services/chamadoService";
import { buscarListaEquipamentos } from "@/app/services/equipamentoService";
import { RootState } from "@/app/redux/store";
import { registrarUso } from "@/app/redux/slices/equipamentosRecentesSlice";
import { useDispatch, useSelector } from "react-redux";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function ChamadoForm({chamadoExistente}:ChamadoFormProps){

    const router = useRouter();

    // Redux: lê a lista de ids usados recentemente e usa o dispatch para registrar um novo uso ao salvar.
    const dispatch = useDispatch();
    const idsRecentes = useSelector((state: RootState) => state.equipamentosRecentes.ids);

    const [chamado, setChamado] = useState<Chamado>(
    chamadoExistente ||
    new Chamado(null, "", "", "", "ABERTO"));

    const [equipamentos, setEquipamentos] = useState<Equipamento[]>([]);

    useEffect(() => {
        carregarEquipamentos();
    }, []);

    const carregarEquipamentos = async () => {
        try{
            const lista = await buscarListaEquipamentos();
            setEquipamentos(lista);
        } catch (error){
            setEquipamentos([]);
        }
    }

    const equipamentosDisponiveis = equipamentos.filter(
        (equipamento) => equipamento.status === 'ATIVO' || equipamento.id === chamado.equipamentoId
    );

    // Os recentes são os ids guardados no Redux que ainda estão disponíveis (ativos) nesta lista, na ordem do mais recente.
    const equipamentosRecentes = idsRecentes
        .map((id) => equipamentosDisponiveis.find((equipamento) => equipamento.id === id))
        .filter((equipamento): equipamento is Equipamento => equipamento !== undefined);

    const equipamentosOutros = equipamentosDisponiveis.filter(
        (equipamento) => !idsRecentes.includes(equipamento.id ?? -1)
    );

    const handlerChange = ( campo: 'titulo' | 'descricao' | 'prioridade' | 'status', valor:string) => {
        setChamado(valorAnterior => 
            new Chamado(
                valorAnterior.id,
                campo === 'titulo' ? valor : valorAnterior.titulo,
                campo === 'descricao' ? valor : valorAnterior.descricao,
                campo === 'prioridade' ? valor : valorAnterior.prioridade,
                campo === 'status' ? valor : valorAnterior.status,
                valorAnterior.solucao,
                valorAnterior.equipamentoId,
                valorAnterior.equipamentoNome
            )
        )
    }

    const handlerEquipamento = (valor:string) => {
        setChamado(valorAnterior => 
            new Chamado(
                valorAnterior.id,
                valorAnterior.titulo,
                valorAnterior.descricao,
                valorAnterior.prioridade,
                valorAnterior.status,
                valorAnterior.solucao,
                valor === "" ? null : Number(valor),
                valorAnterior.equipamentoNome
            )
        )
    }

    const handlerSalvar = async (formData : FormData) => {

        if(chamadoExistente){

            var dadosRetorno = await atualizarChamado(chamado);

            if(dadosRetorno){
                alert("Chamado foi salvo com sucesso");
            }else{
                alert("Erro ao atualizar Chamado!");
                return;
            }

        }else{

            var dadosRetorno = await salvarChamado(chamado);

            if(dadosRetorno){
                alert("Chamado foi salvo com sucesso");
            }else{
                alert("Erro ao salvar Chamado!");
                return;
            }
        }
        // Guarda no Redux o equipamento usado neste chamado, para aparecer em "Usados recentemente" nos próximos.
        if (chamado.equipamentoId != null) {
            dispatch(registrarUso(chamado.equipamentoId));
        }

        router.push("/chamados");

    }

    return(

        <form action={handlerSalvar} className="w-full bg-slate-900/60 backdrop-blur border border-slate-800 rounded-2xl p-8 shadow-xl shadow-black/30 relative overflow-hidden">

            <div className="absolute top-0 left-0 right-0 h-px bg-gradient-to-r from-transparent via-blue-500/60 to-transparent" />

            <div className="flex items-center gap-2 mb-6">
                <div className="h-1.5 w-1.5 rounded-full bg-blue-500" />
                <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400">Dados do chamado</h2>
            </div>

            <div className="flex flex-col gap-5">
                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Título
                    </label>
                    <input name="titulo" 
                    value={chamado.titulo} 
                    required
                    onChange={(e) => handlerChange('titulo',e.target.value)}
                    placeholder="Digite o título do chamado" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                </div>

                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Descrição
                    </label>
                    <textarea name="descricao" 
                    value={chamado.descricao}
                    required
                    onChange={(e) => handlerChange('descricao',e.target.value)}
                    rows={4} placeholder="Descreva o problema ou solicitação" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30 resize-none"></textarea>
                </div>

                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Equipamento
                    </label>
                    <select name="equipamento"
                    value={chamado.equipamentoId ?? ""}
                    onChange={(e) => handlerEquipamento(e.target.value)}
                    className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30">
                        <option value="">Nenhum equipamento</option>
                        {equipamentosRecentes.length > 0 && (
                            <optgroup label="Usados recentemente">
                                {equipamentosRecentes.map((equipamento) => (
                                    <option key={equipamento.id} value={equipamento.id ?? ""}>
                                        {equipamento.equipamento} - {equipamento.tipo}
                                    </option>
                                ))}
                            </optgroup>
                        )}
                        <optgroup label="Todos os equipamentos">
                            {equipamentosOutros.map((equipamento) => (
                                <option key={equipamento.id} value={equipamento.id ?? ""}>
                                    {equipamento.equipamento} - {equipamento.tipo}
                                </option>
                            ))}
                        </optgroup>
                    </select>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            Prioridade
                        </label>
                        <input name="prioridade" 
                        value={chamado.prioridade}
                        required
                        onChange={(e) => handlerChange('prioridade',e.target.value)}
                        placeholder="Ex: Alta, Média, Baixa" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            Status
                        </label>
                        <input name="status" 
                        value={chamado.status}
                        required
                        onChange={(e) => handlerChange('status',e.target.value)}
                        placeholder="Ex: Aberto" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                </div>

                <div className="h-px bg-slate-800 my-1" />

                <div className="flex items-center justify-end gap-3">
                    <Link href="/chamados" className="text-sm font-medium text-slate-300 border border-slate-700 hover:border-slate-500 hover:text-white transition-colors px-4 py-2.5 rounded-lg">Cancelar</Link>
                    <button type="submit" className="text-sm font-semibold text-white bg-gradient-to-b from-blue-500 to-blue-700 hover:from-blue-400 hover:to-blue-600 transition-all shadow-lg shadow-blue-950/50 px-5 py-2.5 rounded-lg">Salvar</button>
                </div>

            </div>

        </form>

    );
}