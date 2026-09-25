export class Equipamento{

    constructor(
        public id:number | null,
        public equipamento:string,
        public tipo:string,
        public status:string,
    ){}

}
export interface EquipamentoFormProps{
    equipamentoExistente?:Equipamento
}