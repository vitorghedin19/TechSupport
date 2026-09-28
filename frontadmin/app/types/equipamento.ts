// Equipamento: mesma lógica da classe Usuario, mas pra equipamentos de TI.
export class Equipamento{

    constructor(
        public id:number | null,
        public equipamento:string,   // nome/descrição do equipamento (ex: "Notebook Dell")
        public tipo:string,          // categoria (ex: "Notebook", "Monitor")
        public status:string,
    ){}

}
export interface EquipamentoFormProps{
    equipamentoExistente?:Equipamento
}
