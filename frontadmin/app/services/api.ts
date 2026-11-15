import axios from "@/node_modules/axios/index";
import { store } from "../redux/store";
import { logout } from "../redux/slices/authSlice";
 
const api = axios.create({
    baseURL: 'http://localhost:8080'
});
 
api.interceptors.request.use(
    (config) =>{
        const token = store.getState().auth.token;
        if(token){
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    }
);

api.interceptors.response.use(
    (response) => response,
    (error) => {
        // 401 Unauthorized (token expirado ou inválido): despacha o logout no Redux.
        // O layout do sistema observa o usuário no store e, com ele nulo, renderiza a tela de login.
        // Só o 401 desloga: um 403 (sem permissão) não pode derrubar a sessão, senão o login do solicitante
        // quebra (ele recebe 403 em /usuarios/buscarusuariologado antes de cair no fluxo de solicitante).
        if(error.response?.status === 401){
            store.dispatch(logout());
        }
        return Promise.reject(error);
    }
);
 
export default api;