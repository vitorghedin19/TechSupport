"use client"

import Link from "next/link";
import SolicitanteForm from "../../components/SolicitanteForm";
import { useParams, useRouter } from "next/navigation";
import { Solicitante } from "@/app/types/solicitante";
import { useEffect, useState } from "react";
import axios from "axios";


// EditarSolicitante: tela de EDIÇÃO (Update). Precisa primeiro BUSCAR o registro específico na API antes de mostrar o formulário. "use client" (1ª linha) é obrigatório: usa hooks (useParams, useRouter, useState, useEffect).
export default function EditarSolicitante(){

    // useParams: lê o parâmetro dinâmico da URL. Como a pasta se chama [codigo], em /solicitantes/3/editar o retorno é { codigo: "3" } — sempre STRING, pois URL é texto.
    const parametro = useParams();

    // Number(...) converte "3" (string) em 3 (number), para usar na URL da API e bater com o tipo do id (number | null) da classe.
    const codigo = Number(parametro.codigo);

    // Estado com o registro buscado. Começa null (ainda não carregou). O tipo Solicitante|null permite tratar o estado de 'carregando'.
    const [solicitante, setSolicitante] = useState<Solicitante|null>(null);


    // useRouter: navegação por código (usado para voltar à listagem caso o registro não seja encontrado).
    const router = useRouter();

    // useEffect com [] roda buscarDados() UMA vez, ao abrir a tela — carrega o registro a ser editado.
    useEffect(()=>{

        buscarDados();

    },[])

    // GET /solicitante/{codigo}: busca só esse registro. Se 200, guarda no estado; senão volta para a listagem.
    const buscarDados = async() => {

        const valorSolicitanteBack = await axios.get<Solicitante>('http://localhost:8080/solicitante/'+codigo)

        if(valorSolicitanteBack.status==200){
            setSolicitante(valorSolicitanteBack.data);
        }else{
        router.push("/solicitantes")}

    }

    // Enquanto o registro não chegou (null), mostra 'Carregando'. Evita renderizar o form vazio e garante que a prop Existente já chegue preenchida.
    if(!solicitante) return(<div className="p-8">Carregando Dados ...</div>)

    return(

        <div className="min-h-screen bg-slate-950 bg-[radial-gradient(ellipse_80%_50%_at_50%_-20%,rgba(37,99,235,0.15),rgba(2,6,23,0))] px-6 py-10">

            <div className="max-w-2xl mx-auto flex flex-col gap-8">

                <div className="flex flex-col gap-4">
                    <Link href="/solicitantes" className="group inline-flex items-center gap-1.5 text-sm font-medium text-slate-400 hover:text-blue-400 transition-colors w-fit">
                        <span aria-hidden="true" className="transition-transform group-hover:-translate-x-0.5">&larr;</span> Voltar
                    </Link>
                    <div className="flex items-center gap-4">
                        <div className="flex h-12 w-12 shrink-0 items-center justify-center rounded-xl bg-gradient-to-br from-blue-500 to-blue-800 shadow-lg shadow-blue-950/50 text-white text-xl font-bold">
                            &#9998;
                        </div>
                        <div>
                            <h1 className="text-3xl font-bold text-white tracking-tight">Editar Solicitante {codigo}</h1>
                            <p className="text-sm text-slate-400 mt-0.5">Preencha os dados para editar o solicitante</p>
                        </div>
                    </div>
                </div>

                <div className="w-full">
                    {/* Passa o registro carregado via prop solicitanteExistente: isso coloca o form em modo EDIÇÃO (usa PUT e vem pré-preenchido). */}
                    <SolicitanteForm solicitanteExistente={solicitante}/>
                </div>

            </div>

        </div>

    );
}