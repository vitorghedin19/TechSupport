"use client"

import { atualizarStatusChamado, buscarListaChamados, deletarChamado, resolverChamado } from "@/app/services/chamadoService";
import { Chamado } from "@/app/types/chamado";
import { usePerfil } from "@/app/hooks/usePerfil";
import Link from "next/link";
import { useEffect, useState } from "react";
import ResolverChamadoModal from "../components/ResolverChamadoModal";
import VerSolucaoModal from "../components/VerSolucaoModal";

export default function Chamados(){

   
    const [chamados,setChamados] = useState<Chamado[]>([])
    const [chamadoParaResolver, setChamadoParaResolver] = useState<Chamado | null>(null)
    const [chamadoParaVerSolucao, setChamadoParaVerSolucao] = useState<Chamado | null>(null)

    const { ehAdmin, ehSolicitante } = usePerfil();

    useEffect(() => {
        carregarDados();
    }, []);

    const carregarDados = async ()=>{

        try{
        const dados = await buscarListaChamados();

        setChamados (dados);
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

 
    const handlerDeletarChamado = async(chamado:Chamado) => {

        var dadosRetorno = await deletarChamado(chamado.id || 0);

        if(dadosRetorno==200){
            alert("Chamado excluído com sucesso");
            
        }else{
            alert("Erro ao excluir chamado");
            return;
        }

        carregarDados();

    }


    const handleAlterarStatusChamado = async(chamado:Chamado) =>{

        await atualizarStatusChamado(chamado);

        carregarDados();

    }

    const handleResolverChamado = async(solucao: string) => {

        if (!chamadoParaResolver) {
            return;
        }

        try{
            await resolverChamado(chamadoParaResolver.id || 0, solucao);
            setChamadoParaResolver(null);
            carregarDados();
        } catch (error){
            alert("Erro ao resolver chamado!");
        }

    }

    return(

        <div className="min-h-screen bg-slate-950 px-6 py-8">
        <div className="max-w-5xl mx-auto flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de chamados</h1>
            {!ehAdmin && (
                <Link href="/chamados/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition">Cadastrar Chamado</Link>
            )}
        </div>

        <div className="max-w-5xl mx-auto rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                 <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Título</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Descrição</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Equipamento</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Prioridade</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300 text-center">Ações</th>
                    </tr>
                </thead>

                <tbody className="divide-y divide-slate-800">
                    {/* '.map()' transforma cada chamado em uma <tr>. key={chamado.id} é obrigatório em listas do React. */}
                    {chamados.map((chamado)=>(

                    <tr key={chamado.id} className="hover:bg-slate-800/40 transition-colors">
                        <td className="px-4 py-3 text-slate-100">{chamado.id}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.titulo}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.descricao}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.equipamentoNome || '-'}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.prioridade}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.status}</td>
                        <td className="px-4 py-3">
                            <div className="flex items-center justify-center gap-4">
                                {/* Link DINÂMICO: o id vai na URL e abre app/(sistema)/chamados/[codigo]/editar. */}
                                {!ehAdmin && (
                                    <Link href={`/chamados/${chamado.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link>
                                )}
                                {/* Ícone de documento: só aparece quando o chamado está RESOLVIDO e tem solução registrada. Qualquer perfil pode ver. */}
                                {chamado.status === 'RESOLVIDO' && chamado.solucao && (
                                    <button onClick={()=> setChamadoParaVerSolucao(chamado)} title="Ver solução" aria-label="Ver solução" className="text-slate-400 hover:text-green-400 transition-colors">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.8" stroke="currentColor" className="w-5 h-5">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 14.25v-2.625a3.375 3.375 0 0 0-3.375-3.375h-1.5A1.125 1.125 0 0 1 13.5 7.125v-1.5a3.375 3.375 0 0 0-3.375-3.375H8.25m0 12.75h7.5m-7.5 3H12M10.5 2.25H5.625c-.621 0-1.125.504-1.125 1.125v17.25c0 .621.504 1.125 1.125 1.125h12.75c.621 0 1.125-.504 1.125-1.125V11.25a9 9 0 0 0-9-9Z" />
                                        </svg>
                                    </button>
                                )}
                                {/* Só aparece para o técnico e enquanto o chamado ainda não foi resolvido nem excluído. Abre o popup da solução. */}
                                {!ehSolicitante && !ehAdmin && chamado.status !== 'RESOLVIDO' && chamado.status !== 'EXCLUIDO' && (
                                    <button onClick={()=> setChamadoParaResolver(chamado)} className="text-sm font-medium transition-colors text-green-500 hover:text-green-400">Resolver</button>
                                )}
                                {!ehAdmin && (
                                    <button onClick={()=> handlerDeletarChamado(chamado)} className="text-sm font-medium transition-colors text-red-500 hover:text-red-400">Deletar</button>
                                )}
                                {/* A cor do botão depende do status (ternários encadeados = if / else if / else): verde=RESOLVIDO, cinza=FECHADO, roxo=EM_ANDAMENTO, vermelho=EXCLUIDO, azul=demais (ABERTO). */}
                                {!ehAdmin && (
                                <button onClick = {()=> handleAlterarStatusChamado(chamado)}
                                       className= {`inline-flex items-center gap-1.5 font-medium transition-colors px-3 py-1 rounded-full border text-xs ${
                                         chamado.status ==='RESOLVIDO'?'text-green-400 border-green-500/40 bg-green-500/10 hover:bg-green-500/20'
                                         :chamado.status ==='FECHADO'?'text-slate-400 border-slate-500/40 bg-slate-500/10 hover:bg-slate-500/20'
                                         :chamado.status ==='EM_ANDAMENTO'?'text-purple-400 border-purple-500/40 bg-purple-500/10 hover:bg-purple-500/20'
                                         :chamado.status ==='EXCLUIDO'?'text-red-400 border-red-500/40 bg-red-500/10 hover:bg-red-500/20'
                                         :'text-blue-400 border-blue-500/40 bg-blue-500/10 hover:bg-blue-500/20' }`
                                         }>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-3.5 h-3.5">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99" />
                                        </svg>
                                        {chamado.status}</button>
                                )}
                            </div>
                        </td>
                    </tr>
                    ))}

                    {/* Estado vazio: linha de aviso quando não há chamados. colSpan={7} = as 7 colunas. */}
                    { chamados.length ===0 && 
                    (

                        <tr>
                            <td colSpan={7} className="px-6 py-12 text-center text-slate-300">
                                Nenhum chamado encontrado!
                            </td>
                        </tr>

                    )
                    }
                </tbody>
            </table>
        </div>

        {/* Os popups só são montados quando existe um chamado escolhido; assim o campo de texto do resolver começa vazio a cada abertura. */}
        {chamadoParaResolver && (
            <ResolverChamadoModal
                chamado={chamadoParaResolver}
                aoCancelar={() => setChamadoParaResolver(null)}
                aoConfirmar={handleResolverChamado}
            />
        )}

        {chamadoParaVerSolucao && (
            <VerSolucaoModal
                chamado={chamadoParaVerSolucao}
                aoFechar={() => setChamadoParaVerSolucao(null)}
            />
        )}
    </div>

    )
}