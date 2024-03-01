import { configureStore } from "@reduxjs/toolkit";
import cryptoSlice from "./slices/CryptoSlice";


export default configureStore({
    reducer: {
        cryptozytos: cryptoSlice
    }
})