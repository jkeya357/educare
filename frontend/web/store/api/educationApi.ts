import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { RootState } from "../store";
import { logout, setCredentials } from "../authSlice/UserSlice";

const baseQuery = fetchBaseQuery({
    baseUrl: process.env.NEXT_PUBLIC_BACKEND_URL,
    credentials: "include",
    prepareHeaders: (headers, {getState}) => {
        const accessToken = (getState() as RootState).auth.token
        if(accessToken){
            headers.set("Authorization", `Bearer ${accessToken}`)
        }

        return headers
    }
})

const baseQueryWithRefresh: typeof baseQuery = async (args, api, extraOptions) => {

    let result = await baseQuery(args, api, extraOptions)

    if(result.error && result?.error.status === 401){

        const refreshResult = await baseQuery(
            {
            url: "/api/v1/auth/refresh",
            method: "POST"
            },
            api,
            extraOptions
        )

        if(refreshResult?.data){

            console.log("REFRESH RESULT: ", refreshResult)

            const data = refreshResult.data as {
                token: string,
                userId: string,
                username: string,
                role: string
            }

            const state = api.getState() as RootState
            const stateUserId = state.auth.user?.id

            api.dispatch(setCredentials({
                token: data.token,
                user:{
                    userId: data.userId,
                    username: data.username,
                    role: data.role
                }
            }))

            result = await baseQuery(args, api, extraOptions)

        }else{
            api.dispatch(logout())
        }
    }

    return result
}


export const educationApi = createApi({
    reducerPath: "educationApi",
    baseQuery: baseQueryWithRefresh,
    tagTypes: ["User", "Course", "Enrollment", "Payment", ],
    endpoints: builder => ({})
})

