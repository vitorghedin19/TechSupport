// Home: página de boas-vindas do sistema, acessível em /home.
// É a página mais simples do projeto: não tem "use client", não tem hooks, não busca
// nada de API — só renderiza um texto fixo. Serve como tela inicial depois do login.
export default function Home(){

    return(
    
    <div className="items-center justify-center bg-slate-950 px-4">
    
    <h1 className="text-3xl font-semibold text-slate-100 text-center">Bem vindo ao TechSupport</h1>

    </div>
    )

}
