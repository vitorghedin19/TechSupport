"use client"

import { useState } from "react";
import { Chamado } from "@/app/types/chamado";

interface ResolverChamadoModalProps {
    chamado: Chamado
    aoCancelar: () => void
    aoConfirmar: (solucao: string) => Promise<void>
}

export default function ResolverChamadoModal({ chamado, aoCancelar, aoConfirmar }: ResolverChamadoModalProps) {

    const [solucao, setSolucao] = useState("");
    const [enviando, setEnviando] = useState(false);

    const solucaoVazia = solucao.trim() === "";

    const handleConfirmar = async () => {
        setEnviando(true);
        await aoConfirmar(solucao.trim());
        setEnviando(false);
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 px-4">
            <div className="w-full max-w-md rounded-xl border border-slate-800 bg-slate-900 p-6 shadow-xl">

                <h2 className="text-lg font-semibold text-slate-100">Resolver chamado</h2>
                <p className="mt-1 text-sm text-slate-400">
                    #{chamado.id} - {chamado.titulo}
                </p>

                <div className="mt-5 flex flex-col gap-1.5">
                    <label className="text-sm font-medium text-slate-300">Solução adotada</label>
                    <textarea
                        rows={4}
                        value={solucao}
                        onChange={(e) => setSolucao(e.target.value)}
                        placeholder="Descreva o que foi feito para resolver o chamado"
                        className="w-full rounded-lg bg-slate-800 border border-slate-700 px-3 py-2 text-slate-100 placeholder-slate-500 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30 transition"
                    />
                </div>

                <div className="mt-6 flex items-center justify-end gap-3">
                    <button
                        onClick={aoCancelar}
                        disabled={enviando}
                        className="rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-sm font-medium px-4 py-2 transition disabled:opacity-50"
                    >
                        Cancelar
                    </button>
                    <button
                        onClick={handleConfirmar}
                        disabled={solucaoVazia || enviando}
                        className="rounded-lg bg-green-600 hover:bg-green-500 text-white text-sm font-medium px-4 py-2 transition disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                        Resolver
                    </button>
                </div>

            </div>
        </div>
    );
}