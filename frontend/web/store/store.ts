import { configureStore } from "@reduxjs/toolkit";
import { educationApi } from "./api/educationApi";
import authReducer from "@/store/authSlice/UserSlice"

export const store = configureStore({
    reducer: {
        [educationApi.reducerPath]: educationApi.reducer,
        auth: authReducer
    },
    middleware: getDefaultMiddleware => 
        getDefaultMiddleware().concat(educationApi.middleware)
})

export type RootState = ReturnType<typeof store.getState>