"use client"


import { Chamado } from "@/app/types/chamado";
import axios from "axios";
import { useEffect, useState } from "react";

export default function Chamados(){

    const [chamados,setChamados] = useState<Chamado[]>([])

    useEffect(() => {
        carregarDados();
    }, []);

    const carregarDados = async ()=>{

        try{
        const dados = await axios.get<Chamado[]>("http://localhost:8080/chamado")

        setChamados (dados.data);
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

    return(

        <div className="min-h-screen bg-slate-950 px-6 py-10">
        <div className="flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de chamados</h1>
            <link href="/chamados/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition" />
        </div>

        <div className="rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                 <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Título</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Descrição</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Prioridade</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                    </tr>
                </thead>

                <tbody className="divide-y divide-slate-800">
                    {chamados.map((chamado)=>(

                    <tr key={chamado.id}>
                        <td className="px-4 py-3 text-slate-100">{chamado.id}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.titulo}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.descricao}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.prioridade}</td>
                        <td className="px-4 py-3 text-slate-100">{chamado.status}</td>
                    </tr>
                    ))}

                    { chamados.length ===0 && 
                    (

                        <tr>
                            <td colSpan={5} className="px-6 py-12 texte-center text-slate-300">
                                Nenhum chamado encontrado!
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