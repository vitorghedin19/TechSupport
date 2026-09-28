import Footer from "./components/Footer";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";

// SistemaLayout: layout que envolve todas as páginas internas do sistema (usuarios,
// equipamentos, chamados, solicitantes, home...). Ele fica DENTRO do RootLayout
// (app/layout.tsx) — não o substitui.
//
// O "(sistema)" no nome da pasta é um ROUTE GROUP do Next.js: pastas entre parênteses
// servem só para ORGANIZAR arquivos e aplicar um layout comum a um grupo de rotas —
// elas NÃO aparecem na URL do navegador. Por isso "/usuarios" funciona normalmente,
// mesmo o arquivo estando em app/(sistema)/usuarios/page.tsx.
export default function SistemaLayout({children}){
    return (
    // Wrapper flex: Sidebar fixa à esquerda; o restante (Header + conteúdo + Footer)
    // ocupa o espaço remanescente, organizado em coluna (flex-col).
    <div className="flex min-h-screen bg-slate-950"> 
        <Sidebar/>
    <div className="flex-1 flex flex-col min-h-screen">
            <Header/>
            {/* {children} aqui é a página específica sendo acessada (ex: <Usuarios/>,
                <Chamados/>, <Home/>...). Ela é injetada entre Header e Footer — ou seja,
                toda página dentro de (sistema) já ganha esses três componentes ao redor
                automaticamente, sem precisar repetir Header/Sidebar/Footer em cada uma. */}
            <main className="flex-1 px-6 py-8 text-slate-100">
            {children} 
            </main>
            <Footer/>
        </div>
    </div>
    );
}
