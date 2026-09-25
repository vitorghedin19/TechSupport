export class Solicitante{

    constructor(
        public id:number | null,
        public nome:string,
        public email:string,
        public setor:string,
        public status:string,
    ){}

}

export interface SolicitanteFormProps{
    solicitanteExistente?:Solicitante
}