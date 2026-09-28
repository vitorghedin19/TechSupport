"use client"
// "use client": obrigatório aqui porque usamos hooks (useParams, useRouter, useState,
// useEffect) — todos só existem no navegador.

import Link from "next/link";
import UsuarioForm from "../../components/UsuarioForm";
import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { Usuario } from "@/app/types/usuario";
import axios from "axios";


// EditarUsuario: tela de EDIÇÃO (o "U" de Update). Diferente da tela de criação, aqui
// precisamos primeiro BUSCAR os dados do usuário específico antes de mostrar o formulário.
export default function EditarUsuario(){

    // useParams: hook que lê os parâmetros dinâmicos da URL atual. Como essa página está
    // na pasta [codigo], o Next injeta o valor da URL nessa chave. Ex: acessando
    // /usuarios/3/editar, parametro = { codigo: "3" } (sempre como STRING, porque URL é texto).
    const parametro = useParams();

    // Number(...) converte a string "3" pro tipo number, já que o id do Usuario é
    // number | null, e vamos usar esse valor pra montar a URL da API e comparar com
    // o id que vem do banco.
    const codigo = Number(parametro.codigo);

    // Guarda o usuário buscado da API. Começa null (ainda não carregado);
    // usuario|null no tipo genérico permite tratar o estado de "carregando" (linha abaixo).
    const [usuario, setUsuario] = useState<Usuario|null>(null);


    // useRouter: permite navegar programaticamente (ex: redirecionar se o usuário não existir).
    const router = useRouter();

    // useEffect com [] no final: roda buscarDados() UMA VEZ, assim que a página é montada —
    // é assim que os dados são carregados automaticamente ao abrir a tela de edição.
    useEffect(()=>{

        buscarDados();

    },[])

    // Busca o usuário específico pelo id (codigo) direto na API.
    const buscarDados = async() => {

        const valorUsuarioBack = await axios.get<Usuario>('http://localhost:8080/usuarios/'+codigo)

        if(valorUsuarioBack.status==200){
            setUsuario(valorUsuarioBack.data);
        }else{
        router.push("/usuarios")} // se não encontrar, manda de volta pra listagem

    }

    // Enquanto usuario ainda for null (a requisição não terminou), mostra um texto de
    // carregando em vez do formulário — evita tentar renderizar o form com dados vazios
    // por uma fração de segundo (e evita erro, já que UsuarioForm espera usuarioExistente
    // preenchido quando fornecido).
    if(!usuario) return(<div className="p-8">Carregando Dados ...</div>)

    return(

        <div className="min-h-screen bg-slate-950 bg-[radial-gradient(ellipse_80%_50%_at_50%_-20%,rgba(37,99,235,0.15),rgba(2,6,23,0))] px-6 py-10">

            <div className="max-w-2xl mx-auto flex flex-col gap-8">

                <div className="flex flex-col gap-4">
                    <Link href="/usuarios" className="group inline-flex items-center gap-1.5 text-sm font-medium text-slate-400 hover:text-blue-400 transition-colors w-fit">
                        <span aria-hidden="true" className="transition-transform group-hover:-translate-x-0.5">&larr;</span> Voltar
                    </Link>
                    <div className="flex items-center gap-4">
                        <div className="flex h-12 w-12 shrink-0 items-center justify-center rounded-xl bg-gradient-to-br from-blue-500 to-blue-800 shadow-lg shadow-blue-950/50 text-white text-xl font-bold">
                            &#9998;
                        </div>
                        <div>
                            <h1 className="text-3xl font-bold text-white tracking-tight">Editar Usuário {codigo}</h1>
                            <p className="text-sm text-slate-400 mt-0.5">Preencha os dados para editar o usuário</p>
                        </div>
                    </div>
                </div>

                {/* Passa o usuário já carregado pro form via prop usuarioExistente.
                    É essa prop que faz o UsuarioForm saber que está em modo EDIÇÃO
                    (usa PUT em vez de POST) e pré-preencher os campos. */}
                <div className="w-full">
                    <UsuarioForm usuarioExistente={usuario}/>
                </div>

            </div>

        </div>

    );
}
