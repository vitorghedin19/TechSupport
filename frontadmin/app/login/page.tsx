'use client'

import { useRouter } from "next/navigation";

export default function Login(){

    const router = useRouter();

   const handlelogin =async(formData: FormData) => {

        router.push("/home")
    
   }


    return(
    <div className="min-h-screen flex items-center justify-center bg-slate-950 px-4">

        <div className="w-full max-w-sm bg-slate-900 border border-slate-800 rounded-2xl shadow-xl p-8">

            <div className="mb-8 text-center">
                <h1 className="text-2xl font-semibold text-slate-100">
                    Entrar no Sistema
                </h1>
            </div>

           <form action={handlelogin} className="flex flex-col gap-5">

                <div className="flex flex-col gap-1.5">
                    <label className="text-sm font-medium text-slate-300">
                        E-mail
                    </label>
                    <input name="email" className="w-full rounded-lg bg-slate-800 border border-slate-700 px-3 py-2 text-slate-100 placeholder-slate-500 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30 transition"></input>
                </div>

                <div className="flex flex-col gap-1.5">
                    <label className="text-sm font-medium text-slate-300">
                        Senha
                    </label>
                    <input name="senha" className="w-full rounded-lg bg-slate-800 border border-slate-700 px-3 py-2 text-slate-100 placeholder-slate-500 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-500/30 transition"></input>
                </div>

                <button type="submit" className="mt-2 w-full rounded-lg bg-blue-600 hover:bg-blue-500 active:bg-blue-700 text-white font-medium py-2.5 transition">Entrar</button>

           </form>

        </div>

    </div>
    
    );
}