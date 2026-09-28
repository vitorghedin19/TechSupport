'use client'
// 'use client' (linha acima): necessário — o componente usa hooks (useState, useRouter) e onChange.
// SolicitanteFormProps define a prop opcional solicitanteExistente (veja app/types).
import { Solicitante, SolicitanteFormProps } from "@/app/types/solicitante";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";

// SolicitanteForm: formulário ÚNICO para CRIAR e EDITAR. Com solicitanteExistente preenchido = EDIÇÃO (PUT); sem = CRIAÇÃO (POST). Mesmo padrão do UsuarioForm.
export default function SolicitanteForm({solicitanteExistente}:SolicitanteFormProps){

     // useRouter: usado no fim do handlerSalvar para voltar à listagem após salvar.
     const router = useRouter();

    // Estado do formulário. Valor inicial: solicitanteExistente (edição) OU new Solicitante(...) vazio (criação); o || escolhe o primeiro que existir.
    // Ordem do construtor: id, nome, email, setor, status (id null = ainda não existe no banco).
    const [solicitante, setSolicitante] = useState<Solicitante>(
    solicitanteExistente ||
    new Solicitante(null, "", "", "", "ATIVO")); /*Solicitante vem com valores zerados, para receber o que vira do forms*/

    // Chamado a cada tecla (onChange): cria um NOVO Solicitante copiando os valores anteriores e trocando só o campo alterado (a classe é tratada como imutável).
    const handlerChange = ( campo: 'nome' | 'email' | 'setor', valor:string) => {
        setSolicitante(valorAnterior => 
            new Solicitante(
                valorAnterior.id,
                campo === 'nome' ? valor : valorAnterior.nome,
                campo === 'email' ? valor : valorAnterior.email,
                campo === 'setor' ? valor : valorAnterior.setor,
                valorAnterior.status,
            )
        )
    }

    // Executa ao enviar o form (action={handlerSalvar}). solicitanteExistente ? PUT /solicitante/{id} (atualiza) : POST /solicitante (cria). Status 200 = alerta de sucesso; caso contrário, return interrompe antes do router.push.
    const handlerSalvar = async (formData : FormData) => {

        if(solicitanteExistente){

             var dadosRetorno = await axios.put<number>('http://localhost:8080/solicitante/'+solicitante.id,solicitante) /*chamando a API de usuario e aplicando o valor de usuario que recebemos*/

        if(dadosRetorno.status==200){
            alert("Solicitante foi salvo com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return; /*parar de executar caso de erro para não chegar no router.push*/
        }

        }else{
        var dadosRetorno = await axios.post<number>('http://localhost:8080/solicitante',solicitante) /*chamando a API de usuario e aplicando o valor de usuario que recebemos*/

        if(dadosRetorno.status==200){
            alert("Solicitante foi salvo com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return; /*parar de executar caso de erro para não chegar no router.push*/
        }
    }
        router.push("/solicitantes");

    }

    // JSX do formulário. Inputs 'controlados': value vem do estado, onChange atualiza o estado. required = validação nativa do navegador.
    return(

        <form action={handlerSalvar} className="w-full bg-slate-900/60 backdrop-blur border border-slate-800 rounded-2xl p-8 shadow-xl shadow-black/30 relative overflow-hidden">

            <div className="absolute top-0 left-0 right-0 h-px bg-gradient-to-r from-transparent via-blue-500/60 to-transparent" />

            <div className="flex items-center gap-2 mb-6">
                <div className="h-1.5 w-1.5 rounded-full bg-blue-500" />
                <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400">Dados do solicitante</h2>
            </div>

            <div className="flex flex-col gap-5">
                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Nome Completo
                    </label>
                    <input name="nome" 
                    value={solicitante.nome}
                    required
                    onChange={(e) => handlerChange('nome',e.target.value)}
                    placeholder="Digite o nome completo" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            E-mail
                        </label>
                        <input name="email" 
                        value={solicitante.email}
                        required
                        onChange={(e) => handlerChange('email',e.target.value)}
                        placeholder="usuario@email.com" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            Setor
                        </label>
                        <input name="setor" 
                        value={solicitante.setor}
                        required
                        onChange={(e) => handlerChange('setor',e.target.value)}
                        placeholder="Ex: Financeiro, TI" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                </div>

                <div className="h-px bg-slate-800 my-1" />

                <div className="flex items-center justify-end gap-3">
                    <Link href="/solicitantes" className="text-sm font-medium text-slate-300 border border-slate-700 hover:border-slate-500 hover:text-white transition-colors px-4 py-2.5 rounded-lg">Cancelar</Link>
                    <button type="submit" className="text-sm font-semibold text-white bg-gradient-to-b from-blue-500 to-blue-700 hover:from-blue-400 hover:to-blue-600 transition-all shadow-lg shadow-blue-950/50 px-5 py-2.5 rounded-lg">Salvar</button>
                </div>

            </div>

        </form>

    );
}