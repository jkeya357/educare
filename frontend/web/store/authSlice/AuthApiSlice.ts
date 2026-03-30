import { educationApi } from "../api/educationApi";
import { CreateUserRequest } from "@/types/CreateUserRequest";
import { UserLoginRequest } from "@/types/UserLoginRequest";

const authApiSlice = educationApi.injectEndpoints({
    endpoints: (builder) => ({
        signUp: builder.mutation({
            query: (requestsBody: CreateUserRequest) => ({
                url: `${process.env.NEXT_PUBLIC_AUTH_REQUEST_URL}/signup`,
                method: "POST",
                body: {...requestsBody}
            }),
            invalidatesTags: () => [{type: "User", id: "LIST    "}]
        }),
        signIn: builder.mutation({
            query: (requestBody: UserLoginRequest) => ({
                url: `${process.env.NEXT_PUBLIC_AUTH_REQUEST_URL}/signin`,
                method: "POST",
                body: {...requestBody}
            })
        }),
        refresh: builder.mutation<any, void>({
            query: () => ({
                url: `${process.env.NEXT_PUBLIC_AUTH_REQUEST_URL}/refresh`,
                method: "POST"
            })
        })
    })
})

export const {useSignInMutation, useSignUpMutation, useRefreshMutation} = authApiSlice