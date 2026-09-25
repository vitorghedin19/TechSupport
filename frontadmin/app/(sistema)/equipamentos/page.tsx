"use client"

import { Equipamento } from "@/app/types/equipamento";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Equipamentos(){
    
    const [equipamentos,setEquipamentos] = useState<Equipamento[]>([])

    useEffect(() => {
        carregarDados();
    }, []);

    const carregarDados = async ()=>{

        try{
        const dados = await axios.get<Equipamento[]>("http://localhost:8080/equipamento")

        setEquipamentos (dados.data);
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

    const handlerDeletarEquipamento = async(equipamento:Equipamento) => {

        var dadosRetorno = await axios.delete('http://localhost:8080/equipamento/' +equipamento.id+'/excluir')

        if(dadosRetorno.status==200){
            alert("Equipamento excluído com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return;
        }

        carregarDados();

    }

    const handleAlterarStatusEquipamento = async(equipamento:Equipamento) =>{


        var novoStatus = {};
        if(equipamento.status ==="ATIVO"){
            novoStatus = {statusEquipamento:"EXCLUIDO"}
        }else{
            novoStatus = {statusEquipamento:"ATIVO"}
        }

        var dadosRetorno = await  
        axios.patch('http://localhost:8080/equipamento/'+equipamento.id+'/status',novoStatus);

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
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de equipamentos</h1>
            <Link href="/equipamentos/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition">Cadastrar Equipamento</Link>
        </div>

        <div className="max-w-5xl mx-auto rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Equipamento</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Tipo</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300 text-center">Ações</th>
                    </tr>
                </thead>
                <tbody className="divide-y divide-slate-800">
                   {equipamentos.map((equipamento)=>(

                    <tr key={equipamento.id} className="hover:bg-slate-800/40 transition-colors">
                        <td className="px-4 py-3 text-slate-100">{equipamento.id}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.equipamento}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.tipo}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.status}</td>
                        <td className="px-4 py-3">
                            <div className="flex items-center justify-center gap-4">
                                <Link href={`/equipamentos/${equipamento.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link>
                                <button onClick={()=> handlerDeletarEquipamento(equipamento)} className="text-sm font-medium transition-colors text-red-500 hover:text-red-400">Deletar</button>
                                <button onClick = {()=> handleAlterarStatusEquipamento(equipamento)}
                                       className= {`inline-flex items-center gap-1.5 font-medium transition-colors px-3 py-1 rounded-full border text-xs ${equipamento.status ==='EXCLUIDO'
                                         ?'text-red-400 border-red-500/40 bg-orange-500/10 hover:bg-red-500/20' 
                                         :'text-green-400 border-green-500/40 bg-green-500/10 hover:bg-green-500/20' }`
                                         }>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-3.5 h-3.5">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99" />
                                        </svg>
                                        {equipamento.status}</button>
                            </div>
                        </td>
                    </tr>
                    ))}

                    { equipamentos.length ===0 && 
                    (

                        <tr>
                            <td colSpan={5} className="px-6 py-12 text-center text-slate-300">
                                Nenhum equipamento encontrado!
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