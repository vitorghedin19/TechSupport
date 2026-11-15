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
        // 401 (não autenticado / token expirado ou inválido) ou 403 (acesso negado): despacha o logout no Redux.
        // O layout do sistema observa o usuário no store e, com ele nulo, renderiza a tela de login.
        const status = error.response?.status;
        if(status === 401 || status === 403){
            store.dispatch(logout());
        }
        return Promise.reject(error);
    }
);
 
export default api;