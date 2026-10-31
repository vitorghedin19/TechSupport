'use client'

import Footer from "./components/Footer";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useRouter } from "next/navigation";


export default function SistemaLayout({children}){

    const usuario = useSelector((state : RootState) => state.auth.usuario);

    const router = useRouter();

    useEffect(() => {
        if (usuario == null) {
            router.push("/login");
        }
    })

    return (
    
    <div className="flex min-h-screen bg-slate-950"> 
        <Sidebar/>
    <div className="flex-1 flex flex-col min-h-screen">
            <Header/>
            <main className="flex-1 px-6 py-8 text-slate-100">
            {children} 
            </main>
            <Footer/>
        </div>
    </div>
    );
}
