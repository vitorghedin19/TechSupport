"use client"

import { Usuario } from "@/app/types/usuario";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Usuarios(){

    const [usuarios,setUsuarios] = useState<Usuario[]>([])

    useEffect(() => {
        carregarDados();
    }, []);

    const carregarDados = async ()=>{

        try{
        const dados = await axios.get<Usuario[]>("http://localhost:8080/usuarios")

        setUsuarios (dados.data);
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

    return(
    
    <div className="min-h-screen bg-slate-950 px-6 py-8">
        <div className="max-w-5xl mx-auto flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de usuários</h1>
            <Link href="/usuarios/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition">Cadastrar Usuário</Link>
        </div>

        <div className="max-w-5xl mx-auto rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Nome</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">CPF</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">E-mail</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Ações</th>
                    </tr>
                </thead>
                
                <tbody className="divide-y divide-slate-800">

                    {usuarios.map((usuario)=>(

                    <tr key={usuario.id} className="hover:bg-slate-800/40 transition-colors">
                        <td className="px-4 py-3 text-slate-100">{usuario.id}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.nome}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.cpf}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.email}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.status}</td>
                        <td className="px-4 py-3 text-slate-100"><Link href={`/usuarios/${usuario.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link></td>
                    </tr>
                    ))}

                    { usuarios.length ===0 && 
                    (

                        <tr>
                            <td colSpan={5} className="px-6 py-12 text-center text-slate-300">
                                Nenhum usuário encontrado!
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