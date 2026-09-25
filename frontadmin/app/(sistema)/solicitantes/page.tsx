"use client"

import { Solicitante } from "@/app/types/solicitante";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Solicitantes(){

    const [solicitantes,setSolicitantes] = useState<Solicitante[]>([])

    useEffect(() => {
        carregarDados();
    }, []);

    const carregarDados = async ()=>{

        try{
        const dados = await axios.get<Solicitante[]>("http://localhost:8080/solicitante")

        setSolicitantes (dados.data);
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

    const handlerDeletarSolicitante = async(solicitante:Solicitante) => {

        var dadosRetorno = await axios.delete('http://localhost:8080/solicitantes/' +solicitante.id+'/excluir')

        if(dadosRetorno.status==200){
            alert("Solicitante excluído com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return;
        }

        carregarDados();

    }

    const handleAlterarStatusSolicitante = async(solicitante:Solicitante) =>{


        var novoStatus = {};
        if(solicitante.status ==="ATIVO"){
            novoStatus = {statusSolicitante:"BLOQUEADO"}
        }else{
            novoStatus = {statusSolicitante:"ATIVO"}
        }

        var dadosRetorno = await  
        axios.patch('http://localhost:8080/solicitante/'+solicitante.id+'/status',novoStatus);

        if(dadosRetorno.status==200){
            alert("Atualizado status com sucesso!");
        }else{
            alert(dadosRetorno.data);

            return;
        }

        carregarDados();

    }

    return(
    
    <div className="min-h-screen bg-slate-950 px-6 py-8">
        <div className="max-w-5xl mx-auto flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de solicitantes</h1>
            <Link href="/solicitantes/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition">Cadastrar Solicitante</Link>
        </div>

        <div className="max-w-5xl mx-auto rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                <thead className="bg-slate-800/60">
                <tr>
                    <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                    <th className="px-4 py-3 text-sm font-medium text-slate-300">Nome</th>
                    <th className="px-4 py-3 text-sm font-medium text-slate-300">Email</th>
                    <th className="px-4 py-3 text-sm font-medium text-slate-300">Setor</th>
                    <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                    <th className="px-4 py-3 text-sm font-medium text-slate-300">Ações</th>
                </tr>
                </thead>
                
                <tbody className="divide-y divide-slate-800">

                    {solicitantes.map((solicitante)=>(

                    <tr key={solicitante.id} className="hover:bg-slate-800/40 transition-colors">
                        <td className="px-4 py-3 text-slate-100">{solicitante.id}</td>
                        <td className="px-4 py-3 text-slate-100">{solicitante.nome}</td>
                        <td className="px-4 py-3 text-slate-100">{solicitante.email}</td>
                        <td className="px-4 py-3 text-slate-100">{solicitante.setor}</td>
                        <td className="px-4 py-3 text-slate-100">{solicitante.status}</td>
                        <td className="px-4 py-3">
                            <div className="flex items-center justify-center gap-4">
                                <Link href={`/solicitantes/${solicitante.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link>
                                <button onClick={()=> handlerDeletarSolicitante(solicitante)} className="text-sm font-medium transition-colors text-red-500 hover:text-red-400">Deletar</button>
                                <button onClick = {()=> handleAlterarStatusSolicitante(solicitante)}
                                       className= {`inline-flex items-center gap-1.5 font-medium transition-colors px-3 py-1 rounded-full border text-xs ${solicitante.status ==='BLOQUEADO'
                                         ?'text-red-400 border-red-500/40 bg-red-500/10 hover:bg-red-500/20' 
                                         :'text-green-400 border-green-500/40 bg-green-500/10 hover:bg-green-500/20' }`
                                         }>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-3.5 h-3.5">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99" />
                                        </svg>
                                        {solicitante.status}</button>
                            </div>
                        </td>
                    </tr>
                    ))}

                    { solicitantes.length === 0 && 
                    (

                        <tr>
                            <td colSpan={6} className="px-6 py-12 text-center text-slate-300">
                                Nenhum solicitante encontrado!
                            </td>
                        </tr>

                    )
                    }

                </tbody>
            </table>
        </div>
    </div>
    
    )
}