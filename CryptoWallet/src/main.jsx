import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './index.css'

import ErrorPage from './vues/ErrorPage';
import App from './App';
import Form from "./vues/Form";
import Wallet from "./vues/Wallet";
import ProtectedRoute from "./vues/ProtectedRoute";

// import router from './app-routing';

const router = createBrowserRouter([
  {
    path: "/",
    element:<App/>,
    errorElement: <ErrorPage />
  },
  {
    path: "/login",
    element:<Form/>,
    errorElement: <ErrorPage />
  },
  {
    path: "/wallet",
    element:<Wallet/>,
    errorElement: <ErrorPage />
  },
  
])

const root = ReactDOM.createRoot(document.getElementById('root')) ;
root.render(
    <RouterProvider router={router} />
);
