import Footer from "./components/Footer";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";

export default function SistemaLayout({children}){
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