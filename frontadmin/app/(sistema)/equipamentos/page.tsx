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
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Ações</th>
                    </tr>
                </thead>
                <tbody className="divide-y divide-slate-800">
                   {equipamentos.map((equipamento)=>(

                    <tr key={equipamento.id} className="hover:bg-slate-800/40 transition-colors">
                        <td className="px-4 py-3 text-slate-100">{equipamento.id}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.equipamento}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.tipo}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.status}</td>
                        <td className="px-4 py-3 text-slate-100"><Link href={`/equipamentos/${equipamento.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link></td>
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