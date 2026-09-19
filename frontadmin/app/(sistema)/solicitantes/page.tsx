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
                        <td className="px-4 py-3 text-slate-100"><Link href={`/solicitantes/${solicitante.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link></td>
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