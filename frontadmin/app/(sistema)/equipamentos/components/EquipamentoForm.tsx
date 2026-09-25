'use client'
import { Equipamento, EquipamentoFormProps } from "@/app/types/equipamento";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function EquipamentoForm({equipamentoExistente}:EquipamentoFormProps){

    const router = useRouter();

    const [equipamento, setEquipamento] = useState<Equipamento>(
    equipamentoExistente ||
    new Equipamento(null, "", "", "ATIVO"));

    const handlerChange = ( campo: 'equipamento' | 'tipo' | 'status', valor:string) => {
        setEquipamento(valorAnterior => 
            new Equipamento(
                valorAnterior.id,
                campo === 'equipamento' ? valor : valorAnterior.equipamento,
                campo === 'tipo' ? valor : valorAnterior.tipo,
                campo === 'status' ? valor : valorAnterior.status
            )
        )
    }

    const handlerSalvar = async (formData : FormData) => {

        if(equipamentoExistente){

             var dadosRetorno = await axios.put<number>('http://localhost:8080/equipamento/'+equipamento.id,equipamento)

        if(dadosRetorno.status==200){
            alert("Equipamento foi salvo com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return;
        }

        }else{
        var dadosRetorno = await axios.post<number>('http://localhost:8080/equipamento',equipamento)

        if(dadosRetorno.status==200){
            alert("Equipamento foi salvo com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return;
        }
    }
        router.push("/equipamentos");

    }

    return(

        <form action={handlerSalvar} className="w-full bg-slate-900/60 backdrop-blur border border-slate-800 rounded-2xl p-8 shadow-xl shadow-black/30 relative overflow-hidden">

            <div className="absolute top-0 left-0 right-0 h-px bg-gradient-to-r from-transparent via-blue-500/60 to-transparent" />

            <div className="flex items-center gap-2 mb-6">
                <div className="h-1.5 w-1.5 rounded-full bg-blue-500" />
                <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400">Dados do equipamento</h2>
            </div>

            <div className="flex flex-col gap-5">
                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Nome do Equipamento
                    </label>
                    <input name="equipamento" 
                    value={equipamento.equipamento} 
                    required
                    onChange={(e) => handlerChange('equipamento',e.target.value)}
                    placeholder="Digite o nome do equipamento" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            Tipo
                        </label>
                        <input name="tipo" 
                        value={equipamento.tipo}
                        required
                        onChange={(e) => handlerChange('tipo',e.target.value)}
                        placeholder="Ex: Notebook, Monitor" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            Status
                        </label>
                        <input name="status"
                        value={equipamento.status}
                        required
                        onChange={(e) => handlerChange('status',e.target.value)}
                        placeholder="Ex: Disponível" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                </div>

                <div className="h-px bg-slate-800 my-1" />

                <div className="flex items-center justify-end gap-3">
                    <Link href="/equipamentos" className="text-sm font-medium text-slate-300 border border-slate-700 hover:border-slate-500 hover:text-white transition-colors px-4 py-2.5 rounded-lg">Cancelar</Link>
                    <button type="submit" className="text-sm font-semibold text-white bg-gradient-to-b from-blue-500 to-blue-700 hover:from-blue-400 hover:to-blue-600 transition-all shadow-lg shadow-blue-950/50 px-5 py-2.5 rounded-lg">Salvar</button>
                </div>

            </div>

        </form>

    );
}