'use client'

import { store } from "./store"
import { Provider } from "react-redux"

// Entrega o store a todas as telas (usado no app/layout.tsx).
export default function StoreProvider({children} : {children : React.ReactNode}){
    return <Provider store = {store}>{children}</Provider>
}