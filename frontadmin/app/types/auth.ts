// LoginResponse: formato esperado da resposta da API de login (POST /auth/login).
// Hoje só declara o campo "token", que normalmente seria o JWT retornado pelo backend
// pra autenticar as próximas requisições (mas repare, olhando o login/page.tsx, que esse
// token ainda não está sendo guardado em lugar nenhum — nem localStorage, nem cookie —
// então ele é recebido e descartado. Ponto pra ficar de olho na prova.
export interface LoginResponse{
    token:string
}
