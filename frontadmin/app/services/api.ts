import axios from "@/node_modules/axios/index";
import { store } from "../redux/store";
import { logout } from "../redux/slices/authSlice";
 
const api = axios.create({
    baseURL: 'http://localhost:8080'
});
 
// Toda requisição sai com o token do Redux no header Authorization.
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
        // 401 (token expirado ou inválido): despacha logout e o layout manda para /login.
        // 403 não desloga: o solicitante recebe 403 em /usuarios/buscarusuariologado durante o login.
        if(error.response?.status === 401){
            store.dispatch(logout());
        }
        return Promise.reject(error);
    }
);
 
export default api;