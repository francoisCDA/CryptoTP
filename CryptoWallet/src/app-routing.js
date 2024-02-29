import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Form from "./vues/Form";
import Wallet from "./vues/Wallet";
import ProtectedRoute from "./vues/ProtectedRoute";


const router = createBrowserRouter([
    {
      path: "/",
      element:<h1>Hello world!</h1>,
  
    }
  ])

export default router;

