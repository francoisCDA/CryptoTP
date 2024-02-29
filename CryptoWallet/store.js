import { configureStore } from "@reduxjs/toolkit";
import CryptoSlice from "./slices/CryptoSlice";


export default configureStore({
    reducer: {
        cryptozytos: CryptoSlice
    }
})