import {link} from "react-router-dom";
import { useDispatch } from "react-redux";

function Navbar() {
    const user = false; // récupérer la connection...
    return (
        <nav>
            <div>
                <Link to={"/"}>Home</Link>
                <div>Crypto-Zythos</div>
                {
                    user ? (
                        <button onClick={()=> console.log("à impémenter")} >déconnection</button>
                    ) : (
                        <Link to="/login" >Connection</Link>
                    )

                }
                <div></div>
                
                <Link to={"/login"}>Login</Link>
            </div>

        </nav>
    )

}

export default Navbar;