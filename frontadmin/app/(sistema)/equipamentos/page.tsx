"use client"

import { Equipamento } from "@/app/types/equipamento";
import axios from "axios";
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
    
        <div className="min-h-screen bg-slate-950 px-6 py-10">
        <div className="flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de equipamentos</h1>
            <link href="/equipamentos/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition" />
        </div>

        <div className="rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Equipamento</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Tipo</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                    </tr>
                </thead>
                <tbody className="divide-y divide-slate-800">
                   {equipamentos.map((equipamento)=>(

                    <tr key={equipamento.id}>
                        <td className="px-4 py-3 text-slate-100">{equipamento.id}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.equipamento}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.tipo}</td>
                        <td className="px-4 py-3 text-slate-100">{equipamento.status}</td>
                    </tr>
                    ))}

                    { equipamentos.length ===0 && 
                    (

                        <tr>
                            <td colSpan={5} className="px-6 py-12 texte-center text-slate-300">
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