"use client"

import { atualizarStatusUsuario, buscarListaUsuarios, deletarUsuario } from "@/app/services/usuarioService";
import { Usuario } from "@/app/types/usuario";
import Link from "next/link";
import { useEffect, useState } from "react";


export default function Usuarios(){

   
    const [usuarios,setUsuarios] = useState<Usuario[]>([])

    useEffect(() => {
        carregarDados();
    }, []);

 
    const carregarDados = async ()=>{

        try{
        
        const dados = await buscarListaUsuarios();

        setUsuarios (dados); 
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

    const handlerDeletarUsuario = async(usuario:Usuario) => {

        var dadosRetorno = await deletarUsuario(usuario.id || 0);

        if(dadosRetorno==200){
            alert("Usuário excluído com sucesso");
            
        }else{
            alert("Erro ao excluir usuário");
            return;
        }

        carregarDados();

    }

    const handleAlterarStatusUsuario = async(usuario:Usuario) =>{

        await atualizarStatusUsuario(usuario);
        
        carregarDados();

    }

    return(
    
    <div className="min-h-screen bg-slate-950 px-6 py-8">
        <div className="max-w-5xl mx-auto flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de usuários</h1>
            {/* Link para a rota de criação: app/(sistema)/usuarios/novo/page.tsx */}
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
                        <th className="px-4 py-3 text-sm font-medium text-slate-300 text-center">Ações</th>
                    </tr>
                </thead>
                
                <tbody className="divide-y divide-slate-800">

                    {/* .map() percorre o array "usuarios" e transforma CADA item numa <tr>.
                        key={usuario.id} é obrigatório no React ao renderizar listas: ajuda
                        o React a identificar qual linha é qual entre uma renderização e outra
                        (evita bugs visuais e melhora performance). */}
                    {usuarios.map((usuario)=>(

                    <tr key={usuario.id} className="hover:bg-slate-800/40 transition-colors">
                        <td className="px-4 py-3 text-slate-100">{usuario.id}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.nome}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.cpf}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.email}</td>
                        <td className="px-4 py-3 text-slate-100">{usuario.status}</td>
                        <td className="px-4 py-3">
                            <div className="flex items-center justify-center gap-4">
                                {/* Link DINÂMICO: o id do usuário é injetado na URL via template
                                    string (crase). Isso navega para
                                    app/(sistema)/usuarios/[codigo]/editar/page.tsx, onde
                                    [codigo] recebe exatamente esse valor. */}
                                <Link href={`/usuarios/${usuario.id}/editar`} className="text-sm font-medium text-blue-500 hover:text-blue-400 transition-colors">Editar</Link>
                                <button onClick={()=> handlerDeletarUsuario(usuario)} className="text-sm font-medium transition-colors text-red-500 hover:text-red-400">Deletar</button>
                                {/* Botão de status: a cor muda dinamicamente de acordo com o
                                    status atual (verde=ativo, laranja=bloqueado, vermelho=excluído) */}
                                <button onClick = {()=> handleAlterarStatusUsuario(usuario)}
                                       className= {`inline-flex items-center gap-1.5 font-medium transition-colors px-3 py-1 rounded-full border text-xs ${
                                        usuario.status ==='BLOQUEADO'?'text-orange-400 border-orange-500/40 bg-orange-500/10 hover:bg-orange-500/20': 
                                        usuario.status ==='EXCLUIDO'?'text-red-400 border-red-500/40 bg-red-500/10 hover:bg-red-500/20' 
                                         :'text-green-400 border-green-500/40 bg-green-500/10 hover:bg-green-500/20' }`
                                         }>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-3.5 h-3.5">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99" />
                                        </svg>
                                        {usuario.status}</button>
                            </div>
                        </td>
                    </tr>
                    ))}

                    {/* Estado vazio: se a lista tiver 0 itens, mostra uma linha única de aviso
                        em vez da tabela ficar em branco sem explicação. colSpan={6} faz essa
                        célula ocupar a largura das 6 colunas do cabeçalho, ficando centralizada. */}
                    { usuarios.length ===0 && 
                    (

                        <tr>
                            <td colSpan={6} className="px-6 py-12 text-center text-slate-300">
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
