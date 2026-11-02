import { Solicitante } from "./solicitante"
import { Usuario } from "./usuario"

export interface LoginResponse{
    token:string
}


export interface AuthState {
    usuario: Usuario | Solicitante | null
    token : String
}