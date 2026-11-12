import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../redux/slices/authSlice"
import equipamentosRecentesReducer from "../redux/slices/equipamentosRecentesSlice"

export const store = configureStore({

    reducer:{
        auth : authReducer,
        equipamentosRecentes : equipamentosRecentesReducer
    }

});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispath = typeof store.dispatch;