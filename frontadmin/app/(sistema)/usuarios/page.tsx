"use client"
// "use client": obrigatório porque esse componente usa hooks (useState/useEffect) e
// interatividade (onClick) — coisas que só existem no navegador, não durante a
// renderização feita no servidor pelo Next.js.

import { Usuario } from "@/app/types/usuario";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

// Usuarios: tela de LISTAGEM (o "R" de Read do CRUD). Mostra a tabela com todos os
// usuários cadastrados e as ações de editar/deletar/alterar status de cada um.
export default function Usuarios(){

    // useState: hook do React que guarda um valor que, quando muda (via setUsuarios),
    // faz o componente renderizar de novo automaticamente refletindo o novo valor.
    // Aqui guardamos a LISTA de usuários vinda do banco; começa vazia ([]) até a API responder.
    const [usuarios,setUsuarios] = useState<Usuario[]>([])

    // useEffect: hook que roda um código depois que o componente aparece na tela.
    // O array [] no final (chamado "array de dependências") significa "rode isso só UMA
    // VEZ, quando o componente for montado" — é assim que a lista é buscada automaticamente
    // ao abrir a página, sem precisar de nenhum clique do usuário.
    useEffect(() => {
        carregarDados();
    }, []);

    // Busca a lista completa de usuários na API (GET) e guarda no estado.
    const carregarDados = async ()=>{

        try{
        // axios.get<Usuario[]>: faz a requisição HTTP e já avisa o TypeScript que a
        // resposta esperada é um array de Usuario (tipagem, não validação em runtime).
        const dados = await axios.get<Usuario[]>("http://localhost:8080/usuarios")

        setUsuarios (dados.data); // dados.data é o corpo (JSON) da resposta; isso atualiza o estado e re-renderiza a tabela.
    
    } catch (error){
        alert("Erro ao carregar dados!")
    }
    }

    // Exclusão lógica: chama DELETE na API (que, no backend, só troca o status pra
    // EXCLUIDO — não apaga a linha do banco de verdade). Depois de excluir, recarrega a
    // lista pra tela refletir a mudança.
    const handlerDeletarUsuario = async(usuario:Usuario) => {

        var dadosRetorno = await axios.delete('http://localhost:8080/usuarios/' +usuario.id+'/excluir')

        if(dadosRetorno.status==200){
            alert("Usuário excluído com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return;
        }

        carregarDados();

    }

    // Alterna o status entre ATIVO e BLOQUEADO (toggle simples de 2 estados). Monta um
    // objeto {statusUsuario: "..."} — formato que o DTO do backend espera receber no PATCH.
    const handleAlterarStatusUsuario = async(usuario:Usuario) =>{


        var novoStatus = {};
        if(usuario.status ==="ATIVO"){
            novoStatus = {statusUsuario:"BLOQUEADO"}
        }else{
            novoStatus = {statusUsuario:"ATIVO"}
        }

        // PATCH = atualização PARCIAL: muda só o campo status, sem reenviar
        // nome/cpf/email/senha (diferente do PUT, que manda o objeto inteiro).
        var dadosRetorno = await  
        axios.patch('http://localhost:8080/usuarios/'+usuario.id+'/status',novoStatus);

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
