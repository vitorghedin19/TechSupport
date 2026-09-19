export default function Footer(){

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