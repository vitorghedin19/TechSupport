import Link from "next/link";

// Sidebar: menu lateral fixo com os links de navegação do sistema.
//
// Link (next/link): diferente da tag <a> pura, ele faz a troca de página do lado do
// CLIENTE (client-side navigation) — o Next só busca e troca o conteúdo necessário,
// sem recarregar o site inteiro do zero. É por isso que a navegação entre as telas
// do painel é instantânea.
export default function Sidebar(){

    return(<aside className="w-64 min-h-screen bg-slate-900 border-r border-slate-800 flex flex-col">

        <div className="px-6 py-5 text-slate-100 font-semibold text-lg border-b border-slate-800">
            <img src="/techsupport-logo.svg" alt="TechSupport" className="h-10" />
        </div>
        {/* Cada Link aponta para a pasta correspondente dentro de app/(sistema)/.
            Ex: href="/usuarios" carrega app/(sistema)/usuarios/page.tsx, mesmo o
            arquivo estando dentro do route group (sistema) — que não entra na URL. */}
        <nav className="flex flex-col gap-1 px-3 py-4">
            <Link href="/home" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Home</Link>
            <Link href="/usuarios" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Usuários</Link>
            <Link href="/equipamentos" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Equipamentos</Link>
            <Link href="/chamados" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Chamados</Link>
            <Link href="/solicitantes" className="px-3 py-2 rounded-md text-sm font-medium text-slate-100 hover:bg-blue-600 hover:text-white transition-colors">Solicitantes</Link>
        </nav>
    </aside>)

}
