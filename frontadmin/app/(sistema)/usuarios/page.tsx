"use client"

import { Usuario } from "@/app/types/usuario";
import axios from "axios";
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
    
    <div className="min-h-screen bg-slate-950 px-6 py-10">
        <div className="flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de usuários</h1>
            <link href="/usuarios/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition" />
        </div>

        <div className="rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Código</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Nome</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">CPF</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">E-mail</th>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Status</th>
                    </tr>
                </thead>
                
                <tbody className="divide-y divide-slate-800">

                    {usuarios.map((usuario)=>(

                    <tr key={usuario.id}>
                        <td className="px-4 py-3 text-slate-100">{usuario.id}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.nome}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.cpf}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.email}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.status}</td>
                    </tr>
                    ))}

                    { usuarios.length ===0 && 
                    (

                        <tr>
                            <td colSpan={5} className="px-6 py-12 texte-center text-slate-300">
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