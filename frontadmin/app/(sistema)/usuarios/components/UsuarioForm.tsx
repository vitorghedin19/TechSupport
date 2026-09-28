'use client'
// 'use client' (linha acima): obrigatório, pois este componente usa hooks (useState, useRouter) e eventos (onChange).
// UsuarioFormProps é a interface que define a prop opcional usuarioExistente (veja app/types/usuario.ts).
import { Usuario, UsuarioFormProps } from "@/app/types/usuario";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";

// UsuarioForm: formulário ÚNICO reaproveitado para CRIAR e EDITAR usuário.
// Se a prop usuarioExistente vier preenchida (página [codigo]/editar) = modo EDIÇÃO; se vier vazia (página novo) = modo CRIAÇÃO.
// A desestruturação ({usuarioExistente}) pega só essa prop do objeto de props recebido.
export default function UsuarioForm({usuarioExistente}:UsuarioFormProps){

    // useRouter: hook do Next.js pra navegar por código. Usado no fim do handlerSalvar para voltar à listagem depois de salvar.
    const router = useRouter();

    // useState guarda o usuário que está sendo digitado no formulário.
    // Valor inicial: usuarioExistente (edição) OU um new Usuario com campos vazios (criação) — o operador || escolhe o primeiro que existir.
    // No new Usuario(...) a ordem segue o construtor da classe: id, nome, email, status, cpf, senha (id null = ainda não existe no banco).
    const [usuario, setUsuario] = useState<Usuario>(
    usuarioExistente ||
    new Usuario(null, "", "", "ATIVO", "", "")); /*Usuario vem com valores zerados, para receber o que vira do forms*/

    // handlerChange: chamado a cada tecla digitada nos inputs (onChange). Recebe QUAL campo mudou e o novo valor.
    // Como os dados são uma classe imutável, criamos um NOVO Usuario copiando os valores anteriores e trocando só o campo alterado.
    // setUsuario(valorAnterior => ...) usa a forma 'função de atualização': garante que estamos partindo do estado mais recente.
    const handlerChange = ( campo: 'nome' | 'email' | 'cpf' | 'senha', valor:string) => {
        setUsuario(valorAnterior => 
            new Usuario(
                valorAnterior.id,
                campo === 'nome' ? valor : valorAnterior.nome,
                campo === 'email' ? valor : valorAnterior.email,
                valorAnterior.status,
                campo === 'cpf' ? valor : valorAnterior.cpf,
                campo === 'senha' ? valor : valorAnterior.senha
            )
        )
    }

    // handlerSalvar: executa ao enviar o formulário (action={handlerSalvar} no <form>, que já entrega um FormData — aqui não usado, pois o estado 'usuario' já tem tudo).
    // FLUXO: se usuarioExistente -> PUT /usuarios/{id} (atualiza tudo); senão -> POST /usuarios (cria novo).
    // Se deu 200 mostra alerta; se não, para com return (não chega no router.push). No final volta pra listagem.
    const handlerSalvar = async (formData : FormData) => {

        if(usuarioExistente){

             var dadosRetorno = await axios.put<number>('http://localhost:8080/usuarios/'+usuario.id,usuario) /*chamando a API de usuario e aplicando o valor de usuario que recebemos*/

        if(dadosRetorno.status==200){
            alert("Usuário foi salvo com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return; /*parar de executar caso de erro para não chegar no router.push*/
        }

        }else{
        var dadosRetorno = await axios.post<number>('http://localhost:8080/usuarios',usuario) /*chamando a API de usuario e aplicando o valor de usuario que recebemos*/

        if(dadosRetorno.status==200){
            alert("Usuário foi salvo com sucesso");
            
        }else{
            alert("dadosRetorno.data");
            return; /*parar de executar caso de erro para não chegar no router.push*/
        }
    }
        router.push("/usuarios");

    }

    // JSX do formulário.
    // action={handlerSalvar} no <form>: forma moderna do React 19/Next de tratar submit — chama a função ao enviar, sem onSubmit + preventDefault.
    // Inputs 'controlados': value={usuario.campo} + onChange={handlerChange} (o React é a fonte da verdade do valor digitado).
    // required = validação nativa do navegador (impede envio com campo vazio). type="password" esconde a senha digitada.
    return(

        <form action={handlerSalvar} className="w-full bg-slate-900/60 backdrop-blur border border-slate-800 rounded-2xl p-8 shadow-xl shadow-black/30 relative overflow-hidden">

            <div className="absolute top-0 left-0 right-0 h-px bg-gradient-to-r from-transparent via-blue-500/60 to-transparent" />

            <div className="flex items-center gap-2 mb-6">
                <div className="h-1.5 w-1.5 rounded-full bg-blue-500" />
                <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400">Dados do usuário</h2>
            </div>

            <div className="flex flex-col gap-5">
                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Nome Completo
                    </label>
                    <input name="nome" 
                    value={usuario.nome} 
                    required
                    onChange={(e) => handlerChange('nome',e.target.value)}
                    placeholder="Digite o nome completo" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            CPF
                        </label>
                        <input name="CPF" 
                        value={usuario.cpf}
                        required
                        onChange={(e) => handlerChange('cpf',e.target.value)}
                        placeholder="000.000.000-00" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>

                    <div className="flex flex-col gap-1.5">
                        <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                            E-mail
                        </label>
                        <input name="email"
                        value={usuario.email}
                        required
                        onChange={(e) => handlerChange('email',e.target.value)}
                        placeholder="usuario@email.com" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                    </div>
                </div>

                <div className="flex flex-col gap-1.5">
                    <label className="text-xs font-semibold uppercase tracking-wide text-slate-400">
                        Senha
                    </label>
                    <input name="Senha"
                    value={usuario.senha}
                    required
                    onChange={(e) => handlerChange('senha',e.target.value)}
                    type="password" placeholder="••••••••" className="bg-slate-950/80 border border-slate-800 rounded-lg px-3.5 py-2.5 text-sm text-slate-100 placeholder-slate-600 outline-none transition-colors focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30"></input>
                </div>

                <div className="h-px bg-slate-800 my-1" />

                <div className="flex items-center justify-end gap-3">
                    <Link href="/usuarios" className="text-sm font-medium text-slate-300 border border-slate-700 hover:border-slate-500 hover:text-white transition-colors px-4 py-2.5 rounded-lg">Cancelar</Link>
                    <button type="submit" className="text-sm font-semibold text-white bg-gradient-to-b from-blue-500 to-blue-700 hover:from-blue-400 hover:to-blue-600 transition-all shadow-lg shadow-blue-950/50 px-5 py-2.5 rounded-lg">Salvar</button>
                </div>

            </div>

        </form>

    );
}