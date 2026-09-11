import Image from "next/image";

export default function Home() {
  return (
    <div className="bg-slate-950 text-slate-200 font-sans antialiased selection:bg-blue-500 selection:text-white min-h-screen">
      {/* Header / Navbar */}
      <header className="fixed top-0 left-0 right-0 z-50 bg-slate-950/85 backdrop-blur-md border-b border-slate-800">
        <div className="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
          {/* Logo */}
          <div className="flex items-center gap-3">
            <img src="/techsupport-logo.svg" alt="TechSupport" className="h-10" />
          </div>

          {/* Navegação */}
          <nav className="hidden md:flex items-center gap-8 text-sm font-medium text-slate-400">
            <a href="#recursos" className="hover:text-blue-400 transition-colors">
              Recursos
            </a>
            <a href="#historia" className="hover:text-blue-400 transition-colors">
              Nossa História
            </a>
            <a href="#beneficios" className="hover:text-blue-400 transition-colors">
              Vantagens
            </a>
          </nav>

          {/* Botão de Login */}
          <div className="flex items-center gap-4">
            <a href="/login" className="px-5 py-2.5 text-sm font-semibold text-white bg-blue-600 hover:bg-blue-500 rounded-xl shadow-lg shadow-blue-500/20 transition-all hover:scale-[1.02] active:scale-[0.98]"
            >Acessar Sistema</a>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="pt-32 pb-20 md:pt-44 md:pb-32 overflow-hidden relative">
        <div className="absolute top-1/4 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[600px] h-[600px] bg-blue-900/20 rounded-full blur-3xl -z-10 pointer-events-none"></div>

        <div className="max-w-7xl mx-auto px-6 text-center">
          <div className="inline-flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-blue-950/60 border border-blue-900 text-blue-300 text-xs font-semibold mb-6 animate-pulse">
            <i className="fa-solid fa-bolt text-blue-400"></i> Nascido da necessidade real de grandes operações
          </div>

          <h1 className="text-4xl md:text-6xl font-extrabold tracking-tight text-slate-100 max-w-4xl mx-auto leading-[1.15] mb-6">
            Revolucione o suporte de TI da sua empresa com{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-400 to-blue-600">
              organização e métricas reais
            </span>
            .
          </h1>

          <p className="text-lg md:text-xl text-slate-400 max-w-2xl mx-auto mb-10 leading-relaxed">
            Esqueça os formulários básicos e a falta de controle. O TechSupport nasceu para trazer clareza aos chamados abertos e mensurar com precisão cirúrgica o trabalho dos seus agentes de TI.
          </p>

          <div className="flex flex-col sm:flex-row items-center justify-center gap-4">
            
            <a href="#recursos"
              className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-white bg-blue-600 hover:bg-blue-500 rounded-2xl shadow-xl shadow-blue-500/25 transition-all hover:translate-y-[-2px]"
            >
              Conhecer o Sistema
            </a>
            
            <a  href="#historia"
              className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-slate-200 bg-slate-900 hover:bg-slate-800 border border-slate-800 rounded-2xl shadow-sm transition-all"
            >
              Nossa Origem
            </a>
          </div>

          {/* Mockup / Preview Visual */}
          <div className="mt-16 max-w-5xl mx-auto rounded-2xl border border-slate-800 bg-slate-900 p-2 shadow-2xl shadow-black/40">
            <div className="bg-slate-950 rounded-xl overflow-hidden aspect-[16/9] md:aspect-[21/9] flex items-center justify-center relative">
              <div className="absolute inset-0 bg-gradient-to-tr from-blue-900/40 to-transparent"></div>
              <div className="text-center p-6 z-10">
                <div className="w-16 h-16 rounded-2xl bg-blue-600/30 border border-blue-500/30 flex items-center justify-center mx-auto mb-4 text-blue-400 text-2xl">
                  <i className="fa-solid fa-chart-line"></i>
                </div>
                <h3 className="text-white font-bold text-xl mb-2">Painel de Controle de Chamados</h3>
                <p className="text-slate-400 text-sm max-w-md mx-auto">
                  Métricas em tempo real, SLA ajustado e produtividade de agentes em uma única tela.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Seção: Nossa História (Origem no Next Fit) */}
      <section id="historia" className="py-24 bg-slate-900 border-y border-slate-800">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-12 items-center">
            <div>
              <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Nossa Origem</span>
              <h2 className="text-3xl md:text-4xl font-bold text-slate-100 mt-2 mb-6">
                Criado por quem viveu o desafio de crescer rápido.
              </h2>
              <p className="text-slate-400 leading-relaxed mb-4">
                Trabalhando na <strong className="text-slate-200 font-semibold">Next Fit</strong>, uma referência absoluta no mercado de softwares para o ecossistema fitness, passamos por um crescimento vertiginoso. Com o aumento do time, percebemos que o suporte de TI interno dependia de ferramentas arcaicas: chamados via formulários básicos.
              </p>
              <p className="text-slate-400 leading-relaxed mb-6">
                Em uma empresa gigante, manter a organização, dar clareza aos chamados abertos e mensurar o esforço real dos agentes de TI se tornou um gargalo crítico.{' '}
                <strong className="text-slate-200 font-semibold">Foi aí que o TechSupport nasceu.</strong> Desenhamos a solução interna perfeita para estruturar fluxos, garantir transparência e metrificar cada entrega do time técnico.
              </p>
              <div className="flex items-center gap-4 p-4 rounded-xl bg-blue-950/50 border border-blue-900">
                <div className="text-blue-400 text-2xl">
                  <i className="fa-solid fa-dumbbell"></i>
                </div>
                <div className="text-sm text-slate-300">
                  Validado na prática em um ambiente corporativo dinâmico e de alta exigência.
                </div>
              </div>
            </div>
            <div className="relative">
              <div className="w-full aspect-square rounded-3xl bg-gradient-to-br from-slate-900 to-blue-950/40 p-8 flex flex-col justify-between border border-slate-800 shadow-inner">
                <div className="flex justify-between items-center">
                  <span className="text-xs font-bold text-blue-300 bg-slate-950 px-3 py-1 rounded-full shadow-sm">
                    Case de Sucesso
                  </span>
                  <i className="fa-solid fa-quote-right text-blue-800 text-4xl"></i>
                </div>
                <blockquote className="text-xl font-medium text-slate-200 italic my-auto">
                  &quot;Precisávamos deixar de apagar incêndios no escuro e passar a gerenciar a TI com dados claros, organização e eficiência absoluta.&quot;
                </blockquote>
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 rounded-full bg-blue-600 text-white font-bold flex items-center justify-center">
                    TF
                  </div>
                  <div>
                    <h4 className="text-sm font-bold text-slate-100">Time Fundador</h4>
                    <p className="text-xs text-slate-500">Ex-colaboradores Next Fit / TechSupport</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Seção: Recursos (Recursos principais) */}
      <section id="recursos" className="py-24 bg-slate-950">
        <div className="max-w-7xl mx-auto px-6">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Recursos Poderosos</span>
            <h2 className="text-3xl font-bold text-slate-100 mt-2 mb-4">Tudo o que sua TI precisa para performar</h2>
            <p className="text-slate-400">Esqueça planilhas e e-mails perdidos. Um sistema feito sob medida para controle de ponta a ponta.</p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {/* Card 1 */}
            <div className="bg-slate-900 p-8 rounded-2xl border border-slate-800 shadow-sm hover:shadow-md hover:border-slate-700 transition-all">
              <div className="w-12 h-12 rounded-xl bg-blue-950/60 text-blue-400 flex items-center justify-center text-xl mb-6">
                <i className="fa-solid fa-folder-open"></i>
              </div>
              <h3 className="text-lg font-bold text-slate-100 mb-2">Clareza nos Chamados</h3>
              <p className="text-slate-400 text-sm leading-relaxed">
                Visibilidade completa do ciclo de vida de cada ticket, desde a abertura até a resolução, sem ruídos na comunicação.
              </p>
            </div>

            {/* Card 2 */}
            <div className="bg-slate-900 p-8 rounded-2xl border border-slate-800 shadow-sm hover:shadow-md hover:border-slate-700 transition-all">
              <div className="w-12 h-12 rounded-xl bg-blue-950/60 text-blue-400 flex items-center justify-center text-xl mb-6">
                <i className="fa-solid fa-chart-pie"></i>
              </div>
              <h3 className="text-lg font-bold text-slate-100 mb-2">Métricas e Indicadores</h3>
              <p className="text-slate-400 text-sm leading-relaxed">
                Mensure o trabalho dos agentes de TI com relatórios de produtividade, tempo médio de atendimento e gargalos operacionais.
              </p>
            </div>

            {/* Card 3 */}
            <div className="bg-slate-900 p-8 rounded-2xl border border-slate-800 shadow-sm hover:shadow-md hover:border-slate-700 transition-all">
              <div className="w-12 h-12 rounded-xl bg-blue-950/60 text-blue-400 flex items-center justify-center text-xl mb-6">
                <i className="fa-solid fa-sitemap"></i>
              </div>
              <h3 className="text-lg font-bold text-slate-100 mb-2">Organização em Grande Escala</h3>
              <p className="text-slate-400 text-sm leading-relaxed">
                Arquitetura pensada para empresas gigantescas, permitindo categorização avançada de chamados por setor, prioridade e impacto.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-slate-950 text-slate-500 py-12 border-t border-slate-800">
        <div className="max-w-7xl mx-auto px-6 flex flex-col md:flex-row items-center justify-between gap-6">
          <div className="flex items-center gap-3">
            <img src="/techsupport-logo.svg" alt="TechSupport" className="h-8" />
          </div>

          <p className="text-xs text-slate-600">
            &copy; 2026 TechSupport. Todos os direitos reservados. Inspirado na excelência operacional da Next Fit.
          </p>

          <div className="flex items-center gap-4 text-sm">
            <a href="/login" className="hover:text-slate-200 transition-colors">
              Termos
            </a>
            <a href="/login" className="hover:text-slate-200 transition-colors">
              Privacidade
            </a>
            <a href="/login" className="text-blue-400 font-medium hover:underline">
              Fazer Login
            </a>
          </div>
        </div>
      </footer>
    </div>
  );
}