import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { RootState } from "../store";

const baseQueryToken = fetchBaseQuery({
    baseUrl: process.env.NEXT_PUBLIC_BACKEND_URL,
    prepareHeaders: (headers, {getState}) => {
        const accessToken = (getState() as RootState).auth.token
        if(accessToken){
            headers.set("Authorization", `Bearer ${accessToken}`)
        }

        return headers
    }
})


export const educationApi = createApi({
    reducerPath: "educationApi",
    baseQuery: baseQueryToken,
    tagTypes: ["User", "Course", "Enrollment", "Payment", ],
    endpoints: builder => ({})
})

