// Solicitante: pessoa que abre um chamado (diferente do Usuario/Técnico, que é quem
// atende). Mesmo padrão de classe + interface de props das outras entidades.
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
