"use client"

import Link from "next/link";
import ChamadoForm from "../../components/ChamadoForm";
import { useParams } from "next/navigation";


export default function EditarChamado(){

    const parametro = useParams();

    const codigo = Number(parametro.codigo);

    return(

        <div className="min-h-screen bg-slate-950 bg-[radial-gradient(ellipse_80%_50%_at_50%_-20%,rgba(37,99,235,0.15),rgba(2,6,23,0))] px-6 py-10">

            <div className="max-w-2xl mx-auto flex flex-col gap-8">

                <div className="flex flex-col gap-4">
                    <Link href="/chamados" className="group inline-flex items-center gap-1.5 text-sm font-medium text-slate-400 hover:text-blue-400 transition-colors w-fit">
                        <span aria-hidden="true" className="transition-transform group-hover:-translate-x-0.5">&larr;</span> Voltar
                    </Link>
                    <div className="flex items-center gap-4">
                        <div className="flex h-12 w-12 shrink-0 items-center justify-center rounded-xl bg-gradient-to-br from-blue-500 to-blue-800 shadow-lg shadow-blue-950/50 text-white text-xl font-bold">
                            &#9998;
                        </div>
                        <div>
                            <h1 className="text-3xl font-bold text-white tracking-tight">Editar Chamado {codigo}</h1>
                            <p className="text-sm text-slate-400 mt-0.5">Preencha os dados para editar o chamado</p>
                        </div>
                    </div>
                </div>

                <div className="w-full">
                    <ChamadoForm />
                </div>

            </div>

        </div>

    );
}