import { useSelector } from "react-redux";
import { RootState } from "@/app/redux/store";

// Diz quem está logado: admin (role ROLE_ADMIN) ou solicitante (só ele tem o campo "setor").
// As telas usam isso para esconder botões; a regra de verdade continua no back.
export function usePerfil() {

    const usuario = useSelector((state: RootState) => state.auth.usuario);

    const ehAdmin = usuario != null && "role" in usuario && usuario.role === "ROLE_ADMIN";
    const ehSolicitante = usuario != null && "setor" in usuario;

    return { ehAdmin, ehSolicitante };
}
