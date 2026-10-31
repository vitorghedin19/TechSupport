import Link from "next/link";
import EquipamentoForm from "../components/EquipamentoForm";

export default function CadastroEquipamento(){

    return(

        <div className="min-h-screen bg-slate-950 bg-[radial-gradient(ellipse_80%_50%_at_50%_-20%,rgba(37,99,235,0.15),rgba(2,6,23,0))] px-6 py-10">

            <div className="max-w-2xl mx-auto flex flex-col gap-8">

                <div className="flex flex-col gap-4">
                    <Link href="/equipamentos" className="group inline-flex items-center gap-1.5 text-sm font-medium text-slate-400 hover:text-blue-400 transition-colors w-fit">
                        <span aria-hidden="true" className="transition-transform group-hover:-translate-x-0.5">&larr;</span> Voltar
                    </Link>
                    <div className="flex items-center gap-4">
                        <div className="flex h-12 w-12 shrink-0 items-center justify-center rounded-xl bg-gradient-to-br from-blue-500 to-blue-800 shadow-lg shadow-blue-950/50 text-white text-xl font-bold">
                            +
                        </div>
                        <div>
                            <h1 className="text-3xl font-bold text-white tracking-tight">Novo Equipamento</h1>
                            <p className="text-sm text-slate-400 mt-0.5">Preencha os dados para registrar um novo equipamento</p>
                        </div>
                    </div>
                </div>

                <div className="w-full">
                    <EquipamentoForm/>
                </div>

            </div>

        </div>

    );
}