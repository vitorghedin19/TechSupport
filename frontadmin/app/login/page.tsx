'use client'
// "use client": obrigatório aqui porque a página usa interatividade (formulário com
// ação de login) e o hook useRouter, que só existem no navegador.

import axios from "axios";
import { useRouter } from "next/navigation";
import { LoginResponse } from "../types/auth";

export default function Login(){

    // useRouter: hook do Next.js que dá acesso à navegação programática — ou seja,
    // permite trocar de página via código (router.push), sem precisar de um <Link>
    // clicável. Usado aqui pra redirecionar o usuário pra /home depois do login OK.
    const router = useRouter();

   // handlelogin: função chamada quando o <form> é enviado (veja o `action={handlelogin}`
   // lá embaixo). Como é passada direto pro `action` de um form, ela recebe um FormData
   // automaticamente — é assim que se lê o valor dos campos sem precisar de onChange/useState
   // em cada input, diferente do que é feito nos formulários de Usuário/Equipamento/etc.
   const handlelogin =async(formData: FormData) => {
    try{
        debugger; // ponto de parada pra debug no navegador (Devtools) — não afeta produção,
                  // mas só faz sentido durante desenvolvimento.

        // formData.get("email") retorna o valor do campo <input name="email">.
        // "?.toString() ?? ''" é uma proteção: se vier null/undefined, vira string vazia
        // em vez de quebrar o código.
        const emailTela = formData.get("email")?.toString() ?? "";
        const senhaTela = formData.get("senha")?.toString() ?? "";

        // Chama a API de autenticação passando email/senha, esperando de volta um
        // objeto no formato LoginResponse (que tem só o campo "token").
        var loginResposta = await axios.post<LoginResponse>("http://localhost:8080/auth/login", {email:emailTela, senha:senhaTela});

       if( loginResposta.status == 200){
             router.push("/home") // login OK: navega pra tela inicial do sistema
       }else{alert ("Login ou senha inválido!")}
   
    } catch (error) {
        // Se a API responder com erro (ex: 401 Unauthorized), o axios lança uma exception
        // automaticamente — por isso o tratamento de "login errado" cai aqui no catch,
        // não no else acima (que praticamente nunca é alcançado na prática).
        alert ("Login ou senha inválido!")
    }
   }

    return(
    <div className="min-h-screen flex items-center justify-center bg-slate-950 px-4">

        <div className="w-full max-w-sm bg-slate-900 border border-slate-800 rounded-2xl shadow-xl p-8">

            <div className="mb-8 text-center">
                <h1 className="text-2xl font-semibold text-slate-100">
                    Entrar no Sistema
                </h1>
            </div>

           {/* action={handlelogin}: forma "moderna" do React de lidar com formulários —
               em vez de onSubmit + preventDefault manual, o próprio form chama a função
               passando os dados já prontos em FormData quando o botão type="submit" é clicado. */}
           <form action={handlelogin} className="flex flex-col gap-5">

                <div className="flex flex-col gap-1.5">
                    <label className="text-sm font-medium text-slate-300">
                        E-mail
                    </label>
                    <input name="email" type="email" className="w-full rounded-lg bg-slate-800 border border-slate-700 px-3 py-2 text-slate-100 placeholder-slate-500 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30 transition"></input>
                </div>

                <div className="flex flex-col gap-1.5">
                    <label className="text-sm font-medium text-slate-300">
                        Senha
                    </label>
                    <input name="senha" type="password" className="w-full rounded-lg bg-slate-800 border border-slate-700 px-3 py-2 text-slate-100 placeholder-slate-500 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30 transition"></input>
                </div>

                <button type="submit" className="mt-2 w-full rounded-lg bg-blue-600 hover:bg-blue-500 active:bg-blue-700 text-white font-medium py-2.5 transition">Entrar</button>

           </form>

        </div>

    </div>
    
    );
}
