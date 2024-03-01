import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";


const URL_API ="http://127.0.0.1:8080/api/v1";

export const axiosPostNewCustomer = createAsyncThunk(
    "cryptozytos/axiosPostNewCustomer",
    async (firstname,lastname,eMail,passWord,piggyBank) => {
        try {
            const customerData = {
                firstName: firstname,
                lastName: lastname,
                email: eMail,
                password: passWord,
                piggyBank:piggyBank
            }

            const reponse = await axios.post(URL_API,customerData);

            return reponse.data;
            
        } catch (error) {
            console.log("customer post error : " + error) 
            throw error;
        }
    }
)

export const axiosPostLoginCustomer = createAsyncThunk(
    "cryptozytos/axiosPostLoginCustomer",
    async (email,password) => {
        try {
            const logInfo = {
                email: email,
                password: password
            }

            const response = await axios.post(URL_API+"/login",logInfo);

            return response.data;

        } catch (error) {
            console.log("loggin error : " + error) 
            throw error;
        }
    }
)


const cryptoSlice = createSlice({
    name:"cryptozytos",
    initialState: {
        firstname:null,
        lastname:null,
        customerToken:null,
        email:null,
        piggyBank:null,

        cryptoWallets:{},
    },
    reducers: {
        disconnect: (state) => {
            state.firstname = null;
            state.lastname = null;
            state.customerToken = null;
            state.email = null;
            state.piggyBank = null;
        }

    },
    extraReducers: (builder) => {
        builder.addCase(axiosPostNewCustomer.fulfilled, (state,action) => {
            state.firstname = action.payload.firstName;
            state.lastname = action.payload.lastName;
            state.email = action.payload.email;
            state.customerToken = action.payload.customerToken;
            state.piggyBank = action.payload.piggyBank;
        })
        builder.addCase(axiosPostLoginCustomer.fulfilled, (state,action) => {
            state.firstname = action.payload.firstName;
            state.lastname = action.payload.lastName;
            state.email = action.payload.email;
            state.customerToken = action.payload.customerToken;
            state.piggyBank = action.payload.piggyBank;

            const transactionsList = action.payload.transactionsList;

            if (transactionsList != null && transactionsList.lenght > 0) {
                transactionsList.forEach(tr => {
                    if (!tr.cryptoCurrencyName in state.transactions) {
                        state.transactions[tr.cryptoCurrencyName] = [];
                    }
                    state.transactions[tr.cryptoCurrencyName].push(tr);
                });
            }

        })
    }
})



export default cryptoSlice.reducer;