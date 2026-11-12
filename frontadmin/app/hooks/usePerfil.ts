import { useSelector } from "react-redux";
import { RootState } from "@/app/redux/store";

export function usePerfil() {

    const usuario = useSelector((state: RootState) => state.auth.usuario);

    const ehAdmin = usuario != null && "role" in usuario && usuario.role === "ROLE_ADMIN";
    const ehSolicitante = usuario != null && "setor" in usuario;

    return { ehAdmin, ehSolicitante };
}
