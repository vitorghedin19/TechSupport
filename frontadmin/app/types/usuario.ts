// Usuario: classe (não interface!) que representa um usuário/técnico no front-end.
// É classe, e não interface, porque em alguns pontos do código (ex: UsuarioForm) ela é
// instanciada com "new Usuario(...)" pra criar um objeto imutável novo a cada mudança de
// campo — interface não permite isso, só descreve o formato, não cria instância.
export class Usuario{

    // "public id" etc. dentro do constructor é um atalho do TypeScript: já declara E
    // atribui a propriedade automaticamente, sem precisar escrever "this.id = id"
    // manualmente pra cada campo.
    constructor(
        public id:number | null,     // null quando o usuário ainda não foi salvo no banco (tela de criação)
        public nome:string,
        public email:string,
        public status:string,        // ex: "ATIVO", "BLOQUEADO"
        public cpf:string,
        public senha: string
    ){}

}

// UsuarioFormProps: formato das props que o componente <UsuarioForm/> aceita.
// "usuarioExistente?" com "?" significa opcional — se vier preenchido, o form entende
// que está em modo EDIÇÃO; se vier undefined, entende que está em modo CRIAÇÃO.
export interface UsuarioFormProps{
    usuarioExistente?:Usuario
}
