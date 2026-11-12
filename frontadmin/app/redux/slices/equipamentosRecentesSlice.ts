import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { logout } from "@/app/redux/slices/authSlice";

// Segundo slice do projeto: "equipamentos usados recentemente".
// Guarda só os IDs dos últimos equipamentos escolhidos ao salvar um chamado, o mais recente primeiro.
// O ChamadoForm lê essa lista para mostrar esses equipamentos no topo do select.
interface EquipamentosRecentesState {
    ids: number[]
}

const LIMITE = 5;

const initialState: EquipamentosRecentesState = {
    ids: []
}

const equipamentosRecentesSlice = createSlice({
    name : 'equipamentosRecentes',
    initialState,
    reducers:{
        // Chamado quando um chamado é salvo com equipamento: tira o id se ele já estava na lista,
        // coloca no começo e mantém só os últimos LIMITE.
        registrarUso:(state, action: PayloadAction<number>) => {

            state.ids = [action.payload, ...state.ids.filter(id => id !== action.payload)].slice(0, LIMITE);

        },
        limpar:(state) => {

            state.ids = [];

        }
    },
    // Quando o usuário sai (logout do authSlice), a lista é zerada. Assim ela nunca passa de um usuário para outro,
    // que pode ser de outra empresa.
    extraReducers:(builder) => {
        builder.addCase(logout, (state) => {
            state.ids = [];
        });
    }
});

export const {registrarUso,limpar} = equipamentosRecentesSlice.actions;
export default equipamentosRecentesSlice.reducer;
