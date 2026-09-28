// Chamado: entidade central do sistema (o "ticket" de suporte).
// Repare que aqui não existe vínculo com Solicitante nem com o Usuario/Técnico
// responsável — o objeto só tem título, descrição, prioridade e status. Se o professor
// perguntar "quem abriu esse chamado?" ou "quem está atendendo?", vale mencionar que essa
// classe do front, do jeito que está, não carrega essa relação (pode ser que o backend
// tenha os IDs e o front simplesmente não esteja usando/exibindo ainda).
export class Chamado{

    constructor(
        public id:number | null,
        public titulo:string,
        public descricao:string,
        public prioridade:string,   // ex: "Alta", "Média", "Baixa"
        public status:string,       // ex: "ABERTO", "EM_ANDAMENTO", "RESOLVIDO", "FECHADO", "ATRASADO", "EXCLUIDO"
    ){}

}
export interface ChamadoFormProps{
    chamadoExistente?:Chamado
}
