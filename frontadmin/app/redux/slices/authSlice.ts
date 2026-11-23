import { AuthState } from "@/app/types/auth";
import { Usuario } from "@/app/types/usuario";
import { Solicitante } from "@/app/types/solicitante";
import { PayloadAction, createSlice } from "@reduxjs/toolkit";

// Guarda o token e o usuário logado (Usuario ou Solicitante). Fica só em memória: F5 zera e volta pro login.
const initialState: AuthState = {
    usuario: null,
    token: ""
}


const authSlice = createSlice({
    name : 'auth',
    initialState,
    reducers:{
        // Só o token: necessário antes de buscar o usuário, pois o interceptor do api.ts lê o token daqui.
        definirToken:(state, action: PayloadAction<string>) => {

            state.token = action.payload;

        },
        // Guarda token + usuário depois de buscarUsuarioLogado.
        login:(state, action: PayloadAction<{token:string, usuario:Usuario | Solicitante}>) => {

            state.token = action.payload.token;
            state.usuario = action.payload.usuario;

        },
        // Zera tudo; o layout do sistema vê usuario nulo e manda para /login.
        logout:(state) => {

            state.token = "";
            state.usuario = null;


        }
    }
});

export const {definirToken,login,logout} = authSlice.actions;
export default authSlice.reducer;