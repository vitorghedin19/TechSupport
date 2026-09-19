import Link from "next/link";



export default function Sidebar(){

    return(<aside className="w-64 min-h-screen bg-slate-900 border-r border-slate-800 flex flex-col">

        <div className="px-6 py-5 text-slate-100 font-semibold text-lg border-b border-slate-800">
            <img src="/techsupport-logo.svg" alt="TechSupport" className="h-10" />
        </div>
        <nav className="flex flex-col gap-1 px-3 py-4">
            <Link href="/home" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Home</Link>
            <Link href="/usuarios" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Usuários</Link>
            <Link href="/equipamentos" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Equipamentos</Link>
            <Link href="/chamados" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Chamados</Link>
            <Link href="/solicitantes" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Solicitantes</Link>
        </nav>
    </aside>)

}