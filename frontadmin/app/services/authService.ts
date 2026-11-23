import { LoginResponse } from "../types/auth";
import api from "./api";

// POST /auth/login: devolve o token JWT. Não precisa de token (rota liberada no back).
export async function loginBack (email:string, senha:string) : Promise<LoginResponse>{

    var loginResposta = await api.post<LoginResponse>("/auth/login",
    {email:email, senha:senha});

    return loginResposta.data;
}