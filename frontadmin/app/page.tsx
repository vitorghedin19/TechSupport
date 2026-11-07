const icones = {
  bolt: "m3.75 13.5 10.5-11.25L12 10.5h8.25L9.75 21.75 12 13.5H3.75Z",
  documento: "M19.5 14.25v-2.625a3.375 3.375 0 0 0-3.375-3.375h-1.5A1.125 1.125 0 0 1 13.5 7.125v-1.5a3.375 3.375 0 0 0-3.375-3.375H8.25m0 12.75h7.5m-7.5 3H12M10.5 2.25H5.625c-.621 0-1.125.504-1.125 1.125v17.25c0 .621.504 1.125 1.125 1.125h12.75c.621 0 1.125-.504 1.125-1.125V11.25a9 9 0 0 0-9-9Z",
  grafico: "M3 13.125C3 12.504 3.504 12 4.125 12h2.25c.621 0 1.125.504 1.125 1.125v6.75C7.5 20.496 6.996 21 6.375 21h-2.25A1.125 1.125 0 0 1 3 19.875v-6.75ZM9.75 8.625c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125v11.25c0 .621-.504 1.125-1.125 1.125h-2.25a1.125 1.125 0 0 1-1.125-1.125V8.625ZM16.5 4.125c0-.621.504-1.125 1.125-1.125h2.25C20.496 3 21 3.504 21 4.125v15.75c0 .621-.504 1.125-1.125 1.125h-2.25a1.125 1.125 0 0 1-1.125-1.125V4.125Z",
  organizacao: "M2.25 7.125C2.25 6.504 2.754 6 3.375 6h6c.621 0 1.125.504 1.125 1.125v3.75c0 .621-.504 1.125-1.125 1.125h-6a1.125 1.125 0 0 1-1.125-1.125v-3.75ZM14.25 8.625c0-.621.504-1.125 1.125-1.125h5.25c.621 0 1.125.504 1.125 1.125v8.25c0 .621-.504 1.125-1.125 1.125h-5.25a1.125 1.125 0 0 1-1.125-1.125v-8.25ZM3.75 16.125c0-.621.504-1.125 1.125-1.125h5.25c.621 0 1.125.504 1.125 1.125v2.25c0 .621-.504 1.125-1.125 1.125h-5.25a1.125 1.125 0 0 1-1.125-1.125v-2.25Z",
  aprovado: "M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z",
  computador: "M9 17.25v1.007a3 3 0 0 1-.879 2.122L7.5 21h9l-.621-.621A3 3 0 0 1 15 18.257V17.25m6-12V15a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 15V5.25m18 0A2.25 2.25 0 0 0 18.75 3H5.25A2.25 2.25 0 0 0 3 5.25m18 0V12a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 12V5.25",
  predio: "M3.75 21h16.5M4.5 3h15M5.25 3v18m13.5-18v18M9 6.75h1.5m-1.5 3h1.5m-1.5 3h1.5m3-6H15m-1.5 3H15m-1.5 3H15M9 21v-3.375c0-.621.504-1.125 1.125-1.125h3.75c.621 0 1.125.504 1.125 1.125V21",
  escudo: "M9 12.75 11.25 15 15 9.75m-3-7.036A11.959 11.959 0 0 1 3.598 6 11.99 11.99 0 0 0 3 9.749c0 5.592 3.824 10.29 9 11.623 5.176-1.332 9-6.03 9-11.622 0-1.31-.21-2.571-.598-3.751h-.152c-3.196 0-6.1-1.248-8.25-3.285Z",
  usuario: "M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.5 20.25a7.5 7.5 0 0 1 15 0",
  adicionar: "M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z",
  cadeado: "M16.5 10.5V6.75a4.5 4.5 0 1 0-9 0v3.75m-.75 11.25h10.5a2.25 2.25 0 0 0 2.25-2.25v-6.75a2.25 2.25 0 0 0-2.25-2.25H6.75a2.25 2.25 0 0 0-2.25 2.25v6.75a2.25 2.25 0 0 0 2.25 2.25Z",
  seta: "M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3",
  abaixo: "m19.5 8.25-7.5 7.5-7.5-7.5",
  atualizar: "M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99",
};

type NomeIcone = keyof typeof icones;

function Icone({ nome, className = "w-6 h-6" }: { nome: NomeIcone; className?: string }) {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.6" stroke="currentColor" className={className} aria-hidden="true">
      <path strokeLinecap="round" strokeLinejoin="round" d={icones[nome]} />
    </svg>
  );
}

const tecnologias = ["Spring Boot", "Next.js", "React", "Redux Toolkit", "PostgreSQL", "JWT", "JavaFX"];

const recursos: { icone: NomeIcone; titulo: string; texto: string }[] = [
  {
    icone: "documento",
    titulo: "Clareza nos Chamados",
    texto: "Visibilidade completa do ciclo de vida de cada ticket, desde a abertura até a resolução, sem ruídos na comunicação.",
  },
  {
    icone: "grafico",
    titulo: "Métricas e Indicadores",
    texto: "Mensure o trabalho dos agentes de TI com relatórios de produtividade, tempo médio de atendimento e gargalos operacionais.",
  },
  {
    icone: "organizacao",
    titulo: "Organização em Grande Escala",
    texto: "Arquitetura pensada para empresas gigantescas, permitindo categorização avançada de chamados por setor, prioridade e impacto.",
  },
  {
    icone: "aprovado",
    titulo: "Solução sempre registrada",
    texto: "Um chamado só é resolvido com a solução adotada. O histórico fica salvo e pode ser consultado depois por quem tem acesso.",
  },
  {
    icone: "computador",
    titulo: "Equipamentos vinculados",
    texto: "Cadastre os equipamentos da empresa e escolha, na abertura do chamado, qual deles apresentou o problema.",
  },
  {
    icone: "predio",
    titulo: "Multiempresa com dados isolados",
    texto: "Cada empresa enxerga apenas os próprios chamados, equipamentos e usuários, mesmo usando o mesmo sistema.",
  },
];

const passos = [
  {
    titulo: "Abertura",
    texto: "O solicitante descreve o problema, define a prioridade e escolhe o equipamento afetado.",
  },
  {
    titulo: "Atendimento",
    texto: "A equipe técnica da empresa assume o chamado e acompanha o andamento: aberto, em andamento ou fechado.",
  },
  {
    titulo: "Resolução",
    texto: "Ao resolver, o técnico registra a solução adotada. Ela fica salva no chamado e pode ser consultada depois.",
  },
];

const perfis: { icone: NomeIcone; nome: string; resumo: string; itens: string[] }[] = [
  {
    icone: "escudo",
    nome: "Administrador",
    resumo: "Visão geral do sistema",
    itens: [
      "Enxerga os dados de todas as empresas",
      "Gerencia usuários, equipamentos e chamados",
      "Criado no primeiro acesso, pelo aplicativo desktop",
    ],
  },
  {
    icone: "usuario",
    nome: "Técnico",
    resumo: "Atende e resolve",
    itens: [
      "Gerencia chamados, equipamentos, solicitantes e usuários da própria empresa",
      "Resolve chamados registrando a solução",
      "Não vê dados de outras empresas",
    ],
  },
  {
    icone: "adicionar",
    nome: "Solicitante",
    resumo: "Abre e acompanha",
    itens: [
      "Abre chamados escolhendo o equipamento",
      "Vê e edita apenas os próprios chamados",
      "Consulta a solução quando o chamado é resolvido",
    ],
  },
];

const vantagens = [
  "Acesso protegido por login e token (JWT)",
  "Cada perfil enxerga apenas o que precisa",
  "Dados separados por empresa",
  "Histórico da solução de cada chamado",
];

const camadas = [
  { numero: "1", titulo: "Login com token", texto: "Toda requisição passa por um token com validade. Sem ele, nada é liberado." },
  { numero: "2", titulo: "Permissão por perfil", texto: "Administrador, técnico e solicitante acessam apenas as telas e ações do seu papel." },
  { numero: "3", titulo: "Dados por empresa", texto: "Cada registro nasce vinculado à empresa de quem criou, e o acesso a outras empresas é bloqueado." },
];

const perguntas = [
  {
    pergunta: "Como é feito o primeiro acesso?",
    resposta: "O primeiro administrador, ou a primeira empresa com o seu técnico, é criado pelo aplicativo desktop do TechSupport. Depois disso, o login é feito aqui, pelo navegador.",
  },
  {
    pergunta: "Uma empresa consegue ver os dados de outra?",
    resposta: "Não. Chamados, equipamentos, solicitantes e usuários ficam vinculados à empresa de quem os criou, e o sistema bloqueia o acesso a dados de outras empresas.",
  },
  {
    pergunta: "O solicitante também precisa de login?",
    resposta: "Sim. Ele entra com o próprio e-mail e senha, abre chamados e acompanha apenas os que ele criou.",
  },
  {
    pergunta: "Dá para resolver um chamado sem explicar o que foi feito?",
    resposta: "Não. A solução adotada é obrigatória para marcar um chamado como resolvido, e ela fica registrada no próprio chamado.",
  },
];

const chamadosExemplo = [
  { id: 12, titulo: "Notebook não liga", descricao: "Não carrega após queda de energia", equipamento: "Notebook 14 pol.", prioridade: "Alta", status: "ABERTO" },
  { id: 11, titulo: "Impressora sem conexão", descricao: "Não aparece na rede do setor", equipamento: "Impressora térmica", prioridade: "Média", status: "EM_ANDAMENTO" },
  { id: 10, titulo: "Troca de monitor", descricao: "Tela com listras verticais", equipamento: "Monitor 24 pol.", prioridade: "Baixa", status: "RESOLVIDO" },
];

const estilosStatus: Record<string, string> = {
  ABERTO: "text-blue-400 border-blue-500/40 bg-blue-500/10",
  EM_ANDAMENTO: "text-purple-400 border-purple-500/40 bg-purple-500/10",
  RESOLVIDO: "text-green-400 border-green-500/40 bg-green-500/10",
  FECHADO: "text-slate-400 border-slate-500/40 bg-slate-500/10",
};

const colunasMockup = "grid-cols-[3.25rem_7rem_9rem_7rem_3.5rem_6.5rem_1fr]";

export default function Home() {
  return (
    <div className="bg-slate-950 text-slate-200 font-sans antialiased selection:bg-blue-500 selection:text-white min-h-screen">
      {/* Header / Navbar */}
      <header className="fixed top-0 left-0 right-0 z-50 bg-slate-950/85 backdrop-blur-md border-b border-slate-800">
        <div className="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
          {/* Logo */}
          <a href="#inicio" className="flex items-center gap-3">
            <img src="/techsupport-logo.svg" alt="TechSupport" className="h-10" />
          </a>

          {/* Navegação */}
          <nav className="hidden lg:flex items-center gap-8 text-sm font-medium text-slate-400">
            <a href="#recursos" className="hover:text-blue-400 transition-colors">
              Recursos
            </a>
            <a href="#como-funciona" className="hover:text-blue-400 transition-colors">
              Como funciona
            </a>
            <a href="#perfis" className="hover:text-blue-400 transition-colors">
              Perfis
            </a>
            <a href="#beneficios" className="hover:text-blue-400 transition-colors">
              Vantagens
            </a>
            <a href="#faq" className="hover:text-blue-400 transition-colors">
              Dúvidas
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
      <section id="inicio" className="pt-32 pb-20 md:pt-44 md:pb-28 overflow-hidden relative">
        <div className="absolute top-1/4 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[600px] h-[600px] bg-blue-900/20 rounded-full blur-3xl -z-10 pointer-events-none"></div>
        <div className="absolute inset-0 -z-10 pointer-events-none opacity-60 bg-[linear-gradient(to_right,rgba(51,65,85,0.25)_1px,transparent_1px),linear-gradient(to_bottom,rgba(51,65,85,0.25)_1px,transparent_1px)] bg-[size:56px_56px] [mask-image:radial-gradient(ellipse_at_top,black,transparent_70%)]"></div>

        <div className="max-w-7xl mx-auto px-6 text-center">
          <div className="inline-flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-blue-950/60 border border-blue-900 text-blue-300 text-xs font-semibold mb-6 animate-pulse">
            <Icone nome="bolt" className="w-3.5 h-3.5 text-blue-400" /> Nascido da necessidade real de grandes operações
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

            <a href="/login"
              className="w-full sm:w-auto inline-flex items-center justify-center gap-2 px-8 py-4 text-base font-semibold text-white bg-blue-600 hover:bg-blue-500 rounded-2xl shadow-xl shadow-blue-500/25 transition-all hover:translate-y-[-2px]"
            >
              Acessar o Sistema
              <Icone nome="seta" className="w-4 h-4" />
            </a>

            <a href="#recursos"
              className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-slate-200 bg-slate-900 hover:bg-slate-800 border border-slate-800 rounded-2xl shadow-sm transition-all"
            >
              Conhecer o Sistema
            </a>
          </div>

          {/* Mockup / Preview Visual: tela de chamados desenhada só com Tailwind */}
          <div className="mt-16 max-w-6xl mx-auto rounded-2xl border border-slate-800 bg-slate-900 p-2 shadow-2xl shadow-blue-950/40 text-left">
            <div className="bg-slate-950 rounded-xl overflow-hidden">

              {/* Barra da janela */}
              <div className="flex items-center gap-2 px-4 py-3 border-b border-slate-800 bg-slate-900/70">
                <span className="w-2.5 h-2.5 rounded-full bg-red-500/70"></span>
                <span className="w-2.5 h-2.5 rounded-full bg-yellow-500/70"></span>
                <span className="w-2.5 h-2.5 rounded-full bg-green-500/70"></span>
                <div className="ml-4 hidden sm:block rounded-md bg-slate-800/80 px-3 py-1 text-xs text-slate-500">
                  techsupport / chamados
                </div>
              </div>

              <div className="flex">
                {/* Menu lateral */}
                <div className="hidden md:flex w-36 shrink-0 flex-col gap-1 border-r border-slate-800 bg-slate-900/50 p-3 text-sm">
                  <span className="px-3 py-2 text-slate-300">Home</span>
                  <span className="px-3 py-2 text-slate-300">Usuários</span>
                  <span className="px-3 py-2 text-slate-300">Equipamentos</span>
                  <span className="px-3 py-2 text-slate-300">Chamados</span>
                  <span className="px-3 py-2 text-slate-300">Solicitantes</span>
                </div>

                <div className="flex-1 min-w-0">
                  {/* Topo com usuário e sair */}
                  <div className="flex items-center justify-between border-b border-slate-800 bg-slate-900/50 px-4 md:px-6 py-3">
                    <div className="flex items-center gap-3">
                      <span className="w-9 h-9 rounded-full bg-slate-800 text-blue-400 flex items-center justify-center">
                        <Icone nome="usuario" className="w-5 h-5" />
                      </span>
                      <span className="text-sm font-medium text-slate-100">MARIA SOUZA</span>
                    </div>
                    <span className="rounded-lg bg-slate-800 px-3 py-1.5 text-xs font-medium text-slate-200">Sair</span>
                  </div>

                  <div className="p-4 md:p-6">
                    <div className="flex items-center justify-between gap-3 mb-5">
                      <h3 className="text-lg md:text-xl font-bold text-slate-100">Gestão de chamados</h3>
                      <span className="shrink-0 rounded-lg bg-blue-600 px-3 py-2 text-xs font-medium text-white">Cadastrar Chamado</span>
                    </div>

                    <div className="rounded-xl border border-slate-800 overflow-x-auto">
                      <div className="min-w-[820px]">
                        <div className={`grid ${colunasMockup} gap-2 bg-slate-800/60 px-4 py-2.5 text-xs font-medium text-slate-400`}>
                          <span>Código</span>
                          <span>Título</span>
                          <span>Descrição</span>
                          <span>Equipamento</span>
                          <span>Prioridade</span>
                          <span>Status</span>
                          <span className="text-center">Ações</span>
                        </div>

                        {chamadosExemplo.map((chamado) => {
                          const resolvido = chamado.status === "RESOLVIDO";
                          return (
                            <div key={chamado.id} className={`grid ${colunasMockup} items-center gap-2 border-t border-slate-800 px-4 py-3 text-xs`}>
                              <span className="text-slate-300">{chamado.id}</span>
                              <span className="text-slate-100">{chamado.titulo}</span>
                              <span className="text-slate-300">{chamado.descricao}</span>
                              <span className="text-slate-300">{chamado.equipamento}</span>
                              <span className="text-slate-300">{chamado.prioridade}</span>
                              <span className="text-slate-100">{chamado.status}</span>
                              <span className="flex items-center justify-center gap-2.5">
                                <span className="text-blue-400">Editar</span>
                                {resolvido ? (
                                  <Icone nome="documento" className="w-4 h-4 text-slate-400" />
                                ) : (
                                  <span className="text-green-400">Resolver</span>
                                )}
                                <span className="text-red-500">Deletar</span>
                                <span className={`inline-flex items-center gap-1 rounded-full border px-2.5 py-1 text-[11px] font-medium ${estilosStatus[chamado.status]}`}>
                                  <Icone nome="atualizar" className="w-3 h-3" />
                                  {chamado.status}
                                </span>
                              </span>
                            </div>
                          );
                        })}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <p className="mt-4 text-xs text-slate-600">Imagem ilustrativa da tela de chamados.</p>
        </div>
      </section>

      {/* Faixa: tecnologias usadas */}
      <section className="border-y border-slate-800 bg-slate-900/40 py-8">
        <div className="max-w-7xl mx-auto px-6 flex flex-col md:flex-row items-center justify-center gap-4 md:gap-8">
          <span className="text-xs font-semibold uppercase tracking-widest text-slate-500">Construído com</span>
          <div className="flex flex-wrap items-center justify-center gap-2.5">
            {tecnologias.map((tecnologia) => (
              <span key={tecnologia} className="rounded-full border border-slate-800 bg-slate-900 px-3.5 py-1.5 text-xs font-medium text-slate-300">
                {tecnologia}
              </span>
            ))}
          </div>
        </div>
      </section>

      {/* Seção: Nossa História (Origem no Next Fit) */}
      <section id="historia" className="scroll-mt-24 py-24 bg-slate-900 border-b border-slate-800">
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
                <div className="text-blue-400">
                  <Icone nome="aprovado" className="w-7 h-7" />
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
                  <span className="text-blue-800 text-7xl font-serif leading-none select-none">&rdquo;</span>
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

      {/* Seção: Recursos */}
      <section id="recursos" className="scroll-mt-24 py-24 bg-slate-950">
        <div className="max-w-7xl mx-auto px-6">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Recursos Poderosos</span>
            <h2 className="text-3xl font-bold text-slate-100 mt-2 mb-4">Tudo o que sua TI precisa para performar</h2>
            <p className="text-slate-400">Esqueça planilhas e e-mails perdidos. Um sistema feito sob medida para controle de ponta a ponta.</p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {recursos.map((recurso) => (
              <div key={recurso.titulo} className="bg-slate-900 p-8 rounded-2xl border border-slate-800 shadow-sm hover:shadow-lg hover:shadow-blue-950/40 hover:border-blue-500/40 hover:-translate-y-1 transition-all">
                <div className="w-12 h-12 rounded-xl bg-blue-950/60 text-blue-400 flex items-center justify-center mb-6">
                  <Icone nome={recurso.icone} className="w-6 h-6" />
                </div>
                <h3 className="text-lg font-bold text-slate-100 mb-2">{recurso.titulo}</h3>
                <p className="text-slate-400 text-sm leading-relaxed">
                  {recurso.texto}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Seção: Como funciona */}
      <section id="como-funciona" className="scroll-mt-24 py-24 bg-slate-900 border-y border-slate-800">
        <div className="max-w-7xl mx-auto px-6">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Como funciona</span>
            <h2 className="text-3xl font-bold text-slate-100 mt-2 mb-4">Do problema à solução em três etapas</h2>
            <p className="text-slate-400">Um fluxo simples, que qualquer pessoa da empresa entende logo no primeiro uso.</p>
          </div>

          <div className="relative grid grid-cols-1 md:grid-cols-3 gap-10">
            <div className="hidden md:block absolute top-6 left-[16%] right-[16%] h-px bg-gradient-to-r from-transparent via-blue-700/60 to-transparent"></div>

            {passos.map((passo, indice) => (
              <div key={passo.titulo} className="relative flex flex-col items-center text-center">
                <div className="w-12 h-12 rounded-full bg-gradient-to-br from-blue-500 to-blue-800 text-white text-lg font-bold flex items-center justify-center shadow-lg shadow-blue-950/60 ring-8 ring-slate-900">
                  {indice + 1}
                </div>
                <h3 className="mt-6 text-lg font-bold text-slate-100">{passo.titulo}</h3>
                <p className="mt-2 max-w-xs text-sm leading-relaxed text-slate-400">{passo.texto}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Seção: Perfis de acesso */}
      <section id="perfis" className="scroll-mt-24 py-24 bg-slate-950">
        <div className="max-w-7xl mx-auto px-6">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Perfis de acesso</span>
            <h2 className="text-3xl font-bold text-slate-100 mt-2 mb-4">Cada pessoa vê o que precisa ver</h2>
            <p className="text-slate-400">Três perfis, com permissões claras, para o suporte funcionar sem bagunça.</p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {perfis.map((perfil) => (
              <div key={perfil.nome} className="flex flex-col bg-slate-900 p-8 rounded-2xl border border-slate-800 hover:border-blue-500/40 transition-colors">
                <div className="flex items-center gap-4 mb-6">
                  <div className="w-12 h-12 rounded-xl bg-gradient-to-br from-blue-500 to-blue-800 text-white flex items-center justify-center shadow-lg shadow-blue-950/50">
                    <Icone nome={perfil.icone} className="w-6 h-6" />
                  </div>
                  <div>
                    <h3 className="text-lg font-bold text-slate-100">{perfil.nome}</h3>
                    <p className="text-xs font-medium uppercase tracking-wide text-blue-400">{perfil.resumo}</p>
                  </div>
                </div>

                <ul className="flex flex-col gap-3">
                  {perfil.itens.map((item) => (
                    <li key={item} className="flex items-start gap-2.5 text-sm leading-relaxed text-slate-400">
                      <Icone nome="aprovado" className="w-5 h-5 shrink-0 mt-0.5 text-blue-500" />
                      <span>{item}</span>
                    </li>
                  ))}
                </ul>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Seção: Vantagens / segurança */}
      <section id="beneficios" className="scroll-mt-24 py-24 bg-slate-900 border-y border-slate-800">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-12 items-center">
            <div>
              <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Vantagens</span>
              <h2 className="text-3xl md:text-4xl font-bold text-slate-100 mt-2 mb-6">
                Segurança e controle desde o primeiro chamado.
              </h2>
              <p className="text-slate-400 leading-relaxed mb-8">
                O TechSupport foi desenhado para que várias empresas usem o mesmo sistema sem que os dados de uma cheguem até a outra.
              </p>

              <ul className="flex flex-col gap-4">
                {vantagens.map((vantagem) => (
                  <li key={vantagem} className="flex items-center gap-3 text-slate-200">
                    <span className="w-8 h-8 shrink-0 rounded-lg bg-blue-950/60 text-blue-400 flex items-center justify-center">
                      <Icone nome="aprovado" className="w-5 h-5" />
                    </span>
                    {vantagem}
                  </li>
                ))}
              </ul>
            </div>

            <div className="rounded-3xl border border-slate-800 bg-gradient-to-br from-slate-900 to-blue-950/40 p-8 shadow-inner">
              <div className="flex items-center gap-3 mb-6">
                <div className="w-10 h-10 rounded-xl bg-blue-950/60 text-blue-400 flex items-center justify-center">
                  <Icone nome="cadeado" className="w-5 h-5" />
                </div>
                <h3 className="text-lg font-bold text-slate-100">Segurança em camadas</h3>
              </div>

              <div className="flex flex-col gap-4">
                {camadas.map((camada) => (
                  <div key={camada.numero} className="flex items-start gap-4 rounded-xl border border-slate-800 bg-slate-950/60 p-4">
                    <span className="w-7 h-7 shrink-0 rounded-full bg-blue-600 text-white text-xs font-bold flex items-center justify-center">
                      {camada.numero}
                    </span>
                    <div>
                      <h4 className="text-sm font-bold text-slate-100">{camada.titulo}</h4>
                      <p className="mt-1 text-sm leading-relaxed text-slate-400">{camada.texto}</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Seção: Perguntas frequentes */}
      <section id="faq" className="scroll-mt-24 py-24 bg-slate-950">
        <div className="max-w-3xl mx-auto px-6">
          <div className="text-center mb-12">
            <span className="text-blue-400 text-sm font-bold uppercase tracking-wider">Dúvidas</span>
            <h2 className="text-3xl font-bold text-slate-100 mt-2">Perguntas frequentes</h2>
          </div>

          <div className="flex flex-col gap-3">
            {perguntas.map((item) => (
              <details key={item.pergunta} className="group rounded-xl border border-slate-800 bg-slate-900 open:border-blue-500/40 transition-colors">
                <summary className="flex cursor-pointer list-none items-center justify-between gap-4 px-6 py-4 text-left font-medium text-slate-100 [&::-webkit-details-marker]:hidden">
                  {item.pergunta}
                  <Icone nome="abaixo" className="w-5 h-5 shrink-0 text-slate-500 transition-transform group-open:rotate-180" />
                </summary>
                <p className="px-6 pb-5 text-sm leading-relaxed text-slate-400">{item.resposta}</p>
              </details>
            ))}
          </div>
        </div>
      </section>

      {/* Chamada final */}
      <section className="pb-24 bg-slate-950">
        <div className="max-w-5xl mx-auto px-6">
          <div className="relative overflow-hidden rounded-3xl border border-blue-900/60 bg-gradient-to-br from-blue-700 via-blue-800 to-slate-900 px-8 py-14 text-center shadow-2xl shadow-blue-950/50">
            <div className="absolute -top-24 -right-24 w-72 h-72 rounded-full bg-blue-400/20 blur-3xl pointer-events-none"></div>
            <h2 className="relative text-3xl md:text-4xl font-extrabold text-white">Pronto para organizar o suporte de TI?</h2>
            <p className="relative mt-4 text-blue-100/80 max-w-xl mx-auto">
              Entre no sistema e acompanhe cada chamado do início ao fim, com clareza e segurança.
            </p>
            <a href="/login"
              className="relative mt-8 inline-flex items-center gap-2 px-8 py-4 text-base font-semibold text-blue-900 bg-white hover:bg-blue-50 rounded-2xl shadow-xl transition-all hover:translate-y-[-2px]"
            >
              Acessar Sistema
              <Icone nome="seta" className="w-4 h-4" />
            </a>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-slate-950 text-slate-500 py-12 border-t border-slate-800">
        <div className="max-w-7xl mx-auto px-6 flex flex-col md:flex-row items-center justify-between gap-6">
          <div className="flex items-center gap-3">
            <img src="/techsupport-logo.svg" alt="TechSupport" className="h-8" />
          </div>

          <p className="text-xs text-slate-600 text-center">
            &copy; 2026 TechSupport. Todos os direitos reservados. Inspirado na excelência operacional da Next Fit.
          </p>

          <div className="flex items-center gap-5 text-sm">
            <a href="#recursos" className="hover:text-slate-200 transition-colors">
              Recursos
            </a>
            <a href="#perfis" className="hover:text-slate-200 transition-colors">
              Perfis
            </a>
            <a href="#faq" className="hover:text-slate-200 transition-colors">
              Dúvidas
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