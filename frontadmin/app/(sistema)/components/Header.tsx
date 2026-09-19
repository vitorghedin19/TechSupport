export default function Header(){

    return(
        <header className="bg-slate-900 border-b border-slate-800">
            <div className="max-w-6xl mx-auto flex items-center justify-between px-6 py-4">
                <div className="flex items-center gap-3">
                    <div className="w-10 h-10 flex items-center justify-center rounded-full bg-slate-800 text-blue-600">
                        <svg xmlns="http://www.w3.org/2000/svg" className="w-6 h-6" fill="none" stroke="currentColor" strokeWidth="2">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Z" />
                            <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 20.25a7.5 7.5 0 0 1 15 0" />
                        </svg>
                    </div>
                    <span className="text-slate-100 font-medium"> Usuário Vitor Ghedin </span>
                 </div>
                <button className="text-sm font-medium text-slate-100 bg-slate-800 hover:bg-blue-600 hover:text-white transition-colors px-4 py-2 rounded-md">Sair</button>
            </div>
        </header>
    );

}