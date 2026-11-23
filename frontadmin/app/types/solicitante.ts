// Solicitante: quem abre chamados (o Usuario/técnico é quem atende). É o único com o campo "setor",
// e o usePerfil usa isso para reconhecer o solicitante.
export class Solicitante{

    constructor(
        public id:number | null,
        public nome:string,
        public email:string,
        public setor:string,    // setor da empresa onde o solicitante trabalha
        public status:string,
    ){}

}

export interface SolicitanteFormProps{
    solicitanteExistente?:Solicitante
}
