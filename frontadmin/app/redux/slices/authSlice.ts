import { AuthState } from "@/app/types/auth";
import { Usuario } from "@/app/types/usuario";
import { Solicitante } from "@/app/types/solicitante";
import { PayloadAction, createSlice } from "@reduxjs/toolkit";

const initialState: AuthState = {
    usuario: null,
    token: ""
}


const authSlice = createSlice({
    name : 'auth',
    initialState,
    reducers:{
        definirToken:(state, action: PayloadAction<string>) => {

            state.token = action.payload;

        },
        login:(state, action: PayloadAction<{token:string, usuario:Usuario | Solicitante}>) => {

            state.token = action.payload.token;
            state.usuario = action.payload.usuario;

        },
        logout:(state) => {

            state.token = "";
            state.usuario = null;


        }
    }
});

export const {definirToken,login,logout} = authSlice.actions;
export default authSlice.reducer;