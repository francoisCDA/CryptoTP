import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
//import './index.css'
import Root from './routes/root';
import ErrorPage from './vues/ErrorPage';
// import App from './App';
 import LogginForm from "./vues/LogginForm";
// import Wallet from "./vues/Wallet";
// import ProtectedRoute from "./vues/ProtectedRoute";

//import router from './app-routing';

// const router = createBrowserRouter([
//   {
//     path: "/",
//     element:<App/>,
//     children: [
//       {
//         path: "/login",
//         element:<Form/>,
//       },
//       {
//         path: "/wallet",
//         element:<Wallet/>,
//       },
//     ]
//   }
// ])

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "login",
        element:<LogginForm />
      },
    ],
  },
  
]);


ReactDOM.createRoot(document.getElementById('root')).render(
    <RouterProvider router={router} />
);
