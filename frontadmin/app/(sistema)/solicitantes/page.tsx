export default function Solicitantes(){
    return(
    
    <div className="min-h-screen bg-slate-950 px-6 py-10">
        <div className="flex items-center justify-between mb-6">
            <h1 className="text-2xl font-semibold text-slate-100">Gestão de solicitantes</h1>
            <link href="/solicitantes/novo" className="rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-sm font-medium px-4 py-2 transition" />
        </div>

        <div className="rounded-xl border border-slate-800 bg-slate-900 overflow-hidden">
            <table className="w-full text-left">
                <thead className="bg-slate-800/60">
                    <tr>
                        <th className="px-4 py-3 text-sm font-medium text-slate-300">Nome</th>
                    </tr>
                </thead>
                <tbody className="divide-y divide-slate-800">
                    <tr>
                        <td className="px-4 py-3 text-slate-100">Jão</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    
    )
}