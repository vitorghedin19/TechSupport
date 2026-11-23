import { Solicitante } from "./solicitante"
import { Usuario } from "./usuario"

// LoginResponse: o que o back devolve no login. AuthState: o que o authSlice guarda no Redux.
export interface LoginResponse{
    token:string
}


export interface AuthState {
    usuario: Usuario | Solicitante | null
    token : String
}