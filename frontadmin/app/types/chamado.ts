// Chamado: o "ticket" de suporte. Só os campos que a tela usa; solicitante e empresa não vêm aqui:
// o back os define pelo token (empresa) e pelo solicitante logado.
export class Chamado{

    constructor(
        public id:number | null,
        public titulo:string,
        public descricao:string,
        public prioridade:string,   // ex: "Alta", "Média", "Baixa"
        public status:string,       // ex: "ABERTO", "EM_ANDAMENTO", "RESOLVIDO", "FECHADO", "EXCLUIDO"
        public solucao?:string,     // só vem preenchida depois que o chamado é resolvido
        public equipamentoId?:number | null,  // id do equipamento escolhido na caixa de seleção (chave estrangeira no back)
        public equipamentoNome?:string,       // nome do equipamento, só para exibir na tabela
    ){}

}
export interface ChamadoFormProps{
    chamadoExistente?:Chamado
}