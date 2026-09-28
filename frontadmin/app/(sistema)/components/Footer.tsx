// Footer: rodapé fixo do sistema interno.
export default function Footer(){

    // anoAtual não é escrito manualmente (ex: "2026") — é calculado toda vez que o
    // componente renderiza, usando a data do sistema. Assim o ano do copyright nunca
    // fica desatualizado sozinho, mesmo passando os anos.
    const anoAtual = new Date().getFullYear();

    return(

        <footer className="bg-slate-900 border-t border-slate-800">
            <div className="max-w-6xl mx-auto px-6 py-4">
                <div className="text-center">
                    <p className="text-sm text-slate-100">&copy;{anoAtual}
                        <span className="font-medium text-blue-600"> TechSupport </span>
                        Todos os direitos reservados.
                    </p>
                </div>
            </div>
        </footer>

    );

}
