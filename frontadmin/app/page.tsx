import Image from "next/image";

export default function Home() {
  return (
    <div className="bg-slate-50 text-slate-800 font-sans antialiased selection:bg-blue-500 selection:text-white min-h-screen">
      {/* Header / Navbar */}
      <header className="fixed top-0 left-0 right-0 z-50 bg-white/85 backdrop-blur-md border-b border-slate-100">
        <div className="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
          {/* Logo */}
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-blue-600 to-blue-900 flex items-center justify-center text-white shadow-lg shadow-blue-500/20">
              <i className="fa-solid fa-layer-group text-lg"></i>
            </div>
            <span className="text-xl font-bold tracking-tight text-slate-900">
              Tech<span className="text-blue-600">Support</span>
            </span>
          </div>

          {/* Navegação */}
          <nav className="hidden md:flex items-center gap-8 text-sm font-medium text-slate-600">
            <a href="#recursos" className="hover:text-blue-600 transition-colors">
              Recursos
            </a>
            <a href="#historia" className="hover:text-blue-600 transition-colors">
              Nossa História
            </a>
            <a href="#beneficios" className="hover:text-blue-600 transition-colors">
              Vantagens
            </a>
          </nav>

          {/* Botão de Login */}
          <div className="flex items-center gap-4">
            <a href="/login" className="px-5 py-2.5 text-sm font-semibold text-white bg-blue-600 hover:bg-blue-700 rounded-xl shadow-lg shadow-blue-500/20 transition-all hover:scale-[1.02] active:scale-[0.98]"
            >Acessar Sistema</a>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="pt-32 pb-20 md:pt-44 md:pb-32 overflow-hidden relative">
        <div className="absolute top-1/4 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[600px] h-[600px] bg-blue-100/50 rounded-full blur-3xl -z-10 pointer-events-none"></div>

        <div className="max-w-7xl mx-auto px-6 text-center">
          <div className="inline-flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-blue-50 border border-blue-100 text-blue-700 text-xs font-semibold mb-6 animate-pulse">
            <i className="fa-solid fa-bolt text-blue-500"></i> Nascido da necessidade real de grandes operações
          </div>

          <h1 className="text-4xl md:text-6xl font-extrabold tracking-tight text-slate-900 max-w-4xl mx-auto leading-[1.15] mb-6">
            Revolucione o suporte de TI da sua empresa com{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-blue-900">
              organização e métricas reais
            </span>
            .
          </h1>

          <p className="text-lg md:text-xl text-slate-600 max-w-2xl mx-auto mb-10 leading-relaxed">
            Esqueça os formulários básicos e a falta de controle. O TechSupport nasceu para trazer clareza aos chamados abertos e mensurar com precisão cirúrgica o trabalho dos seus agentes de TI.
          </p>

          <div className="flex flex-col sm:flex-row items-center justify-center gap-4">
            <a
              href="#recursos"
              className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-white bg-blue-600 hover:bg-blue-700 rounded-2xl shadow-xl shadow-blue-500/25 transition-all hover:translate-y-[-2px]"
            >
              Conhecer o Sistema
            </a>
            <a
              href="#historia"
              className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-slate-700 bg-white hover:bg-slate-50 border border-slate-200 rounded-2xl shadow-sm transition-all"
            >
              Nossa Origem
            </a>
          </div>

          {/* Mockup / Preview Visual */}
          <div className="mt-16 max-w-5xl mx-auto rounded-2xl border border-slate-200 bg-white p-2 shadow-2xl shadow-slate-200/50">
            <div className="bg-slate-900 rounded-xl overflow-hidden aspect-[16/9] md:aspect-[21/9] flex items-center justify-center relative">
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
      <section id="historia" className="py-24 bg-white border-y border-slate-100">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-12 items-center">
            <div>
              <span className="text-blue-600 text-sm font-bold uppercase tracking-wider">Nossa Origem</span>
              <h2 className="text-3xl md:text-4xl font-bold text-slate-900 mt-2 mb-6">
                Criado por quem viveu o desafio de crescer rápido.
              </h2>
              <p className="text-slate-600 leading-relaxed mb-4">
                Trabalhando na <strong className="text-slate-900 font-semibold">Next Fit</strong>, uma referência absoluta no mercado de softwares para o ecossistema fitness, passamos por um crescimento vertiginoso. Com o aumento do time, percebemos que o suporte de TI interno dependia de ferramentas arcaicas: chamados via formulários básicos.
              </p>
              <p className="text-slate-600 leading-relaxed mb-6">
                Em uma empresa gigante, manter a organização, dar clareza aos chamados abertos e mensurar o esforço real dos agentes de TI se tornou um gargalo crítico.{' '}
                <strong className="text-slate-900 font-semibold">Foi aí que o TechSupport nasceu.</strong> Desenhamos a solução interna perfeita para estruturar fluxos, garantir transparência e metrificar cada entrega do time técnico.
              </p>
              <div className="flex items-center gap-4 p-4 rounded-xl bg-blue-50 border border-blue-100">
                <div className="text-blue-600 text-2xl">
                  <i className="fa-solid fa-dumbbell"></i>
                </div>
                <div className="text-sm text-slate-700">
                  Validado na prática em um ambiente corporativo dinâmico e de alta exigência.
                </div>
              </div>
            </div>
            <div className="relative">
              <div className="w-full aspect-square rounded-3xl bg-gradient-to-br from-blue-100 to-blue-50 p-8 flex flex-col justify-between border border-blue-100/50 shadow-inner">
                <div className="flex justify-between items-center">
                  <span className="text-xs font-bold text-blue-700 bg-white px-3 py-1 rounded-full shadow-sm">
                    Case de Sucesso
                  </span>
                  <i className="fa-solid fa-quote-right text-blue-300 text-4xl"></i>
                </div>
                <blockquote className="text-xl font-medium text-slate-800 italic my-auto">
                  &quot;Precisávamos deixar de apagar incêndios no escuro e passar a gerenciar a TI com dados claros, organização e eficiência absoluta.&quot;
                </blockquote>
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 rounded-full bg-blue-600 text-white font-bold flex items-center justify-center">
                    TF
                  </div>
                  <div>
                    <h4 className="text-sm font-bold text-slate-900">Time Fundador</h4>
                    <p className="text-xs text-slate-500">Ex-colaboradores Next Fit / TechSupport</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Seção: Recursos (Recursos principais) */}
      <section id="recursos" className="py-24 bg-slate-50">
        <div className="max-w-7xl mx-auto px-6">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <span className="text-blue-600 text-sm font-bold uppercase tracking-wider">Recursos Poderosos</span>
            <h2 className="text-3xl font-bold text-slate-900 mt-2 mb-4">Tudo o que sua TI precisa para performar</h2>
            <p className="text-slate-600">Esqueça planilhas e e-mails perdidos. Um sistema feito sob medida para controle de ponta a ponta.</p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {/* Card 1 */}
            <div className="bg-white p-8 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-shadow">
              <div className="w-12 h-12 rounded-xl bg-blue-50 text-blue-600 flex items-center justify-center text-xl mb-6">
                <i className="fa-solid fa-folder-open"></i>
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Clareza nos Chamados</h3>
              <p className="text-slate-600 text-sm leading-relaxed">
                Visibilidade completa do ciclo de vida de cada ticket, desde a abertura até a resolução, sem ruídos na comunicação.
              </p>
            </div>

            {/* Card 2 */}
            <div className="bg-white p-8 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-shadow">
              <div className="w-12 h-12 rounded-xl bg-blue-50 text-blue-600 flex items-center justify-center text-xl mb-6">
                <i className="fa-solid fa-chart-pie"></i>
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Métricas e Indicadores</h3>
              <p className="text-slate-600 text-sm leading-relaxed">
                Mensure o trabalho dos agentes de TI com relatórios de produtividade, tempo médio de atendimento e gargalos operacionais.
              </p>
            </div>

            {/* Card 3 */}
            <div className="bg-white p-8 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-shadow">
              <div className="w-12 h-12 rounded-xl bg-blue-50 text-blue-600 flex items-center justify-center text-xl mb-6">
                <i className="fa-solid fa-sitemap"></i>
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Organização em Grande Escala</h3>
              <p className="text-slate-600 text-sm leading-relaxed">
                Arquitetura pensada para empresas gigantescas, permitindo categorização avançada de chamados por setor, prioridade e impacto.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-slate-900 text-slate-400 py-12 border-t border-slate-800">
        <div className="max-w-7xl mx-auto px-6 flex flex-col md:flex-row items-center justify-between gap-6">
          <div className="flex items-center gap-3">
            <div className="w-8 h-8 rounded-lg bg-blue-600 flex items-center justify-center text-white">
              <i className="fa-solid fa-layer-group text-sm"></i>
            </div>
            <span className="text-white font-bold tracking-tight">
              Tech<span className="text-blue-500">Support</span>
            </span>
          </div>

          <p className="text-xs text-slate-500">
            &copy; 2026 TechSupport. Todos os direitos reservados. Inspirado na excelência operacional da Next Fit.
          </p>

          <div className="flex items-center gap-4 text-sm">
            <a href="/login" className="hover:text-white transition-colors">
              Termos
            </a>
            <a href="/login" className="hover:text-white transition-colors">
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
