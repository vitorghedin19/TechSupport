"use client"

import { Chamado } from "@/app/types/chamado";

interface VerSolucaoModalProps {
    chamado: Chamado
    aoFechar: () => void
}

export default function VerSolucaoModal({ chamado, aoFechar }: VerSolucaoModalProps) {

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 px-4">
            <div className="w-full max-w-md rounded-xl border border-slate-800 bg-slate-900 p-6 shadow-xl">

                <h2 className="text-lg font-semibold text-slate-100">Solução do chamado</h2>
                <p className="mt-1 text-sm text-slate-400">
                    #{chamado.id} - {chamado.titulo}
                </p>

                <div className="mt-5 rounded-lg bg-slate-800 border border-slate-700 px-3 py-3 text-slate-100 whitespace-pre-wrap">
                    {chamado.solucao}
                </div>

                <div className="mt-6 flex items-center justify-end">
                    <button
                        onClick={aoFechar}
                        className="rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-sm font-medium px-4 py-2 transition"
                    >
                        Fechar
                    </button>
                </div>

            </div>
        </div>
    );
}